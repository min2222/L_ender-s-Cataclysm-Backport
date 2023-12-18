package com.github.L_Ender.cataclysm.client.render.entity;


import com.github.L_Ender.cataclysm.client.model.entity.ModelAncient_Remnant;
import com.github.L_Ender.cataclysm.client.render.layer.Ancient_Remnant_Layer;
import com.github.L_Ender.cataclysm.entity.BossMonsters.Ancient_Remnant_Entity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererAncient_Remnant extends MobRenderer<Ancient_Remnant_Entity, ModelAncient_Remnant> {
    private static final ResourceLocation REMNANT_TEXTURES = new ResourceLocation("cataclysm:textures/entity/ancient_remnant/ancient_remnant.png");
    private final RandomSource rnd = RandomSource.create();

    public RendererAncient_Remnant(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelAncient_Remnant(), 1.5F);
        this.addLayer(new Ancient_Remnant_Layer(this));
    }


    public Vec3 getRenderOffset(Ancient_Remnant_Entity entityIn, float partialTicks) {
        if (entityIn.getAnimation() == Ancient_Remnant_Entity.REMNANT_DEATH){
            if(entityIn.getAnimationTick() <= 52 && entityIn.getAnimationTick() >= 43 || entityIn.getAnimationTick() <= 73 && entityIn.getAnimationTick() >= 43) {
                double d0 = 0.04D;
                return new Vec3(this.rnd.nextGaussian() * d0, 0.0D, this.rnd.nextGaussian() * d0);
            }
        }
        return super.getRenderOffset(entityIn, partialTicks);
    }

    @Override
    public ResourceLocation getTextureLocation(Ancient_Remnant_Entity entity) {
        return REMNANT_TEXTURES;
    }


    @Override
    protected float getFlipDegrees(Ancient_Remnant_Entity entity) {
        return 0;
    }

}

