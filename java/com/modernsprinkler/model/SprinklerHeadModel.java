package com.modernsprinkler.model;

import com.modernsprinkler.ModernSprinkler;
import com.modernsprinkler.block.entity.SprinklerHeadBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SprinklerHeadModel extends GeoModel<SprinklerHeadBlockEntity> {

    @Override
    public ResourceLocation getModelResource(SprinklerHeadBlockEntity animatable) {
        return new ResourceLocation(ModernSprinkler.MOD_ID, "geo/sprinkler_head.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SprinklerHeadBlockEntity animatable) {
        return new ResourceLocation(ModernSprinkler.MOD_ID, "textures/block/iron_sprinkler_head_side.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SprinklerHeadBlockEntity animatable) {
        // DIES IST DER KORREKTE CODE, DER DORT STEHEN SOLLTE:
        return new ResourceLocation(ModernSprinkler.MOD_ID, "animations/sprinkler_head.animation.json");
    }
}
