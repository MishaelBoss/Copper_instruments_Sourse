package net.MichaelBoss.Brassinstruments.item;

import net.MichaelBoss.Brassinstruments.BrassInstruments;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    COPPER("copper", 20, new int[]{ 2, 4 , 3, 2 },25,
            SoundEvents.ARMOR_EQUIP_GOLD, 1f, 0f, () -> Ingredient.of(ModItems.COPPER_NUGGET.get())),
    EXPOSED_COPPER("copper", 20, new int[]{ 2, 4 , 3, 2 },25,
            SoundEvents.ARMOR_EQUIP_GOLD, 1f, 0f, () -> Ingredient.of(ModItems.COPPER_NUGGET.get())),
    WEATHERED_COPPER("copper", 20, new int[]{ 2, 4 , 3, 2 },25,
            SoundEvents.ARMOR_EQUIP_GOLD, 1f, 0f, () -> Ingredient.of(ModItems.COPPER_NUGGET.get())),
    OXIDIZED_COPPER("copper", 20, new int[]{ 2, 4 , 3, 2 },25,
            SoundEvents.ARMOR_EQUIP_GOLD, 1f, 0f, () -> Ingredient.of(ModItems.COPPER_NUGGET.get()));

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmount;
    private final int enchantmentValue;
    private final SoundEvent equipmentSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = { 160, 230, 210, 130 };

    ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmount, int enchantmentValue, SoundEvent equipmentSound,
                      float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmount = protectionAmount;
        this.enchantmentValue = enchantmentValue;
        this.equipmentSound = equipmentSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] + this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.protectionAmount[type.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipmentSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return BrassInstruments.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
