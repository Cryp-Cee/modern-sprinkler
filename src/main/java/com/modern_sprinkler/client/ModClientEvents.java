package com.modern_sprinkler.client;

import com.modern_sprinkler.ModernSprinkler;
import com.modern_sprinkler.block.entity.ModBlockEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModernSprinkler.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.SPRINKLER_HEAD_BE.get(),
                IronSprinklerHeadRenderer::new);
    }
}