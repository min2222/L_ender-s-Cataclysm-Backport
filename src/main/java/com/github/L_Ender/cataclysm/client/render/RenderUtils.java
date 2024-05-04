package com.github.L_Ender.cataclysm.client.render;


import com.github.L_Ender.lionfishapi.client.model.tools.AdvancedModelBox;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector4f;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class RenderUtils {
    public static void matrixStackFromCitadelModel(PoseStack matrixStack, AdvancedModelBox AdvancedModelBox) {
        AdvancedModelBox parent = AdvancedModelBox.getParent();
        if (parent != null) matrixStackFromCitadelModel(matrixStack, parent);
        AdvancedModelBox.translateAndRotate(matrixStack);
    }

    public static Vec3 matrixStackFromCitadelModel(Entity entity, float entityYaw, AdvancedModelBox modelRenderer) {
        PoseStack matrixStack = new PoseStack();
        matrixStack.translate(entity.getX(), entity.getY(), entity.getZ());
        matrixStack.mulPose(new Quaternion(0, -entityYaw + 180, 0, true));
        matrixStack.scale(-1, -1, 1);
        matrixStack.translate(0, -1.5f, 0);
        RenderUtils.matrixStackFromCitadelModel(matrixStack, modelRenderer);
        PoseStack.Pose matrixEntry = matrixStack.last();
        Matrix4f matrix4f = matrixEntry.pose();
        Vector4f vec = new Vector4f(0, 0, 0, 1);
        vec.transform(matrix4f);
        return new Vec3(vec.x(), vec.y(), vec.z());
    }
}
