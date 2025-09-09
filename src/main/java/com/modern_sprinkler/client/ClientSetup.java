package com.modern_sprinkler.client;

import com.modern_sprinkler.ModernSprinkler;
import com.modern_sprinkler.registry.ModBlockEntities;
import com.modern_sprinkler.screen.ModMenuTypes;
import com.modern_sprinkler.screen.SprinklerScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ModernSprinkler.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.IRON_SPRINKLER_HEAD_BE.get(), ctx -> new IronSprinklerHeadRenderer());
        event.registerBlockEntityRenderer(ModBlockEntities.GOLD_SPRINKLER_HEAD_BE.get(), ctx -> new GoldSprinklerHeadRenderer());
        event.registerBlockEntityRenderer(ModBlockEntities.DIAMOND_SPRINKLER_HEAD_BE.get(), ctx -> new DiamondSprinklerHeadRenderer());
        event.registerBlockEntityRenderer(ModBlockEntities.NETHERITE_SPRINKLER_HEAD_BE.get(), ctx -> new NetheriteSprinklerHeadRenderer());
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> MenuScreens.register(ModMenuTypes.SPRINKLER_MENU.get(), SprinklerScreen::new));
    }
}
