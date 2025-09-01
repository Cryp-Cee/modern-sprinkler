package com.modernsprinkler;

import com.modernsprinkler.core.init.BlockEntityInit;
import com.modernsprinkler.core.init.BlockInit;
import com.modernsprinkler.core.init.ItemInit;
import com.modernsprinkler.core.init.ParticleInit;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ModernSprinkler.MOD_ID)
public class ModernSprinkler {
    public static final String MOD_ID = "modern_sprinkler";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ModernSprinkler() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.register(modEventBus);
        BlockInit.register(modEventBus);
        BlockEntityInit.register(modEventBus);
        ParticleInit.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }
}