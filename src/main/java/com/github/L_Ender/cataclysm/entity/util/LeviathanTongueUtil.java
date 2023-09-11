package com.github.L_Ender.cataclysm.entity.util;

import com.github.L_Ender.cataclysm.entity.BossMonsters.The_Leviathan.The_Leviathan_Tongue_Entity;
import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.entity.CitadelEntityData;
import com.github.alexthe666.citadel.server.message.PropertiesMessage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class LeviathanTongueUtil {

    private static final String LAST_TONGUE_UUID = "LastTongueUUIDCataclysm";
    private static final String LAST_TONGUE_ID = "LastTongueIDCataclysm";

    private static void sync(LivingEntity enchanted, CompoundTag tag) {
        CitadelEntityData.setCitadelTag(enchanted, tag);
        if (!enchanted.level().isClientSide) {
            Citadel.sendMSGToAll(new PropertiesMessage("CataclysmTagUpdate", tag, enchanted.getId()));
        } else {
            Citadel.sendMSGToServer(new PropertiesMessage("CataclysmTagUpdate", tag, enchanted.getId()));
        }
    }

    public static void setLastTongue(LivingEntity entity, The_Leviathan_Tongue_Entity tendon) {
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag(entity);
        if (tendon == null) {
            tag.remove(LAST_TONGUE_UUID);
            tag.putInt(LAST_TONGUE_ID, -1);
        } else {
            tag.putUUID(LAST_TONGUE_UUID, tendon.getUUID());
            tag.putInt(LAST_TONGUE_ID, tendon.getId());
        }
        sync(entity, tag);
    }

    private static UUID getLastTongueUUID(LivingEntity entity) {
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag(entity);
        if (tag.contains(LAST_TONGUE_UUID)) {
            return tag.getUUID(LAST_TONGUE_UUID);
        } else {
            return null;
        }
    }

    public static int getLastTongueId(LivingEntity entity) {
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag(entity);
        if (tag.contains(LAST_TONGUE_ID)) {
            return tag.getInt(LAST_TONGUE_ID);
        } else {
            return -1;
        }
    }

    public static void retractFarTongues(Level level, LivingEntity livingEntity) {
        The_Leviathan_Tongue_Entity last = getLastTendon(livingEntity);
        if (last != null) {
            last.remove(Entity.RemovalReason.DISCARDED);
            setLastTongue(livingEntity, null);
        }
    }

    public static boolean canLaunchTongues(Level level, LivingEntity livingEntity) {
        The_Leviathan_Tongue_Entity last = getLastTendon(livingEntity);
        if (last != null) {
            return last.isRemoved();
        }
        return true;
    }

    public static The_Leviathan_Tongue_Entity getLastTendon(LivingEntity livingEntity) {
        UUID uuid = getLastTongueUUID(livingEntity);
        int id = getLastTongueId(livingEntity);
        if (!livingEntity.level().isClientSide) {
            if (uuid != null) {
                Entity e = livingEntity.level().getEntity(id);
                return e instanceof The_Leviathan_Tongue_Entity ? (The_Leviathan_Tongue_Entity) e : null;
            }
        } else {
            if (id != -1) {
                Entity e = livingEntity.level().getEntity(id);
                return e instanceof The_Leviathan_Tongue_Entity ? (The_Leviathan_Tongue_Entity) e : null;
            }
        }
        return null;
    }
}
