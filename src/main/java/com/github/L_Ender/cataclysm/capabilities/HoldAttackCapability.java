package com.github.L_Ender.cataclysm.capabilities;

import org.jetbrains.annotations.Nullable;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.message.MessageHoldEntity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.network.PacketDistributor;

public class HoldAttackCapability {
    public static ResourceLocation ID = new ResourceLocation(Cataclysm.MODID, "hold_attack_cap");


    public interface IHoldAttackCapability extends INBTSerializable<CompoundTag> {


        void tick(LivingEntity entity);

        void setEntity(LivingEntity entity);

        boolean getHold();

        void setHold(boolean hold, @Nullable LivingEntity holder,Vec3 vector);

        @Nullable
        LivingEntity getHolder();

        void setPosVector(Vec3 vector);

        Vec3 getPosVector();

    }

    public static class HoldAttackCapabilityImp implements IHoldAttackCapability {

        private boolean hold;
        private LivingEntity target;
        @Nullable
        private LivingEntity holder;
        private Vec3 PosVector = Vec3.ZERO;

        @Override
        public void setEntity(LivingEntity entity) {
            this.target = entity;
        }

        @Override
        public void tick(LivingEntity entity) {
            if(holder != null){
                if (hold) { // Actually throw the victim
                    if (this.target instanceof Player) {
                        this.target.setPos(this.PosVector.x(), this.PosVector.y(), this.PosVector.z());
                        this.target.setDeltaMovement(Vec3.ZERO);
                    }else{
                        if (!this.target.level.isClientSide()) {
                            this.target.setPos(this.PosVector.x(), this.PosVector.y(), this.PosVector.z());
                            this.target.setDeltaMovement(Vec3.ZERO);
                        }
                    }

                }
            }else{
                setHold(false,null,Vec3.ZERO);
            }
        }

        @Override
        public boolean getHold() {
            return this.hold;
        }

        @Override
        public void setHold(boolean hold, @Nullable LivingEntity holder,Vec3 vector) {
            this.hold = hold;
            this.holder = holder;
            this.PosVector = vector;
            this.sendUpdatePacket();
        }

        @Override
        public @Nullable LivingEntity getHolder() {
            return this.holder;
        }

        @Override
        public void setPosVector(Vec3 vector) {
            this.PosVector = vector;
        }

        @Override
        public Vec3 getPosVector() {
            return PosVector;
        }

        private void sendUpdatePacket() {
            if (!this.target.level.isClientSide()) {
                Cataclysm.NETWORK_WRAPPER.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> this.target), new MessageHoldEntity(this.target, this));
            }
        }


        @Override
        public CompoundTag serializeNBT() {
            CompoundTag tag = new CompoundTag();
            tag.putBoolean("Hold", this.getHold());
            tag.putDouble("PosX", this.PosVector.x());
            tag.putDouble("PosY", this.PosVector.y());
            tag.putDouble("PosZ", this.PosVector.z());
            return tag;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            this.setHold(nbt.getBoolean("Hold"), null,PosVector);
            this.PosVector = new Vec3(nbt.getDouble("PosX"), nbt.getDouble("PosY"), nbt.getDouble("PosZ"));
        }
    }
}
