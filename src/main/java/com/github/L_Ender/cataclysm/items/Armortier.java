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
     IGNITIUM(new int[] {5, 10, 8, 5}, 4.0f, 45, 15, 0.15f, SoundEvents.ARMOR_EQUIP_NETHERITE , ModItems.IGNITIUM_INGOT),
     CRAB(new int[] {3, 8, 6, 3}, 2.0f, 30, 25, 0.1f, SoundEvents.ARMOR_EQUIP_NETHERITE , ModItems.AMETHYST_CRAB_SHELL);

     private static final int[] DURABILITY_ARRAY = new int[] {13, 16, 15, 11};
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



