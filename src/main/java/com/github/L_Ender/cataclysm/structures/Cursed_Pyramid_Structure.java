package com.github.L_Ender.cataclysm.structures;

import java.util.Map;
import java.util.Optional;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.Koboleton_Entity;
import com.github.L_Ender.cataclysm.entity.BossMonsters.Ancient_Remnant_Entity;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.L_Ender.cataclysm.init.ModStructures;
import com.github.L_Ender.cataclysm.world.structures.Processor.WaterLoggingFixProcessor;
import com.google.common.collect.ImmutableMap;
import com.min01.archaeology.init.ArchaeologyBlockEntityType;
import com.min01.archaeology.init.ArchaeologyBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProtectedBlockProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class Cursed_Pyramid_Structure extends Structure {

    public static final Codec<Cursed_Pyramid_Structure> CODEC = simpleCodec(Cursed_Pyramid_Structure::new);

    private static final ResourceLocation LOWER1 = new ResourceLocation(Cataclysm.MODID, "cursed_pyramid_lower1");
    private static final ResourceLocation LOWER2 = new ResourceLocation(Cataclysm.MODID, "cursed_pyramid_lower2");
    private static final ResourceLocation LOWER3 = new ResourceLocation(Cataclysm.MODID, "cursed_pyramid_lower3");
    private static final ResourceLocation LOWER4 = new ResourceLocation(Cataclysm.MODID, "cursed_pyramid_lower4");
    private static final ResourceLocation UPPER1 = new ResourceLocation(Cataclysm.MODID, "cursed_pyramid_upper1");
    private static final ResourceLocation UPPER2 = new ResourceLocation(Cataclysm.MODID, "cursed_pyramid_upper2");
    private static final ResourceLocation UPPER3 = new ResourceLocation(Cataclysm.MODID, "cursed_pyramid_upper3");
    private static final ResourceLocation UPPER4 = new ResourceLocation(Cataclysm.MODID, "cursed_pyramid_upper4");
    private static final ResourceLocation OBELISK1 = new ResourceLocation(Cataclysm.MODID, "cursed_pyramid_obelisk1");
    private static final ResourceLocation OBELISK2 = new ResourceLocation(Cataclysm.MODID, "cursed_pyramid_obelisk2");

    private static final Map<ResourceLocation, BlockPos> OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
            .put(LOWER1, new BlockPos(0, 1, 0))
            .put(LOWER2, new BlockPos(0, 1, 0))
            .put(LOWER3, new BlockPos(0, 1, 0))
            .put(LOWER4, new BlockPos(0, 1, 0))
            .put(UPPER1, new BlockPos(0, 1, 0))
            .put(UPPER2, new BlockPos(0, 1, 0))
            .put(UPPER3, new BlockPos(0, 1, 0))
            .put(UPPER4, new BlockPos(0, 1, 0))
            .put(OBELISK1, new BlockPos(0, 1, 0))
            .put(OBELISK2, new BlockPos(0, 1, 0))
            .build();


    /*
     * Begins assembling your structure and where the pieces needs to go.
     */
    public static void start(StructureTemplateManager templateManager, BlockPos pos, Rotation rotation, StructurePieceAccessor pieceList, RandomSource random) {
        int x = pos.getX();
        int z = pos.getZ();

        BlockPos rotationOffSet = new BlockPos(0, -39, 0).rotate(rotation);
        BlockPos blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, LOWER1, blockpos, rotation));
        rotationOffSet = new BlockPos(0, -39, 47).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, LOWER2, blockpos, rotation));
        rotationOffSet = new BlockPos(47, -39, 0).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, LOWER3, blockpos, rotation));
        rotationOffSet = new BlockPos(47, -39, 47).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, LOWER4, blockpos, rotation));


        rotationOffSet = new BlockPos(0, 9, 0).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, UPPER1, blockpos, rotation));

        rotationOffSet = new BlockPos(0, 9, 47).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, UPPER2, blockpos, rotation));

        rotationOffSet = new BlockPos(47, 9, 0).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, UPPER3, blockpos, rotation));

        rotationOffSet = new BlockPos(47, 9, 47).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, UPPER4, blockpos, rotation));

        rotationOffSet = new BlockPos(20, -4, 94).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, OBELISK1, blockpos, rotation));

        rotationOffSet = new BlockPos(45, -4, 94).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, OBELISK2, blockpos, rotation));

    }

    public Cursed_Pyramid_Structure(StructureSettings p_227593_) {
        super(p_227593_);
    }

    public Optional<GenerationStub> findGenerationPoint(GenerationContext p_228964_) {
        Rotation rotation = Rotation.getRandom(p_228964_.random());
        BlockPos blockpos = this.getLowestYIn5by5BoxOffset7Blocks(p_228964_, rotation);
        return getLowestY(p_228964_, 107, 87) < p_228964_.chunkGenerator().getSeaLevel() ? Optional.empty() : onTopOfChunkCenter(p_228964_, Heightmap.Types.WORLD_SURFACE_WG, (p_226545_) -> {
            this.generatePieces(p_226545_, p_228964_, blockpos, rotation);
        });
    }

    private void generatePieces(StructurePiecesBuilder p_197233_, GenerationContext p_197234_,BlockPos blockPos, Rotation rotation) {


        Cursed_Pyramid_Structure.start(p_197234_.structureTemplateManager(), blockPos, rotation, p_197233_, p_197234_.random());
    }


    @Override
    public StructureType<?> type() {
        return ModStructures.CURSED_PYRAMID.get();
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    public static class Piece extends TemplateStructurePiece {

        public Piece(StructureTemplateManager templateManagerIn, ResourceLocation resourceLocationIn, BlockPos pos, Rotation rotation) {
            super(ModStructures.CPD.get(), 0, templateManagerIn, resourceLocationIn, resourceLocationIn.toString(), makeSettings(rotation), makePosition(resourceLocationIn, pos));
        }

        public Piece(StructureTemplateManager templateManagerIn, CompoundTag tagCompound) {
            super(ModStructures.CPD.get(), tagCompound, templateManagerIn, (p_162451_) -> {
                return makeSettings(Rotation.valueOf(tagCompound.getString("Rot")));
            });

        }

        public Piece(StructurePieceSerializationContext context, CompoundTag tag) {
            this(context.structureTemplateManager(), tag);
        }

        private static StructurePlaceSettings makeSettings(Rotation p_163156_) {
            BlockIgnoreProcessor blockignoreprocessor = BlockIgnoreProcessor.STRUCTURE_BLOCK;

            StructurePlaceSettings structureplacesettings = (new StructurePlaceSettings()).setRotation(p_163156_).setMirror(Mirror.NONE)
                    .addProcessor(blockignoreprocessor)
                    .addProcessor(new WaterLoggingFixProcessor())
                    .addProcessor(new ProtectedBlockProcessor(BlockTags.FEATURES_CANNOT_REPLACE));


            return structureplacesettings;
        }

        private static BlockPos makePosition(ResourceLocation p_162453_, BlockPos p_162454_) {
            return p_162454_.offset(OFFSET.get(p_162453_));
        }

        protected void addAdditionalSaveData(StructurePieceSerializationContext p_162444_, CompoundTag tagCompound) {
            super.addAdditionalSaveData(p_162444_, tagCompound);
            tagCompound.putString("Rot", this.placeSettings.getRotation().name());
        }


        @Override
        protected void handleDataMarker(String function, BlockPos pos, ServerLevelAccessor worldIn, RandomSource rand, BoundingBox sbb) {
            switch (function) {
                case "necklace" -> {
                    worldIn.setBlock(pos, ArchaeologyBlocks.SUSPICIOUS_SAND.get().defaultBlockState(), 2);
                    worldIn.getBlockEntity(pos, ArchaeologyBlockEntityType.BRUSHABLE_BLOCK.get()).ifPresent((blockEntity) -> {
                        ResourceLocation lootTableLocation = new ResourceLocation(Cataclysm.MODID, "archaeology/cursed_pyramid_necklace");
                        blockEntity.setLootTable(lootTableLocation, pos.asLong());
                    });
                }
                case "sus" -> {
                    worldIn.setBlock(pos, ArchaeologyBlocks.SUSPICIOUS_SAND.get().defaultBlockState(), 2);
                    worldIn.getBlockEntity(pos, ArchaeologyBlockEntityType.BRUSHABLE_BLOCK.get()).ifPresent((blockEntity) -> {
                        ResourceLocation lootTableLocation = new ResourceLocation(Cataclysm.MODID, "archaeology/cursed_pyramid");
                        blockEntity.setLootTable(lootTableLocation, pos.asLong());
                    });
                }
                case "koboleton" -> {
                    Koboleton_Entity koboleton = ModEntities.KOBOLETON.get().create(worldIn.getLevel());
                    if (koboleton != null) {
                        koboleton.setPersistenceRequired();
                        koboleton.moveTo(pos, 0.0F, 0.0F);
                        koboleton.finalizeSpawn(worldIn, worldIn.getCurrentDifficultyAt(koboleton.blockPosition()), MobSpawnType.STRUCTURE, (SpawnGroupData) null, (CompoundTag) null);
                        worldIn.addFreshEntityWithPassengers(koboleton);
                        worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                    }
                }
                case "remnant" -> {
                    Ancient_Remnant_Entity remnant = ModEntities.ANCIENT_REMNANT.get().create(worldIn.getLevel());
                    if (remnant != null) {
                        remnant.setPersistenceRequired();
                        remnant.moveTo(pos, 0.0F, 0.0F);
                        remnant.setIsAct(false);
                        remnant.finalizeSpawn(worldIn, worldIn.getCurrentDifficultyAt(remnant.blockPosition()), MobSpawnType.STRUCTURE, (SpawnGroupData) null, (CompoundTag) null);
                        worldIn.addFreshEntityWithPassengers(remnant);
                        worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                    }
                }
            }
        }
    }
}
