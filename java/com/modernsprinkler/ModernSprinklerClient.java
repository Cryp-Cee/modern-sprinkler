package com.modernsprinkler;

import com.modernsprinkler.core.init.BlockEntityInit;
import com.modernsprinkler.renderer.SprinklerHeadRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModernSprinklerClient {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            BlockEntityRenderers.register(BlockEntityInit.SPRINKLER_HEAD_BLOCK_ENTITY.get(), SprinklerHeadRenderer::new);
        });
    }
}