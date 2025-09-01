package com.modernsprinkler;

import com.modernsprinkler.core.init.BlockEntityInit;
import com.modernsprinkler.core.init.BlockInit;
import com.modernsprinkler.core.init.ItemInit;
import com.modernsprinkler.renderer.SprinklerHeadRenderer; // NEUER IMPORT
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers; // NEUER IMPORT
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent; // NEUER IMPORT
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

@Mod(ModernSprinkler.MOD_ID)
public class ModernSprinkler {
    public static final String MOD_ID = "modern_sprinkler";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ModernSprinkler() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.register(modEventBus);
        BlockInit.register(modEventBus);
        BlockEntityInit.register(modEventBus);

        // Initialisiere GeckoLib mit unserer Mod-ID
        GeckoLib.initialize();

        modEventBus.addListener(this::commonSetup);
        // NEU: Wir fügen den Client-Setup-Listener wieder direkt hier hinzu
        modEventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    // NEU: Die clientSetup-Methode registriert jetzt unseren Renderer
    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            BlockEntityRenderers.register(BlockEntityInit.SPRINKLER_HEAD_BLOCK_ENTITY.get(), SprinklerHeadRenderer::new);
        });
    }
}