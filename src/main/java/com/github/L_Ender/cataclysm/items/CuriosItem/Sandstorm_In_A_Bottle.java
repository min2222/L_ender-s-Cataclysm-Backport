package com.github.L_Ender.cataclysm.items.CuriosItem;

import com.github.L_Ender.cataclysm.capabilities.Gone_With_SandstormCapability;
import com.github.L_Ender.cataclysm.init.ModCapabilities;
import com.github.L_Ender.cataclysm.init.ModKeybind;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

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
                    }
                } else {
                    if (ModKeybind.KEY_ABILITY.consumeClick()) {
                        SandstormCapability.setSandstorm(false);
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
            }
        }
    }

}
