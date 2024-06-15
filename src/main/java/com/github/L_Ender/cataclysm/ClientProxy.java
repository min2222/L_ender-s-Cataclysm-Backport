package com.github.L_Ender.cataclysm;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

import javax.annotation.Nullable;

import com.github.L_Ender.cataclysm.client.event.ClientEvent;
import com.github.L_Ender.cataclysm.client.gui.GUIWeponfusion;
import com.github.L_Ender.cataclysm.client.particle.CursedFlameParticle;
import com.github.L_Ender.cataclysm.client.particle.EM_PulseParticle;
import com.github.L_Ender.cataclysm.client.particle.LightningParticle;
import com.github.L_Ender.cataclysm.client.particle.MaledictusRingParticle;
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
import com.github.L_Ender.cataclysm.client.render.blockentity.RendererAbyssal_Egg;
import com.github.L_Ender.cataclysm.client.render.blockentity.RendererAltar_of_Abyss;
import com.github.L_Ender.cataclysm.client.render.blockentity.RendererAltar_of_Amethyst;
import com.github.L_Ender.cataclysm.client.render.blockentity.RendererAltar_of_Fire;
import com.github.L_Ender.cataclysm.client.render.blockentity.RendererAltar_of_Void;
import com.github.L_Ender.cataclysm.client.render.blockentity.RendererEMP;
import com.github.L_Ender.cataclysm.client.render.blockentity.RendererMechanical_fusion_anvil;
import com.github.L_Ender.cataclysm.client.render.entity.RendererAbyss_Blast;
import com.github.L_Ender.cataclysm.client.render.entity.RendererAbyss_Blast_Portal;
import com.github.L_Ender.cataclysm.client.render.entity.RendererAbyss_Mark;
import com.github.L_Ender.cataclysm.client.render.entity.RendererAbyss_Mine;
import com.github.L_Ender.cataclysm.client.render.entity.RendererAbyss_Orb;
import com.github.L_Ender.cataclysm.client.render.entity.RendererAbyss_Portal;
import com.github.L_Ender.cataclysm.client.render.entity.RendererAmethyst_Cluster_Projectile;
import com.github.L_Ender.cataclysm.client.render.entity.RendererAmethyst_Crab;
import com.github.L_Ender.cataclysm.client.render.entity.RendererAncient_Desert_Stele;
import com.github.L_Ender.cataclysm.client.render.entity.RendererAncient_Remnant;
import com.github.L_Ender.cataclysm.client.render.entity.RendererBlazing_Bone;
import com.github.L_Ender.cataclysm.client.render.entity.RendererCm_Falling_Block;
import com.github.L_Ender.cataclysm.client.render.entity.RendererCoral_Golem;
import com.github.L_Ender.cataclysm.client.render.entity.RendererCoralssus;
import com.github.L_Ender.cataclysm.client.render.entity.RendererDeath_Laser_beam;
import com.github.L_Ender.cataclysm.client.render.entity.RendererDeepling;
import com.github.L_Ender.cataclysm.client.render.entity.RendererDeepling_Angler;
import com.github.L_Ender.cataclysm.client.render.entity.RendererDeepling_Brute;
import com.github.L_Ender.cataclysm.client.render.entity.RendererDeepling_Priest;
import com.github.L_Ender.cataclysm.client.render.entity.RendererDeepling_Warlock;
import com.github.L_Ender.cataclysm.client.render.entity.RendererDimensional_Rift;
import com.github.L_Ender.cataclysm.client.render.entity.RendererEnder_Golem;
import com.github.L_Ender.cataclysm.client.render.entity.RendererEnder_Guardian;
import com.github.L_Ender.cataclysm.client.render.entity.RendererEnder_Guardian_bullet;
import com.github.L_Ender.cataclysm.client.render.entity.RendererEndermaptera;
import com.github.L_Ender.cataclysm.client.render.entity.RendererFlame_Strike;
import com.github.L_Ender.cataclysm.client.render.entity.RendererIgnis;
import com.github.L_Ender.cataclysm.client.render.entity.RendererIgnis_Abyss_Fireball;
import com.github.L_Ender.cataclysm.client.render.entity.RendererIgnis_Fireball;
import com.github.L_Ender.cataclysm.client.render.entity.RendererIgnited_Berserker;
import com.github.L_Ender.cataclysm.client.render.entity.RendererIgnited_Revenant;
import com.github.L_Ender.cataclysm.client.render.entity.RendererKobolediator;
import com.github.L_Ender.cataclysm.client.render.entity.RendererKoboleton;
import com.github.L_Ender.cataclysm.client.render.entity.RendererLaser_Beam;
import com.github.L_Ender.cataclysm.client.render.entity.RendererLava_Bomb;
import com.github.L_Ender.cataclysm.client.render.entity.RendererLionfish;
import com.github.L_Ender.cataclysm.client.render.entity.RendererLionfish_Spike;
import com.github.L_Ender.cataclysm.client.render.entity.RendererMaledictus;
import com.github.L_Ender.cataclysm.client.render.entity.RendererMini_Abyss_Blast;
import com.github.L_Ender.cataclysm.client.render.entity.RendererModern_Remnant;
import com.github.L_Ender.cataclysm.client.render.entity.RendererNameless_Sorcerer;
import com.github.L_Ender.cataclysm.client.render.entity.RendererNetherite_Monstrosity;
import com.github.L_Ender.cataclysm.client.render.entity.RendererNull;
import com.github.L_Ender.cataclysm.client.render.entity.RendererPhantom_Arrow;
import com.github.L_Ender.cataclysm.client.render.entity.RendererPoison_Dart;
import com.github.L_Ender.cataclysm.client.render.entity.RendererPortal_Abyss_Blast;
import com.github.L_Ender.cataclysm.client.render.entity.RendererSandstorm;
import com.github.L_Ender.cataclysm.client.render.entity.RendererSandstorm_Projectile;
import com.github.L_Ender.cataclysm.client.render.entity.RendererThe_Baby_Leviathan;
import com.github.L_Ender.cataclysm.client.render.entity.RendererThe_Harbinger;
import com.github.L_Ender.cataclysm.client.render.entity.RendererThe_Leviathan;
import com.github.L_Ender.cataclysm.client.render.entity.RendererThe_Prowler;
import com.github.L_Ender.cataclysm.client.render.entity.RendererThe_Watcher;
import com.github.L_Ender.cataclysm.client.render.entity.RendererThrown_Coral_Bardiche;
import com.github.L_Ender.cataclysm.client.render.entity.RendererThrown_Coral_Spear;
import com.github.L_Ender.cataclysm.client.render.entity.RendererTidal_Hook;
import com.github.L_Ender.cataclysm.client.render.entity.RendererTidal_Tentacle;
import com.github.L_Ender.cataclysm.client.render.entity.RendererVoid_Howitzer;
import com.github.L_Ender.cataclysm.client.render.entity.RendererVoid_Rune;
import com.github.L_Ender.cataclysm.client.render.entity.RendererVoid_Scatter_Arrow;
import com.github.L_Ender.cataclysm.client.render.entity.RendererVoid_Vortex;
import com.github.L_Ender.cataclysm.client.render.entity.RendererWadjet;
import com.github.L_Ender.cataclysm.client.render.entity.RendererWither_Homing_Missile;
import com.github.L_Ender.cataclysm.client.render.entity.RendererWither_Howitzer;
import com.github.L_Ender.cataclysm.client.render.entity.RendererWither_Missile;
import com.github.L_Ender.cataclysm.client.render.etc.CurioHeadRenderer;
import com.github.L_Ender.cataclysm.client.render.item.CMItemRenderProperties;
import com.github.L_Ender.cataclysm.client.render.item.CustomArmorRenderProperties;
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
        registry.register(ModParticle.MALEDICTUSRING.get(), MaledictusRingParticle.RingFactory::new);
        registry.register(ModParticle.SANDSTORM.get(), SandStormParticle.Factory::new);
        registry.register(ModParticle.TRAP_FLAME.get(), TrapFlameParticle.Factory::new);
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
        EntityRenderers.register(ModEntities.IGNITED_BERSERKER.get(), RendererIgnited_Berserker::new);
        EntityRenderers.register(ModEntities.THE_HARBINGER.get(), RendererThe_Harbinger::new);
        EntityRenderers.register(ModEntities.VOID_SCATTER_ARROW.get(), RendererVoid_Scatter_Arrow::new);
        EntityRenderers.register(ModEntities.POISON_DART.get(), RendererPoison_Dart::new);
        EntityRenderers.register(ModEntities.PHANTOM_ARROW.get(), RendererPhantom_Arrow::new);
        EntityRenderers.register(ModEntities.SCREEN_SHAKE.get(), RendererNull::new);
        EntityRenderers.register(ModEntities.HOLD_ATTACK.get(), RendererNull::new);
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
        EntityRenderers.register(ModEntities.DEEPLING_WARLOCK.get(), RendererDeepling_Warlock::new);
        EntityRenderers.register(ModEntities.ABYSS_MARK.get(), RendererAbyss_Mark::new);
        EntityRenderers.register(ModEntities.CORAL_GOLEM.get(), RendererCoral_Golem::new);
        EntityRenderers.register(ModEntities.CORALSSUS.get(), RendererCoralssus::new);
        EntityRenderers.register(ModEntities.LIONFISH.get(), RendererLionfish::new);
        EntityRenderers.register(ModEntities.TIDAL_HOOK.get(), RendererTidal_Hook::new);
        EntityRenderers.register(ModEntities.AMETHYST_CRAB.get(), RendererAmethyst_Crab::new);
        EntityRenderers.register(ModEntities.ANCIENT_REMNANT.get(), RendererAncient_Remnant::new);
        EntityRenderers.register(ModEntities.MODERN_REMNANT.get(), RendererModern_Remnant::new);
        EntityRenderers.register(ModEntities.SANDSTORM.get(), RendererSandstorm::new);
        EntityRenderers.register(ModEntities.SANDSTORM_PROJECTILE.get(), RendererSandstorm_Projectile::new);
        EntityRenderers.register(ModEntities.THE_WATCHER.get(), RendererThe_Watcher::new);
        EntityRenderers.register(ModEntities.THE_PROWLER.get(), RendererThe_Prowler::new);
        EntityRenderers.register(ModEntities.KOBOLETON.get(), RendererKoboleton::new);
        EntityRenderers.register(ModEntities.KOBOLEDIATOR.get(), RendererKobolediator::new);
        EntityRenderers.register(ModEntities.WADJET.get(), RendererWadjet::new);
        EntityRenderers.register(ModEntities.MALEDICTUS.get(), RendererMaledictus::new);
        EntityRenderers.register(ModEntities.EARTHQUAKE.get(), RendererNull::new);
        EntityRenderers.register(ModEntities.ANCIENT_DESERT_STELE.get(), RendererAncient_Desert_Stele::new);
        EntityRenderers.register(ModEntities.AMETHYST_CLUSTER_PROJECTILE.get(), RendererAmethyst_Cluster_Projectile::new);
        EntityRenderers.register(ModEntities.THE_LEVIATHAN_TONGUE.get(), RendererNull::new);
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
            ItemProperties.register(ModItems.MEAT_SHREDDER.get(), new ResourceLocation("using"), (stack, p_239421_1_, p_239421_2_, j) -> p_239421_2_ != null && p_239421_2_.isUsingItem() && p_239421_2_.getUseItem() == stack ? 1.0F : 0.0F);
            ItemProperties.register(Items.CROSSBOW, new ResourceLocation(Cataclysm.MODID, "void_scatter_arrow"), (stack, world, entity, j) -> entity != null && CrossbowItem.isCharged(stack) && CrossbowItem.containsChargedProjectile(stack, ModItems.VOID_SCATTER_ARROW.get()) ? 1.0F : 0.0F);
            ItemProperties.register(ModItems.CORAL_CHUNK.get(), new ResourceLocation("chunk"), (stack, level, living, j) -> (stack.getCount() % 3 == 0) ? 0.0F : (stack.getCount() % 3 == 1) ? 0.5F : 1.0F);

        } catch (Exception e) {
            Cataclysm.LOGGER.warn("Could not load item models for weapons");

        }
        BlockEntityRenderers.register(ModTileentites.ALTAR_OF_FIRE.get(), RendererAltar_of_Fire::new);
        BlockEntityRenderers.register(ModTileentites.ALTAR_OF_VOID.get(), RendererAltar_of_Void::new);
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
    }

    @OnlyIn(Dist.CLIENT)
    public static Callable<BlockEntityWithoutLevelRenderer> getTEISR() {
        return CMItemstackRenderer::new;
    }

    public Player getClientSidePlayer() {
        return Minecraft.getInstance().player;
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