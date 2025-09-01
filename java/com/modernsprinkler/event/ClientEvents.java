package com.modernsprinkler.event;

import com.modernsprinkler.ModernSprinkler;
import com.modernsprinkler.core.init.ParticleInit;
import com.modernsprinkler.particle.WaterDropParticleProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// KORRIGIERT: Der Parameter "modId = ModernSprinkler.MOD_ID" wurde entfernt.
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleInit.WATER_DROP.get(), WaterDropParticleProvider::new);
    }
}