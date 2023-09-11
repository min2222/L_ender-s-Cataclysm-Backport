package com.github.L_Ender.cataclysm.client.sound;

import com.github.L_Ender.cataclysm.ClientProxy;
import com.github.L_Ender.cataclysm.config.CMConfig;
import com.github.L_Ender.cataclysm.entity.BossMonsters.Netherite_Monstrosity_Entity;
import com.github.L_Ender.cataclysm.init.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundSource;

import java.util.Map;

public class SoundMonstrosityMusic extends AbstractTickableSoundInstance {
    private final Netherite_Monstrosity_Entity Monstrosity;
    private int ticksExisted = 0;
    public SoundMonstrosityMusic(Netherite_Monstrosity_Entity monstrosity) {
        super(ModSounds.MONSTROSITY_MUSIC.get(), SoundSource.RECORDS, monstrosity.getRandom());
        this.Monstrosity = monstrosity;
        this.attenuation = Attenuation.NONE;
        this.looping = true;
        this.delay = 0;
        this.x = this.Monstrosity.getX();
        this.y = this.Monstrosity.getY();
        this.z = this.Monstrosity.getZ();
    }

    public boolean shouldPlaySound() {
        return !this.Monstrosity.isSilent() && ClientProxy.MONSTROSITY_SOUND_MAP.get(this.Monstrosity.getId()) == this;
    }

    public boolean isNearest() {
        float dist = 400;
        for(Map.Entry<Integer, SoundMonstrosityMusic> entry : ClientProxy.MONSTROSITY_SOUND_MAP.entrySet()){
            SoundMonstrosityMusic MonstroMusic = entry.getValue();
            if(MonstroMusic != this && distanceSq(MonstroMusic.x, MonstroMusic.y, MonstroMusic.z) < dist * dist && MonstroMusic.shouldPlaySound()){
                return false;
            }
        }
        return true;
    }


    public double distanceSq(double p_218140_1_, double p_218140_3_, double p_218140_5_) {
        double lvt_10_1_ = (double)this.getX() - p_218140_1_;
        double lvt_12_1_ = (double)this.getY() - p_218140_3_;
        double lvt_14_1_ = (double)this.getZ() - p_218140_5_;
        return lvt_10_1_ * lvt_10_1_ + lvt_12_1_ * lvt_12_1_ + lvt_14_1_ * lvt_14_1_;
    }

    public void tick() {
        if(ticksExisted % 100 == 0){
            Minecraft.getInstance().getMusicManager().stopPlaying();

        }
        if (!this.Monstrosity.isRemoved() && this.Monstrosity.isAlive()) {
            this.volume = (float) CMConfig.MonstrosityMusicVolume;
            this.pitch = 1;
            this.x = this.Monstrosity.getX();
            this.y = this.Monstrosity.getY();
            this.z = this.Monstrosity.getZ();
        } else {
            this.stop();
            ClientProxy.MONSTROSITY_SOUND_MAP.remove(Monstrosity.getId());
        }
        ticksExisted++;
    }

}
