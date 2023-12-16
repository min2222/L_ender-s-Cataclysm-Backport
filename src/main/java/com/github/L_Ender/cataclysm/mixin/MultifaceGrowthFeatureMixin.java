package com.github.L_Ender.cataclysm.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.L_Ender.cataclysm.init.ModTag;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.MultifaceGrowthFeature;
import net.minecraft.world.level.levelgen.structure.Structure;

@Mixin(MultifaceGrowthFeature.class)
public class MultifaceGrowthFeatureMixin {

    @Inject(
            method = {"Lnet/minecraft/world/level/levelgen/feature/MultifaceGrowthFeature;place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z"},
            cancellable = true,
            at = @At(value = "HEAD")
    )
    private void ac_place(FeaturePlaceContext context, CallbackInfoReturnable<Boolean> cir) {
        if(!(context.level() instanceof WorldGenRegion)) {
            return;
        }

        Registry<Structure> configuredStructureFeatureRegistry = context.level().registryAccess().registryOrThrow(Registry.STRUCTURE_REGISTRY);
        StructureManager structureManager = ((WorldGenRegionAccessor)context.level()).getStructureManager();
        for (Holder<Structure> configuredStructureFeature : configuredStructureFeatureRegistry.getOrCreateTag(ModTag.EYE_OF_ABYSS_LOCATED)) {
            if (structureManager.getStructureAt(context.origin(), configuredStructureFeature.value()).isValid()) {
                cir.setReturnValue(false);
                return;
            }
        }
    }
}
