package net.MichaelBoss.Brassinstruments.event;

import net.MichaelBoss.Brassinstruments.item.CopperTiers;
import net.MichaelBoss.Brassinstruments.item.ModArmorMaterials;
import net.MichaelBoss.Brassinstruments.item.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OxidizeEventHandler {

	private boolean mapsInitialized;

	private final Map<UUID, Integer> playerOxidationTimers = new HashMap<>();
	private Map<Item, OxidizeData> toolOxidizeData;
	private Map<Item, OxidizeDataItem> toolOxidizeDataItem;
	private Map<Item, OxidizeData> armorOxidizeData;

	private final int[] OXIDIZE_DAMAGE_TOOL = new int[] {
		CopperTiers.COPPER.getUses() / 4,
			CopperTiers.EXPOSED_COPPER.getUses() * 1/2,
			CopperTiers.WEATHERED_COPPER.getUses() * 3/4
	};

	private final int[] START_DAMAGE_TOOL = new int[] {
			CopperTiers.EXPOSED_COPPER.getUses() / 4,
			CopperTiers.WEATHERED_COPPER.getUses() * 1/2,
			CopperTiers.OXIDIZED_COPPER.getUses() * 3/4
	};

	private final int[][] OXIDIZE_DAMAGE_ARMOR = new int [][] {
		{
			ModArmorMaterials.COPPER.getDurabilityForType(ArmorItem.Type.HELMET) / 4,
			ModArmorMaterials.COPPER.getDurabilityForType(ArmorItem.Type.CHESTPLATE) / 4,
			ModArmorMaterials.COPPER.getDurabilityForType(ArmorItem.Type.LEGGINGS) / 4,
			ModArmorMaterials.COPPER.getDurabilityForType(ArmorItem.Type.BOOTS) / 4
		},
		{
			ModArmorMaterials.EXPOSED_COPPER.getDurabilityForType(ArmorItem.Type.HELMET) * 1/2,
			ModArmorMaterials.EXPOSED_COPPER.getDurabilityForType(ArmorItem.Type.CHESTPLATE) * 1/2,
			ModArmorMaterials.EXPOSED_COPPER.getDurabilityForType(ArmorItem.Type.LEGGINGS) * 1/2,
			ModArmorMaterials.EXPOSED_COPPER.getDurabilityForType(ArmorItem.Type.BOOTS) * 1/2
		},
		{
			ModArmorMaterials.WEATHERED_COPPER.getDurabilityForType(ArmorItem.Type.HELMET) * 3/4,
			ModArmorMaterials.WEATHERED_COPPER.getDurabilityForType(ArmorItem.Type.CHESTPLATE) * 3/4,
			ModArmorMaterials.WEATHERED_COPPER.getDurabilityForType(ArmorItem.Type.LEGGINGS) * 3/4,
			ModArmorMaterials.WEATHERED_COPPER.getDurabilityForType(ArmorItem.Type.BOOTS) * 3/4
		}
	};
	
	private final int[][] START_DAMAGE_ARMOR = new int [][] {
		{
			ModArmorMaterials.EXPOSED_COPPER.getDurabilityForType(ArmorItem.Type.HELMET) / 4,
			ModArmorMaterials.EXPOSED_COPPER.getDurabilityForType(ArmorItem.Type.CHESTPLATE) / 4,
			ModArmorMaterials.EXPOSED_COPPER.getDurabilityForType(ArmorItem.Type.LEGGINGS) / 4,
			ModArmorMaterials.EXPOSED_COPPER.getDurabilityForType(ArmorItem.Type.BOOTS) / 4
		},
		{
			ModArmorMaterials.WEATHERED_COPPER.getDurabilityForType(ArmorItem.Type.HELMET) * 1/2,
			ModArmorMaterials.WEATHERED_COPPER.getDurabilityForType(ArmorItem.Type.CHESTPLATE) * 1/2,
			ModArmorMaterials.WEATHERED_COPPER.getDurabilityForType(ArmorItem.Type.LEGGINGS) * 1/2,
			ModArmorMaterials.WEATHERED_COPPER.getDurabilityForType(ArmorItem.Type.BOOTS) * 1/2
		},
		{
			ModArmorMaterials.OXIDIZED_COPPER.getDurabilityForType(ArmorItem.Type.HELMET) * 3/4,
			ModArmorMaterials.OXIDIZED_COPPER.getDurabilityForType(ArmorItem.Type.CHESTPLATE) * 3/4,
			ModArmorMaterials.OXIDIZED_COPPER.getDurabilityForType(ArmorItem.Type.LEGGINGS) * 3/4,
			ModArmorMaterials.OXIDIZED_COPPER.getDurabilityForType(ArmorItem.Type.BOOTS) * 3/4
		}
	};
	
	List<EquipmentSlot> EQUIPMENT_SLOTS = List.of(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
	
	public OxidizeEventHandler() {
		mapsInitialized = false;
		toolOxidizeData = null;
		toolOxidizeDataItem = null;
		armorOxidizeData = null;
	}

	private void initializeMaps() {
		List< List<RegistryObject<?>> > copper_tools = List.of(
			List.of(ModItems.COPPER_SHOVEL, ModItems.EXPOSED_COPPER_SHOVEL, ModItems.WEATHERED_COPPER_SHOVEL, ModItems.OXIDIZED_COPPER_SHOVEL),
			List.of(ModItems.COPPER_PICKAXE, ModItems.EXPOSED_COPPER_PICKAXE, ModItems.WEATHERED_COPPER_PICKAXE, ModItems.OXIDIZED_COPPER_PICKAXE),
			List.of(ModItems.COPPER_AXE, ModItems.EXPOSED_COPPER_AXE, ModItems.WEATHERED_COPPER_AXE, ModItems.OXIDIZED_COPPER_AXE),
			List.of(ModItems.COPPER_HOE, ModItems.EXPOSED_COPPER_HOE, ModItems.WEATHERED_COPPER_HOE, ModItems.OXIDIZED_COPPER_HOE),
			List.of(ModItems.COPPER_SWORD, ModItems.EXPOSED_COPPER_SWORD, ModItems.WEATHERED_COPPER_SWORD, ModItems.OXIDIZED_COPPER_SWORD),
			List.of(ModItems.COPPER_SHEARS, ModItems.EXPOSED_COPPER_SHEARS, ModItems.WEATHERED_COPPER_SHEARS, ModItems.OXIDIZED_COPPER_SHEARS),
			List.of(ModItems.COPPER_NUGGET, ModItems.EXPOSED_COPPER_NUGGET, ModItems.WEATHERED_COPPER_NUGGET, ModItems.OXIDIZED_COPPER_NUGGET),
			List.of(ModItems.EXPOSED_COPPER_INGOT, ModItems.WEATHERED_COPPER_INGOT, ModItems.OXIDIZED_COPPER_INGOT)
        );
		
		List< List<RegistryObject<?>> > copper_armor = List.of(
			List.of(ModItems.COPPER_HELMET, ModItems.EXPOSED_COPPER_HELMET, ModItems.WEATHERED_COPPER_HELMET, ModItems.OXIDIZED_COPPER_HELMET),
			List.of(ModItems.COPPER_CHESTPLATE, ModItems.EXPOSED_COPPER_CHESTPLATE, ModItems.WEATHERED_COPPER_CHESTPLATE, ModItems.OXIDIZED_COPPER_CHESTPLATE),
			List.of(ModItems.COPPER_LEGGINGS, ModItems.EXPOSED_COPPER_LEGGINGS, ModItems.WEATHERED_COPPER_LEGGINGS, ModItems.OXIDIZED_COPPER_LEGGINGS),
			List.of(ModItems.COPPER_BOOTS, ModItems.EXPOSED_COPPER_BOOTS, ModItems.WEATHERED_COPPER_BOOTS, ModItems.OXIDIZED_COPPER_BOOTS)
		);
		
		toolOxidizeData = new HashMap<>();
		toolOxidizeDataItem = new HashMap<>();
		for(List<RegistryObject<?>> tool_set : copper_tools) {
			addToolOxidizeData(tool_set);
		}
		
		createArmorOxidizeData(copper_armor);
		
		mapsInitialized = true;
	}
	
	private void addToolOxidizeData(List<RegistryObject<?>> items) {
		int size = items.size() - 1;
		for(int i = 0; i < size; i++) {
			toolOxidizeData.put(((Item) items.get(i).get()).asItem(),
							new OxidizeData((Item) items.get(i+1).get(), OXIDIZE_DAMAGE_TOOL[i], START_DAMAGE_TOOL[i]));
			toolOxidizeDataItem.put(((Item) items.get(i).get()).asItem(),
					new OxidizeDataItem((Item) items.get(i+1).get()));
		}
	}
	
	private void createArmorOxidizeData(List< List<RegistryObject<?>> > items) {
		armorOxidizeData = new HashMap<>();
		int equipmentSlot = 0;
		
		for(List<RegistryObject<?>> armor_set : items) {
			int size = armor_set.size() - 1;
			
			for(int i = 0; i < size; i++) {
				armorOxidizeData.put(((Item) armor_set.get(i).get()).asItem(),
									new OxidizeData((Item) armor_set.get(i+1).get(), OXIDIZE_DAMAGE_ARMOR[i][equipmentSlot], START_DAMAGE_ARMOR[i][equipmentSlot]));
				toolOxidizeDataItem.put(((Item) armor_set.get(i).get()).asItem(),
						new OxidizeDataItem((Item) armor_set.get(i+1).get()));
			}
			equipmentSlot++;
		}
	}

	@SubscribeEvent
	public void onBreakEvent(BlockEvent.BreakEvent event) {
		Player player = event.getPlayer();
		if(!event.getPlayer().level().isClientSide()) {
			oxidizeItem(player, null);
		}
	}

	@SubscribeEvent
	public void onToolModificationEvent(BlockEvent.BlockToolModificationEvent event) {
		Player player = event.getPlayer();
		if(!event.getPlayer().level().isClientSide()) {
			oxidizeItem(player, null);
		}
	}

	@SubscribeEvent
	public void onLivingDamageEvent(LivingDamageEvent event) {
		LivingEntity player = event.getEntity();
		if(!event.getEntity().level().isClientSide()) {
			Entity entity = event.getSource().getDirectEntity();
			if(entity instanceof Player) {
				oxidizeItem((Player) entity, null);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if(!mapsInitialized) {
			initializeMaps();
		}

		if (event.phase == TickEvent.Phase.END && event.player != null) {
			Player player = event.player;
			ItemStack heldItem = player.getMainHandItem();

			if (mapsInitialized) {
				UUID playerId = player.getUUID();

				int timer = playerOxidationTimers.getOrDefault(playerId, 0);
				timer++;

				if (timer >= 600) {
					oxidizeItem(player, heldItem);
					playerOxidationTimers.remove(playerId);
				} else playerOxidationTimers.put(playerId, timer);
			} else playerOxidationTimers.remove(player.getUUID());
		}
	}

	private void oxidizeItem(Player player, ItemStack itemStack) {

		if(!mapsInitialized) {
			initializeMaps();
		}

		OxidizeDataItem dataItem = toolOxidizeDataItem.get(itemStack.getItem());
		if(dataItem != null){
			ItemStack nextTool = dataItem.getNextTool();
			dataItem.damageValue = itemStack.getDamageValue();
			dataItem.count = itemStack.getCount();
			copyModifications(itemStack, nextTool);
			nextTool.setCount(dataItem.getCount());
			nextTool.setDamageValue(dataItem.getDamageValue());
			player.setItemInHand(InteractionHand.MAIN_HAND, nextTool);
		}

		if(!player.isCreative()) {
			ItemStack item = player.getMainHandItem();
			OxidizeData data = toolOxidizeData.get(item.getItem());
			
			if(data != null && item.getDamageValue() > data.getOxidizeDamageValue()) {
				ItemStack nextTool = data.getNextTool();
				copyModifications(item, nextTool);
				nextTool.setDamageValue(data.getStartDamageValue());
				player.setItemInHand(InteractionHand.MAIN_HAND, nextTool);
			}
		}
	}

	private void copyModifications(ItemStack oldItem, ItemStack newItem) {
		if(oldItem.hasCustomHoverName()) {
			newItem.setHoverName(oldItem.getHoverName());
		}
		if(oldItem.isEnchanted() && newItem.isEnchantable()) {
			Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(oldItem);
			for(Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
				newItem.enchant(entry.getKey(), entry.getValue());
			}
		}
		if(oldItem.isRepairable() && newItem.isRepairable()) {
			newItem.setRepairCost(oldItem.getBaseRepairCost());
		}
	}

	@SubscribeEvent
	public void oxidizeArmor(LivingDamageEvent event) {
		Entity player = event.getEntity();
		Iterable<ItemStack> armorSlots = player.getArmorSlots();
		for (ItemStack armorSlot : armorSlots){
			if(!mapsInitialized && armorSlot.getItem() == ModItems.WAXED_COPPER_HELMET.get() ||
					armorSlot.getItem() == ModItems.WAXED_COPPER_CHESTPLATE.get() ||
					armorSlot.getItem() == ModItems.WAXED_COPPER_LEGGINGS.get() ||
					armorSlot.getItem() == ModItems.WAXED_COPPER_BOOTS.get()) {
				initializeMaps();
			}
		}
		
		LivingEntity hurtEntity = event.getEntity();
		if(!hurtEntity.level().isClientSide()) {
			
			for(EquipmentSlot equipmentSlot : EQUIPMENT_SLOTS) {
				if(hurtEntity.hasItemInSlot(equipmentSlot)) {
					ItemStack item = hurtEntity.getItemBySlot(equipmentSlot);
					OxidizeData data = armorOxidizeData.get(item.getItem());

					if(data != null && item.getDamageValue() > data.getOxidizeDamageValue()) {
						ItemStack nextItem = data.getNextTool();
						copyModifications(item, nextItem);
						nextItem.setDamageValue(data.getStartDamageValue());
						hurtEntity.setItemSlot(equipmentSlot, nextItem);
					}
				}
			}
		}
	}

	private class OxidizeData {
		private Item nextTool;
		private int oxidizeDamageValue;
		private int startDamageValue;

		OxidizeData(Item nextTool, int oxidizeDamageValue, int startDamageValue) {
			this.nextTool = nextTool;
			this.oxidizeDamageValue = oxidizeDamageValue;
			this.startDamageValue = startDamageValue;
		}

		ItemStack getNextTool() {
			return this.nextTool.getDefaultInstance();
		}
		int getOxidizeDamageValue() {
			return this.oxidizeDamageValue;
		}
		int getStartDamageValue() {
			return this.startDamageValue;
		}
	}

	private class OxidizeDataItem {
		private Item nextTool;
		private int count;
		private int damageValue;

		OxidizeDataItem(Item nextTool) {
			this.nextTool = nextTool;
		}

		ItemStack getNextTool() {
			return this.nextTool.getDefaultInstance();
		}
		int getCount(){return this.count;}
		int getDamageValue(){return this.damageValue;}
	}
}
