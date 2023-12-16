package com.github.L_Ender.cataclysm.client.render.entity;

import com.github.L_Ender.cataclysm.entity.projectile.Lionfish_Spike_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererLionfish_Spike extends EntityRenderer<Lionfish_Spike_Entity> {

    public RendererLionfish_Spike(EntityRendererProvider.Context manager) {
        super(manager);
    }

    @Override
    public void render(Lionfish_Spike_Entity entity, float yaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light) {
        stack.pushPose();
        stack.pushPose();
        stack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
        stack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));
        stack.translate(0f, 0f, 0);
        Minecraft.getInstance().getItemRenderer().renderStatic(entity.getItem(), ItemTransforms.TransformType.GROUND, light, OverlayTexture.NO_OVERLAY, stack, buffer, entity.getId());
        stack.popPose();
        stack.popPose();
    }


    @Override
    public ResourceLocation getTextureLocation(Lionfish_Spike_Entity entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
