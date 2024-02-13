package com.github.L_Ender.cataclysm.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.StructureManager;

@Mixin(StructureManager.class)
public interface StructureManagerAccessor {
    @Accessor
    LevelAccessor getLevel();
}
