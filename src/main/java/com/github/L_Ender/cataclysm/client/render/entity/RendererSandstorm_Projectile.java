package com.github.L_Ender.cataclysm.client.render.entity;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.client.model.entity.Model_Sandstorm_Projectile;
import com.github.L_Ender.cataclysm.entity.projectile.Sandstorm_Projectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererSandstorm_Projectile extends EntityRenderer<Sandstorm_Projectile>
{
	private static final ResourceLocation SANDSTORM =new ResourceLocation(Cataclysm.MODID,"textures/entity/koboleton/sandstorm.png");

	public Model_Sandstorm_Projectile model;

	public RendererSandstorm_Projectile(EntityRendererProvider.Context manager) {
		super(manager);
		this.model = new Model_Sandstorm_Projectile();
	}

	@Override
	public void render(Sandstorm_Projectile entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		matrixStackIn.scale(-0.5F, -0.5F, 0.5F);
		matrixStackIn.translate(0F, -1.5F, 0F);
		float f = Mth.rotLerp(partialTicks, entityIn.yRotO, entityIn.getYRot());
		float f1 = Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot());
		VertexConsumer vertexconsumer = bufferIn.getBuffer(this.model.renderType(this.getTextureLocation(entityIn)));
		this.model.setupAnim(entityIn, 0.0F, 0.0F,entityIn.tickCount + partialTicks, f, f1);
		this.model.renderToBuffer(matrixStackIn, vertexconsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(Sandstorm_Projectile entity)
	{
		return SANDSTORM;
	}
}