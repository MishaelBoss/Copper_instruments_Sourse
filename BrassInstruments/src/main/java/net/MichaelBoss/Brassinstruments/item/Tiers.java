package net.MichaelBoss.Brassinstruments.item;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum Tiers implements Tier {
    COPPER(2, 210,6, 1, 17, () -> Ingredient.of(Items.COPPER_INGOT.getDefaultInstance())),
    EXPOSED_COPPER(2, 210,6, 1, 17, () -> Ingredient.of(Items.COPPER_INGOT.getDefaultInstance())),
    WEATHERED_COPPER(2, 210,6, 1, 17, () -> Ingredient.of(Items.COPPER_INGOT.getDefaultInstance())),
    OXIDIZED_COPPER(2, 210,6, 1, 17, () -> Ingredient.of(Items.COPPER_INGOT.getDefaultInstance()));

    private final int miningLevel;
    private final int durability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    Tiers(int miningLevel, int durability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.durability = durability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getUses() {
        return this.durability;
    }

    public float getSpeed() {
        return this.miningSpeed;
    }

    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    public int getLevel() {
        return this.miningLevel;
    }

    public int getEnchantmentValue() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
