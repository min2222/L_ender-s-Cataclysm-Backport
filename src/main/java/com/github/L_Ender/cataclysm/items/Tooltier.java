package com.github.L_Ender.cataclysm.items;

import java.util.List;
import java.util.Locale;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.init.ModItems;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;


public class Tooltier {

    private static ResourceLocation prefix(String name) {
        return new ResourceLocation(Cataclysm.MODID, name.toLowerCase(Locale.ROOT));
    }


    public static final Tier ANCIENT_METAL = TierSortingRegistry.registerTier(
            new ForgeTier(3, 750, 8F, 2, 25,
                    BlockTags.create(prefix("needs_ancient_metal_tool")), () -> Ingredient.of(ModItems.ANCIENT_METAL_INGOT.get())),
            prefix("ancient_metal"), List.of(Tiers.IRON), List.of(Tiers.DIAMOND));

    public static final Tier BLACK_STEEL = TierSortingRegistry.registerTier(
            new ForgeTier(3, 750, 8F, 2, 25,
                    BlockTags.create(prefix("needs_black_steel_tool")), () -> Ingredient.of(ModItems.ANCIENT_METAL_INGOT.get())),
            prefix("black_steel"), List.of(Tiers.IRON), List.of(Tiers.DIAMOND));

}



