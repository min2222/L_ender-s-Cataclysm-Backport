package com.github.L_Ender.cataclysm.mixin.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import net.minecraft.world.level.levelgen.Beardifier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawJunction;

@Mixin(Beardifier.class)
public interface BeardifierAccessor {
    @Accessor
    ObjectListIterator<Beardifier.Rigid> getPieceIterator();

    @Accessor
    ObjectListIterator<JigsawJunction> getJunctionIterator();
}
