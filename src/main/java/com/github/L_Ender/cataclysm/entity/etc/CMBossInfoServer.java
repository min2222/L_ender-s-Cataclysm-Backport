package com.github.L_Ender.cataclysm.entity.etc;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import com.github.L_Ender.cataclysm.Cataclysm;

import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.Mob;

public class CMBossInfoServer extends ServerBossEvent {
    private final Mob entity;

    private final Set<ServerPlayer> unseen = new HashSet<>();
    private UUID id;

    public CMBossInfoServer(UUID uuid, Mob entity, BossBarColor bossBarColor, boolean dark) {
        super(entity.getDisplayName(), bossBarColor, BossBarOverlay.PROGRESS);
        this.setVisible(true);
        this.setId(uuid);
        this.setDarkenScreen(dark);
        this.entity = entity;
    }

    public void setId(UUID uuid){
        this.id = uuid;
    }

    @Override
    public UUID getId() {
        return this.id;
    }


    public void update() {
        this.setProgress(this.entity.getHealth() / this.entity.getMaxHealth());
        Iterator<ServerPlayer> it = this.unseen.iterator();
        while (it.hasNext()) {
            ServerPlayer player = it.next();
            if (this.entity.getSensing().hasLineOfSight(player)) {
                super.addPlayer(player);
                if (this.entity.level.isClientSide){
                    Cataclysm.PROXY.addBoss(this.entity);
                }
                it.remove();
            }
        }
    }

    @Override
    public void addPlayer(ServerPlayer player) {
        if (this.entity.getSensing().hasLineOfSight(player)) {
            super.addPlayer(player);
            if (this.entity.level.isClientSide){
                Cataclysm.PROXY.addBoss(this.entity);
            }
        } else {
            this.unseen.add(player);
        }
    }

    @Override
    public void removePlayer(ServerPlayer player) {
        super.removePlayer(player);
        this.unseen.remove(player);
        if (this.entity.level.isClientSide){
            Cataclysm.PROXY.removeBoss(this.entity);
        }
    }
}
