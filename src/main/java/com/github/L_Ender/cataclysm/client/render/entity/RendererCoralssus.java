package com.github.L_Ender.cataclysm.client.render.entity;


import com.github.L_Ender.cataclysm.client.model.entity.ModelCoralssus;
import com.github.L_Ender.cataclysm.entity.Deepling.Coralssus_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererCoralssus extends MobRenderer<Coralssus_Entity, ModelCoralssus> {

    private static final ResourceLocation CORALSSUS_TEXTURES = new ResourceLocation("cataclysm:textures/entity/deepling/coralssus.png");
    ;

    public RendererCoralssus(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelCoralssus(), 1.5F);

    }
    @Override
    public ResourceLocation getTextureLocation(Coralssus_Entity entity) {
        return CORALSSUS_TEXTURES;
    }

    @Override
    protected void scale(Coralssus_Entity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.75F, 1.75F, 1.75F);
    }
}

