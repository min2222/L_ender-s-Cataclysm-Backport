package com.github.L_Ender.cataclysm.items.CuriosItem;

import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public abstract class ArtifactItem extends Item {

    public ArtifactItem(Item.Properties p_41170_) {
        super(p_41170_);
    }

    public ArtifactItem() {
        this(new Properties());
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(13 - stack.getDamageValue() * 13F / getMaxDamage(stack));
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return Mth.hsvToRgb(Math.max(0, (getMaxDamage(stack) - stack.getDamageValue()) / (float) getMaxDamage(stack)) / 3, 1, 1);
    }

    @Override
    public boolean canBeDepleted() {
        return getMaxDamage(ItemStack.EMPTY) > 0;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getMaxDamage(stack) > 0;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment != Enchantments.MENDING && super.canApplyAtEnchantingTable(stack, enchantment);
    }
}
