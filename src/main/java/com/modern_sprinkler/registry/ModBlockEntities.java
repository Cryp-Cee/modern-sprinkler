package com.modern_sprinkler.registry;

import com.modern_sprinkler.ModernSprinkler;
import com.modern_sprinkler.blockentity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ModernSprinkler.MOD_ID);

    public static final RegistryObject<BlockEntityType<SprinklerBottomBlockEntity>> SPRINKLER_BOTTOM_BE =
            BLOCK_ENTITIES.register("sprinkler_bottom",
                    () -> BlockEntityType.Builder.of(SprinklerBottomBlockEntity::new,
                            ModBlocks.SPRINKLER_BOTTOM.get()).build(null));

    public static final RegistryObject<BlockEntityType<IronSprinklerHeadBlockEntity>> IRON_SPRINKLER_HEAD_BE =
            BLOCK_ENTITIES.register("iron_sprinkler_head",
                    () -> BlockEntityType.Builder.of(IronSprinklerHeadBlockEntity::new,
                            ModBlocks.IRON_SPRINKLER_HEAD_INACTIVE.get(),
                            ModBlocks.IRON_SPRINKLER_HEAD_ACTIVE.get()).build(null));

    public static final RegistryObject<BlockEntityType<GoldSprinklerHeadBlockEntity>> GOLD_SPRINKLER_HEAD_BE =
            BLOCK_ENTITIES.register("gold_sprinkler_head",
                    () -> BlockEntityType.Builder.of(GoldSprinklerHeadBlockEntity::new,
                            ModBlocks.GOLD_SPRINKLER_HEAD_INACTIVE.get(),
                            ModBlocks.GOLD_SPRINKLER_HEAD_ACTIVE.get()).build(null));

    public static final RegistryObject<BlockEntityType<DiamondSprinklerHeadBlockEntity>> DIAMOND_SPRINKLER_HEAD_BE =
            BLOCK_ENTITIES.register("diamond_sprinkler_head",
                    () -> BlockEntityType.Builder.of(DiamondSprinklerHeadBlockEntity::new,
                            ModBlocks.DIAMOND_SPRINKLER_HEAD_INACTIVE.get(),
                            ModBlocks.DIAMOND_SPRINKLER_HEAD_ACTIVE.get()).build(null));

    public static final RegistryObject<BlockEntityType<NetheriteSprinklerHeadBlockEntity>> NETHERITE_SPRINKLER_HEAD_BE =
            BLOCK_ENTITIES.register("netherite_sprinkler_head",
                    () -> BlockEntityType.Builder.of(NetheriteSprinklerHeadBlockEntity::new,
                            ModBlocks.NETHERITE_SPRINKLER_HEAD_INACTIVE.get(),
                            ModBlocks.NETHERITE_SPRINKLER_HEAD_ACTIVE.get()).build(null));
}
