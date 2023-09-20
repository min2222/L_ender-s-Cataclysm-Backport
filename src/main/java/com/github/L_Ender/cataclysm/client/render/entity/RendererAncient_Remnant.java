package com.github.L_Ender.cataclysm.client.render.entity;


import com.github.L_Ender.cataclysm.client.model.entity.ModelAncient_Remnant;
import com.github.L_Ender.cataclysm.entity.BossMonsters.Ancient_Remnant_Entity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererAncient_Remnant extends MobRenderer<Ancient_Remnant_Entity, ModelAncient_Remnant> {

    private static final ResourceLocation LEVIATHAN_TEXTURES = new ResourceLocation("cataclysm:textures/entity/ancient_remnant/ancient_remnant.png");

    public RendererAncient_Remnant(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelAncient_Remnant(), 1.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Ancient_Remnant_Entity entity) {
        return LEVIATHAN_TEXTURES;
    }


}

