package net.MichaelBoss.Brassinstruments.event;

import net.MichaelBoss.Brassinstruments.BrassInstruments;
import net.MichaelBoss.Brassinstruments.item.ModItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = BrassInstruments.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
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
}