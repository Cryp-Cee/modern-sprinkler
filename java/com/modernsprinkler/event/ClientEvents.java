package com.modernsprinkler.event;

import com.modernsprinkler.ModernSprinkler;
import com.modernsprinkler.core.init.BlockEntityInit;
import com.modernsprinkler.renderer.SprinklerHeadRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modId = ModernSprinkler.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    // Diese Methode registriert unseren GeckoLib BlockEntity-Renderer
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderers-Event.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityInit.SPRINKLER_HEAD_BLOCK_ENTITY.get(), SprinklerHeadRenderer::new);
    }
}