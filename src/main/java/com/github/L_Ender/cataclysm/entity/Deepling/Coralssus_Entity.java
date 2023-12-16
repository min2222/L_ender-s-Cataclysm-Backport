package com.github.L_Ender.cataclysm.entity.Deepling;

import java.util.EnumSet;

import javax.annotation.Nullable;

import com.github.L_Ender.cataclysm.entity.AI.CmAttackGoal;
import com.github.L_Ender.cataclysm.entity.AI.MobAIFindWater;
import com.github.L_Ender.cataclysm.entity.AI.MobAILeaveWater;
import com.github.L_Ender.cataclysm.entity.BossMonsters.Boss_monster;
import com.github.L_Ender.cataclysm.entity.BossMonsters.AI.AttackAnimationGoal1;
import com.github.L_Ender.cataclysm.entity.BossMonsters.AI.SimpleAnimationGoal;
import com.github.L_Ender.cataclysm.entity.BossMonsters.The_Leviathan.The_Leviathan_Entity;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import com.github.L_Ender.cataclysm.entity.etc.CMPathNavigateGround;
import com.github.L_Ender.cataclysm.entity.etc.SmartBodyHelper2;
import com.github.L_Ender.cataclysm.init.ModSounds;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;


public class Coralssus_Entity extends Boss_monster {
    public float SwimProgress = 0;
    public float prevSwimProgress = 0;
    public static final Animation CORALSSUS_LEAP = Animation.create(100);
    public static final Animation CORALSSUS_SMASH = Animation.create(23);
    public static final Animation CORALSSUS_LEFT_SMASH = Animation.create(36);
    public static final Animation CORALSSUS_RIGHT_SMASH = Animation.create(36);
    public static final int LEAP_ATTACK_COOLDOWN = 160;
    private int leap_attack_cooldown = 0;

