package com.github.L_Ender.cataclysm.client.render.entity;


import com.github.L_Ender.cataclysm.client.model.entity.ModelIgnis;
import com.github.L_Ender.cataclysm.client.render.RenderUtils;
import com.github.L_Ender.cataclysm.client.render.layer.Ignis_Armor_Crack_Layer;
import com.github.L_Ender.cataclysm.client.render.layer.Ignis_Shield_Layer;
import com.github.L_Ender.cataclysm.entity.BossMonsters.Ignis_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererIgnis extends MobRenderer<Ignis_Entity, ModelIgnis> {

    private static final ResourceLocation IGNIS1_TEXTURES = new ResourceLocation("cataclysm:textures/entity/ignis/ignis_idle1.png");
    private static final ResourceLocation IGNIS2_TEXTURES = new ResourceLocation("cataclysm:textures/entity/ignis/ignis_idle2.png");
    private static final ResourceLocation IGNIS3_TEXTURES = new ResourceLocation("cataclysm:textures/entity/ignis/ignis_idle3.png");
    private static final ResourceLocation IGNIS4_TEXTURES = new ResourceLocation("cataclysm:textures/entity/ignis/ignis_idle4.png");

    private static final ResourceLocation IGNIS_SOUL1_TEXTURES = new ResourceLocation("cataclysm:textures/entity/ignis/ignis_soul_idle1.png");
    private static final ResourceLocation IGNIS_SOUL2_TEXTURES = new ResourceLocation("cataclysm:textures/entity/ignis/ignis_soul_idle2.png");
    private static final ResourceLocation IGNIS_SOUL3_TEXTURES = new ResourceLocation("cataclysm:textures/entity/ignis/ignis_soul_idle3.png");
    private static final ResourceLocation IGNIS_SOUL4_TEXTURES = new ResourceLocation("cataclysm:textures/entity/ignis/ignis_soul_idle4.png");

    public RendererIgnis(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelIgnis(), 1.0F);
        this.addLayer(new Ignis_Armor_Crack_Layer(this));
        this.addLayer(new Ignis_Shield_Layer(this));
    }
    @Override
    public ResourceLocation getTextureLocation(Ignis_Entity entity) {
        return getIdleTexture(entity,entity.tickCount % 9);
    }

    private ResourceLocation getIdleTexture(Ignis_Entity entity,int age) {
        if (age < 3) {
            return entity.getBossPhase() > 0 ? IGNIS_SOUL1_TEXTURES : IGNIS1_TEXTURES;
        } else if (age < 6) {
            return entity.getBossPhase() > 0 ? IGNIS_SOUL2_TEXTURES : IGNIS2_TEXTURES;
        } else if (age < 9) {
            return entity.getBossPhase() > 0 ? IGNIS_SOUL3_TEXTURES : IGNIS3_TEXTURES;
        } else if (age < 12) {
            return entity.getBossPhase() > 0 ? IGNIS_SOUL4_TEXTURES : IGNIS4_TEXTURES;
        } else {
            return entity.getBossPhase() > 0 ? IGNIS_SOUL1_TEXTURES : IGNIS1_TEXTURES;
        }
    }

    @Override
    public void render(Ignis_Entity entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        if (entity.getAnimation() == Ignis_Entity.HORIZONTAL_SWING_ATTACK
                || entity.getAnimation() == Ignis_Entity.SWING_ATTACK
                || entity.getAnimation() == Ignis_Entity.HORIZONTAL_SWING_ATTACK_SOUL
                || entity.getAnimation() == Ignis_Entity.SWING_ATTACK_SOUL
                || entity.getAnimation() == Ignis_Entity.SWING_ATTACK_BERSERK
                || entity.getAnimation() == Ignis_Entity.REINFORCED_SMASH_IN_AIR
                || entity.getAnimation() == Ignis_Entity.REINFORCED_SMASH_IN_AIR_SOUL
                || entity.getAnimation() == Ignis_Entity.PHASE_3
                || entity.getAnimation() == Ignis_Entity.SPIN_ATTACK
                || entity.getAnimation() == Ignis_Entity.ULTIMATE_ATTACK
                || entity.getAnimation() == Ignis_Entity.STRIKE
                || entity.getAnimation() == Ignis_Entity.COMBO1
                || entity.getAnimation() == Ignis_Entity.COMBO2
                || entity.getAnimation() == Ignis_Entity.SHIELD_BREAK_STRIKE
                || entity.getAnimation() == Ignis_Entity.HORIZONTAL_SMALL_SWING_ATTACK
                || entity.getAnimation() == Ignis_Entity.HORIZONTAL_SMALL_SWING_ALT_ATTACK2
                || entity.getAnimation() == Ignis_Entity.SWING_UPPERSLASH) {
            Vec3 bladePos = RenderUtils.getWorldPosFromModel(entity, entityYaw, model.blade2);
            entity.setSocketPosArray(0, bladePos);
        }
    }

    protected int getBlockLightLevel(Ignis_Entity entityIn, BlockPos pos) {
        return 15;
    }

    @Override
    protected float getFlipDegrees(Ignis_Entity entity) {
        return 0;
    }

}