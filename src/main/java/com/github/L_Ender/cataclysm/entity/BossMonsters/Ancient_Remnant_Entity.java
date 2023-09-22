package com.github.L_Ender.cataclysm.entity.BossMonsters;

import com.github.L_Ender.cataclysm.config.CMConfig;
import com.github.L_Ender.cataclysm.entity.BossMonsters.AI.SimpleAnimationGoal;
import com.github.L_Ender.cataclysm.entity.etc.CMPathNavigateGround;
import com.github.L_Ender.cataclysm.entity.etc.SmartBodyHelper2;
import com.github.L_Ender.cataclysm.init.ModSounds;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;


public class Ancient_Remnant_Entity extends Boss_monster {

    public static final Animation REMNANT_BITE = Animation.create(69);
    public static final Animation REMNANT_CHARGE_PREPARE = Animation.create(135);
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
        setConfigattribute(this, CMConfig.RevenantHealthMultiplier, CMConfig.RevenantDamageMultiplier);
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{
                NO_ANIMATION,
                REMNANT_BITE,
                REMNANT_CHARGE_PREPARE};
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new RemnantAttackGoal(this));
        this.goalSelector.addGoal(0, new RemnantChargeAttackGoal(this, REMNANT_CHARGE_PREPARE));
        this.goalSelector.addGoal(0, new SimpleAnimationGoal<>(this, REMNANT_BITE));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D, 80));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder ancient_ramant() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.33F)
                .add(Attributes.ATTACK_DAMAGE, 14)
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

        frame++;
        float moveX = (float) (getX() - xo);
        float moveZ = (float) (getZ() - zo);
        float speed = Mth.sqrt(moveX * moveX + moveZ * moveZ);
        if (!this.isSilent() && frame % 8 == 1 && speed > 0.05 && this.getIsCharge()) {
            this.playSound(ModSounds.REMNANT_CHARGE_STEP.get(), 1F, 1.0f);
        }
    }

    public void aiStep() {
        super.aiStep();


        if(this.getAnimation() == REMNANT_BITE){
            if(this.getAnimationTick() == 5){
                this.level().playSound((Player) null, this, ModSounds.REMNANT_BITE.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
            }
        }
        if(this.getAnimation() == REMNANT_CHARGE_PREPARE){
            if(this.getAnimationTick() == 1){
                this.level().playSound((Player) null, this, ModSounds.REMNANT_CHARGE_PREPARE.get(), SoundSource.HOSTILE, 3.0f, 1.0f);
            }
            if(this.getAnimationTick() == 62){
                this.level().playSound((Player) null, this, ModSounds.REMNANT_CHARGE_ROAR.get(), SoundSource.HOSTILE, 3.0f, 1.0f);
            }
            if(this.getAnimationTick() == 132) {
                this.level().playSound((Player) null, this, ModSounds.REMNANT_BREATHING.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
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
        return ModSounds.REVENANT_IDLE.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.REVENANT_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.REVENANT_DEATH.get();
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
                entity.getLookControl().setLookAt(target, 30, 30);
            }
            super.start();
        }

        public void stop() {
            super.stop();
            entity.hunting_cooldown = MINE_COOLDOWN;
            LivingEntity target = entity.getTarget();
            if (target != null) {
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
            if(this.entity.getAnimationTick() < 132 &&  this.entity.getAnimationTick() > 62){
                Vec3 vector3d = entity.getDeltaMovement();
                float f = entity.getYRot() * ((float)Math.PI / 180F);
                Vec3 vector3d1 = new Vec3(-Mth.sin(f), vector3d.y, Mth.cos(f)).scale(1.0D).add(vector3d.scale(0.5D));

                entity.setDeltaMovement(vector3d1.x, vector3d1.y, vector3d1.z);
            }


            if(this.entity.getAnimationTick() == 62){
                this.entity.setIsCharge(true);
            }

            if(this.entity.getAnimationTick() == 132){
                this.entity.setIsCharge(false);
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
                this.mob.setTarget((LivingEntity) null);
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
                    double dist = this.mob.distanceTo(target);
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
                        if(dist < 6D){
                            this.mob.mode = Ancient_Remnant_Entity.AttackMode.MELEE;
                        }
                    }
                } else if (this.mob.mode == Ancient_Remnant_Entity.AttackMode.RANGE) {
                    if (this.mob.getRandom().nextFloat() * 100.0F < 3f) {
                        this.mob.setAnimation(REMNANT_CHARGE_PREPARE);
                    }
                } else if (this.mob.mode == Ancient_Remnant_Entity.AttackMode.MELEE) {
                    this.mob.getNavigation().moveTo(target, 1.0D);
                    MeleeModeTime++;
                    this.mob.getLookControl().setLookAt(target, 30, 90);
                    if (MeleeModeTime >= MELEE_MODE_TIME) {
                        this.mob.mode = Ancient_Remnant_Entity.AttackMode.RANGE;
                    } else if (this.mob.getRandom().nextFloat() * 100.0F < 15f && this.mob.distanceTo(target) < 6.0D ) {
                        this.mob.setAnimation(REMNANT_BITE);
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