    public Coralssus_Entity(EntityType entity, Level world) {
        super(entity, world);
        this.xpReward = 15;
        this.setPathfindingMalus(BlockPathTypes.UNPASSABLE_RAIL, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }
    
    @Override
    public float getStepHeight() {
    	return 1.5F;
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{NO_ANIMATION,CORALSSUS_SMASH,CORALSSUS_LEFT_SMASH,CORALSSUS_RIGHT_SMASH,CORALSSUS_LEAP};
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new AttackAnimationGoal1<>(this, CORALSSUS_LEFT_SMASH, 16, true));
        this.goalSelector.addGoal(0, new AttackAnimationGoal1<>(this, CORALSSUS_RIGHT_SMASH, 16, true));
        this.goalSelector.addGoal(0, new Leap(this, CORALSSUS_LEAP));
        this.goalSelector.addGoal(0, new SimpleAnimationGoal<>(this, CORALSSUS_SMASH));
        this.goalSelector.addGoal(2, new CmAttackGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new MobAIFindWater(this,1.0D));
        this.goalSelector.addGoal(4, new MobAILeaveWater(this));
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder coralssus() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.28F)
                .add(Attributes.ATTACK_DAMAGE, 11)
                .add(Attributes.MAX_HEALTH, 110)
                .add(Attributes.ARMOR, 5)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8);
    }


    protected PathNavigation createNavigation(Level worldIn) {
        return new CMPathNavigateGround(this, worldIn);
    }


    public boolean causeFallDamage(float p_148711_, float p_148712_, DamageSource p_148713_) {
        return false;
    }


    @Override
    public boolean hurt(DamageSource source, float damage) {
        return super.hurt(source, damage);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);


    }
    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

    }


    public boolean isPushedByFluid() {
        return !this.isSwimming();
    }

    public boolean canBreatheUnderwater() {
        return true;
    }


    private void floatStrider() {
        if (this.isInWater()) {
            CollisionContext lvt_1_1_ = CollisionContext.of(this);
            if (lvt_1_1_.isAbove(LiquidBlock.STABLE_SHAPE, this.blockPosition().below(), true) && !this.level.getFluidState(this.blockPosition().above()).is(FluidTags.WATER)) {
                this.setOnGround(true);
            } else {
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5D).add(0.0D, random.nextFloat() * 0.5, 0.0D));
            }
        }

    }

    public void tick() {
        super.tick();
        AnimationHandler.INSTANCE.updateAnimations(this);
        this.prevSwimProgress = SwimProgress;
        if (this.isInWater()) {
            if (this.SwimProgress < 10F)
                this.SwimProgress++;
        } else {
            if (this.SwimProgress > 0F)
                this.SwimProgress--;
        }
        this.floatStrider();

        if (leap_attack_cooldown > 0) leap_attack_cooldown--;
        LivingEntity target = this.getTarget();
        if (this.isAlive()) {
            if (target != null && target.isAlive()) {
                if (!this.isInWater() &&leap_attack_cooldown <= 0 && !isNoAi() && this.getAnimation() == NO_ANIMATION && target.isOnGround() && (this.random.nextInt(25) == 0 && this.distanceTo(target) <= 15)) {
                    leap_attack_cooldown = LEAP_ATTACK_COOLDOWN;
                    this.setAnimation(CORALSSUS_LEAP);
                }else if (this.distanceTo(target) < 3.75f && !isNoAi() && this.getAnimation() == NO_ANIMATION) {
                    Animation animation = getRandomAttack(random);
                    this.setAnimation(animation);
                }
            }
        }
    }

    public void aiStep() {
        super.aiStep();
        if (this.getAnimation() == CORALSSUS_RIGHT_SMASH) {
            if (this.getAnimationTick() == 16) {
                EarthQuake(3.0f,2);
                Makeparticle(2.0f,-0.5f);
            }
        }
        if(this.getAnimation() == CORALSSUS_LEFT_SMASH){
            if (this.getAnimationTick() == 16) {
                EarthQuake(3.0f,2);
                Makeparticle(2.0f,0.5f);
            }
        }
        if(this.getAnimation() == CORALSSUS_SMASH){
            if (this.getAnimationTick() == 2) {
                EarthQuake(4.0f,4);
                Makeparticle(2.25f,1.25f);
                Makeparticle(2.25f,-1.25f);
            }
        }

    }


    private void Makeparticle(float vec, float math) {
        if (this.level.isClientSide) {
            for (int i1 = 0; i1 < 80 + random.nextInt(12); i1++) {
                double DeltaMovementX = getRandom().nextGaussian() * 0.07D;
                double DeltaMovementY = getRandom().nextGaussian() * 0.07D;
                double DeltaMovementZ = getRandom().nextGaussian() * 0.07D;
                float f = Mth.cos( this.yBodyRot * ((float)Math.PI / 180F)) ;
                float f1 = Mth.sin( this.yBodyRot * ((float)Math.PI / 180F)) ;
                float angle = (0.01745329251F * this.yBodyRot) + i1;
                double extraX = 0.75F * Mth.sin((float) (Math.PI + angle));
                double extraY = 0.3F;
                double extraZ = 0.75F * Mth.cos(angle);
                double theta = (yBodyRot) * (Math.PI / 180);
                theta += Math.PI / 2;
                double vecX = Math.cos(theta);
                double vecZ = Math.sin(theta);
                int hitX = Mth.floor(getX() + vec * vecX+ extraX);
                int hitY = Mth.floor(getY());
                int hitZ = Mth.floor(getZ() + vec * vecZ + extraZ);
                BlockPos hit = new BlockPos(hitX, hitY, hitZ);
                BlockState block = level.getBlockState(hit.below());
                this.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, block), getX() + vec * vecX + extraX + f * math, this.getY() + extraY, getZ() + vec * vecZ + extraZ + f1 * math, DeltaMovementX, DeltaMovementY, DeltaMovementZ);
            }
        }
    }


    public void positionRider(Entity p_289537_) {
        super.positionRider(p_289537_);
        float radius = 0.5F;
        float angle = (0.01745329251F * this.yBodyRot);
        double extraX = radius * Mth.sin((float) (Math.PI + angle));
        double extraZ = radius * Mth.cos(angle);
        p_289537_.setPos(this.getX() + extraX, this.getY(0.65D) + p_289537_.getMyRidingOffset() + 0.0D, this.getZ()+ extraZ);

    }

    @Nullable
    public LivingEntity getControllingPassenger() {
        return null;
    }


    private void EarthQuake(float grow, int damage) {
        ScreenShake_Entity.ScreenShake(level, this.position(), 10, 0.15f, 0, 20);
        this.playSound(SoundEvents.GENERIC_EXPLODE, 0.5f, 1F + this.getRandom().nextFloat() * 0.1F);
        for (LivingEntity entity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(grow))) {
            if (!isAlliedTo(entity) && !(entity instanceof Coralssus_Entity) && entity != this) {
                entity.hurt(DamageSource.mobAttack(this), (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE) + this.random.nextInt(damage));
                launch(entity, true);

            }
        }
    }


    private static Animation getRandomAttack(RandomSource rand) {
        switch (rand.nextInt(2)) {
            case 0:
                return CORALSSUS_RIGHT_SMASH;
            case 1:
                return CORALSSUS_LEFT_SMASH;
        }
        return CORALSSUS_RIGHT_SMASH;
    }



    private void launch(LivingEntity e, boolean huge) {

        double d0 = e.getX() - this.getX();
        double d1 = e.getZ() - this.getZ();
        double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
        float f = huge ? 2F : 0.5F;
        e.push(d0 / d2 * f, huge ? 0.5D : 0.2F, d1 / d2 * f);
    }

    public boolean isAlliedTo(Entity entityIn) {
        if (entityIn == this) {
            return true;
        } else if (super.isAlliedTo(entityIn)) {
            return true;
        } else if (entityIn instanceof Coralssus_Entity || entityIn instanceof AbstractDeepling || entityIn instanceof Lionfish_Entity || entityIn instanceof The_Leviathan_Entity) {
            return this.getTeam() == null && entityIn.getTeam() == null;
        } else {
            return false;
        }
    }



    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.IRON_GOLEM_STEP, 1.0F, 1.0F);
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.CORALSSUS_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.CORALSSUS_DEATH.get();
    }

    public boolean canStandOnFluid(FluidState p_230285_1_) {
        return p_230285_1_.is(FluidTags.WATER);
    }

    public void travel(Vec3 p_32394_) {
        if (this.getAnimation() !=NO_ANIMATION) {
            if (this.getNavigation().getPath() != null) {
                this.getNavigation().stop();
            }
            p_32394_ = Vec3.ZERO;
            super.travel(p_32394_);
            return;
        }
        this.setSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED) * (isInWater() ? 0.2F : 1F));
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), p_32394_);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
        } else {
            super.travel(p_32394_);
        }
    }


    @Override
    protected BodyRotationControl createBodyControl() {
        return new SmartBodyHelper2(this);
    }

    static class Leap extends SimpleAnimationGoal<Coralssus_Entity> {
        private final Coralssus_Entity drowned;

        public Leap(Coralssus_Entity entity, Animation animation) {
            super(entity, animation);
            this.drowned = entity;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP, Flag.LOOK));
        }

        public void tick() {
            LivingEntity target = this.drowned.getTarget();;
            if (target != null) {
                this.drowned.lookAt(target, 30.0F, 30.0F);
                this.drowned.getLookControl().setLookAt(target, 30.0F, 30.0F);
                if(this.drowned.getAnimationTick() == 22) {
                    double d0 = target.getX() - this.drowned.getX();
                    double d1 = target.getY() - this.drowned.getY();
                    double d2 = target.getZ() - this.drowned.getZ();
                    this.drowned.setDeltaMovement(d0 * 0.15D, 0.75 + Mth.clamp(d1 * 0.05D, 0, 10), d2 * 0.15D);
                }
            }else{
                if(this.drowned.getAnimationTick() == 22) {
                    this.drowned.setDeltaMovement(0, 0.75, 0);
                }
            }

            if (this.drowned.getAnimationTick() > 22 && this.drowned.isOnGround()) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this.drowned, CORALSSUS_SMASH);
            }

        }
    }
}





