package com.github.L_Ender.cataclysm.entity.projectile;

import com.github.L_Ender.cataclysm.config.CMConfig;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class Ancient_Desert_Stele_Entity extends Entity {
    private int warmupDelayTicks;
    private boolean sentSpikeEvent;
    private int lifeTicks = 70;
    private LivingEntity caster;
    private UUID casterUuid;
    private static final EntityDataAccessor<Boolean> ACTIVATE = SynchedEntityData.defineId(Ancient_Desert_Stele_Entity.class, EntityDataSerializers.BOOLEAN);

    public Ancient_Desert_Stele_Entity(EntityType<? extends Ancient_Desert_Stele_Entity> p_i50170_1_, Level p_i50170_2_) {
        super(p_i50170_1_, p_i50170_2_);
    }

    public Ancient_Desert_Stele_Entity(Level worldIn, double x, double y, double z, float p_i47276_8_, int p_i47276_9_, LivingEntity casterIn) {
        this(ModEntities.ANCIENT_DESERT_STELE.get(), worldIn);
        this.warmupDelayTicks = p_i47276_9_;
        this.setCaster(casterIn);
        this.setYRot(p_i47276_8_ * (180F / (float)Math.PI));
        this.setPos(x, y, z);
    }

    protected void defineSynchedData() {
        this.entityData.define(ACTIVATE, Boolean.valueOf(false));
    }

    public void setCaster(@Nullable LivingEntity p_190549_1_) {
        this.caster = p_190549_1_;
        this.casterUuid = p_190549_1_ == null ? null : p_190549_1_.getUUID();
    }

    @Nullable
    public LivingEntity getCaster() {
        if (this.caster == null && this.casterUuid != null && this.level() instanceof ServerLevel) {
            Entity entity = ((ServerLevel)this.level()).getEntity(this.casterUuid);
            if (entity instanceof LivingEntity) {
                this.caster = (LivingEntity)entity;
            }
        }

        return this.caster;
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readAdditionalSaveData(CompoundTag compound) {
        this.warmupDelayTicks = compound.getInt("Warmup");
        if (compound.hasUUID("Owner")) {
            this.casterUuid = compound.getUUID("Owner");
        }

    }

    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putInt("Warmup", this.warmupDelayTicks);
        if (this.casterUuid != null) {
            compound.putUUID("Owner", this.casterUuid);
        }

    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        super.tick();

        HitResult result = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        List<Entity> intersecting = this.level().getEntitiesOfClass(Entity.class, this.getBoundingBox(), this::canHitEntity);
        if (result.getType() != HitResult.Type.MISS || !intersecting.isEmpty()) {
            intersecting.forEach(e -> this.onHitEntity(new EntityHitResult(e)));
            if (result.getType() == HitResult.Type.ENTITY && intersecting.isEmpty())
                this.onHitEntity((EntityHitResult) result);
            this.onHit(result);
        }

        this.checkInsideBlocks();

        if (this.level().isClientSide) {
            --this.lifeTicks;

        } else if (--this.warmupDelayTicks < 0) {
            if(!this.isActivate()) {
                this.setActivate(true);
            }
            if (--this.lifeTicks < 0) {
                this.discard();
            }
        }

        if (this.isActivate()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.04, 0.0));
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
        }

    }

    protected boolean canHitEntity(Entity entity) {
        if (!entity.isSpectator() && entity.isAlive() && entity.isPickable() && !entity.noPhysics) {
            Entity caster = this.getCaster();
            return caster == null || !caster.isPassengerOfSameVehicle(entity);
        } else {
            return false;
        }
    }

    protected void onHit(HitResult ray) {
        HitResult.Type hitresult$type = ray.getType();
        if (hitresult$type == HitResult.Type.ENTITY) {
            this.onHitEntity((EntityHitResult)ray);
            this.level().gameEvent(GameEvent.PROJECTILE_LAND, ray.getLocation(), GameEvent.Context.of(this, (BlockState)null));
        } else if (hitresult$type == HitResult.Type.BLOCK) {
            BlockHitResult blockhitresult = (BlockHitResult)ray;
            this.onHitBlock(blockhitresult);
            BlockPos blockpos = blockhitresult.getBlockPos();
            this.level().gameEvent(GameEvent.PROJECTILE_LAND, blockpos, GameEvent.Context.of(this, this.level().getBlockState(blockpos)));
        }
        BlockState state = Blocks.SANDSTONE.defaultBlockState();
        SoundType soundtype = state.getSoundType(this.level(), this.blockPosition(), null);
        this.playSound(soundtype.getBreakSound(), (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
        if (!this.level().isClientSide) {
            ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, state), this.getX(), this.getY() + this.getBbHeight() / 2.0, this.getZ(), 256, this.getBbWidth() / 2.0, this.getBbHeight() / 2.0, this.getBbWidth() / 2.0, 1);
            this.discard();
        }

    }

    protected void onHitEntity(EntityHitResult p_213868_1_) {
        LivingEntity shooter = this.getCaster();
        if (!this.level().isClientSide) {
                Entity entity = p_213868_1_.getEntity();
                boolean flag;
                if (shooter != null) {
                    LivingEntity owner = shooter;
                    flag = entity.hurt(damageSources().mobProjectile(this, owner), (float) CMConfig.AncientDesertSteledamage);
                    if (flag) {
                        this.doEnchantDamageEffects(owner, entity);
                    }
                } else {
                    flag = entity.hurt(this.damageSources().magic(), (float) CMConfig.AncientDesertSteledamage);
                }
                if (flag && entity instanceof LivingEntity) {
                    MobEffectInstance effectinstance = new MobEffectInstance(ModEffect.EFFECTCURSE_OF_DESERT.get(), 200, 0);
                    ((LivingEntity)entity).addEffect(effectinstance);
                }
            }


    }

    protected void onHitBlock(BlockHitResult p_230299_1_) {
    }

    public boolean isActivate() {
        return this.entityData.get(ACTIVATE);
    }

    public void setActivate(boolean Activate) {
        this.entityData.set(ACTIVATE, Activate);
    }


    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
