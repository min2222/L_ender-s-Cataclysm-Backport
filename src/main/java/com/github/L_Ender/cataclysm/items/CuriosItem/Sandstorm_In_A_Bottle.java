package com.github.L_Ender.cataclysm.items.CuriosItem;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.capabilities.Gone_With_SandstormCapability;
import com.github.L_Ender.cataclysm.config.CMConfig;
import com.github.L_Ender.cataclysm.init.ModCapabilities;
import com.github.L_Ender.cataclysm.init.ModKeybind;
import com.github.L_Ender.cataclysm.items.UpdatesStackTags;
import com.github.L_Ender.cataclysm.message.MessageUpdateItemTag;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nullable;
import java.util.List;

public class Sandstorm_In_A_Bottle extends CuriosItem implements UpdatesStackTags {

    public Sandstorm_In_A_Bottle(Properties group) {
        super(group);

    }

    @Override
    public ICurio.SoundInfo getEquipSound(SlotContext slotContext, ItemStack stack) {
        return new ICurio.SoundInfo(SoundEvents.BOTTLE_FILL_DRAGONBREATH, 1.0F, 1.0F);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (livingEntity instanceof Player) {
                Gone_With_SandstormCapability.IGone_With_SandstormCapability SandstormCapability = ModCapabilities.getCapability(livingEntity, ModCapabilities.GONE_WITH_SANDSTORM_CAPABILITY);
                if (SandstormCapability != null) {
                    if (!livingEntity.level.isClientSide()) {
                        if (!isActivated(stack) && SandstormCapability.getSandstormTimer() <= 0) {
                            if (Cataclysm.PROXY.isKeyDown(2)) {
                                Cataclysm.sendMSGToAll(new MessageUpdateItemTag(livingEntity.getId(), stack));
                                setActivated(stack, true);
                                toggleFlight(livingEntity, true);
                            }
                        } else {
                            if (Cataclysm.PROXY.isKeyDown(2)) {
                                Cataclysm.sendMSGToAll(new MessageUpdateItemTag(livingEntity.getId(), stack));
                                setActivated(stack, false);
                                toggleFlight(livingEntity, false);
                            }
                        }

                    }
                    if (isActivated(stack)) {
                        if (SandstormCapability.getSandstormTimer() < CMConfig.Sandstorm_In_A_Bottle_Timer) {
                            SandstormCapability.setSandstorm(true);
                        } else {
                            Cataclysm.sendMSGToAll(new MessageUpdateItemTag(livingEntity.getId(), stack));
                            setActivated(stack, false);
                            toggleFlight(livingEntity, false);
                        }
                    } else {
                        SandstormCapability.setSandstorm(false);

                }
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        Gone_With_SandstormCapability.IGone_With_SandstormCapability SandstormCapability = ModCapabilities.getCapability(livingEntity, ModCapabilities.GONE_WITH_SANDSTORM_CAPABILITY);
        if (livingEntity instanceof Player) {
            if (!livingEntity.level.isClientSide()) {
                if (SandstormCapability != null) {
                    SandstormCapability.setSandstorm(false);
                    toggleFlight(livingEntity, false);
                }
                Cataclysm.sendMSGToAll(new MessageUpdateItemTag(livingEntity.getId(), stack));
                setActivated(stack, false);
            }
        }
    }


    private void toggleFlight(LivingEntity living, boolean flight) {
        if (!living.level.isClientSide && living instanceof ServerPlayer player) {
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

    public static boolean isActivated(ItemStack p_40933_) {
        CompoundTag compoundtag = p_40933_.getTag();
        return compoundtag != null && compoundtag.getBoolean("isActivated");
    }

    public static void setActivated(ItemStack p_40885_, boolean p_40886_) {
        CompoundTag compoundtag = p_40885_.getOrCreateTag();
        compoundtag.putBoolean("isActivated", p_40886_);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("item.cataclysm.sandstorm_in_a_bottle.desc",ModKeybind.KEY_ABILITY.getTranslatedKeyMessage()).withStyle(ChatFormatting.DARK_GREEN));
    }

}
