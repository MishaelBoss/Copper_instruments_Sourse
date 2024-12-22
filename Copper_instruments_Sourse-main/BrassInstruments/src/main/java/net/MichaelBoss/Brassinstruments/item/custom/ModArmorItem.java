package net.MichaelBoss.Brassinstruments.item.custom;

import com.google.common.collect.ImmutableMap;
import net.MichaelBoss.Brassinstruments.item.ModArmorMaterials;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Map;

public class ModArmorItem extends ArmorItem {
    private static final Map<Holder<ArmorMaterial>, List<MobEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<Holder<ArmorMaterial>, List<MobEffectInstance>>())
                    .put(ModArmorMaterials.EXPOSED_COPPER,
                            List.of(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 0, 0, false, false)))
                    .put(ModArmorMaterials.WEATHERED_COPPER,
                            List.of(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 0, 1, false, false)))
                    .put(ModArmorMaterials.OXIDIZED_COPPER,
                            List.of(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 0, 2, false, false)))
                    .build();

    public ModArmorItem(ArmorMaterial material, Type type, Properties properties){
        super(material, type, properties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectdIndex){
        if(!level.isClientSide() && hasFullSuitOfArmorOn(player)){
            evaluateArmorEffect(player);
        }
    }

    private void evaluateArmorEffect(Player player){
        for(Map.Entry<Holder<ArmorMaterial>, List<MobEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()){
            Holder<ArmorMaterial> armorMaterialHolder = entry.getKey();
            List<MobEffectInstance> mobEffectInstanceList = entry.getValue();

            if(hasPlayerFullCorrectOfArmorOn(armorMaterialHolder, player)){
                addEffectToPlayer(player, mobEffectInstanceList);
            }
        }
    }

    private void addEffectToPlayer(Player player, List<MobEffectInstance> mapEffect){
        boolean hasPlayerEffect = mapEffect.stream().allMatch(effect -> player.hasEffect(effect.getEffect()));

        if(!hasPlayerEffect){
            for(MobEffectInstance effect : mapEffect){
                player.addEffect(new MobEffectInstance(effect.getEffect(),
                        effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.isVisible()));
            }
        }
    }

    private boolean hasPlayerFullCorrectOfArmorOn(Holder<ArmorMaterial> armorMaterialHolder, Player player){
        for(ItemStack itemStack : player.getArmorSlots()){
            if(!(itemStack.getItem() instanceof ArmorItem)){
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
        ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());

        return boots.getMaterial() == armorMaterialHolder &&
                leggings.getMaterial() == armorMaterialHolder &&
                chestplate.getMaterial() == armorMaterialHolder &&
                helmet.getMaterial() == armorMaterialHolder;
    }

    private boolean hasFullSuitOfArmorOn(Player player){
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !boots.isEmpty() &&
                !leggings.isEmpty() &&
                !chestplate.isEmpty() &&
                !helmet.isEmpty();
    }
}
