package com.github.L_Ender.cataclysm.items.CuriosItem;

import com.github.L_Ender.cataclysm.capabilities.Gone_With_SandstormCapability;
import com.github.L_Ender.cataclysm.init.ModCapabilities;
import com.github.L_Ender.cataclysm.init.ModKeybind;
import net.minecraft.ChatFormatting;
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

public class Sandstorm_In_A_Bottle extends CuriosItem {

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
        Gone_With_SandstormCapability.IGone_With_SandstormCapability SandstormCapability = ModCapabilities.getCapability(livingEntity, ModCapabilities.GONE_WITH_SANDSTORM_CAPABILITY);
        if (SandstormCapability != null) {
            if (!livingEntity.level().isClientSide()) {
                if (!SandstormCapability.isSandstorm() && SandstormCapability.getSandstormTimer() <= 0) {
                    if (ModKeybind.KEY_ABILITY.consumeClick()) {
                        SandstormCapability.setSandstorm(true);
                        toggleFlight(livingEntity, true);
                    }
                } else {
                    if (ModKeybind.KEY_ABILITY.consumeClick()) {
                        SandstormCapability.setSandstorm(false);
                        toggleFlight(livingEntity, false);
                    }
                }
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        Gone_With_SandstormCapability.IGone_With_SandstormCapability SandstormCapability = ModCapabilities.getCapability(livingEntity, ModCapabilities.GONE_WITH_SANDSTORM_CAPABILITY);
        if (!livingEntity.level().isClientSide()) {
            if (SandstormCapability != null) {
                SandstormCapability.setSandstorm(false);
                toggleFlight(livingEntity, false);
            }
        }
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

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("item.cataclysm.sandstorm_in_a_bottle.desc",ModKeybind.KEY_ABILITY.getTranslatedKeyMessage()).withStyle(ChatFormatting.DARK_GREEN));
    }

}
