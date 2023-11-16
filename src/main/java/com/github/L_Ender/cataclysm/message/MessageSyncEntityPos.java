package com.github.L_Ender.cataclysm.message;

import com.github.L_Ender.cataclysm.Cataclysm;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageSyncEntityPos {

    public int entityId;
    public double posX;
    public double posY;
    public double posZ;

    public MessageSyncEntityPos(int eagleId, double posX, double posY, double posZ) {
        this.entityId = eagleId;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }

    public MessageSyncEntityPos() {
    }

    public static MessageSyncEntityPos read(FriendlyByteBuf buf) {
        return new MessageSyncEntityPos(buf.readInt(), buf.readDouble(), buf.readDouble(), buf.readDouble());
    }

    public static void write(MessageSyncEntityPos message, FriendlyByteBuf buf) {
        buf.writeInt(message.entityId);
        buf.writeDouble(message.posX);
        buf.writeDouble(message.posY);
        buf.writeDouble(message.posZ);
    }

    public static class Handler {
        public Handler() {
        }

        public static void handle(MessageSyncEntityPos message, Supplier<NetworkEvent.Context> context) {
            context.get().setPacketHandled(true);
            context.get().enqueueWork(() -> {
                Player player = context.get().getSender();
                if (context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                    player = Cataclysm.PROXY.getClientSidePlayer();
                }
                if (player != null) {
                    if (player.level() != null) {
                        Entity entity = player.level().getEntity(message.entityId);
                        if (entity !=null) {
                            entity.setPos(message.posX, message.posY, message.posZ);
                            entity.setDeltaMovement(Vec3.ZERO);
                        }
                    }
                }
            });
        }
    }
}