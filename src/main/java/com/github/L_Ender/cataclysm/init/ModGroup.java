package com.github.L_Ender.cataclysm.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModGroup {
	public static final CreativeModeTab ITEM = new CreativeModeTab("cataclysm.item") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModItems.THE_INCINERATOR.get());
		}
	};
	
	public static final CreativeModeTab BLOCK = new CreativeModeTab("cataclysm.block") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModItems.VOID_STONE.get());
		}
	};
}