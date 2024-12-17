package net.MichaelBoss.Brassinstruments.item;

import net.MichaelBoss.Brassinstruments.BrassInstruments;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.world.item.Tiers.IRON;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BrassInstruments.MOD_ID);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }


    public static final RegistryObject<Item> COPPER_SHEARS = ITEMS.register("copper_shears",
            ()-> new ShearsItem(new Item.Properties()));


    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword",
            ()-> new SwordItem(TutTiers.COPPER, 1, 0.625f, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",
            ()-> new PickaxeItem(TutTiers.COPPER, 3, 1, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe",
            ()-> new AxeItem(TutTiers.COPPER, 4, 0.83f, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel",
            ()-> new ShovelItem(TutTiers.COPPER, 0, 0, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe",
            ()-> new HoeItem(TutTiers.COPPER, 0, 0, new Item.Properties()));


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