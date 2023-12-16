package com.github.L_Ender.cataclysm.client.render.entity;

import com.github.L_Ender.cataclysm.client.model.entity.ModelThe_Leviathan_Tongue;
import com.github.L_Ender.cataclysm.client.model.entity.ModelThe_Leviathan_Tongue_End;
import com.github.L_Ender.cataclysm.entity.BossMonsters.The_Leviathan.The_Leviathan_Entity;
import com.github.L_Ender.cataclysm.entity.BossMonsters.The_Leviathan.The_Leviathan_Tongue_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class RendererThe_Leviathan_Tongue extends EntityRenderer<The_Leviathan_Tongue_Entity> {

    private static final ResourceLocation THE_LEVIATHAN_TEXTURES = new ResourceLocation("cataclysm:textures/entity/leviathan/the_leviathan.png");
    private static final ModelThe_Leviathan_Tongue TONGUE_MODEL = new ModelThe_Leviathan_Tongue();
    private static final ModelThe_Leviathan_Tongue_End TONGUE_END_MODEL = new ModelThe_Leviathan_Tongue_End();
    public static final int MAX_NECK_SEGMENTS = 128;

    public RendererThe_Leviathan_Tongue(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }

    public boolean shouldRender(The_Leviathan_Tongue_Entity grapple, Frustum f, double d1, double d2, double d3) {
        return true;
    }

    @Override
    public void render(The_Leviathan_Tongue_Entity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int light) {
        super.render(entity, yaw, partialTicks, poseStack, buffer, light);
        poseStack.pushPose();
        Entity fromEntity = entity.getFromEntity();
        float x = (float)Mth.lerp(partialTicks, entity.xo, entity.getX());
        float y = (float)Mth.lerp(partialTicks, entity.yo, entity.getY());
        float z = (float)Mth.lerp(partialTicks, entity.zo, entity.getZ());

        if (fromEntity != null) {
            float progress = (entity.prevProgress + (entity.getProgress() - entity.prevProgress) * partialTicks) / entity.getMaxExtendTime();
            Vec3 distVec = getPositionOfPriorMob(entity, fromEntity, partialTicks).subtract(x, y, z);
            Vec3 to = distVec.scale(1F - progress);
            Vec3 from = distVec;
            int segmentCount = 0;
            Vec3 currentNeckButt = from;
            VertexConsumer neckConsumer;

            neckConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(THE_LEVIATHAN_TEXTURES));
            double remainingDistance = to.distanceTo(from);
            while (segmentCount < MAX_NECK_SEGMENTS && remainingDistance > 0) {
                remainingDistance = Math.min(from.distanceTo(to), 0.5F);
                Vec3 linearVec = to.subtract(currentNeckButt);
                Vec3 powVec = new Vec3(modifyVecAngle(linearVec.x), modifyVecAngle(linearVec.y), modifyVecAngle(linearVec.z));
                Vec3 smoothedVec = powVec;
                Vec3 next = smoothedVec.normalize().scale(remainingDistance).add(currentNeckButt);
                int neckLight = getLightColor(entity, to.add(currentNeckButt).add(x, y, z));
                renderNeckCube(currentNeckButt, next, poseStack, neckConsumer, neckLight, OverlayTexture.NO_OVERLAY, 0);
                currentNeckButt = next;
                segmentCount++;
            }
            VertexConsumer clawConsumer;

            clawConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(THE_LEVIATHAN_TEXTURES));

            poseStack.pushPose();
            poseStack.translate(to.x, to.y, to.z);
            float rotY = (float) (Mth.atan2(to.x, to.z) * (double) (180F / (float) Math.PI));
            float rotX = (float) (-(Mth.atan2(to.y, to.horizontalDistance()) * (double) (180F / (float) Math.PI)));
            TONGUE_END_MODEL.setAttributes(rotX, rotY);
            TONGUE_END_MODEL.renderToBuffer(poseStack, clawConsumer, getLightColor(entity, to.add(x, y, z)), OverlayTexture.NO_OVERLAY, 1, 1F, 1, 1F);
            poseStack.popPose();

        }
        poseStack.popPose();
    }

    private Vec3 getPositionOfPriorMob(The_Leviathan_Tongue_Entity segment, Entity mob, float partialTicks){
        double d4 = Mth.lerp(partialTicks, mob.xo, mob.getX());
        double d5 = Mth.lerp(partialTicks, mob.yo, mob.getY());
        double d6 = Mth.lerp(partialTicks, mob.zo, mob.getZ());

        if(mob instanceof The_Leviathan_Entity leviathan && segment.isCreator(mob)){

            float f1 = -Mth.sin(leviathan.getYRot() * ((float)Math.PI / 180F)) * Mth.cos(leviathan.getXRot() * ((float)Math.PI / 180F));
            float f2 = -Mth.sin(leviathan.getXRot() * ((float)Math.PI / 180F));
            float f3 = Mth.cos(leviathan.getYRot() * ((float)Math.PI / 180F)) * Mth.cos(leviathan.getXRot() * ((float)Math.PI / 180F));



            d4 = Mth.lerp(partialTicks, leviathan.xo + f1 * 3.0, leviathan.getX() + f1 * 3.0);
            d5 = Mth.lerp(partialTicks, leviathan.yo + f2 * 3.5, leviathan.getY() + f2 * 3.5);
            d6 = Mth.lerp(partialTicks, leviathan.zo + f3 * 3.0, leviathan.getZ() + f3 * 3.0);

        }
        return new Vec3(d4, d5, d6);
    }


    public static void renderNeckCube(Vec3 from, Vec3 to, PoseStack poseStack, VertexConsumer buffer, int packedLightIn, int overlayCoords, float additionalYaw) {
        Vec3 sub = from.subtract(to);
        double d = sub.horizontalDistance();
        float rotY = (float) (Mth.atan2(sub.x, sub.z) * (double) (180F / (float) Math.PI));
        float rotX = (float) (-(Mth.atan2(sub.y, d) * (double) (180F / (float) Math.PI))) - 90.0F;
        poseStack.pushPose();
        poseStack.translate(from.x, from.y, from.z);
        TONGUE_MODEL.setAttributes((float) sub.length(), rotX, rotY, additionalYaw);
        TONGUE_MODEL.renderToBuffer(poseStack, buffer, packedLightIn, overlayCoords, 1, 1F, 1, 1);
        poseStack.popPose();
    }

    private double modifyVecAngle(double dimension) {
        float abs = (float) Math.abs(dimension);
        return Math.signum(dimension) * Mth.clamp(Math.pow(abs, 0.1), 0.05 * abs, abs);
    }

    private int getLightColor(Entity head, Vec3 vec3) {
        BlockPos blockpos = new BlockPos(vec3);
        if(head.level.hasChunkAt(blockpos)){
            int i = LevelRenderer.getLightColor(head.level, blockpos);
            int j = LevelRenderer.getLightColor(head.level, blockpos.above());
            int k = i & 255;
            int l = j & 255;
            int i1 = i >> 16 & 255;
            int j1 = j >> 16 & 255;
            return (k > l ? k : l) | (i1 > j1 ? i1 : j1) << 16;
        }else{
            return 0;
        }
    }

    @Override
    public ResourceLocation getTextureLocation(The_Leviathan_Tongue_Entity entity) {
        return THE_LEVIATHAN_TEXTURES;
    }

}