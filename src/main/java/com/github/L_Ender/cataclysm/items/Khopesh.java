package com.github.L_Ender.cataclysm.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;

public class Khopesh extends SwordItem {

    public Khopesh(Tiers toolMaterial, Properties props) {
        super(toolMaterial, 4, -2.6f, props);
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return attacker.getRandom().nextBoolean();
    }

}
