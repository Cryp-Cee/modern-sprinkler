package com.modernsprinkler.core.init;

import com.modernsprinkler.ModernSprinkler;
import com.modernsprinkler.block.entity.SprinklerHeadBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ModernSprinkler.MOD_ID);

    public static final RegistryObject<BlockEntityType<SprinklerHeadBlockEntity>> SPRINKLER_HEAD_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("sprinkler_head_block_entity", () ->
                    BlockEntityType.Builder.of(SprinklerHeadBlockEntity::new,
                            // Wichtig: Liste hier alle Blöcke auf, die dieses "Gehirn" nutzen können
                            BlockInit.IRON_SPRINKLER_HEAD.get(),
                            BlockInit.GOLD_SPRINKLER_HEAD.get(),
                            BlockInit.DIAMOND_SPRINKLER_HEAD.get(),
                            BlockInit.NETHERITE_SPRINKLER_HEAD.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}