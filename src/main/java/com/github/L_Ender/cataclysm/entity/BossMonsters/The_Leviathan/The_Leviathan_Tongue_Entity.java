package com.github.L_Ender.cataclysm.entity.BossMonsters.The_Leviathan;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.github.L_Ender.cataclysm.init.ModEntities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class The_Leviathan_Tongue_Entity extends Entity {

    private static final EntityDataAccessor<Optional<UUID>> CONTROLLER_UUID = SynchedEntityData.defineId(The_Leviathan_Tongue_Entity.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Integer> CONTROLLER_ID = SynchedEntityData.defineId(The_Leviathan_Tongue_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TARGET_ID = SynchedEntityData.defineId(The_Leviathan_Tongue_Entity.class, EntityDataSerializers.INT);
    private boolean comingBack = false;

    public The_Leviathan_Tongue_Entity(EntityType<?> entityType, Level level) {
        super(entityType, level);
        this.noPhysics = true;
    }

    public The_Leviathan_Tongue_Entity(PlayMessages.SpawnEntity spawnEntity, Level level) {
        this(ModEntities.THE_LEVIATHAN_TONGUE.get(), level);
        this.setBoundingBox(this.makeBoundingBox());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return (Packet<ClientGamePacketListener>) NetworkHooks.getEntitySpawningPacket(this);
    }

    public boolean shouldRender(double p_37588_, double p_37589_, double p_37590_) {
        return true;
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        return distance < 1024;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(CONTROLLER_UUID, Optional.empty());
        this.entityData.define(CONTROLLER_ID, -1);
        this.entityData.define(TARGET_ID, -1);
    }


    public void tick() {
        super.tick();
        Entity controller = getController();
        Entity target = getTarget();

        if (!this.getPassengers().isEmpty() && this.getPassengers().get(0).isShiftKeyDown()) {
            this.getPassengers().get(0).setShiftKeyDown(false);
        }

        if (controller instanceof The_Leviathan_Entity levi) {
            this.entityData.set(CONTROLLER_ID, levi.getId());
            levi.setTongueUUID(this.getUUID());
            if (!level.isClientSide) {
                Entity e = levi.getTarget();
                this.entityData.set(TARGET_ID, e != null && e.isAlive() ? e.getId() : -1);
            }
            boolean attacking = !comingBack && target != null && target.isAlive();
            Vec3 vec3 = attacking ? target.getEyePosition() : levi.getTonguePosition();
            Vec3 want = vec3.subtract(this.position());
            if (target != null && !comingBack) {
                if (want.length() < target.getBbWidth() + 1F) {
                    hurtEntity(levi, target);
                    this.comingBack = true;
                }
            }
            directMovementTowards(vec3, 0.15F);
            if (this.distanceTo(controller) < 5F) {
                if (this.comingBack) {
                    this.remove(RemovalReason.DISCARDED);
                }
            }
        }
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.9F));
    }

    private void hurtEntity(LivingEntity holder, Entity target) {
        if(target.hurt(DamageSource.mobAttack(holder), 6)){
            target.startRiding(this);
        }
    }

    private void directMovementTowards(Vec3 moveTo, float speed) {
        Vec3 want = moveTo.subtract(this.position());
        if (want.length() > 1F) {
            want = want.normalize();
        }
        float targetXRot = (float) (-(Mth.atan2(want.y, want.horizontalDistance()) * (double) (180F / (float) Math.PI)));
        float targetYRot = (float) (-Mth.atan2(want.x, want.z) * (double) (180F / (float) Math.PI));
        this.setXRot(Mth.approachDegrees(this.getXRot(), targetXRot, 5F));
        this.setYRot(Mth.approachDegrees(this.getYRot(), targetYRot, 5F));
        this.setDeltaMovement(this.getDeltaMovement().add(want.scale(speed)));

    }

    public boolean shouldRiderSit() {
        return false;
    }

    public double getPassengersRidingOffset() {
        return -0.5D;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.hasUUID("ControllerUUID")) {
            this.setControllerUUID(tag.getUUID("ControllerUUID"));
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        if (this.getControllerUUID() != null) {
            tag.putUUID("ControllerUUID", this.getControllerUUID());
        }
    }


    @Nullable
    public UUID getControllerUUID() {
        return this.entityData.get(CONTROLLER_UUID).orElse(null);
    }

    public void setControllerUUID(@Nullable UUID uniqueId) {
        this.entityData.set(CONTROLLER_UUID, Optional.ofNullable(uniqueId));
    }

    public Entity getController() {
        if (!level.isClientSide) {
            final UUID id = getControllerUUID();
            return id == null ? null : ((ServerLevel) level).getEntity(id);
        } else {
            int id = this.entityData.get(CONTROLLER_ID);
            return id == -1 ? null : level.getEntity(id);
        }
    }

    public Entity getTarget() {
        int id = this.entityData.get(TARGET_ID);
        return id == -1 ? null : level.getEntity(id);
    }
}