package com.github.L_Ender.cataclysm.items;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.List;
import java.util.Optional;

public class Meat_Shredder extends Item {
	private final Multimap<Attribute, AttributeModifier> whirligigsawAttributes;

	public Meat_Shredder(Properties properties) {
		super(properties);
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 6.5D, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", -2.8F, AttributeModifier.Operation.ADDITION));
		this.whirligigsawAttributes = builder.build();
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		if (hand == InteractionHand.MAIN_HAND) {
			player.startUsingItem(hand);
			return InteractionResultHolder.consume(player.getItemInHand(hand));
		} else {
			return InteractionResultHolder.fail(player.getItemInHand(hand));
		}
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}

	@Override
	public int getEnchantmentValue() {
		return 16;
	}

	public boolean canAttackBlock(BlockState state, Level worldIn, BlockPos pos, Player player) {
		return !player.isCreative();
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return super.canApplyAtEnchantingTable(stack, enchantment) || enchantment.category != EnchantmentCategory.BREAKABLE && enchantment.category == EnchantmentCategory.WEAPON && enchantment != Enchantments.SWEEPING_EDGE;
	}

	@Override
	public void onUseTick(Level level, LivingEntity living, ItemStack stack, int count) {
		double range = 2.5D;
		Vec3 srcVec = living.getEyePosition();
		Vec3 lookVec = living.getViewVector(1.0F);
		Vec3 destVec = srcVec.add(lookVec.x() * range, lookVec.y() * range, lookVec.z() * range);
		float var9 = 1.0F;
		List<Entity> possibleList = level.getEntities(living, living.getBoundingBox().expandTowards(lookVec.x() * range, lookVec.y() * range, lookVec.z() * range).inflate(var9, var9, var9));

		boolean flag = false;


		for (Entity entity : possibleList) {
			if (entity instanceof LivingEntity) {
				float borderSize = 0.5F;
				AABB collisionBB = entity.getBoundingBox().inflate(borderSize, borderSize, borderSize);
				Optional<Vec3> interceptPos = collisionBB.clip(srcVec, destVec);
				if (count % 5 == 0) {
					if (collisionBB.contains(srcVec)) {
						flag =true;
					} else if (interceptPos.isPresent()) {
						flag =true;
					}
					if (flag) {
						entity.hurt(level.damageSources().mobAttack(living), (float) living.getAttributeValue(Attributes.ATTACK_DAMAGE));
						entity.setDeltaMovement(0, 0, 0);

						double d0 = (level.getRandom().nextFloat() - 0.5F) + entity.getDeltaMovement().x;
						double d1 = (level.getRandom().nextFloat() - 0.5F) + entity.getDeltaMovement().y;
						double d2 = (level.getRandom().nextFloat() - 0.5F) + entity.getDeltaMovement().z;
						double dist = 1F + level.getRandom().nextFloat() * 0.2F;
						double d3 = d0 * dist;
						double d4 = d1 * dist;
						double d5 = d2 * dist;

						entity.level().addParticle(ParticleTypes.LAVA, entity.getX(), living.getEyeY() - 0.1D + (entity.getEyePosition().y - living.getEyeY()), entity.getZ(), d3, d4, d5);

					}
				}
			}
		}
	}

	public float getDestroySpeed(ItemStack p_41004_, BlockState p_41005_) {
		float speed = 15;

		return p_41005_.is(BlockTags.MINEABLE_WITH_AXE) ? speed : 1.0F;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	@Override
	public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
		consumer.accept((IClientItemExtensions) Cataclysm.PROXY.getISTERProperties());
	}

	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
		return equipmentSlot == EquipmentSlot.MAINHAND ? this.whirligigsawAttributes : super.getDefaultAttributeModifiers(equipmentSlot);
	}
}