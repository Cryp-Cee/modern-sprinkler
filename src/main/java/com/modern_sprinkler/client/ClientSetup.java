package com.modern_sprinkler.client;

import com.modern_sprinkler.ModernSprinkler;
import com.modern_sprinkler.registry.ModBlockEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModernSprinkler.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.IRON_SPRINKLER_HEAD_BE.get(),
                ctx -> new IronSprinklerHeadRenderer());
    }
}
