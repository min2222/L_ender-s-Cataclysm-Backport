package com.github.L_Ender.cataclysm.message;

import java.util.function.Supplier;

import com.github.L_Ender.cataclysm.capabilities.HoldAttackCapability;
import com.github.L_Ender.cataclysm.init.ModCapabilities;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

public class MessageHoldEntity {

	private final int entityID;
	private final boolean hold;
	private int holder = 0;
	private Vec3 PosVector;

	public MessageHoldEntity(int id, HoldAttackCapability.IHoldAttackCapability cap) {
		this.entityID = id;
		this.hold = cap.getHold();
		this.PosVector = cap.getPosVector();
		if(cap.getHolder() != null) {
			this.holder = cap.getHolder().getId();
		}
	}

	public MessageHoldEntity(Entity entity, HoldAttackCapability.IHoldAttackCapability cap) {
		this(entity.getId(), cap);
	}

	public MessageHoldEntity(FriendlyByteBuf buf) {
		this.entityID = buf.readInt();
		this.hold = buf.readBoolean();
		this.holder = buf.readInt();
		this.PosVector = new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());

	}

	public void write(FriendlyByteBuf buf) {
		buf.writeInt(this.entityID);
		buf.writeBoolean(this.hold);
		buf.writeInt(this.holder);
		buf.writeDouble(this.PosVector.x);
		buf.writeDouble(this.PosVector.y);
		buf.writeDouble(this.PosVector.z);
	}

	public static class Handler {

		public static boolean handle(MessageHoldEntity message, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				Entity entity = Minecraft.getInstance().level.getEntity(message.entityID);
				if (entity instanceof LivingEntity) {
					entity.getCapability(ModCapabilities.HOLD_ATTACK_CAPABILITY).ifPresent(cap -> {
						LivingEntity holder = message.holder != 0 ? (LivingEntity) Minecraft.getInstance().level.getEntity(message.holder) : null;
						if (entity instanceof Player) {
							cap.setHold(message.hold, holder,message.PosVector);
						}
					});
				}
			});

			ctx.get().setPacketHandled(true);
			return true;
		}
	}
}
