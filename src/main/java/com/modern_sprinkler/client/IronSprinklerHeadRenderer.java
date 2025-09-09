package com.modern_sprinkler.client;

import com.modern_sprinkler.block.entity.SprinklerHeadBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class IronSprinklerHeadRenderer extends GeoBlockRenderer<SprinklerHeadBlockEntity> {
    public IronSprinklerHeadRenderer(BlockEntityRendererProvider.Context context) {
        super(new IronSprinklerHeadModel());
    }
}