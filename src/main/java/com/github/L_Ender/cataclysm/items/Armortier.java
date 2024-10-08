package com.github.L_Ender.cataclysm.items;

import java.util.function.Supplier;

import com.github.L_Ender.cataclysm.init.ModItems;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;


 public enum Armortier implements ArmorMaterial {
     IGNITIUM(new int[] {6, 11, 9, 6}, 4.0f, 45, 15, 0.15f, SoundEvents.ARMOR_EQUIP_NETHERITE , ModItems.IGNITIUM_INGOT),
     CURSIUM(new int[] {5, 10, 8, 5}, 4.0f, 45, 15, 0.05f, SoundEvents.ARMOR_EQUIP_NETHERITE , ModItems.CURSIUM_INGOT),
     CRAB(new int[] {3, 6, 8, 3}, 2.0f, 30, 15, 0.1f, SoundEvents.ARMOR_EQUIP_IRON , ModItems.AMETHYST_CRAB_SHELL),
     BONE_REPTILE(new int[] {4, 7, 11, 6}, 3f, 35, 15, 0.2f, SoundEvents.ARMOR_EQUIP_NETHERITE , ModItems.ANCIENT_METAL_INGOT);

     private static final int[] DURABILITY_ARRAY = new int[] {13, 15, 16, 11};
     private final int durability, enchantability;
     private final int[] dmgReduction; // helm[0], chest[1], leggings[2], boots[3]
     private final float toughness, knockbackResistance;
     private final SoundEvent sound;
     private final Supplier<Item> repairMaterial;

     Armortier(int[] dmgReduction, float toughness, int durability, int enchantability, float knockbackResistance, SoundEvent sound, Supplier<Item> repairMaterial)
     {
         this.durability = durability;
         this.dmgReduction = dmgReduction;
         this.enchantability = enchantability;
         this.toughness = toughness;
         this.knockbackResistance = knockbackResistance;
         this.sound = sound;
         this.repairMaterial = repairMaterial;
     }

     @Override
     public int getDurabilityForSlot(EquipmentSlot type) {
         return DURABILITY_ARRAY[type.getIndex()] * this.durability;
     }

     @Override
     public int getDefenseForSlot(EquipmentSlot type) {
         return this.dmgReduction[type.getIndex()];
     }

     @Override
     public int getEnchantmentValue()
     {
         return enchantability;
     }

     @Override
     public SoundEvent getEquipSound()
     {
         return sound;
     }

     @Override
     public Ingredient getRepairIngredient()
     {
         return Ingredient.of(repairMaterial.get());
     }

     @Override
     public String getName()
     {
         return toString().toLowerCase();
     }

     @Override
     public float getToughness()
     {
         return toughness;
     }

     @Override
     public float getKnockbackResistance()
     {
         return knockbackResistance;
     }
 }



