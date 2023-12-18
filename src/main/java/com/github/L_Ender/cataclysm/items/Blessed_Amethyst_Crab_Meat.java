package com.github.L_Ender.cataclysm.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class Blessed_Amethyst_Crab_Meat extends Item {
    public Blessed_Amethyst_Crab_Meat(Properties p_41170_) {
        super(p_41170_);
    }

    public boolean isFoil(ItemStack p_41172_) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("item.cataclysm.blessed_amethyst_crab_meat.desc").withStyle(ChatFormatting.DARK_GREEN));
    }

}