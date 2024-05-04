package com.github.L_Ender.cataclysm.util;

import java.util.Arrays;
import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

public class EntityExcludedDamageSource extends DamageSource {

	protected final List<EntityType<?>> entities;

	public EntityExcludedDamageSource(String msgId, EntityType<?>... entities) {
		super(msgId);
		this.entities = Arrays.stream(entities).toList();
	}

	@Override
	public Component getLocalizedDeathMessage(LivingEntity living) {
		LivingEntity livingentity = living.getKillCredit();
		String s = "death.attack." + this.msgId;
		String s1 = s + ".player";
		if (livingentity != null) {
			for (EntityType<?> entity : entities) {
				if (livingentity.getType() == entity) {
					return Component.translatable(s, living.getDisplayName());
				}
			}
		}
		return livingentity != null ? Component.translatable(s1, living.getDisplayName(), livingentity.getDisplayName()) : Component.translatable(s, living.getDisplayName());
	}
}