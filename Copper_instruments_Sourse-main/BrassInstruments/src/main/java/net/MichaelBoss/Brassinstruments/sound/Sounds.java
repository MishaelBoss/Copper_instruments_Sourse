package net.MichaelBoss.Brassinstruments.sound;

import net.MichaelBoss.Brassinstruments.BrassInstruments;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Sounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BrassInstruments.MOD_ID);

    public static final RegistryObject<SoundEvent> COPPER_MUSIC_SOUND = registerSoundEvents("copper_music");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name){
        return SOUNDS.register(name,
                () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(BrassInstruments.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus){
        SOUNDS.register(eventBus);
    }
}