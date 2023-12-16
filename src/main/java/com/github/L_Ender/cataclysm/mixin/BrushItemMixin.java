package com.github.L_Ender.cataclysm.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.L_Ender.cataclysm.entity.Pet.Modern_Remnant_Entity;
import com.min01.archaeology.block.BrushableBlock;
import com.min01.archaeology.blockentity.BrushableBlockEntity;
import com.min01.archaeology.init.ArchaeologyItems;
import com.min01.archaeology.init.ArchaeologySounds;
import com.min01.archaeology.item.BrushItem;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

@Mixin(BrushItem.class)
public class BrushItemMixin {
	@Inject(method = { "onUseTick" }, at = @At(value = "HEAD"), cancellable = true)
	public void onUseTick(Level p_273467_, LivingEntity p_273619_, ItemStack p_273316_, int p_273101_, CallbackInfo ci) 
	{
		ci.cancel();
		if (p_273101_ >= 0 && p_273619_ instanceof Player player) 
		{
			HitResult hitresult = this.calculateHitResult(p_273619_);
			if (hitresult instanceof BlockHitResult blockhitresult)
			{
				if (hitresult.getType() == HitResult.Type.BLOCK) 
				{
					int i = BrushItem.class.cast(this).getUseDuration(p_273316_) - p_273101_ + 1;
					boolean flag = i % 10 == 5;
					if (flag) 
					{
						BlockPos blockpos = blockhitresult.getBlockPos();
						BlockState blockstate = p_273467_.getBlockState(blockpos);
						HumanoidArm humanoidarm = p_273619_.getUsedItemHand() == InteractionHand.MAIN_HAND ? player.getMainArm() : player.getMainArm().getOpposite();
						this.spawnDustParticles(p_273467_, blockhitresult, blockstate, p_273619_.getViewVector(0.0F), humanoidarm);
						Block $$18 = blockstate.getBlock();
						SoundEvent soundevent;
						if ($$18 instanceof BrushableBlock) 
						{
							BrushableBlock brushableblock = (BrushableBlock) $$18;
							soundevent = brushableblock.getBrushSound();
						}
						else 
						{
							soundevent = ArchaeologySounds.BRUSH_GENERIC.get();
						}

						p_273467_.playSound(player, blockpos, soundevent, SoundSource.BLOCKS, 1, 1);
						if (!p_273467_.isClientSide()) 
						{
							BlockEntity blockentity = p_273467_.getBlockEntity(blockpos);
							if (blockentity instanceof BrushableBlockEntity) 
							{
								BrushableBlockEntity brushableblockentity = (BrushableBlockEntity) blockentity;
								boolean flag1 = brushableblockentity.brush(p_273467_.getGameTime(), player, blockhitresult.getDirection());
								if (flag1) 
								{
									EquipmentSlot equipmentslot = p_273316_ .equals(player.getItemBySlot(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
									p_273316_.hurtAndBreak(1, p_273619_, (p_279044_) -> {
										p_279044_.broadcastBreakEvent(equipmentslot);
									});
								}
							}
						}
					}

					return;
				}
			}
			else if(hitresult instanceof EntityHitResult entityhitresult)
			{
				if (hitresult.getType() == HitResult.Type.ENTITY) 
				{
					Entity entity = entityhitresult.getEntity();
					if(entity instanceof Modern_Remnant_Entity remnant)
					{
						int i = BrushItem.class.cast(this).getUseDuration(p_273316_) - p_273101_ + 1;
						boolean flag = i % 10 == 5;
						if (flag) 
						{
							HumanoidArm humanoidarm = p_273619_.getUsedItemHand() == InteractionHand.MAIN_HAND ? player.getMainArm() : player.getMainArm().getOpposite();
							this.spawnDustParticlesToRemnant(p_273467_, remnant, humanoidarm);
							p_273467_.playSound(player, remnant.blockPosition(), ArchaeologySounds.BRUSH_GENERIC.get(), SoundSource.BLOCKS, 1, 1);
					        if (!remnant.isTame() && p_273316_.getItem() == ArchaeologyItems.BRUSH.get()) {
					            //this.usePlayerItem(player, hand, itemstack);
					        	if(!player.getAbilities().instabuild) {
					        		p_273316_.hurtAndBreak(1, player, (p_219739_) -> {
					                    p_219739_.broadcastBreakEvent(p_273619_.getUsedItemHand());
					                    net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, p_273316_, p_273619_.getUsedItemHand());
					                 });
					        	}
					        	remnant.gameEvent(GameEvent.EAT);
					            if (!net.minecraftforge.event.ForgeEventFactory.onAnimalTame(remnant, player) && i >= 60) {
					            	remnant.tame(player);
					                remnant.level.broadcastEntityEvent(remnant, (byte) 7);
					            } else {
					            	remnant.level.broadcastEntityEvent(remnant, (byte) 6);
					            }
					        }
						}
					}

					return;
				}
			}

			p_273619_.releaseUsingItem();
		} 
		else
		{
			p_273619_.releaseUsingItem();
		}
	}
	
	@Unique
	private void spawnDustParticlesToRemnant(Level p_278327_, Modern_Remnant_Entity p_278272_, HumanoidArm p_285071_) 
	{
		int i = p_285071_ == HumanoidArm.RIGHT ? 1 : -1;
		int j = p_278327_.getRandom().nextInt(7, 12);
		BlockParticleOption blockparticleoption = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.SAND.defaultBlockState());
		for(int k = 0; k < j; ++k)
		{
			p_278327_.addParticle(blockparticleoption, p_278272_.getX(), p_278272_.getEyeY(), p_278272_.getZ(), (double)i * 3.0D * p_278327_.getRandom().nextDouble(), 0.0D, (double)i * 3.0D * p_278327_.getRandom().nextDouble());
		}
	}
	
	@Shadow
	public void spawnDustParticles(Level p_278327_, BlockHitResult p_278272_, BlockState p_278235_, Vec3 p_278337_, HumanoidArm p_285071_)
	{
		
	}

	@Shadow
	private HitResult calculateHitResult(LivingEntity p_281264_)
	{
		throw new IllegalStateException();
	}
}
