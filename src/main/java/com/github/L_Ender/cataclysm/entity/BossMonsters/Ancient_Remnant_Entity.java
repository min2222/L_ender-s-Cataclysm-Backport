package com.github.L_Ender.cataclysm.entity.BossMonsters;

import com.github.L_Ender.cataclysm.config.CMConfig;
import com.github.L_Ender.cataclysm.entity.BossMonsters.AI.SimpleAnimationGoal;
import com.github.L_Ender.cataclysm.entity.effect.Cm_Falling_Block_Entity;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import com.github.L_Ender.cataclysm.entity.etc.CMPathNavigateGround;
import com.github.L_Ender.cataclysm.entity.etc.SmartBodyHelper2;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModSounds;
import com.github.L_Ender.cataclysm.init.ModTag;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.List;


public class Ancient_Remnant_Entity extends Boss_monster {

    public static final Animation REMNANT_BITE1 = Animation.create(69);
    public static final Animation REMNANT_BITE2 = Animation.create(65);
    public static final Animation REMNANT_CHARGE_PREPARE = Animation.create(125);
    public static final Animation REMNANT_TAIL_ATTACK1 = Animation.create(57);
    public static final Animation REMNANT_TAIL_ATTACK2 = Animation.create(57);
    public static final Animation REMNANT_LEFT_STOMP = Animation.create(49);
    private static final EntityDataAccessor<Boolean> CHARGE = SynchedEntityData.defineId(Ancient_Remnant_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> MODE_CHANCE = SynchedEntityData.defineId(Ancient_Remnant_Entity.class, EntityDataSerializers.INT);
    private AttackMode mode = AttackMode.CIRCLE;
    public float chargeProgress;
    public float prevchargeProgress;
    private int hunting_cooldown = 160;
    public int frame;
    public static final int MINE_COOLDOWN = 100;
    public Ancient_Remnant_Entity(EntityType entity, Level world) {
        super(entity, world);
        this.xpReward = 15;
        this.setMaxUpStep(1.5F);
        this.setPathfindingMalus(BlockPathTypes.UNPASSABLE_RAIL, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        setConfigattribute(this, CMConfig.AncientRemnantHealthMultiplier, CMConfig.AncientRemnantDamageMultiplier);
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{
                NO_ANIMATION,
                REMNANT_BITE1,
                REMNANT_CHARGE_PREPARE,
                REMNANT_BITE2,
                REMNANT_TAIL_ATTACK1,
                REMNANT_LEFT_STOMP};
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new RemnantAttackGoal(this));
        this.goalSelector.addGoal(0, new RemnantChargeAttackGoal(this, REMNANT_CHARGE_PREPARE));
        this.goalSelector.addGoal(0, new RemnantAnimationAttackGoal(this, REMNANT_BITE1,29));
        this.goalSelector.addGoal(0, new RemnantAnimationAttackGoal(this, REMNANT_BITE2,25));
        this.goalSelector.addGoal(0, new RemnantAnimationAttackGoal(this, REMNANT_LEFT_STOMP,24));
        this.goalSelector.addGoal(0, new RemnantAnimationAttackGoal(this, REMNANT_TAIL_ATTACK1,13));

        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D, 80));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Sniffer.class, true));
    }

    public static AttributeSupplier.Builder ancient_ramant() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 70.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.33F)
                .add(Attributes.ATTACK_DAMAGE, 20)
                .add(Attributes.MAX_HEALTH, 400)
                .add(Attributes.ARMOR, 10)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0);
    }

    protected int decreaseAirSupply(int air) {
        return air;
    }

    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    public boolean causeFallDamage(float p_148711_, float p_148712_, DamageSource p_148713_) {
        return false;
    }


    @Override
    public ItemEntity spawnAtLocation(ItemStack stack) {
        ItemEntity itementity = this.spawnAtLocation(stack,0.0f);
        if (itementity != null) {
            itementity.setGlowingTag(true);
            itementity.setExtendedLifetime();
        }
        return itementity;
    }


    @Override
    public boolean hurt(DamageSource source, float damage) {
        if (!source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            damage = Math.min(CMConfig.AncientRemnantDamageCap, damage);
        }
        double range = calculateRange(source);

        if (range > CMConfig.AncientRemnantLongRangelimit * CMConfig.AncientRemnantLongRangelimit) {
            return false;
        }

        return super.hurt(source, damage);
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CHARGE, false);
        this.entityData.define(MODE_CHANCE, 0);
    }


    public int getModeChance() {
        return this.entityData.get(MODE_CHANCE);
    }
    public void setModeChance(int chance) {
        this.entityData.set(MODE_CHANCE, chance);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
    }

    public void setIsCharge(boolean isAnger) {
        this.entityData.set(CHARGE, isAnger);
    }

    public boolean getIsCharge() {
        return this.entityData.get(CHARGE);
    }

    public void tick() {
        super.tick();
       // setYRot(yBodyRot);
        AnimationHandler.INSTANCE.updateAnimations(this);
        prevchargeProgress = chargeProgress;
        if (this.getIsCharge() && chargeProgress < 3F) {
            chargeProgress++;
        }
        if (!this.getIsCharge() && chargeProgress > 0F) {
            chargeProgress--;
        }

        if (hunting_cooldown > 0) {
            hunting_cooldown--;
        }
      //  if(this.getAnimation() == NO_ANIMATION) setAnimation(REMNANT_LEFT_STOMP);
        Charge();
        frame++;
        float moveX = (float) (getX() - xo);
        float moveZ = (float) (getZ() - zo);
        float speed = Mth.sqrt(moveX * moveX + moveZ * moveZ);
        if (!this.isSilent() && frame % 8 == 1 && speed > 0.05 && this.getIsCharge() && this.onGround()) {
            this.playSound(ModSounds.REMNANT_CHARGE_STEP.get(), 1F, 1.0f);
            ScreenShake_Entity.ScreenShake(level(), this.position(), 30, 0.1f, 0, 10);
        }
       // if (!this.isSilent() && frame % 16 == 1 && speed > 0.05 && !this.getIsCharge()) {
       //     this.playSound(ModSounds.REMNANT_CHARGE_STEP.get(), 1F, 1.0f);
        //    ScreenShake_Entity.ScreenShake(level(), this.position(), 30, 0.02f, 0, 10);
       // }
    }

    private void Charge() {
        if (getIsCharge()) {
            if (!this.level().isClientSide) {
                if(CMConfig.AncientRemnantBlockBreaking) {
                    ChargeBlockBreaking();
                }else{
                    if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level(), this)) {
                        ChargeBlockBreaking();
                    }
                }
            }
            if (this.tickCount % 4 == 0) {
                for (LivingEntity Lentity : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(1.5D))) {
                    if (!isAlliedTo(Lentity) && !(Lentity instanceof Ancient_Remnant_Entity) && Lentity != this) {
                        boolean flag = Lentity.hurt(this.damageSources().mobAttack(this), (float) ((float) this.getAttributeValue(Attributes.ATTACK_DAMAGE) * 1.5f + Math.min(this.getAttributeValue(Attributes.ATTACK_DAMAGE) * 1.5f, Lentity.getMaxHealth() * CMConfig.HarbingerChargeHpDamage)));
                        if (flag) {
                            if (Lentity.onGround()) {
                                double d0 = Lentity.getX() - this.getX();
                                double d1 = Lentity.getZ() - this.getZ();
                                double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
                                float f = 1.5F;
                                Lentity.push(d0 / d2 * f, 0.5F, d1 / d2 * f);
                            }
                        }
                    }
                }
            }
        }
    }


    private void ChargeBlockBreaking(){
        boolean flag = false;
        AABB aabb = this.getBoundingBox().inflate(0.5D, 0.2D, 0.5D);
        for (BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(this.getY()), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
            BlockState blockstate = this.level().getBlockState(blockpos);
            if (blockstate != Blocks.AIR.defaultBlockState() && blockstate.canEntityDestroy(this.level(), blockpos, this) && !blockstate.is(ModTag.REMNANT_IMMUNE) && net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(this, blockpos, blockstate)) {
                if (random.nextInt(6) == 0 && !blockstate.hasBlockEntity()) {
                    Cm_Falling_Block_Entity fallingBlockEntity = new Cm_Falling_Block_Entity(level(), blockpos.getX() + 0.5D, blockpos.getY() + 0.5D, blockpos.getZ() + 0.5D, blockstate, 20);
                    flag = this.level().destroyBlock(blockpos, false, this) || flag;
                    fallingBlockEntity.setDeltaMovement(fallingBlockEntity.getDeltaMovement().add(this.position().subtract(fallingBlockEntity.position()).multiply((-1.2D + random.nextDouble()) / 3, 0.2D + getRandom().nextGaussian() * 0.15D, (-1.2D + random.nextDouble()) / 3)));
                    level().addFreshEntity(fallingBlockEntity);
                } else {
                    flag = this.level().destroyBlock(blockpos, false, this) || flag;
                }
            }


        }
    }

    public void aiStep() {
        super.aiStep();

        if(this.getAnimation() == REMNANT_BITE1){
            if(this.getAnimationTick() == 5){
                this.level().playSound((Player) null, this, ModSounds.REMNANT_BITE.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
            }
            if(this.getAnimationTick() == 28){
                AreaAttack(8,8,70,1.0f,0.05f,160,0);
            }
        }

        if(this.getAnimation() == REMNANT_BITE2){
            if(this.getAnimationTick() == 1){
                this.level().playSound((Player) null, this, ModSounds.REMNANT_BITE.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
            }
            if(this.getAnimationTick() == 24){
                AreaAttack(8,8,70,1.0f,0.05f,160,0);
            }
        }


        if(this.getAnimation() == REMNANT_TAIL_ATTACK1){
            if(this.getAnimationTick() == 16){
                TailAreaAttack(8,8,1.05F,120,1.0f,0.05f,200,100);
            }
        }

        if(this.getAnimation() == REMNANT_CHARGE_PREPARE){
            if(this.getAnimationTick() == 1){
                this.level().playSound((Player) null, this, ModSounds.REMNANT_CHARGE_PREPARE.get(), SoundSource.HOSTILE, 3.0f, 1.0f);
            }

            if(this.getAnimationTick() == 15){
                ScreenShake_Entity.ScreenShake(level(), this.position(), 30, 0.1f, 0, 10);
                StompParticle(-0.1f,-0.75f);
            }

            if(this.getAnimationTick() == 41){
                ScreenShake_Entity.ScreenShake(level(), this.position(), 30, 0.1f, 0, 10);
                StompParticle(-0.1f,0.75f);
            }

            if(this.getAnimationTick() == 62){
                this.level().playSound((Player) null, this, ModSounds.REMNANT_CHARGE_ROAR.get(), SoundSource.HOSTILE, 3.0f, 1.0f);
            }
            if(this.getAnimationTick() == 132) {
                this.level().playSound((Player) null, this, ModSounds.REMNANT_BREATHING.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
            }
        }
    }

    private void AreaAttack(float range, float height, float arc, float damage, float hpdamage, int shieldbreakticks, int stunticks) {
        List<LivingEntity> entitiesHit = this.getEntityLivingBaseNearby(range, height, range, range);
        for (LivingEntity entityHit : entitiesHit) {
            float entityHitAngle = (float) ((Math.atan2(entityHit.getZ() - this.getZ(), entityHit.getX() - this.getX()) * (180 / Math.PI) - 90) % 360);
            float entityAttackingAngle = this.yBodyRot % 360;
            if (entityHitAngle < 0) {
                entityHitAngle += 360;
            }
            if (entityAttackingAngle < 0) {
                entityAttackingAngle += 360;
            }
            float entityRelativeAngle = entityHitAngle - entityAttackingAngle;
            float entityHitDistance = (float) Math.sqrt((entityHit.getZ() - this.getZ()) * (entityHit.getZ() - this.getZ()) + (entityHit.getX() - this.getX()) * (entityHit.getX() - this.getX()));
            if (entityHitDistance <= range && (entityRelativeAngle <= arc / 2 && entityRelativeAngle >= -arc / 2) || (entityRelativeAngle >= 360 - arc / 2 || entityRelativeAngle <= -360 + arc / 2)) {
                if (!isAlliedTo(entityHit) && !(entityHit instanceof Ancient_Remnant_Entity) && entityHit != this) {
                    boolean flag = entityHit.hurt(this.damageSources().mobAttack(this), (float) (this.getAttributeValue(Attributes.ATTACK_DAMAGE) * damage + Math.min(this.getAttributeValue(Attributes.ATTACK_DAMAGE) * damage, entityHit.getMaxHealth() * hpdamage) ));
                    if (entityHit instanceof Player && entityHit.isBlocking() && shieldbreakticks > 0) {
                        disableShield(entityHit, shieldbreakticks);
                    }

                    if (flag) {
                        if (stunticks > 0) {
                            entityHit.addEffect(new MobEffectInstance(ModEffect.EFFECTSTUN.get(), stunticks));
                        }
                    }

                }
            }
        }
    }


    private void TailAreaAttack(float range, float height, float height2 , float arc, float damage, float hpdamage, int shieldbreakticks, int stunticks) {
        List<LivingEntity> entitiesHit = this.getTailEntityLivingBaseNearby(range, height,height2, range, range);
        for (LivingEntity entityHit : entitiesHit) {
            float entityHitAngle = (float) ((Math.atan2(entityHit.getZ() - this.getZ(), entityHit.getX() - this.getX()) * (180 / Math.PI) - 90) % 360);
            float entityAttackingAngle = this.yBodyRot % 360;
            if (entityHitAngle < 0) {
                entityHitAngle += 360;
            }
            if (entityAttackingAngle < 0) {
                entityAttackingAngle += 360;
            }
            float entityRelativeAngle = entityHitAngle - entityAttackingAngle;
            float entityHitDistance = (float) Math.sqrt((entityHit.getZ() - this.getZ()) * (entityHit.getZ() - this.getZ()) + (entityHit.getX() - this.getX()) * (entityHit.getX() - this.getX()));
            if (entityHitDistance <= range && (entityRelativeAngle <= arc / 2 && entityRelativeAngle >= -arc / 2) || (entityRelativeAngle >= 360 - arc / 2 || entityRelativeAngle <= -360 + arc / 2)) {
                if (!isAlliedTo(entityHit) && !(entityHit instanceof Ancient_Remnant_Entity) && entityHit != this) {
                    boolean flag = entityHit.hurt(this.damageSources().mobAttack(this), (float) (this.getAttributeValue(Attributes.ATTACK_DAMAGE) * damage + Math.min(this.getAttributeValue(Attributes.ATTACK_DAMAGE) * damage, entityHit.getMaxHealth() * hpdamage) ));
                    if (entityHit instanceof Player && entityHit.isBlocking() && shieldbreakticks > 0) {
                        disableShield(entityHit, shieldbreakticks);
                    }

                    if (flag) {
                        if (stunticks > 0) {
                            entityHit.addEffect(new MobEffectInstance(ModEffect.EFFECTSTUN.get(), stunticks));
                        }
                        double d0 = entityHit.getX() - this.getX();
                        double d1 = entityHit.getZ() - this.getZ();
                        double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
                        entityHit.push(d0 / d2 * 4.0D, 0.2D, d1 / d2 * 4.0D);
                    }

                }
            }
        }
    }



    public  List<LivingEntity> getTailEntityLivingBaseNearby(double distanceX, double distanceMinY, double distanceMaxY,double distanceZ, double radius) {
        return getTailEntitiesNearby(LivingEntity.class, distanceX, distanceMinY,distanceMaxY, distanceZ, radius);
    }

    public <T extends Entity> List<T> getTailEntitiesNearby(Class<T> entityClass, double dX, double dY, double pY,double dZ, double r) {
        return level().getEntitiesOfClass(entityClass, new AABB(this.getX() - dX, this.getY() - dY, this.getZ() - dZ, this.getX() + dX, this.getY() + pY, this.getZ() + dZ), e -> e != this && distanceTo(e) <= r + e.getBbWidth() / 2f && e.getY() <= getY() + dY);
    }

    private void StompParticle(float vec, float math) {
        if (this.level().isClientSide) {
            for (int i1 = 0; i1 < 80 + random.nextInt(12); i1++) {
                double DeltaMovementX = getRandom().nextGaussian() * 0.07D;
                double DeltaMovementY = getRandom().nextGaussian() * 0.07D;
                double DeltaMovementZ = getRandom().nextGaussian() * 0.07D;
                float angle = (0.01745329251F * this.yBodyRot) + i1;
                float f = Mth.cos(this.yBodyRot * ((float)Math.PI / 180F)) ;
                float f1 = Mth.sin(this.yBodyRot * ((float)Math.PI / 180F)) ;
                double extraX = 0.5 * Mth.sin((float) (Math.PI + angle));
                double extraY = 0.3F;
                double extraZ = 0.5 * Mth.cos(angle);
                double theta = (yBodyRot) * (Math.PI / 180);
                theta += Math.PI / 2;
                double vecX = Math.cos(theta);
                double vecZ = Math.sin(theta);
                int hitX = Mth.floor(getX() + vec * vecX+ extraX);
                int hitY = Mth.floor(getY());
                int hitZ = Mth.floor(getZ() + vec * vecZ + extraZ);
                BlockPos hit = new BlockPos(hitX, hitY, hitZ);
                BlockState block = level().getBlockState(hit.below());
                this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, block), getX() + vec * vecX + extraX + f * math, this.getY() + extraY, getZ() + vec * vecZ + extraZ + f1 * math, DeltaMovementX, DeltaMovementY, DeltaMovementZ);

            }
        }
    }

    public boolean isAlliedTo(Entity entityIn) {
        if (entityIn == this) {
            return true;
        } else if (super.isAlliedTo(entityIn)) {
            return true;
        } else if (entityIn instanceof Ancient_Remnant_Entity) {
            return this.getTeam() == null && entityIn.getTeam() == null;
        } else {
            return false;
        }
    }



    protected SoundEvent getAmbientSound() {
        return super.getAmbientSound();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return super.getHurtSound(damageSourceIn);
    }

    protected SoundEvent getDeathSound() {
        return super.getDeathSound();
    }

    @Override
    protected void onDeathAIUpdate() {
        super.onDeathAIUpdate();

    }


    @Override
    protected BodyRotationControl createBodyControl() {
        return new SmartBodyHelper2(this);
    }

    protected PathNavigation createNavigation(Level worldIn) {
        return new CMPathNavigateGround(this, worldIn);
    }

    @Override
    protected void repelEntities(float x, float y, float z, float radius) {
        super.repelEntities(x, y, z, radius);
    }

    @Override
    public boolean canBePushedByEntity(Entity entity) {
        return false;
    }

    private enum AttackMode {
        CIRCLE,
        MELEE,
        RANGE
    }


    static class RemnantChargeAttackGoal extends SimpleAnimationGoal<Ancient_Remnant_Entity> {

        public RemnantChargeAttackGoal(Ancient_Remnant_Entity entity, Animation animation) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
        }

        public void start() {
            entity.getNavigation().stop();
            LivingEntity target = entity.getTarget();
            if (target != null) {
                this.entity.lookAt(target, 30.0F, 30.0F);
                entity.getLookControl().setLookAt(target, 30, 30);
            }
            super.start();
        }

        public void stop() {
            super.stop();
            entity.hunting_cooldown = MINE_COOLDOWN;
            LivingEntity target = entity.getTarget();
            if (target != null) {
                this.entity.lookAt(target, 30.0F, 30.0F);
                entity.getLookControl().setLookAt(target, 30, 30);
            }
        }

        public void tick() {
            LivingEntity target = entity.getTarget();
            if(this.entity.getAnimationTick() < 62 && target !=null){
                this.entity.lookAt(target, 30.0F, 30.0F);
                this.entity.getLookControl().setLookAt(target, 30f, 30f);
            }else{
                entity.setYRot(entity.yRotO);
            }
            if(this.entity.getAnimationTick() < 122 && this.entity.getAnimationTick() > 62 && this.entity.onGround()){
                Vec3 vector3d = entity.getDeltaMovement();
                float f = entity.getYRot() * ((float)Math.PI / 180F);
                Vec3 vector3d1 = new Vec3(-Mth.sin(f), entity.getDeltaMovement().y, Mth.cos(f)).scale(1.0D).add(vector3d.scale(0.5D));
                entity.setDeltaMovement(vector3d1.x, entity.getDeltaMovement().y, vector3d1.z);
            }


            if(this.entity.getAnimationTick() == 62){
                this.entity.setIsCharge(true);
            }

            if(this.entity.getAnimationTick() == 122){
                this.entity.setIsCharge(false);
            }

        }
    }


    static class RemnantAnimationAttackGoal extends SimpleAnimationGoal<Ancient_Remnant_Entity> {
        private final int look;

        public RemnantAnimationAttackGoal(Ancient_Remnant_Entity entity, Animation animation,int look) {
            super(entity, animation);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
            this.look =look;
        }

        public void start() {
            entity.getNavigation().stop();
            LivingEntity target = entity.getTarget();
            if (target != null) {
                this.entity.lookAt(target, 30.0F, 30.0F);
                entity.getLookControl().setLookAt(target, 30, 30);
            }
            super.start();
        }

        public void tick() {
            LivingEntity target = entity.getTarget();
            if(this.entity.getAnimationTick() < look && target !=null){
                this.entity.lookAt(target, 30.0F, 30.0F);
                this.entity.getLookControl().setLookAt(target, 30f, 30f);
            }else{
                entity.setYRot(entity.yRotO);
            }
        }
    }

    static class RemnantAttackGoal extends Goal {
        private final Ancient_Remnant_Entity mob;
        private LivingEntity target;
        private float circlingTime = 0;
        private float MeleeModeTime = 0;
        private static final int MELEE_MODE_TIME = 160;
        private float circleDistance = 18;
        private boolean clockwise = false;

        public RemnantAttackGoal(Ancient_Remnant_Entity mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            this.target = this.mob.getTarget();
            return this.target != null && target.isAlive() && this.mob.getAnimation() == NO_ANIMATION;
        }

        public boolean canContinueToUse() {
            this.target = this.mob.getTarget();
            return this.target != null;
        }

        public void start() {
            this.mob.mode = Ancient_Remnant_Entity.AttackMode.CIRCLE;
            circlingTime = 0;
            MeleeModeTime = 0;
            circleDistance = 18 + this.mob.random.nextInt(10);
            clockwise = this.mob.random.nextBoolean();
            this.mob.setAggressive(true);
        }

        public void stop() {
            this.mob.mode = Ancient_Remnant_Entity.AttackMode.CIRCLE;
            circlingTime = 0;
            MeleeModeTime = 0;
            circleDistance = 18 + this.mob.random.nextInt(10);
            clockwise = this.mob.random.nextBoolean();
            this.target = this.mob.getTarget();
            if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(target)) {
                this.mob.setTarget(null);
            }

            this.mob.getNavigation().stop();
            if (this.mob.getTarget() == null) {
                this.mob.setAggressive(false);
                this.mob.getNavigation().stop();
            }
        }


        public void tick() {
            LivingEntity target = this.mob.getTarget();
            if (target != null) {
                if (this.mob.mode == Ancient_Remnant_Entity.AttackMode.CIRCLE) {
                    this.mob.lookAt(target, 30.0F, 30.0F);
                    BlockPos circlePos = getRemnantCirclePos(target);
                    if (circlePos != null) {
                        this.mob.getNavigation().moveTo(circlePos.getX() + 0.5D, circlePos.getY(), circlePos.getZ() + 0.5D, 1.0D);
                    }
                    if (circlingTime >= this.mob.hunting_cooldown) {
                        int i = Math.max(this.mob.getModeChance(), 2);
                        if (this.mob.random.nextInt(i) == 0) {
                            this.mob.mode = Ancient_Remnant_Entity.AttackMode.RANGE;
                        } else {
                            this.mob.mode = Ancient_Remnant_Entity.AttackMode.MELEE;
                        }
                    }else{
                        if(this.mob.distanceTo(target) < 7D){
                            this.mob.mode = Ancient_Remnant_Entity.AttackMode.MELEE;
                            this.mob.setAnimation(REMNANT_TAIL_ATTACK1);
                        }
                    }
                } else if (this.mob.mode == Ancient_Remnant_Entity.AttackMode.RANGE) {
                    if (this.mob.getRandom().nextFloat() * 100.0F < 3f && this.mob.distanceTo(target) > 10.0D) {
                        this.mob.setAnimation(REMNANT_CHARGE_PREPARE);
                    }
                } else if (this.mob.mode == Ancient_Remnant_Entity.AttackMode.MELEE) {
                    this.mob.getNavigation().moveTo(target, 1.0D);
                    MeleeModeTime++;
                    this.mob.getLookControl().setLookAt(target, 30, 90);
                    if (MeleeModeTime >= MELEE_MODE_TIME) {
                        this.mob.mode = Ancient_Remnant_Entity.AttackMode.RANGE;
                    } else if (this.mob.getRandom().nextFloat() * 100.0F < 15f && this.mob.distanceTo(target) < 6.0D) {
                        if (this.mob.random.nextInt(2) == 0) {
                            this.mob.setAnimation(REMNANT_BITE1);
                        } else {
                            this.mob.setAnimation(REMNANT_BITE2);
                        }
                    }else if (this.mob.getRandom().nextFloat() * 100.0F < 10f && this.mob.distanceTo(target) < 7.0D && target.getY() < this.mob.getY() + 1) {
                        this.mob.setAnimation(REMNANT_TAIL_ATTACK1);
                    }

                }


            }
        }

        public BlockPos getRemnantCirclePos (LivingEntity target){
            float angle = (0.01745329251F * (clockwise ? -circlingTime : circlingTime));
            double extraX = circleDistance * Mth.sin((angle));
            double extraZ = circleDistance * Mth.cos(angle);

            return BlockPos.containing(target.getX() + 0.5F + extraX, target.getY(), target.getZ() + 0.5F + extraZ);
        }

    }
}





