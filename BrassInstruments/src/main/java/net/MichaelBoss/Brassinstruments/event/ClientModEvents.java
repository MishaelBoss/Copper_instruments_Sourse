package net.MichaelBoss.Brassinstruments.event;

import com.mojang.logging.LogUtils;
import net.MichaelBoss.Brassinstruments.BrassInstruments;
import net.MichaelBoss.Brassinstruments.item.ModArmorMaterials;
import net.MichaelBoss.Brassinstruments.item.ModItems;
import net.MichaelBoss.Brassinstruments.item.CopperTiers;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.level.*;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Mod.EventBusSubscriber(modid = BrassInstruments.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void isCurrentlyHoldingACopperObject(PlayerInteractEvent event) {
        Player player = event.getEntity();
        Level level = player.level();
        ItemStack mainHandItem = player.getMainHandItem();
        Iterable<ItemStack> armorSlots = player.getArmorSlots();

        int rngNum = new Random().nextInt(0, 100);

        if(level.isRaining()){
            boolean isCopperInHand = mainHandItem.getItem() == ModItems.COPPER_SWORD.get() ||
                    mainHandItem.getItem() == ModItems.COPPER_PICKAXE.get() ||
                    mainHandItem.getItem() == ModItems.COPPER_AXE.get() ||
                    mainHandItem.getItem() == ModItems.COPPER_SHOVEL.get() ||
                    mainHandItem.getItem() == ModItems.COPPER_HOE.get() ||
                    mainHandItem.getItem() == ModItems.COPPER_HELMET.get() ||
                    mainHandItem.getItem() == ModItems.COPPER_CHESTPLATE.get() ||
                    mainHandItem.getItem() == ModItems.COPPER_LEGGINGS.get() ||
                    mainHandItem.getItem() == ModItems.COPPER_BOOTS.get();

            boolean hasCopperArmor = false;
            for (ItemStack armorSlot : armorSlots) {
                if (armorSlot.getItem() == ModItems.COPPER_HELMET.get() ||
                        armorSlot.getItem() == ModItems.COPPER_CHESTPLATE.get() ||
                        armorSlot.getItem() == ModItems.COPPER_LEGGINGS.get() ||
                        armorSlot.getItem() == ModItems.COPPER_BOOTS.get()) {
                    hasCopperArmor = true;
                    break;
                }
            }

            if ((isCopperInHand || hasCopperArmor) && rngNum == 0) {
                LightningBolt(event);
            }
        }
    }

    public static void LightningBolt(PlayerInteractEvent event){
        Player player = event.getEntity();
        Level level = player.level();

        Vec3 vec3d = player.getEyePosition(1);
        Vec3 vec3d1 = player.getViewVector(1);
        BlockHitResult pos = player.level().clip(new ClipContext(vec3d, vec3d1, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));

        LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
        bolt.moveTo(pos.getLocation().offsetRandom(RandomSource.create(), 8));
        level.addFreshEntity(bolt);
    }

    public static final Logger LOGGER = LogUtils.getLogger();

    private boolean mapsInitialized;

    private Map<Item, OxidizeData> toolOxidizeData;

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

    public void OxidizeEventHandler() {
        mapsInitialized = false;
        toolOxidizeData = null;
        armorOxidizeData = null;
    }

    private void initializeMaps() {
        List< List<RegistryObject<?>> > copper_tools = List.of(
                List.of(ModItems.COPPER_SHOVEL, ModItems.EXPOSED_COPPER_SHOVEL, ModItems.WEATHERED_COPPER_SHOVEL, ModItems.OXIDIZED_COPPER_SHOVEL),
                List.of(ModItems.COPPER_PICKAXE, ModItems.EXPOSED_COPPER_PICKAXE, ModItems.WEATHERED_COPPER_PICKAXE, ModItems.OXIDIZED_COPPER_PICKAXE),
                List.of(ModItems.COPPER_AXE, ModItems.EXPOSED_COPPER_AXE, ModItems.WEATHERED_COPPER_AXE, ModItems.OXIDIZED_COPPER_AXE),
                List.of(ModItems.COPPER_HOE, ModItems.EXPOSED_COPPER_HOE, ModItems.WEATHERED_COPPER_HOE, ModItems.OXIDIZED_COPPER_HOE),
                List.of(ModItems.COPPER_SWORD, ModItems.EXPOSED_COPPER_SWORD, ModItems.WEATHERED_COPPER_SWORD, ModItems.OXIDIZED_COPPER_SWORD)
        );

        List< List<RegistryObject<?>> > copper_armor = List.of(
                List.of(ModItems.COPPER_HELMET, ModItems.EXPOSED_COPPER_HELMET, ModItems.WEATHERED_COPPER_HELMET, ModItems.OXIDIZED_COPPER_HELMET),
                List.of(ModItems.COPPER_CHESTPLATE, ModItems.EXPOSED_COPPER_CHESTPLATE, ModItems.WEATHERED_COPPER_CHESTPLATE, ModItems.OXIDIZED_COPPER_CHESTPLATE),
                List.of(ModItems.COPPER_LEGGINGS, ModItems.EXPOSED_COPPER_LEGGINGS, ModItems.WEATHERED_COPPER_LEGGINGS, ModItems.OXIDIZED_COPPER_LEGGINGS),
                List.of(ModItems.COPPER_BOOTS, ModItems.EXPOSED_COPPER_BOOTS, ModItems.WEATHERED_COPPER_BOOTS, ModItems.OXIDIZED_COPPER_BOOTS)
        );

        toolOxidizeData = new HashMap<>();
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
            }
            equipmentSlot++;
        }
    }

    @SubscribeEvent
    public void onBreakEvent(BlockEvent.BreakEvent event) {
        if(!event.getPlayer().level().isClientSide()) {
            Player player = event.getPlayer();
            oxidizeTool(player);
        }
    }

    @SubscribeEvent
    public void onToolModificationEvent(BlockEvent.BlockToolModificationEvent event) {
        if(!event.getPlayer().level().isClientSide()) {
            Player player = event.getPlayer();
            oxidizeTool(player);
        }
    }

    @SubscribeEvent
    public void onLivingDamageEvent(LivingDamageEvent event) {
        if(!event.getEntity().level().isClientSide()) {
            Entity entity = event.getSource().getDirectEntity();
            if(entity instanceof Player) {
                oxidizeTool((Player) entity);
            }
        }
    }

    private void oxidizeTool(Player player) {
        if(!mapsInitialized) {
            initializeMaps();
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
        if(!mapsInitialized) {
            initializeMaps();
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
}