package com.github.L_Ender.cataclysm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

import javax.annotation.Nullable;

import com.github.L_Ender.cataclysm.client.event.ClientEvent;
import com.github.L_Ender.cataclysm.client.gui.GUIWeponfusion;
import com.github.L_Ender.cataclysm.client.particle.CursedFlameParticle;
import com.github.L_Ender.cataclysm.client.particle.CustomExplodeParticle;
import com.github.L_Ender.cataclysm.client.particle.EM_PulseParticle;
import com.github.L_Ender.cataclysm.client.particle.FlameJetParticle;
import com.github.L_Ender.cataclysm.client.particle.LightTrailParticle;
import com.github.L_Ender.cataclysm.client.particle.LightningParticle;
import com.github.L_Ender.cataclysm.client.particle.Phantom_Wing_FlameParticle;
import com.github.L_Ender.cataclysm.client.particle.RingParticle;
import com.github.L_Ender.cataclysm.client.particle.SandStormParticle;
import com.github.L_Ender.cataclysm.client.particle.Shock_WaveParticle;
import com.github.L_Ender.cataclysm.client.particle.SoulLavaParticle;
import com.github.L_Ender.cataclysm.client.particle.StormParticle;
import com.github.L_Ender.cataclysm.client.particle.TrackLightningParticle;
import com.github.L_Ender.cataclysm.client.particle.TrapFlameParticle;
import com.github.L_Ender.cataclysm.client.render.CMItemstackRenderer;
import com.github.L_Ender.cataclysm.client.render.blockentity.Cataclysm_Skull_Block_Renderer;
import com.github.L_Ender.cataclysm.client.render.blockentity.Cursed_Tombstone_Renderer;
import com.github.L_Ender.cataclysm.client.render.blockentity.Door_Of_Seal_Renderer;
import com.github.L_Ender.cataclysm.client.render.blockentity.RendererAbyssal_Egg;
import com.github.L_Ender.cataclysm.client.render.blockentity.RendererAltar_of_Abyss;
import com.github.L_Ender.cataclysm.client.render.blockentity.RendererAltar_of_Amethyst;
import com.github.L_Ender.cataclysm.client.render.blockentity.RendererAltar_of_Fire;
import com.github.L_Ender.cataclysm.client.render.blockentity.RendererAltar_of_Void;
import com.github.L_Ender.cataclysm.client.render.blockentity.RendererEMP;
import com.github.L_Ender.cataclysm.client.render.blockentity.RendererMechanical_fusion_anvil;
import com.github.L_Ender.cataclysm.client.render.entity.Abyss_Blast_Portal_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Abyss_Blast_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Abyss_Mark_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Abyss_Mine_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Abyss_Orb_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Abyss_Portal_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Amethyst_Cluster_Projectile_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Amethyst_Crab_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Ancient_Desert_Stele_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Ancient_Remnant_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Ancient_Remnant_Rework_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Aptrgangr_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Axe_Blade_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Blazing_Bone_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Boltstrike_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Cm_Falling_Block_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Coral_Golem_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Coralssus_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Cursed_Sandstorm_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Death_Laser_beam_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Deepling_Angler_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Deepling_Brute_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Deepling_Priest_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Deepling_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Deepling_Warlock_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Dimensional_Rift_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Draugr_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Elite_Draugr_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Ender_Golem_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Ender_Guardian_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Ender_Guardian_bullet_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Endermaptera_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Flame_Strike_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Flare_Bomb_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Ignis_Abyss_Fireball_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Ignis_Fireball_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Ignis_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Ignited_Berserker_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Ignited_Revenant_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Kobolediator_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Koboleton_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Laser_Beam_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Lava_Bomb_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Lionfish_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Lionfish_Spike_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Maledictus_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Mini_Abyss_Blast_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Modern_Remnant_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Nameless_Sorcerer_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Netherite_Ministrosity_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Netherite_Monstrosity_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.New_Netherite_Monstrosity_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Phantom_Arrow_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Phantom_Halberd_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Poison_Dart_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Portal_Abyss_Blast_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.RendererNull;
import com.github.L_Ender.cataclysm.client.render.entity.Royal_Draugr_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Sandstorm_Projectile_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Sandstorm_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.The_Baby_Leviathan_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.The_Harbinger_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.The_Leviathan_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.The_Prowler_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.The_Watcher_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Thrown_Coral_Bardiche_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Thrown_Coral_Spear_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Tidal_Hook_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Tidal_Tentacle_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Void_Howitzer_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Void_Rune_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Void_Scatter_Arrow_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Void_Vortex_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Wadjet_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Wither_Homing_Missile_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Wither_Howitzer_Renderer;
import com.github.L_Ender.cataclysm.client.render.entity.Wither_Missile_Renderer;
import com.github.L_Ender.cataclysm.client.render.etc.CurioHeadRenderer;
import com.github.L_Ender.cataclysm.client.render.item.CMItemRenderProperties;
import com.github.L_Ender.cataclysm.client.render.item.CustomArmorRenderProperties;
import com.github.L_Ender.cataclysm.client.render.item.CuriosItemREnderer.Blazing_Grips_Renderer;
import com.github.L_Ender.cataclysm.client.render.item.CuriosItemREnderer.RendererSandstorm_In_A_Bottle;
import com.github.L_Ender.cataclysm.client.render.item.CuriosItemREnderer.RendererSticky_Gloves;
import com.github.L_Ender.cataclysm.client.sound.MeatShredderSound;
import com.github.L_Ender.cataclysm.client.sound.SandstormSound;
import com.github.L_Ender.cataclysm.entity.effect.Sandstorm_Entity;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.L_Ender.cataclysm.init.ModKeybind;
import com.github.L_Ender.cataclysm.init.ModMenu;
import com.github.L_Ender.cataclysm.init.ModParticle;
import com.github.L_Ender.cataclysm.init.ModTileentites;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Cataclysm.MODID, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {
    public static final Int2ObjectMap<AbstractTickableSoundInstance> ENTITY_SOUND_INSTANCE_MAP = new Int2ObjectOpenHashMap<>();
    public static final Map<BlockEntity, AbstractTickableSoundInstance> BLOCK_ENTITY_SOUND_INSTANCE_MAP = new HashMap<>();
    public static Map<UUID, Integer> bossBarRenderTypes = new HashMap<>();

    public static List<UUID> blockedEntityRenders = new ArrayList<>();
    private Entity referencedMob = null;
    public void init() {
       // FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientLayerEvent::onAddLayers);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupParticles);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerKeybinds);
    }

    public void setupParticles(RegisterParticleProvidersEvent registry) {
        Cataclysm.LOGGER.debug("Registered particle factories");
        registry.register(ModParticle.SOUL_LAVA.get(), SoulLavaParticle.Factory::new);
        registry.register(ModParticle.CURSED_FLAME.get(), CursedFlameParticle.Provider::new);
        registry.register(ModParticle.SMALL_CURSED_FLAME.get(), CursedFlameParticle.SmallFlameProvider::new);
        registry.register(ModParticle.PHANTOM_WING_FLAME.get(), Phantom_Wing_FlameParticle.EmissiveProvider::new);
        registry.register(ModParticle.EM_PULSE.get(), new EM_PulseParticle.Factory());
        registry.register(ModParticle.SHOCK_WAVE.get(), new Shock_WaveParticle.Factory());
        registry.register(ModParticle.LIGHTNING.get(), new LightningParticle.OrbFactory());
        registry.register(ModParticle.TRACK_LIGHTNING.get(), new TrackLightningParticle.OrbFactory());
        registry.register(ModParticle.STORM.get(), new StormParticle.OrbFactory());
        registry.register(ModParticle.RING.get(), RingParticle.RingFactory::new);
        registry.register(ModParticle.SANDSTORM.get(), SandStormParticle.Factory::new);
        registry.register(ModParticle.TRAP_FLAME.get(), TrapFlameParticle.Factory::new);
        registry.register(ModParticle.LIGHT_TRAIL.get(), new LightTrailParticle.OrbFactory());
        registry.register(ModParticle.FLAME_JET.get(), FlameJetParticle.Factory::new);
        registry.register(ModParticle.FLARE_EXPLODE.get(), CustomExplodeParticle.FlareFactory::new);
    }

    public void clientInit() {
        ItemRenderer itemRendererIn = Minecraft.getInstance().getItemRenderer();
        EntityRenderers.register(ModEntities.ENDER_GOLEM.get(), Ender_Golem_Renderer::new);
        EntityRenderers.register(ModEntities.NETHERITE_MONSTROSITY.get(), New_Netherite_Monstrosity_Renderer::new);
        EntityRenderers.register(ModEntities.NETHERITE_MINISTROSITY.get(), Netherite_Ministrosity_Renderer::new);
        EntityRenderers.register(ModEntities.OLD_NETHERITE_MONSTROSITY.get(), Netherite_Monstrosity_Renderer::new);
        EntityRenderers.register(ModEntities.LAVA_BOMB.get(), Lava_Bomb_Renderer::new);
        EntityRenderers.register(ModEntities.FLARE_BOMB.get(), Flare_Bomb_Renderer::new);
        EntityRenderers.register(ModEntities.FLAME_JET.get(), RendererNull::new);
        EntityRenderers.register(ModEntities.NAMELESS_SORCERER.get(), Nameless_Sorcerer_Renderer::new);
        EntityRenderers.register(ModEntities.IGNIS.get(), Ignis_Renderer::new);
        EntityRenderers.register(ModEntities.ENDER_GUARDIAN.get(), Ender_Guardian_Renderer::new);
        EntityRenderers.register(ModEntities.ENDER_GUARDIAN_BULLET.get(), Ender_Guardian_bullet_Renderer::new);
        EntityRenderers.register(ModEntities.VOID_RUNE.get(), Void_Rune_Renderer::new);
        EntityRenderers.register(ModEntities.ENDERMAPTERA.get(), Endermaptera_Renderer::new);
        EntityRenderers.register(ModEntities.IGNITED_REVENANT.get(), Ignited_Revenant_Renderer::new);
        EntityRenderers.register(ModEntities.IGNITED_BERSERKER.get(), Ignited_Berserker_Renderer::new);
        EntityRenderers.register(ModEntities.THE_HARBINGER.get(), The_Harbinger_Renderer::new);
        EntityRenderers.register(ModEntities.VOID_SCATTER_ARROW.get(), Void_Scatter_Arrow_Renderer::new);
        EntityRenderers.register(ModEntities.POISON_DART.get(), Poison_Dart_Renderer::new);
        EntityRenderers.register(ModEntities.PHANTOM_ARROW.get(), Phantom_Arrow_Renderer::new);
        EntityRenderers.register(ModEntities.SCREEN_SHAKE.get(), RendererNull::new);
        EntityRenderers.register(ModEntities.WITHER_SMOKE_EFFECT.get(), RendererNull::new);
        EntityRenderers.register(ModEntities.ASHEN_BREATH.get(), RendererNull::new);
        EntityRenderers.register(ModEntities.WALL_WATCHER.get(), RendererNull::new);
        EntityRenderers.register(ModEntities.FLAME_STRIKE.get(), Flame_Strike_Renderer::new);

        EntityRenderers.register(ModEntities.BOLT_STRIKE.get(), Boltstrike_Renderer::new);
        
        EntityRenderers.register(ModEntities.CM_FALLING_BLOCK.get(), Cm_Falling_Block_Renderer::new);
        EntityRenderers.register(ModEntities.IGNIS_FIREBALL.get(), Ignis_Fireball_Renderer::new);
        EntityRenderers.register(ModEntities.IGNIS_ABYSS_FIREBALL.get(), Ignis_Abyss_Fireball_Renderer::new);
        EntityRenderers.register(ModEntities.DEATH_LASER_BEAM.get(), Death_Laser_beam_Renderer::new);
        EntityRenderers.register(ModEntities.ABYSS_BLAST.get(), Abyss_Blast_Renderer::new);
        EntityRenderers.register(ModEntities.MINI_ABYSS_BLAST.get(), Mini_Abyss_Blast_Renderer::new);
        EntityRenderers.register(ModEntities.LASER_BEAM.get(), Laser_Beam_Renderer::new);
        EntityRenderers.register(ModEntities.WITHER_MISSILE.get(), Wither_Missile_Renderer::new);
        EntityRenderers.register(ModEntities.WITHER_HOMING_MISSILE.get(), Wither_Homing_Missile_Renderer::new);
        EntityRenderers.register(ModEntities.WITHER_HOWITZER.get(), Wither_Howitzer_Renderer::new);
        EntityRenderers.register(ModEntities.VOID_HOWITZER.get(), Void_Howitzer_Renderer::new);
        EntityRenderers.register(ModEntities.VOID_VORTEX.get(), Void_Vortex_Renderer::new);
        EntityRenderers.register(ModEntities.THE_LEVIATHAN.get(), The_Leviathan_Renderer::new);
        EntityRenderers.register(ModEntities.THE_BABY_LEVIATHAN.get(), The_Baby_Leviathan_Renderer::new);
        EntityRenderers.register(ModEntities.ABYSS_PORTAL.get(), Abyss_Portal_Renderer::new);
        EntityRenderers.register(ModEntities.ABYSS_ORB.get(), Abyss_Orb_Renderer::new);
        EntityRenderers.register(ModEntities.ABYSS_BLAST_PORTAL.get(), Abyss_Blast_Portal_Renderer::new);
        EntityRenderers.register(ModEntities.PORTAL_ABYSS_BLAST.get(), Portal_Abyss_Blast_Renderer::new);
        EntityRenderers.register(ModEntities.DEEPLING.get(), Deepling_Renderer::new);
        EntityRenderers.register(ModEntities.ABYSS_MINE.get(), Abyss_Mine_Renderer::new);
        EntityRenderers.register(ModEntities.CORAL_SPEAR.get(), Thrown_Coral_Spear_Renderer::new);
        EntityRenderers.register(ModEntities.CORAL_BARDICHE.get(), Thrown_Coral_Bardiche_Renderer::new);
        EntityRenderers.register(ModEntities.DEEPLING_BRUTE.get(), Deepling_Brute_Renderer::new);
        EntityRenderers.register(ModEntities.DEEPLING_PRIEST.get(), Deepling_Priest_Renderer::new);
        EntityRenderers.register(ModEntities.DIMENSIONAL_RIFT.get(), Dimensional_Rift_Renderer::new);
        EntityRenderers.register(ModEntities.DEEPLING_ANGLER.get(), Deepling_Angler_Renderer::new);
        EntityRenderers.register(ModEntities.DEEPLING_WARLOCK.get(), Deepling_Warlock_Renderer::new);
        EntityRenderers.register(ModEntities.ABYSS_MARK.get(), Abyss_Mark_Renderer::new);
        EntityRenderers.register(ModEntities.CORAL_GOLEM.get(), Coral_Golem_Renderer::new);
        EntityRenderers.register(ModEntities.CORALSSUS.get(), Coralssus_Renderer::new);
        EntityRenderers.register(ModEntities.LIONFISH.get(), Lionfish_Renderer::new);
        EntityRenderers.register(ModEntities.TIDAL_HOOK.get(), Tidal_Hook_Renderer::new);
        EntityRenderers.register(ModEntities.AMETHYST_CRAB.get(), Amethyst_Crab_Renderer::new);
        EntityRenderers.register(ModEntities.ANCIENT_ANCIENT_REMNANT.get(), Ancient_Remnant_Renderer::new);
        EntityRenderers.register(ModEntities.ANCIENT_REMNANT.get(), Ancient_Remnant_Rework_Renderer::new);
        EntityRenderers.register(ModEntities.MODERN_REMNANT.get(), Modern_Remnant_Renderer::new);
        EntityRenderers.register(ModEntities.SANDSTORM.get(), Sandstorm_Renderer::new);
        EntityRenderers.register(ModEntities.SANDSTORM_PROJECTILE.get(), Sandstorm_Projectile_Renderer::new);
        EntityRenderers.register(ModEntities.CURSED_SANDSTORM.get(), Cursed_Sandstorm_Renderer::new);
        EntityRenderers.register(ModEntities.THE_WATCHER.get(), The_Watcher_Renderer::new);
        EntityRenderers.register(ModEntities.THE_PROWLER.get(), The_Prowler_Renderer::new);
        EntityRenderers.register(ModEntities.KOBOLETON.get(), Koboleton_Renderer::new);
        EntityRenderers.register(ModEntities.KOBOLEDIATOR.get(), Kobolediator_Renderer::new);
        EntityRenderers.register(ModEntities.WADJET.get(), Wadjet_Renderer::new);
        EntityRenderers.register(ModEntities.MALEDICTUS.get(), Maledictus_Renderer::new);
        EntityRenderers.register(ModEntities.DRAUGR.get(), Draugr_Renderer::new);
        EntityRenderers.register(ModEntities.ROYAL_DRAUGR.get(), Royal_Draugr_Renderer::new);
        EntityRenderers.register(ModEntities.ELITE_DRAUGR.get(), Elite_Draugr_Renderer::new);
        EntityRenderers.register(ModEntities.APTRGANGR.get(), Aptrgangr_Renderer::new);
        EntityRenderers.register(ModEntities.AXE_BLADE.get(), Axe_Blade_Renderer::new);
        EntityRenderers.register(ModEntities.PHANTOM_HALBERD.get(), Phantom_Halberd_Renderer::new);
        EntityRenderers.register(ModEntities.EARTHQUAKE.get(), RendererNull::new);
        EntityRenderers.register(ModEntities.ANCIENT_DESERT_STELE.get(), Ancient_Desert_Stele_Renderer::new);
        EntityRenderers.register(ModEntities.AMETHYST_CLUSTER_PROJECTILE.get(), Amethyst_Cluster_Projectile_Renderer::new);
        EntityRenderers.register(ModEntities.THE_LEVIATHAN_TONGUE.get(), RendererNull::new);
        EntityRenderers.register(ModEntities.VOID_SHARD.get(), (render) -> {
            return new ThrownItemRenderer<>(render, 0.75F, true);
        });
        EntityRenderers.register(ModEntities.EYE_OF_DUNGEON.get(), (render) -> {
            return new ThrownItemRenderer<>(render, 1.0F, true);
        });
        EntityRenderers.register(ModEntities.BLAZING_BONE.get(), Blazing_Bone_Renderer::new);
        EntityRenderers.register(ModEntities.LIONFISH_SPIKE.get(), Lionfish_Spike_Renderer::new);
        EntityRenderers.register(ModEntities.TIDAL_TENTACLE.get(), Tidal_Tentacle_Renderer::new);
        MinecraftForge.EVENT_BUS.register(new ClientEvent());
        try {
            ItemProperties.register(ModItems.BULWARK_OF_THE_FLAME.get(), new ResourceLocation("blocking"), (stack, p_239421_1_, p_239421_2_, j) -> p_239421_2_ != null && p_239421_2_.isUsingItem() && p_239421_2_.getUseItem() == stack ? 1.0F : 0.0F);
            ItemProperties.register(ModItems.SOUL_RENDER.get(), new ResourceLocation("blocking"), (stack, p_239421_1_, p_239421_2_, j) -> p_239421_2_ != null && p_239421_2_.isUsingItem() && p_239421_2_.getUseItem() == stack ? 1.0F : 0.0F);
            ItemProperties.register(ModItems.CORAL_SPEAR.get(), new ResourceLocation("throwing"), (stack, p_239421_1_, p_239421_2_, j) -> p_239421_2_ != null && p_239421_2_.isUsingItem() && p_239421_2_.getUseItem() == stack ? 1.0F : 0.0F);
            ItemProperties.register(ModItems.CORAL_BARDICHE.get(), new ResourceLocation("throwing"), (stack, p_239421_1_, p_239421_2_, j) -> p_239421_2_ != null && p_239421_2_.isUsingItem() && p_239421_2_.getUseItem() == stack ? 1.0F : 0.0F);
            ItemProperties.register(ModItems.MEAT_SHREDDER.get(), new ResourceLocation("using"), (stack, p_239421_1_, p_239421_2_, j) -> p_239421_2_ != null && p_239421_2_.isUsingItem() && p_239421_2_.getUseItem() == stack ? 1.0F : 0.0F);
            ItemProperties.register(Items.CROSSBOW, new ResourceLocation(Cataclysm.MODID, "void_scatter_arrow"), (stack, world, entity, j) -> entity != null && CrossbowItem.isCharged(stack) && CrossbowItem.containsChargedProjectile(stack, ModItems.VOID_SCATTER_ARROW.get()) ? 1.0F : 0.0F);
            ItemProperties.register(ModItems.CORAL_CHUNK.get(), new ResourceLocation("chunk"), (stack, level, living, j) -> (stack.getCount() % 3 == 0) ? 0.0F : (stack.getCount() % 3 == 1) ? 0.5F : 1.0F);
            ItemProperties.register(ModItems.BLACK_STEEL_TARGE.get(), new ResourceLocation("blocking"), (stack, p_239421_1_, p_239421_2_, j) -> p_239421_2_ != null && p_239421_2_.isUsingItem() && p_239421_2_.getUseItem() == stack ? 1.0F : 0.0F);
            
        } catch (Exception e) {
            Cataclysm.LOGGER.warn("Could not load item models for weapons");

        }
        BlockEntityRenderers.register(ModTileentites.ALTAR_OF_FIRE.get(), RendererAltar_of_Fire::new);
        BlockEntityRenderers.register(ModTileentites.ALTAR_OF_VOID.get(), RendererAltar_of_Void::new);
        BlockEntityRenderers.register(ModTileentites.DOOR_OF_SEAL.get(), Door_Of_Seal_Renderer::new);
        BlockEntityRenderers.register(ModTileentites.CURSED_TOMBSTONE.get(), Cursed_Tombstone_Renderer::new);
        BlockEntityRenderers.register(ModTileentites.EMP.get(), RendererEMP::new);
        BlockEntityRenderers.register(ModTileentites.MECHANICAL_FUSION_ANVIL.get(), RendererMechanical_fusion_anvil::new);
        BlockEntityRenderers.register(ModTileentites.ALTAR_OF_AMETHYST.get(), RendererAltar_of_Amethyst::new);
        BlockEntityRenderers.register(ModTileentites.CATACLYSM_SKULL.get(), Cataclysm_Skull_Block_Renderer::new);
        BlockEntityRenderers.register(ModTileentites.ALTAR_OF_ABYSS.get(), RendererAltar_of_Abyss::new);
        BlockEntityRenderers.register(ModTileentites.ABYSSAL_EGG.get(), RendererAbyssal_Egg::new);
        MenuScreens.register(ModMenu.WEAPON_FUSION.get(), GUIWeponfusion::new);
        CuriosRendererRegistry.register(ModItems.SANDSTORM_IN_A_BOTTLE.get(), RendererSandstorm_In_A_Bottle::new);
        CuriosRendererRegistry.register(ModItems.STICKY_GLOVES.get(), RendererSticky_Gloves::new);
        CuriosRendererRegistry.register(ModItems.KOBOLEDIATOR_SKULL.get(), CurioHeadRenderer::new);
        CuriosRendererRegistry.register(ModItems.APTRGANGR_HEAD.get(), CurioHeadRenderer::new);
        CuriosRendererRegistry.register(ModItems.DRAUGR_HEAD.get(), CurioHeadRenderer::new);
        CuriosRendererRegistry.register(ModItems.BLAZING_GRIPS.get(), Blazing_Grips_Renderer::new);
    }

    @OnlyIn(Dist.CLIENT)
    public static Callable<BlockEntityWithoutLevelRenderer> getTEISR() {
        return CMItemstackRenderer::new;
    }

    public Player getClientSidePlayer() {
        return Minecraft.getInstance().player;
    }
    
    public void blockRenderingEntity(UUID id) {
        blockedEntityRenders.add(id);
    }

    public void releaseRenderingEntity(UUID id) {
        blockedEntityRenders.remove(id);
    }

    public boolean isFirstPersonPlayer(Entity entity) {
        return entity.equals(Minecraft.getInstance().cameraEntity) && Minecraft.getInstance().options.getCameraType().isFirstPerson();
    }

    @Override
    public Object getISTERProperties() {
        return new CMItemRenderProperties();
    }

    @Override
    public Object getArmorRenderProperties() {
        return new CustomArmorRenderProperties();
    }

    public void clearSoundCacheFor(Entity entity) {
        ENTITY_SOUND_INSTANCE_MAP.remove(entity.getId());
    }

    public void clearSoundCacheFor(BlockEntity entity) {
        BLOCK_ENTITY_SOUND_INSTANCE_MAP.remove(entity);
    }

    public float getPartialTicks() {
        return Minecraft.getInstance().getPartialTick();
    }

    public boolean isKeyDown(int keyType) {
        if (keyType == -1) {
            return Minecraft.getInstance().options.keyLeft.isDown() || Minecraft.getInstance().options.keyRight.isDown() || Minecraft.getInstance().options.keyUp.isDown() || Minecraft.getInstance().options.keyDown.isDown() || Minecraft.getInstance().options.keyJump.isDown();
        }
        if (keyType == 0) {
            return Minecraft.getInstance().options.keyJump.isDown();
        }
        if (keyType == 1) {
            return Minecraft.getInstance().options.keySprint.isDown();
        }
        if (keyType == 2) {
            return ModKeybind.KEY_ABILITY.consumeClick();
        }
        if (keyType == 3) {
            return Minecraft.getInstance().options.keyAttack.isDown();
        }
        if (keyType == 4) {
            return Minecraft.getInstance().options.keyShift.isDown();
        }
        if (keyType == 5) {
            return ModKeybind.HELMET_KEY_ABILITY.isDown();
        }
        if (keyType == 6) {
            return ModKeybind.CHESTPLATE_KEY_ABILITY.isDown();
        }
        if (keyType == 7) {
            return ModKeybind.BOOTS_KEY_ABILITY.isDown();
        }
        return false;
    }


    @Override
    public void playWorldSound(@Nullable Object soundEmitter, byte type) {
        if (soundEmitter instanceof Entity entity && !entity.level.isClientSide) {
            return;
        }
        switch (type) {
            case 1:
                if (soundEmitter instanceof LivingEntity livingEntity) {
                    MeatShredderSound sound;
                    AbstractTickableSoundInstance old = ENTITY_SOUND_INSTANCE_MAP.get(livingEntity.getId());
                    if (old == null || !(old instanceof MeatShredderSound shredderSound && shredderSound.isSameEntity(livingEntity))) {
                        sound = new MeatShredderSound(livingEntity);
                        ENTITY_SOUND_INSTANCE_MAP.put(livingEntity.getId(), sound);
                    } else {
                        sound = (MeatShredderSound) old;
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound()) {
                        Minecraft.getInstance().getSoundManager().queueTickingSound(sound);
                    }
                }
                break;
            case 2:
                if (soundEmitter instanceof Sandstorm_Entity sandstom) {
                    SandstormSound sound;
                    AbstractTickableSoundInstance old = ENTITY_SOUND_INSTANCE_MAP.get(sandstom.getId());
                    if (old == null || !(old instanceof SandstormSound sandstomSound && sandstomSound.isSameEntity(sandstom))) {
                        sound = new SandstormSound(sandstom);
                        ENTITY_SOUND_INSTANCE_MAP.put(sandstom.getId(), sound);
                    } else {
                        sound = (SandstormSound) old;
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound()) {
                        Minecraft.getInstance().getSoundManager().queueTickingSound(sound);
                    }
                }
                break;
        }
    }

    private void registerKeybinds(RegisterKeyMappingsEvent e) {
        e.register(ModKeybind.KEY_ABILITY);
        e.register(ModKeybind.HELMET_KEY_ABILITY);
        e.register(ModKeybind.CHESTPLATE_KEY_ABILITY);
        e.register(ModKeybind.BOOTS_KEY_ABILITY);
    }

    public void removeBossBarRender(UUID bossBar) {
        this.bossBarRenderTypes.remove(bossBar);
    }

    public void setBossBarRender(UUID bossBar, int renderType) {
        this.bossBarRenderTypes.put(bossBar, renderType);
    }

}