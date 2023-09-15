package com.github.L_Ender.cataclysm;

import com.github.L_Ender.cataclysm.crafting.AltarOfAmethystRecipeManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.common.Mod;

import static com.github.L_Ender.cataclysm.Cataclysm.MODID;


@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {

    private AltarOfAmethystRecipeManager AltarOfAmethystManager;

    public void init() {
    }
    public Player getClientSidePlayer() {
        return null;
    }

    public void clientInit() {
    }

    public Object getISTERProperties() {
        return null;
    }

    public Object getArmorRenderProperties() {
        return null;
    }

    public void onEntityStatus(Entity entity, byte updateKind) {
    }

    public AltarOfAmethystRecipeManager getAltarOfAmethystRecipeManager(){
        if(AltarOfAmethystManager == null){
            AltarOfAmethystManager = new AltarOfAmethystRecipeManager();
        }
        return AltarOfAmethystManager;
    }

    public void addBoss(Mob mob) {
    }

    public void removeBoss(Mob mob) {
    }

}
