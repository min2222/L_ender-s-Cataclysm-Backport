package com.github.L_Ender.cataclysm.util;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class CMDamageTypes {
    public static final DamageSource EMP = new DamageSource("cataclysm.emp").bypassArmor();
    public static final DamageSource ABYSSAL_BURN = new DamageSource("cataclysm.abyssal_burn").bypassArmor();
    public static final DamageSource DEATHLASER = new DamageSource("cataclysm.deathlaser").setProjectile().bypassArmor();
    public static final DamageSource SHREDDER = new DamageSource("cataclysm.shredder").setExplosion();

    public static DamageSource causeLaserDamage(Entity entity, @Nullable LivingEntity livingentity) {
        return new IndirectEntityDamageSource("cataclysm.laser", entity, livingentity).setProjectile();
    }
    
    public static DamageSource causeDeathLaserDamage(Entity entity, @Nullable LivingEntity livingentity) {
        return new IndirectEntityDamageSource("cataclysm.deathlaser", entity, livingentity);
    }

    public static DamageSource causeShredderDamage(LivingEntity attacker) {
        return new EntityDamageSource("cataclysm.shredder", attacker);
    }

}
