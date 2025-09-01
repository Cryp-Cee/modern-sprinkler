package com.modernsprinkler.event;

import com.modernsprinkler.ModernSprinkler;
import com.modernsprinkler.core.init.BlockEntityInit;
import com.modernsprinkler.core.init.ParticleInit;
import com.modernsprinkler.particle.WaterDropParticleProvider;
import com.modernsprinkler.renderer.SprinklerHeadRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// KORRIGIERT: Der 'modId'-Parameter wurde entfernt.
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleInit.WATER_DROP.get(), WaterDropParticleProvider::new);
    }

    // KORRIGIERT: Die Methode für die Renderer-Registrierung.
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityInit.SPRINKLER_HEAD_BLOCK_ENTITY.get(), SprinklerHeadRenderer::new);
    }
}