package com.github.L_Ender.cataclysm.entity.util;

import java.util.UUID;

import com.github.L_Ender.cataclysm.entity.projectile.Tidal_Tentacle_Entity;
import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.entity.CitadelEntityData;
import com.github.alexthe666.citadel.server.message.PropertiesMessage;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class TidalTentacleUtil {

    private static final String LAST_TENTACLE_UUID = "LastTidalTentacleUUIDCataclysm";
    private static final String LAST_TENTACLE_ID = "LastTidalTentacleIDCataclysm";

    private static void sync(LivingEntity enchanted, CompoundTag tag) {
        CitadelEntityData.setCitadelTag(enchanted, tag);
        if (!enchanted.level.isClientSide) {
            Citadel.sendMSGToAll(new PropertiesMessage("CataclysmTagUpdate", tag, enchanted.getId()));
        } else {
            Citadel.sendMSGToServer(new PropertiesMessage("CataclysmTagUpdate", tag, enchanted.getId()));
        }
    }

    public static void setLastTentacle(LivingEntity entity, Tidal_Tentacle_Entity tendon) {
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag(entity);
        if (tendon == null) {
            tag.remove(LAST_TENTACLE_UUID);
            tag.putInt(LAST_TENTACLE_ID, -1);
        } else {
            tag.putUUID(LAST_TENTACLE_UUID, tendon.getUUID());
            tag.putInt(LAST_TENTACLE_ID, tendon.getId());
        }
        sync(entity, tag);
    }

    private static UUID getLastTentacleUUID(LivingEntity entity) {
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag(entity);
        if (tag.contains(LAST_TENTACLE_UUID)) {
            return tag.getUUID(LAST_TENTACLE_UUID);
        } else {
            return null;
        }
    }

    public static int getLastTentacleId(LivingEntity entity) {
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag(entity);
        if (tag.contains(LAST_TENTACLE_ID)) {
            return tag.getInt(LAST_TENTACLE_ID);
        } else {
            return -1;
        }
    }

    public static void retractFarTentacles(Level level, LivingEntity livingEntity) {
        Tidal_Tentacle_Entity last = getLastTendon(livingEntity);
        if (last != null) {
            last.remove(Entity.RemovalReason.DISCARDED);
            setLastTentacle(livingEntity, null);
        }
    }

    public static boolean canLaunchTentacles(Level level, LivingEntity livingEntity) {
        Tidal_Tentacle_Entity last = getLastTendon(livingEntity);
        if (last != null) {
            return last.isRemoved();
        }
        return true;
    }

    public static Tidal_Tentacle_Entity getLastTendon(LivingEntity livingEntity) {
        UUID uuid = getLastTentacleUUID(livingEntity);
        int id = getLastTentacleId(livingEntity);
        if (!livingEntity.level.isClientSide) {
            if (uuid != null) {
                Entity e = livingEntity.level.getEntity(id);
                return e instanceof Tidal_Tentacle_Entity ? (Tidal_Tentacle_Entity) e : null;
            }
        } else {
            if (id != -1) {
                Entity e = livingEntity.level.getEntity(id);
                return e instanceof Tidal_Tentacle_Entity ? (Tidal_Tentacle_Entity) e : null;
            }
        }
        return null;
    }
}
