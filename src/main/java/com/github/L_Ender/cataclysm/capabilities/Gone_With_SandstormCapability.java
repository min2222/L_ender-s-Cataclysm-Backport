package com.github.L_Ender.cataclysm.capabilities;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.config.CMConfig;
import com.github.L_Ender.cataclysm.init.ModCapabilities;
import com.github.L_Ender.cataclysm.message.MessageGoneWithSandstorm;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.network.PacketDistributor;

public class Gone_With_SandstormCapability {
    public static ResourceLocation ID = new ResourceLocation(Cataclysm.MODID, "sandstorm_cap");


    public interface IGone_With_SandstormCapability extends INBTSerializable<CompoundTag> {


        void tick(LivingEntity entity);

        void setSandstorm(boolean getSandstorm);

        boolean isSandstorm();

        void setSandstormTimer(int timer);

        int getSandstormTimer();

    }

    public static class Gone_With_SandstormCapabilityImp implements IGone_With_SandstormCapability {
        public int Timer;
        private boolean Sandstorm;
        private final LivingEntity player;

        public Gone_With_SandstormCapabilityImp(LivingEntity player) {
            this.player = player;
        }

        @Override
        public void tick(LivingEntity entity) {
            entity.getCapability(ModCapabilities.GONE_WITH_SANDSTORM_CAPABILITY).ifPresent(handler -> {
                if(handler.isSandstorm()){
                    if(getSandstormTimer() < CMConfig.Sandstorm_In_A_Bottle_Timer){
                        setSandstormTimer(getSandstormTimer() + 1);
                    }else{
                        setSandstorm(false);
                    }
                }else{
                    if(getSandstormTimer() >0) {
                        setSandstormTimer(getSandstormTimer() - 1);
                    }
                }
            }
            );
        }

        private void toggleFlight(LivingEntity living, boolean flight) {
            if (!living.level().isClientSide && living instanceof ServerPlayer player) {
                boolean prevFlying = player.getAbilities().flying;
                boolean trueFlight = isCreativePlayer(living) || flight;
                player.getAbilities().mayfly = trueFlight;
                player.getAbilities().flying = trueFlight;
                float defaultFlightSpeed = 0.05F;
                if (flight) {
                    player.getAbilities().setFlyingSpeed(defaultFlightSpeed * 0.5F);
                } else {
                    player.getAbilities().setFlyingSpeed(defaultFlightSpeed);
                    if (!player.isSpectator()) {
                        player.getAbilities().flying = false;
                        if(!player.isCreative()){
                            player.getAbilities().mayfly = false;
                        }
                    }
                }
                if (prevFlying != flight) {
                    player.onUpdateAbilities();
                }
            }
        }

        private boolean isCreativePlayer(LivingEntity living) {
            return living instanceof Player player && player.isCreative();
        }

        public MessageGoneWithSandstorm makeSyncMessage()
        {
            return new MessageGoneWithSandstorm(player.getId(), this,Timer);
        }

        public void sendSync() {
            if(player instanceof ServerPlayer serverPlayer) {
                Cataclysm.NETWORK_WRAPPER.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> serverPlayer), makeSyncMessage());
            }
        }

        @Override
        public void setSandstorm(boolean sandstorm) {
            this.Sandstorm = sandstorm;
            toggleFlight(player, sandstorm);
            sendSync();
        }

        @Override
        public boolean isSandstorm() {
            return this.Sandstorm;
        }

        @Override
        public void setSandstormTimer(int timer) {
            this.Timer = timer;
            sendSync();
        }

        @Override
        public int getSandstormTimer() {
            return Timer;
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag tag = new CompoundTag();
            tag.putBoolean("isSandstorm", this.isSandstorm());
            tag.putInt("Sandstorm_timer", this.getSandstormTimer());
            return tag;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            this.setSandstorm(nbt.getBoolean("isSandstorm"));
            this.setSandstormTimer(nbt.getInt("Sandstorm_timer"));
        }
    }
}
