package com.github.L_Ender.cataclysm.message;

import java.util.function.Supplier;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.blockentities.TileEntityAltarOfAbyss;
import com.github.L_Ender.cataclysm.blockentities.TileEntityAltarOfAmethyst;
import com.github.L_Ender.cataclysm.blockentities.TileEntityAltarOfFire;
import com.github.alexthe666.citadel.server.message.PacketBufferUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public class MessageUpdateblockentity {

    public long blockPos;
    public ItemStack heldStack;

    public MessageUpdateblockentity(long blockPos, ItemStack heldStack) {
        this.blockPos = blockPos;
        this.heldStack = heldStack;

    }

    public MessageUpdateblockentity() {
    }

    public static MessageUpdateblockentity read(FriendlyByteBuf buf) {
        return new MessageUpdateblockentity(buf.readLong(), PacketBufferUtils.readItemStack(buf));
    }

    public static void write(MessageUpdateblockentity message, FriendlyByteBuf buf) {
        buf.writeLong(message.blockPos);
        PacketBufferUtils.writeItemStack(buf, message.heldStack);
    }

    public static class Handler {
        public Handler() {
        }

        public static void handle(MessageUpdateblockentity message, Supplier<NetworkEvent.Context> context) {
            context.get().setPacketHandled(true);
            Player player = context.get().getSender();
            if(context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT){
                player = Cataclysm.PROXY.getClientSidePlayer();
            }
            if (player != null) {
                if (player.level != null) {
                    BlockPos pos = BlockPos.of(message.blockPos);
                    if (player.level.getBlockEntity(pos) != null) {
                        if (player.level.getBlockEntity(pos) instanceof TileEntityAltarOfFire) {
                            TileEntityAltarOfFire podium = (TileEntityAltarOfFire) player.level.getBlockEntity(pos);
                            podium.setItem(0, message.heldStack);
                        }
                        if (player.level.getBlockEntity(pos) instanceof TileEntityAltarOfAmethyst) {
                            TileEntityAltarOfAmethyst podium = (TileEntityAltarOfAmethyst) player.level.getBlockEntity(pos);
                            podium.setItem(0, message.heldStack);
                        }
                        if (player.level.getBlockEntity(pos) instanceof TileEntityAltarOfAbyss) {
                            TileEntityAltarOfAbyss podium = (TileEntityAltarOfAbyss) player.level.getBlockEntity(pos);
                            podium.setItem(0, message.heldStack);
                        }
                    }
                }
            }
        }
    }

}