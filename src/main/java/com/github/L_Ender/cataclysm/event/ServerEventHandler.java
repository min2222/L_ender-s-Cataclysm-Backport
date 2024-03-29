package com.github.L_Ender.cataclysm.event;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.capabilities.Bloom_Stone_PauldronsCapability;
import com.github.L_Ender.cataclysm.capabilities.ChargeCapability;
import com.github.L_Ender.cataclysm.capabilities.Gone_With_SandstormCapability;
import com.github.L_Ender.cataclysm.capabilities.HoldAttackCapability;
import com.github.L_Ender.cataclysm.capabilities.HookCapability;
import com.github.L_Ender.cataclysm.init.ModBlocks;
import com.github.L_Ender.cataclysm.init.ModCapabilities;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.L_Ender.cataclysm.items.ILeftClick;
import com.github.L_Ender.cataclysm.message.MessageSwingArm;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Cataclysm.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEventHandler {


    @SubscribeEvent
    public void onLivingUpdateEvent(LivingEvent.LivingTickEvent event) {
        int p_45022_ = 2;
        final BlockPos p_45021_ = event.getEntity().blockPosition();
        if (!event.getEntity().getItemBySlot(EquipmentSlot.FEET).isEmpty() && event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.IGNITIUM_BOOTS.get()) {
            if(!event.getEntity().isShiftKeyDown()){
            if (event.getEntity().isOnGround()) {
                BlockState blockstate = ModBlocks.MELTING_NETHERRACK.get().defaultBlockState();
                int f = Math.min(16, 2 + p_45022_);
                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
                for(BlockPos blockpos : BlockPos.betweenClosed(p_45021_.offset(-f, -1, -f), p_45021_.offset(f, -1, f))) {
                    if (blockpos.closerToCenterThan(event.getEntity().position(), (double) f)) {
                        blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                        BlockState blockstate1 = event.getEntity().level.getBlockState(blockpos$mutableblockpos);
                        if (blockstate1.isAir()) {
                            BlockState blockstate2 = event.getEntity().level.getBlockState(blockpos);
                            boolean isFull = blockstate2.getBlock() == Blocks.LAVA && blockstate2.getValue(LiquidBlock.LEVEL) == 0; //TODO: Forge, modded waters?
                            if (blockstate2 == Blocks.LAVA.defaultBlockState() && isFull && blockstate.canSurvive(event.getEntity().level, blockpos) && event.getEntity().level.isUnobstructed(blockstate, blockpos, CollisionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(event.getEntity(), net.minecraftforge.common.util.BlockSnapshot.create(event.getEntity().level.dimension(), event.getEntity().level, blockpos), net.minecraft.core.Direction.UP)) {
                                event.getEntity().level.setBlockAndUpdate(blockpos, blockstate);
                                event.getEntity().level.scheduleTick(blockpos, ModBlocks.MELTING_NETHERRACK.get(), Mth.nextInt(event.getEntity().getRandom(), 60, 120));
                            }

                        }
                    }
                }
            }
            }
        }
        HookCapability.IHookCapability hookCapability = ModCapabilities.getCapability(event.getEntity(), ModCapabilities.HOOK_CAPABILITY);
        if (hookCapability != null) {
            hookCapability.tick(event.getEntity());
        }

        ChargeCapability.IChargeCapability chargeCapability = ModCapabilities.getCapability(event.getEntity(), ModCapabilities.CHARGE_CAPABILITY);
        if (chargeCapability != null) {
            chargeCapability.tick(event.getEntity());
        }

        HoldAttackCapability.IHoldAttackCapability HoldAttackCapability = ModCapabilities.getCapability(event.getEntity(), ModCapabilities.HOLD_ATTACK_CAPABILITY);
        if (HoldAttackCapability != null) {
            HoldAttackCapability.tick(event.getEntity());
        }

    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Bloom_Stone_PauldronsCapability.IBloom_Stone_PauldronsCapability chargeCapability = ModCapabilities.getCapability(player, ModCapabilities.BLOOM_STONE_PAULDRONS_CAPABILITY_CAPABILITY);
        if (chargeCapability != null) {
            chargeCapability.tick(event);
        }
        Gone_With_SandstormCapability.IGone_With_SandstormCapability SandstormCapability = ModCapabilities.getCapability(player, ModCapabilities.GONE_WITH_SANDSTORM_CAPABILITY);
        if (SandstormCapability != null) {
            SandstormCapability.tick(event);
        }
    }


    @SubscribeEvent
    public void onLivingDamage(LivingHurtEvent event) {
        LivingEntity target = event.getEntity();
        if (!target.level.isClientSide() && event.getSource().getDirectEntity() instanceof LivingEntity living) {
            ItemStack weapon = living.getMainHandItem();

            if (!weapon.isEmpty()) {
                if ((weapon.is(ModItems.ZWEIENDER.get()))) {
                    Vec3 lookDir = new Vec3(target.getLookAngle().x, 0, target.getLookAngle().z).normalize();
                    Vec3 vecBetween = new Vec3(target.getX() - living.getX(), 0, target.getZ() - living.getZ()).normalize();
                    double dot = lookDir.dot(vecBetween);
                    if (dot > 0.05) {
                        event.setAmount(event.getAmount() * 2);
                        target.playSound(SoundEvents.ENDERMAN_TELEPORT, 0.75F, 0.5F);
                    }
                    // enchantment attack sparkles
                }

                if ((weapon.is(ModItems.FINAL_FRACTAL.get()))) {
                    event.setAmount(event.getAmount() + target.getMaxHealth() * 0.03f);
                }

            }
        }

    }

    @SubscribeEvent
    public void Knockbackevent(LivingKnockBackEvent event) {
        if(event.getEntity().getCombatTracker().getLastEntry() != null) {
            if(event.getEntity().getCombatTracker().getLastEntry().getSource().getMsgId().equals("cataclysm.shredder")) {
                event.setCanceled(true);
            }

        }
    }

    @SubscribeEvent
    public void onPlayerAttack(AttackEntityEvent event) {
        if (event.isCancelable() && event.getEntity().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void BlockHeal(LivingHealEvent event) {
        if (event.getEntity().hasEffect(ModEffect.EFFECTABYSSAL_FEAR.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onLivingJump(LivingEvent.LivingJumpEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.getEffect(ModEffect.EFFECTSTUN.get()) != null){
            entity.setDeltaMovement(entity.getDeltaMovement().x(), 0.0D, entity.getDeltaMovement().z());
        }
    }

    @SubscribeEvent
    public void onPlayerLeftClick(PlayerInteractEvent.LeftClickBlock event) {
        Player player = event.getEntity();
        if (event.isCancelable() && player.hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onUseItem(LivingEntityUseItemEvent event) {
        LivingEntity living = event.getEntity();
        if (event.isCancelable() && living.hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlaceBlock(BlockEvent.EntityPlaceEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) entity;
            if (event.isCancelable() && living.hasEffect(ModEffect.EFFECTSTUN.get())) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onFillBucket(FillBucketEvent event) {
        LivingEntity living = event.getEntity();
        if (living != null) {
            if (event.isCancelable() && living.hasEffect(ModEffect.EFFECTSTUN.get())) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onBreakBlock(BlockEvent.BreakEvent event) {
        if (event.isCancelable() && event.getPlayer().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.RightClickEmpty event) {
        if (event.isCancelable() && event.getEntity().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.LeftClickEmpty event) {
        if (event.isCancelable() && event.getEntity().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
        boolean flag = false;
        ItemStack leftItem = event.getEntity().getOffhandItem();
        ItemStack rightItem = event.getEntity().getMainHandItem();
        if(!event.getEntity().hasEffect(ModEffect.EFFECTSTUN.get())){
            if (leftItem.getItem() instanceof ILeftClick) {
                ((ILeftClick) leftItem.getItem()).onLeftClick(leftItem, event.getEntity());
                flag = true;
            }
            if (rightItem.getItem() instanceof ILeftClick) {
                ((ILeftClick) rightItem.getItem()).onLeftClick(rightItem, event.getEntity());
                flag = true;
            }
            if (event.getLevel().isClientSide && flag) {
                Cataclysm.sendMSGToServer(MessageSwingArm.INSTANCE);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.isCancelable() && event.getEntity().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.RightClickBlock event) {
        if (event.isCancelable() && event.getEntity().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.LeftClickBlock event) {
        if (event.isCancelable() && event.getEntity().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.getHealth() <= event.getAmount() && entity.hasEffect(ModEffect.EFFECTSTUN.get())) {
            entity.removeEffect(ModEffect.EFFECTSTUN.get());
        }
        if (!event.getEntity().getItemBySlot(EquipmentSlot.LEGS).isEmpty() && event.getSource() != null && event.getSource().getEntity() != null) {
            if(event.getEntity().getItemBySlot(EquipmentSlot.LEGS).getItem() == ModItems.IGNITIUM_LEGGINGS.get()){
                Entity attacker = event.getSource().getEntity();
                if (attacker instanceof LivingEntity && attacker != event.getEntity()) {
                    if (event.getEntity().getRandom().nextFloat() < 0.5F) {
                        MobEffectInstance effectinstance1 = ((LivingEntity) attacker).getEffect(ModEffect.EFFECTBLAZING_BRAND.get());
                        int i = 1;
                        if (effectinstance1 != null) {
                            i += effectinstance1.getAmplifier();
                            ((LivingEntity) attacker).removeEffectNoUpdate(ModEffect.EFFECTBLAZING_BRAND.get());
                        } else {
                            --i;
                        }

                        i = Mth.clamp(i, 0, 2);
                        MobEffectInstance effectinstance = new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), 100, i, false, false, true);
                        ((LivingEntity) attacker).addEffect(effectinstance);

                        if (!attacker.isOnFire()) {
                            attacker.setSecondsOnFire(5);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.RightClickItem event) {
        if (event.isCancelable() && event.getEntity().hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }



    @SubscribeEvent
    public void onAddReloadListener(AddReloadListenerEvent event){
        Cataclysm.LOGGER.info("Adding datapack listener altar_of_amethyst_recipes");
        event.addListener(Cataclysm.PROXY.getAltarOfAmethystRecipeManager());
    }
}


