package net.MichaelBoss.Brassinstruments;

import net.MichaelBoss.Brassinstruments.event.OxidizeEventHandler;
import net.MichaelBoss.Brassinstruments.item.ModItems;
import net.MichaelBoss.Brassinstruments.sound.Sounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BrassInstruments.MOD_ID)
public class BrassInstruments
{
    public static final String MOD_ID = "brassinstrumentsmod";

    public BrassInstruments()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        Sounds.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new OxidizeEventHandler());

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onGatherData);
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

            //Instrument
            event.accept(ModItems.WAXED_COPPER_SWORD);
            event.accept(ModItems.WAXED_COPPER_AXE);

            event.accept(ModItems.WAXED_EXPOSED_COPPER_SWORD);
            event.accept(ModItems.WAXED_EXPOSED_COPPER_AXE);

            event.accept(ModItems.WAXED_WEATHERED_COPPER_SWORD);
            event.accept(ModItems.WAXED_WEATHERED_COPPER_AXE);

            event.accept(ModItems.WAXED_OXIDIZED_COPPER_SWORD);
            event.accept(ModItems.WAXED_OXIDIZED_COPPER_AXE);

            //Armor
            event.accept(ModItems.WAXED_COPPER_HELMET);
            event.accept(ModItems.WAXED_COPPER_CHESTPLATE);
            event.accept(ModItems.WAXED_COPPER_LEGGINGS);
            event.accept(ModItems.WAXED_COPPER_BOOTS);

            event.accept(ModItems.WAXED_COPPER_HELMET);
            event.accept(ModItems.WAXED_COPPER_CHESTPLATE);
            event.accept(ModItems.WAXED_COPPER_LEGGINGS);
            event.accept(ModItems.WAXED_COPPER_BOOTS);

            event.accept(ModItems.WAXED_EXPOSED_COPPER_HELMET);
            event.accept(ModItems.WAXED_EXPOSED_COPPER_CHESTPLATE);
            event.accept(ModItems.WAXED_EXPOSED_COPPER_LEGGINGS);
            event.accept(ModItems.WAXED_EXPOSED_COPPER_BOOTS);

            event.accept(ModItems.WAXED_WEATHERED_COPPER_HELMET);
            event.accept(ModItems.WAXED_WEATHERED_COPPER_CHESTPLATE);
            event.accept(ModItems.WAXED_WEATHERED_COPPER_LEGGINGS);
            event.accept(ModItems.WAXED_WEATHERED_COPPER_BOOTS);

            event.accept(ModItems.WAXED_OXIDIZED_COPPER_HELMET);
            event.accept(ModItems.WAXED_OXIDIZED_COPPER_CHESTPLATE);
            event.accept(ModItems.WAXED_OXIDIZED_COPPER_LEGGINGS);
            event.accept(ModItems.WAXED_OXIDIZED_COPPER_BOOTS);
        }
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            //Instrument
            event.accept(ModItems.COPPER_PICKAXE);
            event.accept(ModItems.COPPER_SHOVEL);
            event.accept(ModItems.COPPER_HOE);

            event.accept(ModItems.EXPOSED_COPPER_PICKAXE);
            event.accept(ModItems.EXPOSED_COPPER_SHOVEL);
            event.accept(ModItems.EXPOSED_COPPER_HOE);

            event.accept(ModItems.WEATHERED_COPPER_PICKAXE);
            event.accept(ModItems.WEATHERED_COPPER_SHOVEL);
            event.accept(ModItems.WEATHERED_COPPER_HOE);

            event.accept(ModItems.OXIDIZED_COPPER_PICKAXE);
            event.accept(ModItems.OXIDIZED_COPPER_SHOVEL);
            event.accept(ModItems.OXIDIZED_COPPER_HOE);

            event.accept(ModItems.WAXED_COPPER_PICKAXE);
            event.accept(ModItems.WAXED_COPPER_SHOVEL);
            event.accept(ModItems.WAXED_COPPER_HOE);

            event.accept(ModItems.WAXED_EXPOSED_COPPER_PICKAXE);
            event.accept(ModItems.WAXED_EXPOSED_COPPER_SHOVEL);
            event.accept(ModItems.WAXED_EXPOSED_COPPER_HOE);

            event.accept(ModItems.WAXED_WEATHERED_COPPER_PICKAXE);
            event.accept(ModItems.WAXED_WEATHERED_COPPER_SHOVEL);
            event.accept(ModItems.WAXED_WEATHERED_COPPER_HOE);

            event.accept(ModItems.WAXED_OXIDIZED_COPPER_PICKAXE);
            event.accept(ModItems.WAXED_OXIDIZED_COPPER_SHOVEL);
            event.accept(ModItems.WAXED_OXIDIZED_COPPER_HOE);

            //Item
            event.accept(ModItems.COPPER_SHEARS);
            event.accept(ModItems.EXPOSED_COPPER_SHEARS);
            event.accept(ModItems.WEATHERED_COPPER_SHEARS);
            event.accept(ModItems.OXIDIZED_COPPER_SHEARS);

            event.accept(ModItems.COPPER_MUSIC_MUSIC_DISK);
        }
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.COPPER_NUGGET);
        }
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        //LOGGER.info("Thanks for playing with Saurfort's MoreDisc");
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        //LOGGER.info("Thanks for playing with Saurfort's MoreDisc");
    }

    private void onGatherData(GatherDataEvent dataEvent) {
        DataGenerator generator = dataEvent.getGenerator();
        ExistingFileHelper existingFileHelper = dataEvent.getExistingFileHelper();
    }
}
