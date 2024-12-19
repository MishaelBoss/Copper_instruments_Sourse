package net.MichaelBoss.Brassinstruments.event;

import net.MichaelBoss.Brassinstruments.BrassInstruments;
import net.MichaelBoss.Brassinstruments.item.ModItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.level.*;

import java.util.Random;

@Mod.EventBusSubscriber(modid = BrassInstruments.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void isCurrentlyHoldingACopperObject(PlayerInteractEvent event) {
        Player player = event.getEntity();
        Level level = player.level();
        ItemStack mainHandItem = player.getMainHandItem();
        if(level.isRaining()){
            if(mainHandItem.getItem() == ModItems.COPPER_SWORD.get()
                    || mainHandItem.getItem() == ModItems.COPPER_PICKAXE.get()
                    || mainHandItem.getItem() == ModItems.COPPER_AXE.get()
                    || mainHandItem.getItem() == ModItems.COPPER_SHOVEL.get()
                    || mainHandItem.getItem() == ModItems.COPPER_HOE.get()){
                int rngNum = new Random().nextInt(0, 10);
                if (rngNum == 0) {
                    player.setSecondsOnFire(10);

                    Vec3 vec3d = player.getEyePosition(1);
                    Vec3 vec3d1 = player.getViewVector(1);
                    BlockHitResult pos = player.level().clip(new ClipContext(vec3d, vec3d1, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));

                    LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                    bolt.moveTo(pos.getLocation().offsetRandom(RandomSource.create(), 8));
                    level.addFreshEntity(bolt);
                }
            }
        }
    }
}