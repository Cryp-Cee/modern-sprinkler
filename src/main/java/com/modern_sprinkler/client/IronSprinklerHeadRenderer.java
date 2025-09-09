package com.modern_sprinkler.client;

import com.modern_sprinkler.blockentity.IronSprinklerHeadBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class IronSprinklerHeadRenderer extends GeoBlockRenderer<IronSprinklerHeadBlockEntity> {

    public IronSprinklerHeadRenderer() {
        super(new IronSprinklerHeadModel());
    }

    @Override
    public RenderType getRenderType(IronSprinklerHeadBlockEntity animatable,
                                    ResourceLocation texture,
                                    @Nullable MultiBufferSource bufferSource,
                                    float partialTick) {
        // wichtig gegen "dunkel/schwarz" wirkende Texturen
        return RenderType.entityCutoutNoCull(texture);
    }

    @Override
    public void preRender(PoseStack poseStack, IronSprinklerHeadBlockEntity animatable,
                          BakedGeoModel model, @Nullable MultiBufferSource bufferSource,
                          @Nullable VertexConsumer buffer, boolean isReRender, float partialTick,
                          int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick,
                packedLight, packedOverlay, red, green, blue, alpha);
    }
}
