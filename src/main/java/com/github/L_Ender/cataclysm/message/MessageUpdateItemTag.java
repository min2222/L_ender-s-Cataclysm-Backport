package com.github.L_Ender.cataclysm.message;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.items.UpdatesStackTags;
import com.github.alexthe666.citadel.server.message.PacketBufferUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageUpdateItemTag {

    private int entityId;
    private ItemStack itemStackFrom;

    public MessageUpdateItemTag(int entityId, ItemStack itemStackFrom) {
        this.entityId = entityId;
        this.itemStackFrom = itemStackFrom;
    }


    public static MessageUpdateItemTag read(FriendlyByteBuf buf) {
        return new MessageUpdateItemTag(buf.readInt(), PacketBufferUtils.readItemStack(buf));
    }

    public static void write(MessageUpdateItemTag message, FriendlyByteBuf buf) {
        buf.writeInt(message.entityId);
        PacketBufferUtils.writeItemStack(buf, message.itemStackFrom);
    }

    public static void handle(MessageUpdateItemTag message, Supplier<NetworkEvent.Context> context) {
        context.get().setPacketHandled(true);
        Player playerSided = context.get().getSender();
        if (context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
            playerSided = Cataclysm.PROXY.getClientSidePlayer();
        }
        if(playerSided != null){

            Entity holder = playerSided.level.getEntity(message.entityId);

            if (holder instanceof LivingEntity living) {
                ItemStack stackFrom = message.itemStackFrom;
                ItemStack to = null;
                if(living.getItemInHand(InteractionHand.MAIN_HAND).is(stackFrom.getItem())){
                    to = living.getItemInHand(InteractionHand.MAIN_HAND);
                }else if(living.getItemInHand(InteractionHand.OFF_HAND).is(stackFrom.getItem())){
                    to = living.getItemInHand(InteractionHand.OFF_HAND);
                }
                if(to != null && to.getItem() instanceof UpdatesStackTags updatesStackTags && stackFrom.getTag() != null){
                    updatesStackTags.updateTagFromServer(holder, to, stackFrom.getTag());
                }
            }
        }
    }

}
