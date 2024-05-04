package com.github.L_Ender.cataclysm.jei;

import java.util.List;

import com.github.L_Ender.cataclysm.crafting.AltarOfAmethystRecipe;
import com.github.L_Ender.cataclysm.crafting.WeaponfusionRecipe;
import com.github.L_Ender.cataclysm.init.ModRecipeTypes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.crafting.RecipeManager;

public class CMRecipes
{
	private final RecipeManager recipeManager;

	public CMRecipes() {
		Minecraft minecraft = Minecraft.getInstance();
		ClientLevel level = minecraft.level;

		if (level != null) {
			this.recipeManager = level.getRecipeManager();
		} else {
			throw new NullPointerException("minecraft world must not be null.");
		}
	}

	public List<WeaponfusionRecipe> getWeaponfusionRecipes() {
		return recipeManager.getAllRecipesFor(ModRecipeTypes.WEAPON_FUSION.get()).stream().toList();
	}

	public List<AltarOfAmethystRecipe> getAmethystBlessRecipes() {
		return recipeManager.getAllRecipesFor(ModRecipeTypes.AMETHYST_BLESS.get()).stream().toList();
	}

}
