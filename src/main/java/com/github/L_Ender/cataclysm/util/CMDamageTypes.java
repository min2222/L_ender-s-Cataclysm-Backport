package com.github.L_Ender.cataclysm.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class CMDamageTypes {
    public static final ResourceKey<DamageType> LASER = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("cataclysm:laser"));
    public static final ResourceKey<DamageType> DEATHLASER = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("cataclysm:deathlaser"));
    public static final ResourceKey<DamageType> EMP = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("cataclysm:emp"));
    public static final ResourceKey<DamageType> ABYSSAL_BURN = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("cataclysm:abyssal_burn"));

    public static DamageSource causeLaserDamage(Entity attacker, LivingEntity caster) {
        return new DamageSource(attacker.level().registryAccess().registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(LASER), attacker, caster);
    }

    public static DamageSource causeDeathLaserDamage(Entity attacker, LivingEntity caster) {
        return new DamageSource(attacker.level().registryAccess().registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(DEATHLASER), attacker, caster);
    }

}
