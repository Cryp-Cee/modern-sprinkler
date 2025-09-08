package com.modern_sprinkler.block.entity;

import com.modern_sprinkler.ModernSprinkler;
import com.modern_sprinkler.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ModernSprinkler.MOD_ID);

    public static final RegistryObject<BlockEntityType<SprinklerBlockEntity>> SPRINKLER_BOTTOM_BE =
            BLOCK_ENTITIES.register("sprinkler_bottom_be", () ->
                    BlockEntityType.Builder.of(SprinklerBlockEntity::new,
                            ModBlocks.SPRINKLER_BOTTOM.get()).build(null));

    public static final RegistryObject<BlockEntityType<SprinklerHeadBlockEntity>> SPRINKLER_HEAD_BE =
            BLOCK_ENTITIES.register("sprinkler_head_be", () ->
                    BlockEntityType.Builder.of(SprinklerHeadBlockEntity::new,
                            ModBlocks.IRON_SPRINKLER_HEAD.get(),
                            ModBlocks.GOLD_SPRINKLER_HEAD.get(),
                            ModBlocks.DIAMOND_SPRINKLER_HEAD.get(),
                            ModBlocks.NETHERITE_SPRINKLER_HEAD.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}