package com.github.L_Ender.cataclysm.init;

import com.github.L_Ender.cataclysm.Cataclysm;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModGroup extends CreativeModeTab {

    public ModGroup() {
        super(Cataclysm.MODID);
    }

    @Override
    public ItemStack makeIcon() {
        return ModItems.THE_INCINERATOR.get().getDefaultInstance();
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> items) {
        super.fillItemList(items);
    }
}