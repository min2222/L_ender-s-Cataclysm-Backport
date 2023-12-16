package com.github.L_Ender.cataclysm.entity.BossMonsters.The_Leviathan;

import java.util.Optional;
import java.util.UUID;

import com.github.L_Ender.cataclysm.entity.util.LeviathanTongueUtil;
import com.github.L_Ender.cataclysm.init.ModEntities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class The_Leviathan_Tongue_Entity extends Entity {

    private static final EntityDataAccessor<Optional<UUID>> CREATOR_ID = SynchedEntityData.defineId(The_Leviathan_Tongue_Entity.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Integer> FROM_ID = SynchedEntityData.defineId(The_Leviathan_Tongue_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CURRENT_TARGET_ID = SynchedEntityData.defineId(The_Leviathan_Tongue_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> PROGRESS = SynchedEntityData.defineId(The_Leviathan_Tongue_Entity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> MAX_TIME = SynchedEntityData.defineId(The_Leviathan_Tongue_Entity.class, EntityDataSerializers.FLOAT);

    private static final EntityDataAccessor<Boolean> RETRACTING = SynchedEntityData.defineId(The_Leviathan_Tongue_Entity.class, EntityDataSerializers.BOOLEAN);
    private boolean hasTouched = false;
    public float prevProgress = 0;



    public The_Leviathan_Tongue_Entity(EntityType<?> type, Level level) {
        super(type, level);
    }


    public The_Leviathan_Tongue_Entity(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(ModEntities.THE_LEVIATHAN_TONGUE.get(), world);

    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return (Packet<?>) NetworkHooks.getEntitySpawningPacket(this);
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
        this.entityData.define(CREATOR_ID, Optional.empty());
        this.entityData.define(FROM_ID, -1);
        this.entityData.define(CURRENT_TARGET_ID, -1);
        this.entityData.define(PROGRESS, 0F);
        this.entityData.define(MAX_TIME, 0F);
        this.entityData.define(RETRACTING, false);
    }

    @Override
    public void tick() {
        float progress = this.getProgress();
        float MAX_EXTEND_TIME = this.getMaxExtendTime();
        this.prevProgress = progress;
        super.tick();
        Entity creator = getCreatorEntity();
        Entity current = getToEntity();
        if(!this.isRetracting() && progress < MAX_EXTEND_TIME){
            this.setProgress(progress + 1);
        }
        if(this.isRetracting() && progress > 0F){
            this.setProgress(progress - 1.25f);
        }
        if(this.isRetracting() && progress <= 0F){
            Entity from = this.getFromEntity();
            if(from instanceof The_Leviathan_Tongue_Entity){
                The_Leviathan_Tongue_Entity tongueSegment = (The_Leviathan_Tongue_Entity) from;
                tongueSegment.setRetracting(true);
                updateLastTongue(tongueSegment);
            }else{
                updateLastTongue(null);
            }

            this.remove(RemovalReason.DISCARDED);
        }
        if (creator instanceof LivingEntity) {
            if(creator.isAlive()) {
                if (current != null) {
                    Vec3 target = new Vec3(current.getX(), current.getY(0.4F), current.getZ());
                    Vec3 lerp = target.subtract(this.position());
                    this.setDeltaMovement(lerp.scale(0.5F));
                    if (!level.isClientSide) {
                        if (!hasTouched && progress >= MAX_EXTEND_TIME) {
                            hasTouched = true;
                            Entity entity = getCreatorEntity();
                            if (entity instanceof LivingEntity) {
                                if (current.hurt(DamageSource.indirectMobAttack(this, (LivingEntity) creator), 6)) {
                                    Vec3 vec3 = (new Vec3(entity.getX() - current.getX(), entity.getY() - current.getY(), entity.getZ()  - current.getZ())).scale(0.6D);
                                    current.setDeltaMovement(current.getDeltaMovement().add(vec3));
                                }
                            }
                        }
                    }
                }
            }else {
                this.remove(RemovalReason.DISCARDED);
            }
        }

        Vec3 vector3d = this.getDeltaMovement();
        if(!level.isClientSide){
            if(creator instanceof LivingEntity && this.getProgress() >= MAX_EXTEND_TIME) {
                this.setRetracting(true);
            }
        }
        double d0 = this.getX() + vector3d.x;
        double d1 = this.getY() + vector3d.y;
        double d2 = this.getZ() + vector3d.z;
        this.setDeltaMovement(vector3d.scale(0.99F));
        this.setPos(d0, d1, d2);
    }

    private void updateLastTongue(The_Leviathan_Tongue_Entity lastTendon){
        Entity creator = getCreatorEntity();
        if(creator == null){
            creator = level.getPlayerByUUID(this.getCreatorEntityUUID());
        }
        if(creator instanceof LivingEntity){
            LeviathanTongueUtil.setLastTongue((LivingEntity)creator, lastTendon);
        }
    }


    public UUID getCreatorEntityUUID() {
        return this.entityData.get(CREATOR_ID).orElse(null);
    }

    public void setCreatorEntityUUID(UUID id) {
        this.entityData.set(CREATOR_ID, Optional.ofNullable(id));
    }

    public Entity getCreatorEntity() {
        UUID uuid = getCreatorEntityUUID();
        if(uuid != null && !level.isClientSide){
            return ((ServerLevel) level).getEntity(uuid);
        }
        return null;
    }

    public int getFromEntityID() {
        return this.entityData.get(FROM_ID);
    }

    public void setFromEntityID(int id) {
        this.entityData.set(FROM_ID, id);
    }

    public Entity getFromEntity() {
        return getFromEntityID() == -1 ? null : this.level.getEntity(getFromEntityID());
    }

    public int getToEntityID() {
        return this.entityData.get(CURRENT_TARGET_ID);
    }

    public void setToEntityID(int id) {
        this.entityData.set(CURRENT_TARGET_ID, id);
    }

    public Entity getToEntity() {
        return getToEntityID() == -1 ? null : this.level.getEntity(getToEntityID());
    }

    public float getProgress() {
        return this.entityData.get(PROGRESS);
    }

    public void setProgress(float progress) {
        this.entityData.set(PROGRESS, progress);
    }

    public float getMaxExtendTime() {
        return this.entityData.get(MAX_TIME);
    }

    public void setMaxExtendTime(float progress) {
        this.entityData.set(MAX_TIME, progress);
    }


    public boolean isRetracting() {
        return this.entityData.get(RETRACTING);
    }

    public void setRetracting(boolean retract) {
        this.entityData.set(RETRACTING, retract);
    }



    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {

    }

    public boolean isCreator(Entity mob) {
        return this.getCreatorEntityUUID() != null && mob.getUUID().equals(this.getCreatorEntityUUID());
    }

}
