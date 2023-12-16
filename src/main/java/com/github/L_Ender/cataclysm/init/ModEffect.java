package com.github.L_Ender.cataclysm.init;


import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.effects.EffectAbyssal_Burn;
import com.github.L_Ender.cataclysm.effects.EffectAbyssal_Curse;
import com.github.L_Ender.cataclysm.effects.EffectAbyssal_Fear;
import com.github.L_Ender.cataclysm.effects.EffectBlazing_Brand;
import com.github.L_Ender.cataclysm.effects.EffectBlessing_Of_Amethyst;
import com.github.L_Ender.cataclysm.effects.EffectBone_Fracture;
import com.github.L_Ender.cataclysm.effects.EffectCurse_Of_Desert;
import com.github.L_Ender.cataclysm.effects.EffectMonstrous;
import com.github.L_Ender.cataclysm.effects.EffectStun;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffect {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,
            Cataclysm.MODID);

    public static final RegistryObject<MobEffect> EFFECTMONSTROUS = EFFECTS.register("monstrous", EffectMonstrous::new);

    public static final RegistryObject<MobEffect> EFFECTBLAZING_BRAND = EFFECTS.register("blazing_brand", EffectBlazing_Brand::new);

    public static final RegistryObject<MobEffect> EFFECTSTUN = EFFECTS.register("stun", EffectStun::new);

    public static final RegistryObject<MobEffect> EFFECTABYSSAL_BURN = EFFECTS.register("abyssal_burn", EffectAbyssal_Burn::new);

    public static final RegistryObject<MobEffect> EFFECTBONE_FRACTURE = EFFECTS.register("bone_fracture", EffectBone_Fracture::new);

    public static final RegistryObject<MobEffect> EFFECTABYSSAL_FEAR = EFFECTS.register("abyssal_fear", EffectAbyssal_Fear::new);

    public static final RegistryObject<MobEffect> EFFECTABYSSAL_CURSE = EFFECTS.register("abyssal_curse", EffectAbyssal_Curse::new);

    public static final RegistryObject<MobEffect> EFFECTBLESSING_OF_AMETHYST = EFFECTS.register("blessing_of_amethyst", EffectBlessing_Of_Amethyst::new);

    public static final RegistryObject<MobEffect> EFFECTCURSE_OF_DESERT = EFFECTS.register("curse_of_desert", EffectCurse_Of_Desert::new);
}
