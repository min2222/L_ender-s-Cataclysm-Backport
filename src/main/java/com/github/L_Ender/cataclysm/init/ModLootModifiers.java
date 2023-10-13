package com.github.L_Ender.cataclysm.init;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.loot.AddItemModifier;
import com.github.L_Ender.cataclysm.loot.AddSusSandItemModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> DEF_REG =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Cataclysm.MODID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM =
            DEF_REG.register("add_item", AddItemModifier.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_SUS_SAND_ITEM =
            DEF_REG.register("add_sus_sand_item", AddSusSandItemModifier.CODEC);

}
