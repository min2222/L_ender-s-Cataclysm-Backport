package com.github.L_Ender.cataclysm.init;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.world.feature.EndIceSpikeFeature;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Modfeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Cataclysm.MODID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> END_ICE_SPIKE_FEATURE = FEATURES.register("end_ice_spike_feature", () -> new EndIceSpikeFeature(NoneFeatureConfiguration.CODEC));

}
