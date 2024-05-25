package net.MichaelBoss.Brassinstruments.item;

import net.MichaelBoss.Brassinstruments.BrassInstruments;
import net.minecraft.world.item.*;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.annotation.ElementType;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BrassInstruments.MOD_ID);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

    public static final RegistryObject<Item> COPPER = ITEMS.register("copper",
            ()-> new Item(new Item.Properties()));


    public static final RegistryObject<Item> COPPER_SHEARS = ITEMS.register("copper_shears",
            ()-> new ShearsItem(new Item.Properties()));


    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword",
            ()-> new SwordItem(ModToolTiers.COPPER, 1, 0.625f, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",
            ()-> new PickaxeItem(ModToolTiers.COPPER, 3, 1, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe",
            ()-> new AxeItem(ModToolTiers.COPPER, 4, 0.83f, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_SHOVE = ITEMS.register("copper_shove",
            ()-> new ShovelItem(ModToolTiers.COPPER, 0, 0, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe",
            ()-> new HoeItem(ModToolTiers.COPPER, 0, 0, new Item.Properties()));


    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet",
            ()-> new ArmorItem(ModArmorMaterials.Copper, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate",
            ()-> new ArmorItem(ModArmorMaterials.Copper, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings",
            ()-> new ArmorItem(ModArmorMaterials.Copper, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots",
            ()-> new ArmorItem(ModArmorMaterials.Copper, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> COPPER_HORSE_ARMOR = ITEMS.register("copper_horse_armor",
            ()-> new HorseArmorItem(7, "copper", new Item.Properties()));
}
