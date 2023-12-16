package com.github.L_Ender.cataclysm.client.model.armor;

import com.github.L_Ender.cataclysm.client.model.item.CuriosModel.Sandstorm_In_A_BottleModel;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;

@OnlyIn(Dist.CLIENT)
public class CMModelLayers {

    public static final ModelLayerLocation ELYTRA_ARMOR = createLocation("elytra_armor", "main");
    public static final ModelLayerLocation MONSTROUS_HELM = createLocation("monstrous", "main");
    public static final ModelLayerLocation IGNITIUM_ARMOR_MODEL = createLocation("ignitium_armor_model", "main");
    public static final ModelLayerLocation IGNITIUM_ARMOR_MODEL_LEGS = createLocation("ignitium_armor_model_leg", "main");
    public static final ModelLayerLocation BLOOM_STONE_PAULDRONS_MODEL = createLocation("bloom_stone_pauldrons_model", "main");
    public static final ModelLayerLocation SANDSTORM_IN_A_BOTTLE_MODEL = createLocation("sandstorm_in_a_bottle_model", "main");


    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MONSTROUS_HELM, () -> ModelMonstrousHelm.createArmorLayer(new CubeDeformation(0.3F)));
        event.registerLayerDefinition(IGNITIUM_ARMOR_MODEL, () -> ModelIgnitium_Armor.createArmorLayer(new CubeDeformation(0.5F)));
        event.registerLayerDefinition(BLOOM_STONE_PAULDRONS_MODEL, () -> ModelBloom_Stone_Pauldrons.createArmorLayer(new CubeDeformation(0.5F)));
        event.registerLayerDefinition(ELYTRA_ARMOR, () -> Modelignitium_Elytra_chestplate.createArmorLayer(new CubeDeformation(0.5F)));
        event.registerLayerDefinition(IGNITIUM_ARMOR_MODEL_LEGS, () -> ModelIgnitium_Armor.createArmorLayer(new CubeDeformation(0.2F)));
        event.registerLayerDefinition(SANDSTORM_IN_A_BOTTLE_MODEL, () -> Sandstorm_In_A_BottleModel.createLayer(new CubeDeformation(0.2F)));
    }

    private static ModelLayerLocation createLocation(String model, String layer) {
        return new ModelLayerLocation(new ResourceLocation("cataclysm", model), layer);
    }
}
