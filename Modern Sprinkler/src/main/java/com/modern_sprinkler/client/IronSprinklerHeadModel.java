package com.modern_sprinkler.client;

import com.modern_sprinkler.ModernSprinkler;
import com.modern_sprinkler.block.entity.SprinklerHeadBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class IronSprinklerHeadModel extends GeoModel<SprinklerHeadBlockEntity> {
    @Override
    public ResourceLocation getModelResource(SprinklerHeadBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(ModernSprinkler.MOD_ID, "geo/block/iron_sprinkler_head.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SprinklerHeadBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(ModernSprinkler.MOD_ID, "textures/block/iron_sprinkler_head.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SprinklerHeadBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(ModernSprinkler.MOD_ID, "animations/sprinkler_head.animation.json");
    }
}