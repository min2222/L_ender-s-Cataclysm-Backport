package com.github.L_Ender.cataclysm.client.sound;

import com.github.L_Ender.cataclysm.ClientProxy;
import com.github.L_Ender.cataclysm.config.CMConfig;
import com.github.L_Ender.cataclysm.entity.BossMonsters.Ignis_Entity;
import com.github.L_Ender.cataclysm.init.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundSource;

import java.util.Map;

public class SoundIgnisMusic extends AbstractTickableSoundInstance {
    private final Ignis_Entity Ignis;
    private int ticksExisted = 0;
    public SoundIgnisMusic(Ignis_Entity ignis) {
        super(ModSounds.IGNIS_MUSIC.get(), SoundSource.RECORDS, ignis.getRandom());
        this.Ignis = ignis;
        this.attenuation = Attenuation.NONE;
        this.looping = true;
        this.delay = 0;
        this.x = this.Ignis.getX();
        this.y = this.Ignis.getY();
        this.z = this.Ignis.getZ();
    }

    public boolean shouldPlaySound() {
        return !this.Ignis.isSilent() && ClientProxy.IGNIS_SOUND_MAP.get(this.Ignis.getId()) == this;
    }

    public boolean isNearest() {
        float dist = 400;
        for(Map.Entry<Integer, SoundIgnisMusic> entry : ClientProxy.IGNIS_SOUND_MAP.entrySet()){
            SoundIgnisMusic IgnisMusic = entry.getValue();
            if(IgnisMusic != this && distanceSq(IgnisMusic.x, IgnisMusic.y, IgnisMusic.z) < dist * dist && IgnisMusic.shouldPlaySound()){
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
        if (!this.Ignis.isRemoved() && this.Ignis.isAlive()) {
            this.volume = (float) CMConfig.IgnisMusicVolume;
            this.pitch = 1;
            this.x = this.Ignis.getX();
            this.y = this.Ignis.getY();
            this.z = this.Ignis.getZ();
        } else {
            this.stop();
            ClientProxy.IGNIS_SOUND_MAP.remove(Ignis.getId());
        }
        ticksExisted++;
    }

}
