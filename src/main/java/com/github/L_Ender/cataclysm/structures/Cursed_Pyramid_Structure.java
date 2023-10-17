package com.github.L_Ender.cataclysm.structures;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.init.ModStructures;
import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProtectedBlockProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.Map;
import java.util.Optional;

public class Cursed_Pyramid_Structure extends Structure {

    public static final Codec<Cursed_Pyramid_Structure> CODEC = simpleCodec(Cursed_Pyramid_Structure::new);

    private static final ResourceLocation ARENA1 = new ResourceLocation(Cataclysm.MODID, "cursed_pyramid1");


    private static final Map<ResourceLocation, BlockPos> OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
            .put(ARENA1, new BlockPos(0, 1, 0))
            .build();


    /*
     * Begins assembling your structure and where the pieces needs to go.
     */
    public static void start(StructureTemplateManager templateManager, BlockPos pos, Rotation rotation, StructurePieceAccessor pieceList, RandomSource random) {
        int x = pos.getX();
        int z = pos.getZ();

        BlockPos rotationOffSet = new BlockPos(0, 0, 0).rotate(rotation);
        BlockPos blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, ARENA1, blockpos, rotation));
    }

    public Cursed_Pyramid_Structure(StructureSettings p_227593_) {
        super(p_227593_);
    }
    
    public Optional<GenerationStub> findGenerationPoint(GenerationContext p_228964_) {
        int i = p_228964_.chunkPos().x >> 16;
        int j = p_228964_.chunkPos().z >> 16;
        BlockPos blockpos = new BlockPos(p_228964_.chunkPos().getMinBlockX(), 60, p_228964_.chunkPos().getMinBlockZ());
        WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(0L));
        worldgenrandom.setSeed((long) (i ^ j << 9) ^ p_228964_.seed());
        worldgenrandom.nextInt();
        return Optional.of(new GenerationStub(blockpos, (p_228526_) -> {
            generatePieces(p_228526_, p_228964_);
        }));
    }

    private static void generatePieces(StructurePiecesBuilder p_197233_, GenerationContext p_197234_) {
        BlockPos blockpos = new BlockPos(p_197234_.chunkPos().getMinBlockX(), 70, p_197234_.chunkPos().getMinBlockZ());
        Rotation rotation = Rotation.getRandom(p_197234_.random());
        Cursed_Pyramid_Structure.start(p_197234_.structureTemplateManager(), blockpos, rotation, p_197233_, p_197234_.random());
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
            int treasureBox = rand.nextInt(5) + 1;
            for (int i = 1; i <= 5; i++) {
                String string = "sand_" + i;
                if (string.equals(function)) {
                    if (i == treasureBox) {
                        worldIn.setBlock(pos, Blocks.SUSPICIOUS_SAND.defaultBlockState(), 2);
                        worldIn.getBlockEntity(pos, BlockEntityType.BRUSHABLE_BLOCK).ifPresent((p_277328_) -> {
                            p_277328_.setLootTable(new ResourceLocation(Cataclysm.MODID, "archaeology/cursed_pyramid"), pos.asLong());
                        });
                    }else{
                        worldIn.setBlock(pos, Blocks.DIAMOND_BLOCK.defaultBlockState(), 2);
                    }
                }
            }
        }
    }
}
