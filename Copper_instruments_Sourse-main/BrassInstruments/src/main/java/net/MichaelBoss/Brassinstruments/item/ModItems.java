package net.MichaelBoss.Brassinstruments.item;

import net.MichaelBoss.Brassinstruments.BrassInstruments;
import net.MichaelBoss.Brassinstruments.item.custom.ModArmorItem;
import net.MichaelBoss.Brassinstruments.sound.Sounds;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BrassInstruments.MOD_ID);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> COPPER_SHEARS = ITEMS.register("copper_shears",
            ()-> new ShearsItem(new Item.Properties().stacksTo(1).durability(CopperTiers.COPPER.getUses())));
    public static final RegistryObject<Item> EXPOSED_COPPER_SHEARS = ITEMS.register("exposed_copper_shears",
            ()-> new ShearsItem(new Item.Properties().stacksTo(1).durability(CopperTiers.COPPER.getUses())));
    public static final RegistryObject<Item> WEATHERED_COPPER_SHEARS = ITEMS.register("weathered_copper_shears",
            ()-> new ShearsItem(new Item.Properties().stacksTo(1).durability(CopperTiers.COPPER.getUses())));
    public static final RegistryObject<Item> OXIDIZED_COPPER_SHEARS = ITEMS.register("oxidized_copper_shears",
            ()-> new ShearsItem(new Item.Properties().stacksTo(1).durability(CopperTiers.COPPER.getUses())));

    public static final RegistryObject<Item> COPPER_MUSIC_MUSIC_DISK = ITEMS.register("copper_music_disc",
            () -> new RecordItem(1, Sounds.COPPER_MUSIC, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 2080));

    public static final RegistryObject<SwordItem> COPPER_SWORD = ITEMS.register("copper_sword",
            ()-> new SwordItem(CopperTiers.COPPER, 3,0.6f ,new Item.Properties()));
    public static final RegistryObject<PickaxeItem> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",
            ()-> new PickaxeItem(CopperTiers.COPPER, 3, 0.5f, new Item.Properties()));
    public static final RegistryObject<AxeItem> COPPER_AXE = ITEMS.register("copper_axe",
            ()-> new AxeItem(CopperTiers.COPPER, 7, -3.2f, new Item.Properties()));
    public static final RegistryObject<ShovelItem> COPPER_SHOVEL = ITEMS.register("copper_shovel",
            ()-> new ShovelItem(CopperTiers.COPPER, 0.5f, 0.2f, new Item.Properties()));
    public static final RegistryObject<HoeItem> COPPER_HOE = ITEMS.register("copper_hoe",
            ()-> new HoeItem(CopperTiers.COPPER, 0, 1.3f, new Item.Properties()));

    public static final RegistryObject<SwordItem> WAXED_COPPER_SWORD = ITEMS.register("waxed_copper_sword",
            ()-> new SwordItem(CopperTiers.COPPER, 3,0.6f ,new Item.Properties()));
    public static final RegistryObject<PickaxeItem> WAXED_COPPER_PICKAXE = ITEMS.register("waxed_copper_pickaxe",
            ()-> new PickaxeItem(CopperTiers.COPPER, 3, 0.5f, new Item.Properties()));
    public static final RegistryObject<AxeItem> WAXED_COPPER_AXE = ITEMS.register("waxed_copper_axe",
            ()-> new AxeItem(CopperTiers.COPPER, 7, -3.2f, new Item.Properties()));
    public static final RegistryObject<ShovelItem> WAXED_COPPER_SHOVEL = ITEMS.register("waxed_copper_shovel",
            ()-> new ShovelItem(CopperTiers.COPPER, 0.5f, 0.2f, new Item.Properties()));
    public static final RegistryObject<HoeItem> WAXED_COPPER_HOE = ITEMS.register("waxed_copper_hoe",
            ()-> new HoeItem(CopperTiers.COPPER, 0, 1.3f, new Item.Properties()));

    public static final RegistryObject<SwordItem> EXPOSED_COPPER_SWORD = ITEMS.register("exposed_copper_sword",
            ()-> new SwordItem(CopperTiers.EXPOSED_COPPER, 3,-0.2f, new Item.Properties()));
    public static final RegistryObject<PickaxeItem> EXPOSED_COPPER_PICKAXE = ITEMS.register("exposed_copper_pickaxe",
            ()-> new PickaxeItem(CopperTiers.EXPOSED_COPPER, 3, -0.2f, new Item.Properties()));
    public static final RegistryObject<AxeItem> EXPOSED_COPPER_AXE = ITEMS.register("exposed_copper_axe",
            ()-> new AxeItem(CopperTiers.EXPOSED_COPPER, 7, -3.4f, new Item.Properties()));
    public static final RegistryObject<ShovelItem> EXPOSED_COPPER_SHOVEL = ITEMS.register("exposed_copper_shovel",
            ()-> new ShovelItem(CopperTiers.EXPOSED_COPPER, 0.5f, -0.2f, new Item.Properties()));
    public static final RegistryObject<HoeItem> EXPOSED_COPPER_HOE = ITEMS.register("exposed_copper_hoe",
            ()-> new HoeItem(CopperTiers.EXPOSED_COPPER, 0, -0.2f, new Item.Properties()));

    public static final RegistryObject<SwordItem> WAXED_EXPOSED_COPPER_SWORD = ITEMS.register("waxed_exposed_copper_sword",
            ()-> new SwordItem(CopperTiers.EXPOSED_COPPER, 3,-0.2f, new Item.Properties()));
    public static final RegistryObject<PickaxeItem> WAXED_EXPOSED_COPPER_PICKAXE = ITEMS.register("waxed_exposed_copper_pickaxe",
            ()-> new PickaxeItem(CopperTiers.EXPOSED_COPPER, 3, -0.2f, new Item.Properties()));
    public static final RegistryObject<AxeItem> WAXED_EXPOSED_COPPER_AXE = ITEMS.register("waxed_exposed_copper_axe",
            ()-> new AxeItem(CopperTiers.EXPOSED_COPPER, 7, -3.4f, new Item.Properties()));
    public static final RegistryObject<ShovelItem> WAXED_EXPOSED_COPPER_SHOVEL = ITEMS.register("waxed_exposed_copper_shovel",
            ()-> new ShovelItem(CopperTiers.EXPOSED_COPPER, 0.5f, -0.2f, new Item.Properties()));
    public static final RegistryObject<HoeItem> WAXED_EXPOSED_COPPER_HOE = ITEMS.register("waxed_exposed_copper_hoe",
            ()-> new HoeItem(CopperTiers.EXPOSED_COPPER, 0, -0.2f, new Item.Properties()));

    public static final RegistryObject<SwordItem> WEATHERED_COPPER_SWORD = ITEMS.register("weathered_copper_sword",
            ()-> new SwordItem(CopperTiers.WEATHERED_COPPER, 3,-0.4f, new Item.Properties()));
    public static final RegistryObject<PickaxeItem> WEATHERED_COPPER_PICKAXE = ITEMS.register("weathered_copper_pickaxe",
            ()-> new PickaxeItem(CopperTiers.WEATHERED_COPPER, 3, -0.4f, new Item.Properties()));
    public static final RegistryObject<AxeItem> WEATHERED_COPPER_AXE = ITEMS.register("weathered_copper_axe",
            ()-> new AxeItem(CopperTiers.WEATHERED_COPPER, 7, -3.6f, new Item.Properties()));
    public static final RegistryObject<ShovelItem> WEATHERED_COPPER_SHOVEL = ITEMS.register("weathered_copper_shovel",
            ()-> new ShovelItem(CopperTiers.WEATHERED_COPPER, 0.5f, -0.4f, new Item.Properties()));
    public static final RegistryObject<HoeItem> WEATHERED_COPPER_HOE = ITEMS.register("weathered_copper_hoe",
            ()-> new HoeItem(CopperTiers.WEATHERED_COPPER, 0, -0.4f, new Item.Properties()));

    public static final RegistryObject<SwordItem> WAXED_WEATHERED_COPPER_SWORD = ITEMS.register("waxed_weathered_copper_sword",
            ()-> new SwordItem(CopperTiers.WEATHERED_COPPER, 3,-0.4f, new Item.Properties()));
    public static final RegistryObject<PickaxeItem> WAXED_WEATHERED_COPPER_PICKAXE = ITEMS.register("waxed_weathered_copper_pickaxe",
            ()-> new PickaxeItem(CopperTiers.WEATHERED_COPPER, 3, -0.4f, new Item.Properties()));
    public static final RegistryObject<AxeItem> WAXED_WEATHERED_COPPER_AXE = ITEMS.register("waxed_weathered_copper_axe",
            ()-> new AxeItem(CopperTiers.WEATHERED_COPPER, 7, -3.6f, new Item.Properties()));
    public static final RegistryObject<ShovelItem> WAXED_WEATHERED_COPPER_SHOVEL = ITEMS.register("waxed_weathered_copper_shovel",
            ()-> new ShovelItem(CopperTiers.WEATHERED_COPPER, 0.5f, -0.4f, new Item.Properties()));
    public static final RegistryObject<HoeItem> WAXED_WEATHERED_COPPER_HOE = ITEMS.register("waxed_weathered_copper_hoe",
            ()-> new HoeItem(CopperTiers.WEATHERED_COPPER, 0, -0.4f, new Item.Properties()));

    public static final RegistryObject<SwordItem> OXIDIZED_COPPER_SWORD = ITEMS.register("oxidized_copper_sword",
            ()-> new SwordItem(CopperTiers.OXIDIZED_COPPER, 3,-0.6f, new Item.Properties()));
    public static final RegistryObject<PickaxeItem> OXIDIZED_COPPER_PICKAXE = ITEMS.register("oxidized_copper_pickaxe",
            ()-> new PickaxeItem(CopperTiers.OXIDIZED_COPPER, 3, -0.6f, new Item.Properties()));
    public static final RegistryObject<AxeItem> OXIDIZED_COPPER_AXE = ITEMS.register("oxidized_copper_axe",
            ()-> new AxeItem(CopperTiers.OXIDIZED_COPPER, 7, -3.8f, new Item.Properties()));
    public static final RegistryObject<ShovelItem> OXIDIZED_COPPER_SHOVEL = ITEMS.register("oxidized_copper_shovel",
            ()-> new ShovelItem(CopperTiers.OXIDIZED_COPPER, 0.5f, -0.6f, new Item.Properties()));
    public static final RegistryObject<HoeItem> OXIDIZED_COPPER_HOE = ITEMS.register("oxidized_copper_hoe",
            ()-> new HoeItem(CopperTiers.OXIDIZED_COPPER, 0, -0.6f, new Item.Properties()));

    public static final RegistryObject<SwordItem> WAXED_OXIDIZED_COPPER_SWORD = ITEMS.register("waxed_oxidized_copper_sword",
            ()-> new SwordItem(CopperTiers.OXIDIZED_COPPER, 3,-0.6f, new Item.Properties()));
    public static final RegistryObject<PickaxeItem> WAXED_OXIDIZED_COPPER_PICKAXE = ITEMS.register("waxed_oxidized_copper_pickaxe",
            ()-> new PickaxeItem(CopperTiers.OXIDIZED_COPPER, 3, -0.6f, new Item.Properties()));
    public static final RegistryObject<AxeItem> WAXED_OXIDIZED_COPPER_AXE = ITEMS.register("waxed_oxidized_copper_axe",
            ()-> new AxeItem(CopperTiers.OXIDIZED_COPPER, 7, -3.8f, new Item.Properties()));
    public static final RegistryObject<ShovelItem> WAXED_OXIDIZED_COPPER_SHOVEL = ITEMS.register("waxed_oxidized_copper_shovel",
            ()-> new ShovelItem(CopperTiers.OXIDIZED_COPPER, 0.5f, -0.6f, new Item.Properties()));
    public static final RegistryObject<HoeItem> WAXED_OXIDIZED_COPPER_HOE = ITEMS.register("waxed_oxidized_copper_hoe",
            ()-> new HoeItem(CopperTiers.OXIDIZED_COPPER, 0, -0.6f, new Item.Properties()));

    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet",
            ()-> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate",
            ()-> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings",
            ()-> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots",
            ()-> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> WAXED_COPPER_HELMET = ITEMS.register("waxed_copper_helmet",
            ()-> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> WAXED_COPPER_CHESTPLATE = ITEMS.register("waxed_copper_chestplate",
            ()-> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> WAXED_COPPER_LEGGINGS = ITEMS.register("waxed_copper_leggings",
            ()-> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> WAXED_COPPER_BOOTS = ITEMS.register("waxed_copper_boots",
            ()-> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> EXPOSED_COPPER_HELMET = ITEMS.register("exposed_copper_helmet",
            ()-> new ModArmorItem(ModArmorMaterials.EXPOSED_COPPER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> EXPOSED_COPPER_CHESTPLATE = ITEMS.register("exposed_copper_chestplate",
            ()-> new ModArmorItem(ModArmorMaterials.EXPOSED_COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> EXPOSED_COPPER_LEGGINGS = ITEMS.register("exposed_copper_leggings",
            ()-> new ModArmorItem(ModArmorMaterials.EXPOSED_COPPER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> EXPOSED_COPPER_BOOTS = ITEMS.register("exposed_copper_boots",
            ()-> new ModArmorItem(ModArmorMaterials.EXPOSED_COPPER, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> WAXED_EXPOSED_COPPER_HELMET = ITEMS.register("waxed_exposed_copper_helmet",
            ()-> new ModArmorItem(ModArmorMaterials.EXPOSED_COPPER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> WAXED_EXPOSED_COPPER_CHESTPLATE = ITEMS.register("waxed_exposed_copper_chestplate",
            ()-> new ModArmorItem(ModArmorMaterials.EXPOSED_COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> WAXED_EXPOSED_COPPER_LEGGINGS = ITEMS.register("waxed_exposed_copper_leggings",
            ()-> new ModArmorItem(ModArmorMaterials.EXPOSED_COPPER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> WAXED_EXPOSED_COPPER_BOOTS = ITEMS.register("waxed_exposed_copper_boots",
            ()-> new ModArmorItem(ModArmorMaterials.EXPOSED_COPPER, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> WEATHERED_COPPER_HELMET = ITEMS.register("weathered_copper_helmet",
            ()-> new ModArmorItem(ModArmorMaterials.WEATHERED_COPPER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> WEATHERED_COPPER_CHESTPLATE = ITEMS.register("weathered_copper_chestplate",
            ()-> new ModArmorItem(ModArmorMaterials.WEATHERED_COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> WEATHERED_COPPER_LEGGINGS = ITEMS.register("weathered_copper_leggings",
            ()-> new ModArmorItem(ModArmorMaterials.WEATHERED_COPPER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> WEATHERED_COPPER_BOOTS = ITEMS.register("weathered_copper_boots",
            ()-> new ModArmorItem(ModArmorMaterials.WEATHERED_COPPER, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> WAXED_WEATHERED_COPPER_HELMET = ITEMS.register("waxed_weathered_copper_helmet",
            ()-> new ModArmorItem(ModArmorMaterials.WEATHERED_COPPER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> WAXED_WEATHERED_COPPER_CHESTPLATE = ITEMS.register("waxed_weathered_copper_chestplate",
            ()-> new ModArmorItem(ModArmorMaterials.WEATHERED_COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> WAXED_WEATHERED_COPPER_LEGGINGS = ITEMS.register("waxed_weathered_copper_leggings",
            ()-> new ModArmorItem(ModArmorMaterials.WEATHERED_COPPER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> WAXED_WEATHERED_COPPER_BOOTS = ITEMS.register("waxed_weathered_copper_boots",
            ()-> new ModArmorItem(ModArmorMaterials.WEATHERED_COPPER, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> OXIDIZED_COPPER_HELMET = ITEMS.register("oxidized_copper_helmet",
            ()-> new ModArmorItem(ModArmorMaterials.OXIDIZED_COPPER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> OXIDIZED_COPPER_CHESTPLATE = ITEMS.register("oxidized_copper_chestplate",
            ()-> new ModArmorItem(ModArmorMaterials.OXIDIZED_COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> OXIDIZED_COPPER_LEGGINGS = ITEMS.register("oxidized_copper_leggings",
            ()-> new ModArmorItem(ModArmorMaterials.OXIDIZED_COPPER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> OXIDIZED_COPPER_BOOTS = ITEMS.register("oxidized_copper_boots",
            ()-> new ModArmorItem(ModArmorMaterials.OXIDIZED_COPPER, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> WAXED_OXIDIZED_COPPER_HELMET = ITEMS.register("waxed_oxidized_copper_helmet",
            ()-> new ModArmorItem(ModArmorMaterials.OXIDIZED_COPPER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> WAXED_OXIDIZED_COPPER_CHESTPLATE = ITEMS.register("waxed_oxidized_copper_chestplate",
            ()-> new ModArmorItem(ModArmorMaterials.OXIDIZED_COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> WAXED_OXIDIZED_COPPER_LEGGINGS = ITEMS.register("waxed_oxidized_copper_leggings",
            ()-> new ModArmorItem(ModArmorMaterials.OXIDIZED_COPPER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> WAXED_OXIDIZED_COPPER_BOOTS = ITEMS.register("waxed_oxidized_copper_boots",
            ()-> new ModArmorItem(ModArmorMaterials.OXIDIZED_COPPER, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> COPPER_HORSE_ARMOR = ITEMS.register("copper_horse_armor",
            ()-> new HorseArmorItem(7, "copper", new Item.Properties()));
}