package com.github.L_Ender.cataclysm.items;

import com.github.L_Ender.cataclysm.entity.projectile.Void_Scatter_Arrow_Entity;
import com.github.L_Ender.cataclysm.init.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ModItemArrow extends ArrowItem {
    public ModItemArrow(Properties group) {
        super(group);
    }

    public AbstractArrow createArrow(Level worldIn, ItemStack stack, LivingEntity shooter) {
        if(this == ModItems.VOID_SCATTER_ARROW.get()){
            Arrow arrowentity = new Void_Scatter_Arrow_Entity(worldIn, shooter);
            arrowentity.setEffectsFromItem(stack);
            return arrowentity;
        }else {
            return super.createArrow(worldIn, stack, shooter);
        }
    }

}
