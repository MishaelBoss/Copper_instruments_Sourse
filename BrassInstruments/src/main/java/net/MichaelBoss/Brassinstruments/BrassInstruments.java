package net.MichaelBoss.Brassinstruments;

import com.mojang.logging.LogUtils;
import net.MichaelBoss.Brassinstruments.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(BrassInstruments.MOD_ID)
public class BrassInstruments
{
    public static final String MOD_ID = "brassinstrumentsmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public BrassInstruments()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event){
        if(event.getTabKey() == CreativeModeTabs.COMBAT){

            //Instrument
            event.accept(ModItems.COPPER_SWORD);
            event.accept(ModItems.COPPER_AXE);

            event.accept(ModItems.EXPOSED_COPPER_SWORD);
            event.accept(ModItems.EXPOSED_COPPER_AXE);

            event.accept(ModItems.WEATHERED_COPPER_SWORD);
            event.accept(ModItems.WEATHERED_COPPER_AXE);

            event.accept(ModItems.OXIDIZED_COPPER_SWORD);
            event.accept(ModItems.OXIDIZED_COPPER_AXE);

            //Item
            event.accept(ModItems.COPPER_HORSE_ARMOR);

            //Armor
            event.accept(ModItems.COPPER_HELMET);
            event.accept(ModItems.COPPER_CHESTPLATE);
            event.accept(ModItems.COPPER_LEGGINGS);
            event.accept(ModItems.COPPER_BOOTS);

            event.accept(ModItems.EXPOSED_COPPER_HELMET);
            event.accept(ModItems.EXPOSED_COPPER_CHESTPLATE);
            event.accept(ModItems.EXPOSED_COPPER_LEGGINGS);
            event.accept(ModItems.EXPOSED_COPPER_BOOTS);

            event.accept(ModItems.WEATHERED_COPPER_HELMET);
            event.accept(ModItems.WEATHERED_COPPER_CHESTPLATE);
            event.accept(ModItems.WEATHERED_COPPER_LEGGINGS);
            event.accept(ModItems.WEATHERED_COPPER_BOOTS);

            event.accept(ModItems.OXIDIZED_COPPER_HELMET);
            event.accept(ModItems.OXIDIZED_COPPER_CHESTPLATE);
            event.accept(ModItems.OXIDIZED_COPPER_LEGGINGS);
            event.accept(ModItems.OXIDIZED_COPPER_BOOTS);
        }
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.accept(ModItems.COPPER_PICKAXE);
            event.accept(ModItems.COPPER_SHOVEL);
            event.accept(ModItems.COPPER_HOE);

            //Item
            event.accept(ModItems.COPPER_SHEARS);

            event.accept(ModItems.EXPOSED_COPPER_PICKAXE);
            event.accept(ModItems.EXPOSED_COPPER_SHOVEL);
            event.accept(ModItems.EXPOSED_COPPER_HOE);

            event.accept(ModItems.WEATHERED_COPPER_PICKAXE);
            event.accept(ModItems.WEATHERED_COPPER_SHOVEL);
            event.accept(ModItems.WEATHERED_COPPER_HOE);

            event.accept(ModItems.OXIDIZED_COPPER_PICKAXE);
            event.accept(ModItems.OXIDIZED_COPPER_SHOVEL);
            event.accept(ModItems.OXIDIZED_COPPER_HOE);
        }
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.COPPER_NUGGET);
        }
    }
}
