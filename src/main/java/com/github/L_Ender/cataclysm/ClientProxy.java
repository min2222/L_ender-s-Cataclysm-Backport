package com.github.L_Ender.cataclysm;

import com.github.L_Ender.cataclysm.client.event.BossBarEvent;
import com.github.L_Ender.cataclysm.client.event.ClientEvent;
import com.github.L_Ender.cataclysm.client.gui.GUIWeponfusion;
import com.github.L_Ender.cataclysm.client.particle.EM_PulseParticle;
import com.github.L_Ender.cataclysm.client.particle.LightningParticle;
import com.github.L_Ender.cataclysm.client.particle.Shock_WaveParticle;
import com.github.L_Ender.cataclysm.client.particle.SoulLavaParticle;
import com.github.L_Ender.cataclysm.client.render.CMItemstackRenderer;
import com.github.L_Ender.cataclysm.client.render.blockentity.*;
import com.github.L_Ender.cataclysm.client.render.entity.*;
import com.github.L_Ender.cataclysm.client.render.item.CMItemRenderProperties;
import com.github.L_Ender.cataclysm.client.render.item.CustomArmorRenderProperties;
import com.github.L_Ender.cataclysm.client.sound.*;
import com.github.L_Ender.cataclysm.config.CMConfig;
import com.github.L_Ender.cataclysm.entity.BossMonsters.Ender_Guardian_Entity;
import com.github.L_Ender.cataclysm.entity.BossMonsters.Ignis_Entity;
import com.github.L_Ender.cataclysm.entity.BossMonsters.Netherite_Monstrosity_Entity;
import com.github.L_Ender.cataclysm.entity.BossMonsters.The_Harbinger_Entity;
import com.github.L_Ender.cataclysm.entity.BossMonsters.The_Leviathan.The_Leviathan_Entity;
import com.github.L_Ender.cataclysm.init.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Cataclysm.MODID, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {

    public static final Map<Integer, SoundMonstrosityMusic> MONSTROSITY_SOUND_MAP = new HashMap<>();
    public static final Map<Integer, SoundEnderGuardianMusic> GUARDIAN_SOUND_MAP = new HashMap<>();
    public static final Map<Integer, SoundIgnisMusic> IGNIS_SOUND_MAP = new HashMap<>();
    public static final Map<Integer, SoundHarbingerMusic> HARBINGER_SOUND_MAP = new HashMap<>();
    public static final Map<Integer, SoundLeviathanMusic> LEVIATHAN_SOUND_MAP = new HashMap<>();


    public void init() {
       // FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientLayerEvent::onAddLayers);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientProxy::setupParticles);
    }

    public static void setupParticles(RegisterParticleProvidersEvent registry) {
        Cataclysm.LOGGER.debug("Registered particle factories");
        registry.registerSpriteSet(ModParticle.SOUL_LAVA.get(), SoulLavaParticle.Factory::new);
        registry.registerSpecial(ModParticle.EM_PULSE.get(), new EM_PulseParticle.Factory());
        registry.registerSpecial(ModParticle.SHOCK_WAVE.get(), new Shock_WaveParticle.Factory());
        registry.registerSpecial(ModParticle.LIGHTNING.get(), new LightningParticle.OrbFactory());
    }

    public void clientInit() {
        ItemRenderer itemRendererIn = Minecraft.getInstance().getItemRenderer();
        EntityRenderers.register(ModEntities.ENDER_GOLEM.get(), RendererEnder_Golem::new);
        EntityRenderers.register(ModEntities.NETHERITE_MONSTROSITY.get(), RendererNetherite_Monstrosity::new);
        EntityRenderers.register(ModEntities.LAVA_BOMB.get(), RendererLava_Bomb::new);
        EntityRenderers.register(ModEntities.NAMELESS_SORCERER.get(), RendererNameless_Sorcerer::new);
        EntityRenderers.register(ModEntities.IGNIS.get(), RendererIgnis::new);
        EntityRenderers.register(ModEntities.ENDER_GUARDIAN.get(), RendererEnder_Guardian::new);
        EntityRenderers.register(ModEntities.ENDER_GUARDIAN_BULLET.get(), RendererEnder_Guardian_bullet::new);
        EntityRenderers.register(ModEntities.VOID_RUNE.get(), RendererVoid_Rune::new);
        EntityRenderers.register(ModEntities.ENDERMAPTERA.get(), RendererEndermaptera::new);
        EntityRenderers.register(ModEntities.IGNITED_REVENANT.get(), RendererIgnited_Revenant::new);
        EntityRenderers.register(ModEntities.THE_HARBINGER.get(), RendererThe_Harbinger::new);
        EntityRenderers.register(ModEntities.VOID_SCATTER_ARROW.get(), RendererVoid_Scatter_Arrow::new);

        EntityRenderers.register(ModEntities.POISON_DART.get(), RendererPoison_Dart::new);
        EntityRenderers.register(ModEntities.SCREEN_SHAKE.get(), RendererNull::new);
        EntityRenderers.register(ModEntities.WITHER_SMOKE_EFFECT.get(), RendererNull::new);
        EntityRenderers.register(ModEntities.ASHEN_BREATH.get(), RendererNull::new);
        EntityRenderers.register(ModEntities.WALL_WATCHER.get(), RendererNull::new);
        EntityRenderers.register(ModEntities.FLAME_STRIKE.get(), RendererFlame_Strike::new);
        EntityRenderers.register(ModEntities.CM_FALLING_BLOCK.get(), RendererCm_Falling_Block::new);
        EntityRenderers.register(ModEntities.IGNIS_FIREBALL.get(), RendererIgnis_Fireball::new);
        EntityRenderers.register(ModEntities.IGNIS_ABYSS_FIREBALL.get(), RendererIgnis_Abyss_Fireball::new);
        EntityRenderers.register(ModEntities.DEATH_LASER_BEAM.get(), RendererDeath_Laser_beam::new);
        EntityRenderers.register(ModEntities.ABYSS_BLAST.get(), RendererAbyss_Blast::new);
        EntityRenderers.register(ModEntities.MINI_ABYSS_BLAST.get(), RendererMini_Abyss_Blast::new);
        EntityRenderers.register(ModEntities.LASER_BEAM.get(), RendererLaser_Beam::new);
        EntityRenderers.register(ModEntities.WITHER_MISSILE.get(), RendererWither_Missile::new);
        EntityRenderers.register(ModEntities.WITHER_HOMING_MISSILE.get(), RendererWither_Homing_Missile::new);
        EntityRenderers.register(ModEntities.WITHER_HOWITZER.get(), RendererWither_Howitzer::new);
        EntityRenderers.register(ModEntities.VOID_HOWITZER.get(), RendererVoid_Howitzer::new);
        EntityRenderers.register(ModEntities.VOID_VORTEX.get(), RendererVoid_Vortex::new);
        EntityRenderers.register(ModEntities.THE_LEVIATHAN.get(), RendererThe_Leviathan::new);
        EntityRenderers.register(ModEntities.THE_BABY_LEVIATHAN.get(), RendererThe_Baby_Leviathan::new);
        EntityRenderers.register(ModEntities.THE_LEVIATHAN_TONGUE.get(), RendererThe_Leviathan_Tongue::new);
        EntityRenderers.register(ModEntities.ABYSS_PORTAL.get(), RendererAbyss_Portal::new);
        EntityRenderers.register(ModEntities.ABYSS_ORB.get(), RendererAbyss_Orb::new);
        EntityRenderers.register(ModEntities.ABYSS_BLAST_PORTAL.get(), RendererAbyss_Blast_Portal::new);
        EntityRenderers.register(ModEntities.PORTAL_ABYSS_BLAST.get(), RendererPortal_Abyss_Blast::new);
        EntityRenderers.register(ModEntities.DEEPLING.get(), RendererDeepling::new);
        EntityRenderers.register(ModEntities.ABYSS_MINE.get(), RendererAbyss_Mine::new);
        EntityRenderers.register(ModEntities.CORAL_SPEAR.get(), RendererThrown_Coral_Spear::new);
        EntityRenderers.register(ModEntities.CORAL_BARDICHE.get(), RendererThrown_Coral_Bardiche::new);
        EntityRenderers.register(ModEntities.DEEPLING_BRUTE.get(), RendererDeepling_Brute::new);
        EntityRenderers.register(ModEntities.DEEPLING_PRIEST.get(), RendererDeepling_Priest::new);
        EntityRenderers.register(ModEntities.DIMENSIONAL_RIFT.get(), RendererDimensional_Rift::new);
        EntityRenderers.register(ModEntities.DEEPLING_ANGLER.get(), RendererDeepling_Angler::new);
        EntityRenderers.register(ModEntities.CORALSSUS.get(), RendererCoralssus::new);
        EntityRenderers.register(ModEntities.LIONFISH.get(), RendererLionfish::new);
        EntityRenderers.register(ModEntities.TIDAL_HOOK.get(), RendererTidal_Hook::new);
        EntityRenderers.register(ModEntities.AMETHYST_CRAB.get(), RendererAmethyst_Crab::new);
        EntityRenderers.register(ModEntities.ANCIENT_REMNANT.get(), RendererAncient_Remnant::new);
        EntityRenderers.register(ModEntities.THE_WATCHER.get(), RendererThe_Watcher::new);

        EntityRenderers.register(ModEntities.THE_PROWLER.get(), RendererThe_Prowler::new);

        EntityRenderers.register(ModEntities.EARTHQUAKE.get(), RendererNull::new);
        EntityRenderers.register(ModEntities.AMETHYST_CLUSTER_PROJECTILE.get(), RendererAmethyst_Cluster_Projectile::new);
        EntityRenderers.register(ModEntities.VOID_SHARD.get(), (render) -> {
            return new ThrownItemRenderer<>(render, 0.75F, true);
        });
        EntityRenderers.register(ModEntities.EYE_OF_DUNGEON.get(), (render) -> {
            return new ThrownItemRenderer<>(render, 1.0F, true);
        });
        EntityRenderers.register(ModEntities.BLAZING_BONE.get(), RendererBlazing_Bone::new);
        EntityRenderers.register(ModEntities.LIONFISH_SPIKE.get(), RendererLionfish_Spike::new);
        EntityRenderers.register(ModEntities.TIDAL_TENTACLE.get(), RendererTidal_Tentacle::new);
        MinecraftForge.EVENT_BUS.register(new ClientEvent());
        try {
            ItemProperties.register(ModItems.BULWARK_OF_THE_FLAME.get(), new ResourceLocation("blocking"), (stack, p_239421_1_, p_239421_2_, j) -> p_239421_2_ != null && p_239421_2_.isUsingItem() && p_239421_2_.getUseItem() == stack ? 1.0F : 0.0F);
            ItemProperties.register(ModItems.CORAL_SPEAR.get(), new ResourceLocation("throwing"), (stack, p_239421_1_, p_239421_2_, j) -> p_239421_2_ != null && p_239421_2_.isUsingItem() && p_239421_2_.getUseItem() == stack ? 1.0F : 0.0F);
            ItemProperties.register(ModItems.CORAL_BARDICHE.get(), new ResourceLocation("throwing"), (stack, p_239421_1_, p_239421_2_, j) -> p_239421_2_ != null && p_239421_2_.isUsingItem() && p_239421_2_.getUseItem() == stack ? 1.0F : 0.0F);
            ItemProperties.register(Items.CROSSBOW, new ResourceLocation(Cataclysm.MODID, "void_scatter_arrow"), (stack, world, entity, j) -> entity != null && CrossbowItem.isCharged(stack) && CrossbowItem.containsChargedProjectile(stack, ModItems.VOID_SCATTER_ARROW.get()) ? 1.0F : 0.0F);
        } catch (Exception e) {
            Cataclysm.LOGGER.warn("Could not load item models for weapons");

        }
        MinecraftForge.EVENT_BUS.addListener(BossBarEvent::renderBossBar);
        BlockEntityRenderers.register(ModTileentites.ALTAR_OF_FIRE.get(), RendererAltar_of_Fire::new);
        BlockEntityRenderers.register(ModTileentites.ALTAR_OF_VOID.get(), RendererAltar_of_Void::new);
        BlockEntityRenderers.register(ModTileentites.EMP.get(), RendererEMP::new);
        BlockEntityRenderers.register(ModTileentites.MECHANICAL_FUSION_ANVIL.get(), RendererMechanical_fusion_anvil::new);
        BlockEntityRenderers.register(ModTileentites.ALTAR_OF_AMETHYST.get(), RendererAltar_of_Amethyst::new);
        BlockEntityRenderers.register(ModTileentites.ALTAR_OF_ABYSS.get(), RendererAltar_of_Abyss::new);
        BlockEntityRenderers.register(ModTileentites.ABYSSAL_EGG.get(), RendererAbyssal_Egg::new);
        MenuScreens.register(ModMenu.WEAPON_FUSION.get(), GUIWeponfusion::new);
    }


    @OnlyIn(Dist.CLIENT)
    public static Callable<BlockEntityWithoutLevelRenderer> getTEISR() {
        return CMItemstackRenderer::new;
    }


    public Player getClientSidePlayer() {
        return Minecraft.getInstance().player;
    }


    @OnlyIn(Dist.CLIENT)
    public void onEntityStatus(Entity entity, byte updateKind) {
        float f2 = Minecraft.getInstance().options.getSoundSourceVolume(SoundSource.RECORDS);
        if(CMConfig.BossMusic) {
            if (entity instanceof Netherite_Monstrosity_Entity && entity.isAlive() && updateKind == 67) {
                if (f2 <= 0) {
                    MONSTROSITY_SOUND_MAP.clear();
                } else {
                    SoundMonstrosityMusic sound;
                    if (MONSTROSITY_SOUND_MAP.get(entity.getId()) == null) {
                        sound = new SoundMonstrosityMusic((Netherite_Monstrosity_Entity) entity);
                        MONSTROSITY_SOUND_MAP.put(entity.getId(), sound);
                    } else {
                        sound = MONSTROSITY_SOUND_MAP.get(entity.getId());
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.isNearest()) {
                        Minecraft.getInstance().getSoundManager().play(sound);
                    }
                }
            }
            if (entity instanceof Ignis_Entity && entity.isAlive() && updateKind == 67) {
                if (f2 <= 0) {
                    IGNIS_SOUND_MAP.clear();
                } else {
                    SoundIgnisMusic sound;
                    if (IGNIS_SOUND_MAP.get(entity.getId()) == null) {
                        sound = new SoundIgnisMusic((Ignis_Entity) entity);
                        IGNIS_SOUND_MAP.put(entity.getId(), sound);
                    } else {
                        sound = IGNIS_SOUND_MAP.get(entity.getId());
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.isNearest()) {
                        Minecraft.getInstance().getSoundManager().play(sound);
                    }
                }

            }
            if (entity instanceof Ender_Guardian_Entity && entity.isAlive() && updateKind == 67) {
                if (f2 <= 0) {
                    GUARDIAN_SOUND_MAP.clear();
                } else {
                    SoundEnderGuardianMusic sound;
                    if (GUARDIAN_SOUND_MAP.get(entity.getId()) == null) {
                        sound = new SoundEnderGuardianMusic((Ender_Guardian_Entity) entity);
                        GUARDIAN_SOUND_MAP.put(entity.getId(), sound);
                    } else {
                        sound = GUARDIAN_SOUND_MAP.get(entity.getId());
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.isNearest()) {
                        Minecraft.getInstance().getSoundManager().play(sound);
                    }
                }

            }
            if (entity instanceof The_Harbinger_Entity && entity.isAlive() && updateKind == 67) {
                if (f2 <= 0) {
                    HARBINGER_SOUND_MAP.clear();
                } else {
                    SoundHarbingerMusic sound;
                    if (HARBINGER_SOUND_MAP.get(entity.getId()) == null) {
                        sound = new SoundHarbingerMusic((The_Harbinger_Entity) entity);
                        HARBINGER_SOUND_MAP.put(entity.getId(), sound);
                    } else {
                        sound = HARBINGER_SOUND_MAP.get(entity.getId());
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.isNearest()) {
                        Minecraft.getInstance().getSoundManager().play(sound);
                    }
                }

            }
            if (entity instanceof The_Leviathan_Entity && entity.isAlive() && updateKind == 67) {
                if (f2 <= 0) {
                    LEVIATHAN_SOUND_MAP.clear();
                } else {
                    SoundLeviathanMusic sound;
                    if (LEVIATHAN_SOUND_MAP.get(entity.getId()) == null) {
                        sound = new SoundLeviathanMusic((The_Leviathan_Entity) entity);
                        LEVIATHAN_SOUND_MAP.put(entity.getId(), sound);
                    } else {
                        sound = LEVIATHAN_SOUND_MAP.get(entity.getId());
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.isNearest()) {
                        Minecraft.getInstance().getSoundManager().play(sound);
                    }
                }

            }

        }
    }


    @Override
    public Object getISTERProperties() {
        return new CMItemRenderProperties();
    }

    @Override
    public Object getArmorRenderProperties() {
        return new CustomArmorRenderProperties();
    }


    @Override
    public void addBoss(Mob mob) {
        BossBarEvent.addBoss(mob);
    }

    @Override
    public void removeBoss(Mob mob) {
        BossBarEvent.removeBoss(mob);
    }
}