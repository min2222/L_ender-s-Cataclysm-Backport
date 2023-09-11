package com.github.L_Ender.cataclysm.items;

import com.github.L_Ender.cataclysm.Cataclysm;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class Bloom_Stone_Pauldrons extends ArmorItem {

    public Bloom_Stone_Pauldrons(Armortier material, ArmorItem.Type slot, Properties properties) {
        super(material, slot, properties);

    }

    @Override
    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) Cataclysm.PROXY.getArmorRenderProperties());
    }


    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return Cataclysm.MODID + ":textures/armor/bloom_stone_pauldrons.png";
    }
}