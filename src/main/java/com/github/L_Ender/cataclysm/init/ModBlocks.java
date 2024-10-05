package com.github.L_Ender.cataclysm.init;


import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.blocks.Abyssal_Egg_Block;
import com.github.L_Ender.cataclysm.blocks.Altar_Of_Abyss_Block;
import com.github.L_Ender.cataclysm.blocks.Altar_Of_Amethyst_Block;
import com.github.L_Ender.cataclysm.blocks.Altar_Of_Fire_Block;
import com.github.L_Ender.cataclysm.blocks.Altar_Of_Void_Block;
import com.github.L_Ender.cataclysm.blocks.Cataclysm_Skull_Block;
import com.github.L_Ender.cataclysm.blocks.Cursed_Tombstone_Block;
import com.github.L_Ender.cataclysm.blocks.Door_of_Seal_Block;
import com.github.L_Ender.cataclysm.blocks.EMP_Block;
import com.github.L_Ender.cataclysm.blocks.EndStoneTeleportTrapBricks;
import com.github.L_Ender.cataclysm.blocks.Mechanical_fusion_Anvil;
import com.github.L_Ender.cataclysm.blocks.MeltingNetherrack;
import com.github.L_Ender.cataclysm.blocks.ObsidianExplosionTrapBricks;
import com.github.L_Ender.cataclysm.blocks.PointedIcicleBlock;
import com.github.L_Ender.cataclysm.blocks.PurpurVoidRuneTrapBlock;
import com.github.L_Ender.cataclysm.blocks.Sandstone_Falling_Trap;
import com.github.L_Ender.cataclysm.blocks.Sandstone_Ignite_Trap;
import com.github.L_Ender.cataclysm.blocks.Sandstone_Poison_Dart_Trap;
import com.github.L_Ender.cataclysm.blocks.Wall_Cataclysm_Skull_Block;
import com.github.L_Ender.cataclysm.items.BlockItemCMRender;
import com.github.L_Ender.cataclysm.items.CMBlockItem;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            Cataclysm.MODID);

    public static final RegistryObject<Block> WITHERITE_BLOCK = BLOCKS.register("witherite_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).color(MaterialColor.COLOR_BLACK)
                    .strength(50f, 1200f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHERITE_BLOCK)));

    public static final RegistryObject<Block> ENDERRITE_BLOCK = BLOCKS.register("enderite_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).color(MaterialColor.COLOR_BLACK)
                    .strength(50f, 1200f)
                    .sound(SoundType.NETHERITE_BLOCK)));

    public static final RegistryObject<Block> IGNITIUM_BLOCK = BLOCKS.register("ignitium_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).color(MaterialColor.COLOR_BLACK)
                    .strength(50f, 1200f)
                    .sound(SoundType.NETHERITE_BLOCK).lightLevel((state) -> {
                        return 15;
                    })));

    public static final RegistryObject<Block> ANCIENT_METAL_BLOCK = BLOCKS.register("ancient_metal_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).color(MaterialColor.COLOR_YELLOW)
                    .strength(25f, 600f)
                    .sound(SoundType.METAL)));
    
    public static final RegistryObject<Block> CURSIUM_BLOCK = BLOCKS.register("cursium_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).color(MaterialColor.COLOR_LIGHT_GREEN)
                    .strength(50f, 1200f)
                    .sound(SoundType.METAL)));

    public static final RegistryObject<Block> POLISHED_END_STONE = BLOCKS.register("polished_end_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE)));


    public static final RegistryObject<Block> POLISHED_END_STONE_SLAB = BLOCKS.register("polished_end_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(POLISHED_END_STONE.get())));

    public static final RegistryObject<Block> POLISHED_END_STONE_STAIRS = BLOCKS.register("polished_end_stone_stairs",
            () -> new StairBlock(POLISHED_END_STONE.get().defaultBlockState(),BlockBehaviour.Properties.copy(POLISHED_END_STONE.get())));


    public static final RegistryObject<Block> CHISELED_END_STONE_BRICKS = BLOCKS.register("chiseled_end_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS)));

    public static final RegistryObject<Block> VOID_INFUSED_END_STONE_BRICKS = BLOCKS.register("void_infused_end_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS)
                    .lightLevel((state) -> {
                        return 7;
                    })));

    public static final RegistryObject<Block> VOID_STONE = BLOCKS.register("void_stone",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).color(MaterialColor.COLOR_PURPLE)
                    .requiresCorrectToolForDrops()
                    .strength(50f, 1200f).lightLevel((state) -> {
                        return 7;
                    })));

    public static final RegistryObject<Block> VOID_LANTERN_BLOCK = BLOCKS.register("void_lantern_block" ,
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).color(MaterialColor.QUARTZ)
            .sound(SoundType.GLASS)
            .requiresCorrectToolForDrops()
            .strength(50f, 1200f)
            .lightLevel((state) -> {
        return 15;
    })));

    public static final RegistryObject<Block> END_STONE_PILLAR = BLOCKS.register("end_stone_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).color(MaterialColor.SAND)
                    .requiresCorrectToolForDrops()
                    .strength(3f, 9f)));


    public static final RegistryObject<Block> CHISELED_PURPUR_BLOCK = BLOCKS.register("chiseled_purpur_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));

    public static final RegistryObject<Block> OBSIDIAN_BRICKS = BLOCKS.register("obsidian_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)));

    public static final RegistryObject<Block> CHISELED_OBSIDIAN_BRICKS = BLOCKS.register("chiseled_obsidian_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(OBSIDIAN_BRICKS.get())));

    public static final RegistryObject<Block> OBSIDIAN_BRICK_SLAB = BLOCKS.register("obsidian_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(OBSIDIAN_BRICKS.get())));

    public static final RegistryObject<Block> OBSIDIAN_BRICK_STAIRS = BLOCKS.register("obsidian_brick_stairs",
            () -> new StairBlock(OBSIDIAN_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(OBSIDIAN_BRICKS.get())));

    public static final RegistryObject<Block> OBSIDIAN_BRICK_WALL = BLOCKS.register("obsidian_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(OBSIDIAN_BRICKS.get())));

    public static final RegistryObject<Block> PURPUR_WALL = BLOCKS.register("purpur_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));

    public static final RegistryObject<Block> PURPUR_VOID_RUNE_TRAP_BLOCK = BLOCKS.register("purpur_void_rune_trap_block",
            () -> new PurpurVoidRuneTrapBlock(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK).randomTicks().lightLevel(getLightValueLit(7))));

    public static final RegistryObject<Block> END_STONE_TELEPORT_TRAP_BRICKS = BLOCKS.register("end_stone_teleport_trap_bricks",
            () -> new EndStoneTeleportTrapBricks(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS).randomTicks().lightLevel(getLightValueLit(7))));

    public static final RegistryObject<Block> OBSIDIAN_EXPLOSION_TRAP_BRICKS = BLOCKS.register("obsidian_explosion_trap_bricks",
            () -> new ObsidianExplosionTrapBricks(BlockBehaviour.Properties.copy(OBSIDIAN_BRICKS.get()).randomTicks().lightLevel(getLightValueLit(7))));

    public static final RegistryObject<Block> SANDSTONE_POISON_DART_TRAP = BLOCKS.register("sandstone_poison_dart_trap",
            () -> new Sandstone_Poison_Dart_Trap((BlockBehaviour.Properties.of(Material.STONE)
                    .color(MaterialColor.SAND)
                    .requiresCorrectToolForDrops().strength(0.8F))));

    public static final RegistryObject<Block> SANDSTONE_IGNITE_TRAP = BLOCKS.register("sandstone_ignite_trap",
            () -> new Sandstone_Ignite_Trap(BlockBehaviour.Properties.of(Material.STONE)
                    .color(MaterialColor.SAND)
                    .randomTicks()
                    .requiresCorrectToolForDrops()
                    .strength(0.8F)));

    public static final RegistryObject<Block> SANDSTONE_FALLING_TRAP = BLOCKS.register("sandstone_falling_trap",
            () -> new Sandstone_Falling_Trap(BlockBehaviour.Properties.of(Material.STONE)
                    .color(MaterialColor.SAND)
                    .randomTicks()
                    .requiresCorrectToolForDrops()
                    .strength(0.8F)));

    public static final RegistryObject<Block> ALTAR_OF_FIRE = registerBlockAndItem("altar_of_fire",
            Altar_Of_Fire_Block::new, new Item.Properties().rarity(Rarity.EPIC).fireResistant(), true);

    public static final RegistryObject<Block> ALTAR_OF_VOID = registerBlockAndItem("altar_of_void",
            Altar_Of_Void_Block::new, new Item.Properties().rarity(Rarity.EPIC).fireResistant(), true);

    public static final RegistryObject<Block> ALTAR_OF_AMETHYST = registerBlockAndItem("altar_of_amethyst",
            Altar_Of_Amethyst_Block::new, new Item.Properties().rarity(Rarity.EPIC).fireResistant(), true);

    public static final RegistryObject<Block> ALTAR_OF_ABYSS = registerBlockAndItem("altar_of_abyss",
            Altar_Of_Abyss_Block::new, new Item.Properties().rarity(Rarity.EPIC).fireResistant(), true);
    
    public static final RegistryObject<Block> CURSED_TOMBSTONE = registerBlockAndItem("cursed_tombstone",
            Cursed_Tombstone_Block::new, new Item.Properties().rarity(Rarity.EPIC).fireResistant(), true);

    public static final RegistryObject<Block> DUNGEON_BLOCK = BLOCKS.register("dungeon_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).color(MaterialColor.STONE).strength(-1.0F, 3600000.0F).noLootTable()));

    public static final RegistryObject<Block> EMP = registerBlockAndItem("emp",
            EMP_Block::new, new Item.Properties().rarity(Rarity.EPIC), true);

    public static final RegistryObject<Block> MECHANICAL_FUSION_ANVIL = registerBlockAndItem("mechanical_fusion_anvil",
            Mechanical_fusion_Anvil::new, new Item.Properties().rarity(Rarity.EPIC).fireResistant(), true);

    public static final RegistryObject<Block> DOOR_OF_SEAL = registerBlockAndItem("door_of_seal",
            Door_of_Seal_Block::new, new Item.Properties().rarity(Rarity.EPIC), true);

    public static final RegistryObject<Block> DOOR_OF_SEAL_PART = BLOCKS.register("door_of_seal_part", () -> new
            Door_of_Seal_Block.Door_Of_Seal_Part_Block(BlockBehaviour.Properties.copy(ModBlocks.DOOR_OF_SEAL.get())));
    
    public static final RegistryObject<Block> KOBOLEDIATOR_SKULL = BLOCKS.register("kobolediator_skull", () -> new Cataclysm_Skull_Block(Cataclysm_Skull_Block.Types.KOBOLEDIATOR, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));


    public static final RegistryObject<Block> KOBOLEDIATOR_WALL_SKULL = BLOCKS.register("kobolediator_wall_skull", () -> new Wall_Cataclysm_Skull_Block(Cataclysm_Skull_Block.Types.KOBOLEDIATOR, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F).lootFrom(() -> KOBOLEDIATOR_SKULL.get())));

    public static final RegistryObject<Block> APTRGANGR_HEAD = BLOCKS.register("aptrgangr_head", () -> new Cataclysm_Skull_Block(Cataclysm_Skull_Block.Types.APTRGANGR, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));


    public static final RegistryObject<Block> APTRGANGR_WALL_HEAD = BLOCKS.register("aptrgangr_wall_head", () -> new Wall_Cataclysm_Skull_Block(Cataclysm_Skull_Block.Types.APTRGANGR, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F).lootFrom(() -> APTRGANGR_HEAD.get())));

    public static final RegistryObject<Block> DRAUGR_HEAD = BLOCKS.register("draugr_head", () -> new Cataclysm_Skull_Block(Cataclysm_Skull_Block.Types.DRAUGR, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));

    public static final RegistryObject<Block> DRAUGR_WALL_HEAD = BLOCKS.register("draugr_wall_head", () -> new Wall_Cataclysm_Skull_Block(Cataclysm_Skull_Block.Types.DRAUGR, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F).lootFrom(() -> APTRGANGR_HEAD.get())));
    
    public static final RegistryObject<Block> ABYSSAL_EGG = registerBlockAndItem("abyssal_egg",
            Abyssal_Egg_Block::new, new Item.Properties().rarity(Rarity.EPIC).fireResistant(), true);

    public static final RegistryObject<Block> CHORUS_STEM = BLOCKS.register("chorus_stem",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).color(MaterialColor.COLOR_PURPLE).
                    strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CHORUS_PLANKS = BLOCKS.register("chorus_planks",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).color(MaterialColor.COLOR_PURPLE).
                    strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CHORUS_SLAB = BLOCKS.register("chorus_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(CHORUS_PLANKS.get())));

    public static final RegistryObject<Block> CHORUS_STAIRS = BLOCKS.register("chorus_stairs",
            () -> new StairBlock(CHORUS_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(CHORUS_PLANKS.get())));
    
    public static final RegistryObject<Block> CHORUS_FENCE = BLOCKS.register("chorus_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(CHORUS_PLANKS.get())));

    public static final RegistryObject<Block> QUARTZ_BRICK_WALL = BLOCKS.register("quartz_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BRICKS)));

    public static final RegistryObject<Block> STONE_PILLAR = BLOCKS.register("stone_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).color(MaterialColor.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> CHISELED_STONE_BRICK_PILLAR = BLOCKS.register("chiseled_stone_brick_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).color(MaterialColor.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> STONE_TILES = BLOCKS.register("stone_tiles",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).color(MaterialColor.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> FROSTED_STONE_BRICKS = BLOCKS.register("frosted_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).color(MaterialColor.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> FROSTED_STONE_BRICK_SLAB = BLOCKS.register("frosted_stone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(FROSTED_STONE_BRICKS.get())));

    public static final RegistryObject<Block> FROSTED_STONE_BRICK_STAIRS = BLOCKS.register("frosted_stone_brick_stairs",
            () -> new StairBlock(FROSTED_STONE_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(FROSTED_STONE_BRICKS.get())));

    public static final RegistryObject<Block> FROSTED_STONE_BRICK_WALL = BLOCKS.register("frosted_stone_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(FROSTED_STONE_BRICKS.get())));

    public static final RegistryObject<Block> BLACK_STEEL_BLOCK = BLOCKS.register("black_steel_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).color(MaterialColor.COLOR_BLACK)
                    .strength(25f, 600f)
                    .sound(SoundType.METAL)));

    public static final RegistryObject<Block> BLACK_STEEL_FENCE = BLOCKS.register("black_steel_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(BLACK_STEEL_BLOCK.get())));

    public static final RegistryObject<Block> BLACK_STEEL_WALL = BLOCKS.register("black_steel_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(BLACK_STEEL_BLOCK.get())));

    public static final RegistryObject<Block> STONE_TILE_SLAB = BLOCKS.register("stone_tile_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(STONE_TILES.get())));

    public static final RegistryObject<Block> STONE_TILE_STAIRS = BLOCKS.register("stone_tile_stairs",
            () -> new StairBlock(STONE_TILES.get().defaultBlockState(),BlockBehaviour.Properties.copy(STONE_TILES.get())));

    public static final RegistryObject<Block> STONE_TILE_WALL = BLOCKS.register("stone_tile_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(STONE_TILES.get())));

    public static final RegistryObject<Block> POLISHED_SANDSTONE = BLOCKS.register("polished_sandstone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));

    public static final RegistryObject<Block> BLACKSTONE_PILLAR = BLOCKS.register("blackstone_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).color(MaterialColor.COLOR_BLACK)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> POINTED_ICICLE = BLOCKS.register("pointed_icicle",
            () -> new PointedIcicleBlock(BlockBehaviour.Properties.of(Material.ICE).color(MaterialColor.ICE)
                    .noOcclusion()
                    .randomTicks()
                    .sound(SoundType.GLASS)
                    .strength(0.5F)
                    .dynamicShape()
                    .offsetType(BlockBehaviour.OffsetType.XZ)));

    private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
        return (state) -> {
            return state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
        };
    }

    public static final RegistryObject<Block> MELTING_NETHERRACK = BLOCKS.register("melting_netherrack" ,
            () -> new MeltingNetherrack(BlockBehaviour.Properties.of(Material.STONE).color(MaterialColor.NETHER).randomTicks().
                    lightLevel((state) -> {
                        return 3;
                    })));

    public static RegistryObject<Block> registerBlockAndItem(String name, Supplier<Block> block, Item.Properties blockItemProps, boolean specialRender){
        RegistryObject<Block> blockObj = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> specialRender ?  new BlockItemCMRender(blockObj, blockItemProps.tab(Cataclysm.TAB)) :  new CMBlockItem(blockObj, blockItemProps.tab(Cataclysm.TAB)));
        return blockObj;
    }

}
