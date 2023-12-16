package com.github.L_Ender.cataclysm.client.event;


import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.config.CMConfig;
import com.github.L_Ender.cataclysm.entity.BossMonsters.Ancient_Remnant_Entity;
import com.github.L_Ender.cataclysm.entity.BossMonsters.Ender_Guardian_Entity;
import com.github.L_Ender.cataclysm.entity.BossMonsters.Ignis_Entity;
import com.github.L_Ender.cataclysm.entity.BossMonsters.Netherite_Monstrosity_Entity;
import com.github.L_Ender.cataclysm.entity.BossMonsters.The_Harbinger_Entity;
import com.github.L_Ender.cataclysm.entity.BossMonsters.The_Leviathan.The_Leviathan_Entity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class BossBarEvent {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Cataclysm.MODID, "textures/gui/boss_bar_frames.png");
    private static final ResourceLocation GUI_BARS_LOCATION = new ResourceLocation("textures/gui/bars.png");
    public static final Set<Mob> BOSSES = Collections.newSetFromMap(new WeakHashMap<>());


    @SubscribeEvent
    public static void renderBossBar(CustomizeGuiOverlayEvent.BossEventProgress event){
        Minecraft minecraft = Minecraft.getInstance();
        if(CMConfig.custombossbar) {
            if (!BOSSES.isEmpty()) {
                int i = minecraft.getWindow().getGuiScaledWidth();
                for (Mob boss : BOSSES) {
                    if (boss != null) {
                        if (event.getBossEvent().getId() == boss.getUUID()) {
                            event.setCanceled(true);
                            int k = i / 2 - 94;
                            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                            //  RenderSystem.setShaderTexture(0, GUI_BARS_LOCATION);

                            drawBar(event.getPoseStack(), k, event.getY() - 2, boss,event.getBossEvent());
                            Component itextcomponent = boss.getDisplayName();
                            int l = minecraft.font.width(itextcomponent);
                            int i1 = i / 2 - l / 2;
                            Screen.drawString(event.getPoseStack(), minecraft.font, itextcomponent, i1, event.getY() - 10, 16777215);
                            if (event.getY() >= minecraft.getWindow().getGuiScaledHeight() / 3) {
                                break;
                            }
                            event.setIncrement(12 + minecraft.font.lineHeight);
                        }
                    }

                }
            }
        }
    }

    private static void drawBar(PoseStack pPoseStack, int pX, int pY, Mob pEntity, BossEvent bossEvent) {
        int i = (int) (bossEvent.getProgress() * 182.0F);
        if (pEntity instanceof Netherite_Monstrosity_Entity) {
            RenderSystem.setShaderTexture(0, GUI_BARS_LOCATION);
            Screen.blit(pPoseStack, pX + 3, pY + 2, 0, 20, 182, 5, 256, 256);
            if (i > 0) {
                Screen.blit(pPoseStack, pX + 3, pY + 3, 0, 26, i, 5, 256, 256);
            }
            RenderSystem.setShaderTexture(0, TEXTURE);
            Screen.blit(pPoseStack, pX , pY, 0, 0, 188, 9, 256, 256);
        }

        if (pEntity instanceof Ender_Guardian_Entity) {
            RenderSystem.setShaderTexture(0, GUI_BARS_LOCATION);
            Screen.blit(pPoseStack, pX + 3, pY + 2, 0, 50, 182, 5, 256, 256);
            if (i > 0) {
                Screen.blit(pPoseStack, pX + 3, pY + 3, 0, 56, i, 5, 256, 256);
            }
            RenderSystem.setShaderTexture(0, TEXTURE);
            Screen.blit(pPoseStack, pX , pY, 0, 9, 188, 9, 256, 256);
        }

        if (pEntity instanceof Ignis_Entity ignis) {
            RenderSystem.setShaderTexture(0, GUI_BARS_LOCATION);
            if(ignis.getBossPhase() > 0){
                Screen.blit(pPoseStack, pX + 3, pY + 2, 0, 10, 182, 5, 256, 256);
                if (i > 0) {
                    Screen.blit(pPoseStack, pX + 3, pY + 3, 0, 16, i, 5, 256, 256);
                }
                RenderSystem.setShaderTexture(0, TEXTURE);
                Screen.blit(pPoseStack, pX , pY, 0, 36, 188, 9, 256, 256);
            }else{
                Screen.blit(pPoseStack, pX + 3, pY + 2, 0, 40, 182, 5, 256, 256);
                if (i > 0) {
                    Screen.blit(pPoseStack, pX + 3, pY + 3, 0, 46, i, 5, 256, 256);
                }
                RenderSystem.setShaderTexture(0, TEXTURE);
                Screen.blit(pPoseStack, pX , pY, 0, 27, 188, 9, 256, 256);
            }
        }

        if (pEntity instanceof The_Harbinger_Entity) {
            RenderSystem.setShaderTexture(0, GUI_BARS_LOCATION);
            Screen.blit(pPoseStack, pX + 3, pY + 2, 0, 20, 182, 5, 256, 256);
            if (i > 0) {
                Screen.blit(pPoseStack, pX + 3, pY + 3, 0, 26, i, 5, 256, 256);
            }
            RenderSystem.setShaderTexture(0, TEXTURE);
            Screen.blit(pPoseStack, pX , pY, 0, 18, 188, 9, 256, 256);
        }

        if (pEntity instanceof The_Leviathan_Entity leviathan) {
            RenderSystem.setShaderTexture(0, GUI_BARS_LOCATION);
            if(leviathan.getMeltDown()){
                Screen.blit(pPoseStack, pX + 3, pY + 6, 0, 50, 182, 5, 256, 256);
                if (i > 0) {
                    Screen.blit(pPoseStack, pX + 3, pY + 7, 0, 56, i, 5, 256, 256);
                }
                RenderSystem.setShaderTexture(0, TEXTURE);
                Screen.blit(pPoseStack, pX , pY, 0, 62, 188, 16, 256, 256);
            }else{
                Screen.blit(pPoseStack, pX + 3, pY + 5, 0, 50, 182, 5, 256, 256);
                if (i > 0) {
                    Screen.blit(pPoseStack, pX + 3, pY + 6, 0, 56, (int) i, 5, 256, 256);
                }
                RenderSystem.setShaderTexture(0, TEXTURE);
                Screen.blit(pPoseStack, pX , pY, 0, 45, 188, 13, 256, 256);
            }
        }
        if (pEntity instanceof Ancient_Remnant_Entity) {
            RenderSystem.setShaderTexture(0, GUI_BARS_LOCATION);
            Screen.blit(pPoseStack, pX + 3, pY + 10, 0, 60, 182, 5, 256, 256);
            if (i > 0) {
                Screen.blit(pPoseStack, pX + 3, pY +11, 0, 66, i, 5, 256, 256);
            }
            RenderSystem.setShaderTexture(0, TEXTURE);
            Screen.blit(pPoseStack, pX , pY, 0, 79, 188, 26, 256, 256);
        }

    }

    public static void addBoss(Mob mob){
        BOSSES.add(mob);
    }

    public static void removeBoss(Mob mob){
        BOSSES.remove(mob);
    }

    public static Set<Mob> getBosses(){
        return BOSSES;
    }

}
