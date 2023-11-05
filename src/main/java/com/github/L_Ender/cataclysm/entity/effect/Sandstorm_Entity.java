package com.github.L_Ender.cataclysm.entity.effect;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.config.CMConfig;
import com.github.L_Ender.cataclysm.entity.BossMonsters.Ancient_Remnant_Entity;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.L_Ender.cataclysm.init.ModParticle;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;
import java.util.UUID;

public class Sandstorm_Entity extends Entity {

    private static final EntityDataAccessor<Optional<UUID>> CREATOR_ID = SynchedEntityData.defineId(Sandstorm_Entity.class, EntityDataSerializers.OPTIONAL_UUID);
    protected static final EntityDataAccessor<Integer> LIFESPAN = SynchedEntityData.defineId(Sandstorm_Entity.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Float> OFFSET = SynchedEntityData.defineId(Sandstorm_Entity.class, EntityDataSerializers.FLOAT);


    public Sandstorm_Entity(EntityType<?> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Sandstorm_Entity(Level worldIn, double x, double y, double z, int lifespan, float offset, UUID casterIn) {
        this(ModEntities.SANDSTORM.get(), worldIn);
        this.setCreatorEntityUUID(casterIn);
        this.setLifespan(lifespan);
        this.setPos(x, y, z);
        this.setOffset(offset);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    public void tick() {
        super.tick();
        updateMotion();
        Entity owner = getCreatorEntity();
        if (owner != null && !owner.isAlive()) discard();
        if(level().isClientSide) {
            float spawnPercent = 2.0f;
            float maxY = 2.5F * spawnPercent * 1.25F;
            float y = 0;
            float nY = 30 * spawnPercent;
            float dY = maxY / nY;
            double posX = this.getX();
            double posY = this.getY();
            double posZ = this.getZ();
            for (float a = 0, nA = 16, dA = (1.4F * (float) Math.PI) / nA; y < maxY; a += dA) {
                float radius = y * 0.35F;
                float cosA = Mth.cos(a) * radius;
                float sinA = Mth.sin(a) * radius;
                level().addParticle(ModParticle.SANDSTORM.get(), posX + cosA, posY + y - (maxY * 0.15), posZ + sinA, 0.0D, 0D, 0.0D);
                y += dY;
            }
        }

        if (!this.isSilent() && level().isClientSide) {
            Cataclysm.PROXY.playWorldSound(this, (byte) 2);
        }

        for(LivingEntity entity : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.2D, 0.0D, 0.2D))) {
            if (entity instanceof Player && ((Player) entity).getAbilities().invulnerable) continue;
            if(entity != owner) {
                MobEffectInstance effectinstance = new MobEffectInstance(ModEffect.EFFECTCURSE_OF_DESERT.get(), 200, 0);
                entity.addEffect(effectinstance);
                if (entity.isAlive() && !entity.isInvulnerable() ) {
                    if (this.tickCount % 3 == 0) {
                        if (owner == null) {
                            entity.hurt(this.damageSources().magic(), (float) CMConfig.Sandstormdamage);
                        } else {
                            if (entity.isAlliedTo(entity)) {
                                return;
                            }
                            entity.hurt(this.damageSources().indirectMagic(this, owner), (float) CMConfig.Sandstormdamage);
                        }
                    }
                }
            }

        }

        this.setLifespan(this.getLifespan() - 1);

        if (this.getLifespan() <= 0) {
            this.remove(RemovalReason.DISCARDED);
        }
    }

    public int getLifespan() {
        return this.entityData.get(LIFESPAN);
    }

    public void setLifespan(int i) {
        this.entityData.set(LIFESPAN, i);
    }

    public float getOffset() {
        return this.entityData.get(OFFSET);
    }

    public void setOffset(float i) {
        this.entityData.set(OFFSET, i);
    }

    public UUID getCreatorEntityUUID() {
        return this.entityData.get(CREATOR_ID).orElse(null);
    }

    public void setCreatorEntityUUID(UUID id) {
        this.entityData.set(CREATOR_ID, Optional.ofNullable(id));
    }

    public Entity getCreatorEntity() {
        UUID uuid = getCreatorEntityUUID();
        if(uuid != null && !this.level().isClientSide){
            return ((ServerLevel) level()).getEntity(uuid);
        }
        return null;
    }

    private void updateMotion() {
        Entity owner = getCreatorEntity();
        if(owner !=null) {
            if (owner instanceof Ancient_Remnant_Entity) {
                Vec3 center = owner.position().add(0.0, 0, 0.0);
                float radius = 8;
                float speed = this.tickCount * 0.04f;
                float offset = this.getOffset();
                Vec3 orbit = new Vec3(center.x + Math.cos((double) (speed + offset)) * (double) radius, center.y, center.z + Math.sin((double) (speed + offset)) * (double) radius);
                this.moveTo(orbit);
            }
        }
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(CREATOR_ID, Optional.empty());
        this.entityData.define(LIFESPAN, 300);
        this.entityData.define(OFFSET,0f);
    }

    protected void readAdditionalSaveData(CompoundTag compound) {
        this.setLifespan(compound.getInt("Lifespan"));
        UUID uuid;
        if (compound.hasUUID("Owner")) {
            uuid = compound.getUUID("Owner");
        } else {
            String s = compound.getString("Owner");
            uuid = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), s);
        }
        if (uuid != null) {
            try {
                this.setCreatorEntityUUID(uuid);} catch (Throwable ignored) {

            }
        }
    }

    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putInt("Lifespan", getLifespan());
       if (this.getCreatorEntityUUID() != null) {
           compound.putUUID("Owner", this.getCreatorEntityUUID());
        }
    }
}
