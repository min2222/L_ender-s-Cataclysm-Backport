package com.github.L_Ender.cataclysm.crafting;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.ItemStack;

public class AltarOfAmethystRecipeManager extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(AltarOfAmethystRecipe.class, new AltarOfAmethystRecipe.Deserializer()).create();

    private final List<AltarOfAmethystRecipe> altarRecipes = Lists.newArrayList();

    public AltarOfAmethystRecipeManager() {
        super(GSON, "altar_of_amethyst_recipes");
    }

    protected void apply(Map<ResourceLocation, JsonElement> jsonMap, ResourceManager resourceManager, ProfilerFiller profile) {
        this.altarRecipes.clear();
        ImmutableMap.Builder<ResourceLocation, AltarOfAmethystRecipe> builder = ImmutableMap.builder();
        Cataclysm.LOGGER.log(Level.ALL, "Loading in altar_of_amethyst_recipes jsons...");
        jsonMap.forEach((resourceLocation, jsonElement) -> {
            try {
                AltarOfAmethystRecipe capsidRecipe = GSON.fromJson(jsonElement, AltarOfAmethystRecipe.class);
                builder.put(resourceLocation, capsidRecipe);
            } catch (Exception exception) {
                Cataclysm.LOGGER.error("Couldn't parse altar of amethyst recipe {}", resourceLocation, exception);
            }
        });
        ImmutableMap<ResourceLocation, AltarOfAmethystRecipe> immutablemap = builder.build();
        immutablemap.forEach((resourceLocation, capsidRecipe) -> {
            altarRecipes.add(capsidRecipe);
        });
    }

    public AltarOfAmethystRecipe getRecipeFor(ItemStack stack){
        for(AltarOfAmethystRecipe recipe : altarRecipes){
            if(recipe.matches(stack)){
                return recipe;
            }
        }

        return null;
    }

    public List<AltarOfAmethystRecipe> getaltarRecipes() {
        return altarRecipes;
    }

    @Override
    public String getName() {
        return "AltarOfAmethystRecipeManager";
    }
}
