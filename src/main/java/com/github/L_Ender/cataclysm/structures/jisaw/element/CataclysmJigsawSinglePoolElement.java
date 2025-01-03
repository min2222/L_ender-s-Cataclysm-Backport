package com.github.L_Ender.cataclysm.structures.jisaw.element;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.annotation.ParametersAreNonnullByDefault;

import com.github.L_Ender.cataclysm.init.ModJigsaw;
import com.github.L_Ender.cataclysm.structures.jisaw.condition.StructureCondition;
import com.github.L_Ender.cataclysm.world.structures.modifier.StructureModifier;
import com.github.L_Ender.cataclysm.world.structures.terrainadaptation.EnhancedTerrainAdaptation;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.properties.StructureMode;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.JigsawReplacementProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

/**
 * Custom {@link SinglePoolElement} with support for many additional settings.
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CataclysmJigsawSinglePoolElement extends CataclysmJigsawPoolElement {
    private static final Codec<Either<ResourceLocation, StructureTemplate>> TEMPLATE_CODEC = Codec.of(CataclysmJigsawSinglePoolElement::encodeTemplate, ResourceLocation.CODEC.map(Either::left));
    public static final Codec<CataclysmJigsawSinglePoolElement> CODEC = RecordCodecBuilder.create((builder) -> builder
            .group(
                    templateCodec(),
                    processorsCodec(),
                    projectionCodec(),
                    nameCodec(),
                    maxCountCodec(),
                    minRequiredDepthCodec(),
                    maxPossibleDepthCodec(),
                    isPriorityCodec(),
                    ignoreBoundsCodec(),
                    conditionCodec(),
                    enhancedTerrainAdaptationCodec(),
                    ResourceLocation.CODEC.optionalFieldOf("deadend_pool").forGetter(element -> element.deadendPool),
                    StructureModifier.CODEC.listOf().optionalFieldOf("modifiers", new ArrayList<>()).forGetter(element -> element.modifiers)
            ).apply(builder, CataclysmJigsawSinglePoolElement::new));

    public final Either<ResourceLocation, StructureTemplate> template;

    public final Holder<StructureProcessorList> processors;

    /**
     * Whether this piece should apply dead end adjustments.
     * If enabled, this piece has the possibility of being converted into one of its fallback pieces under certain conditions:
     * <ul>
     *     <li>This piece has one or more jigsaw blocks in addition to the one required to connect to its parent piece.</li>
     *     <li>This piece is not able to generate any other pieces from itself, including encased pieces.
     *     This could be due to either overlap with other pieces or reaching the max depth.</li>
     * </ul>
     * If both of these conditions are met, then a piece marked for deadend adjustments will be replaced with
     * an appropriate piece from its fallback pool instead.
     * <br />
     * <p>
     * <b>Note that at least one of the fallback pool elements MUST be a "true" terminator, i.e. have NO jigsaw pieces
     * except the one required for connection to its parent piece.</b> If all of your fallback pieces have more than one
     * jigsaw block, your structure's generation may get stuck in an infinite loop!
     * </p>
     */
    public final Optional<ResourceLocation> deadendPool;

    /**
     * Post-placement modifiers.
     * These are modifications run on a piece after the entire structure's layout has been generated,
     * allowing for last-second conditional changes.
     */
    public final List<StructureModifier> modifiers;

    public CataclysmJigsawSinglePoolElement(
            Either<ResourceLocation, StructureTemplate> template,
            Holder<StructureProcessorList> processors,
            StructureTemplatePool.Projection projection,
            Optional<String> name,
            Optional<Integer> maxCount,
            Optional<Integer> minRequiredDepth,
            Optional<Integer> maxPossibleDepth,
            boolean isPriority,
            boolean ignoreBounds,
            StructureCondition condition,
            Optional<EnhancedTerrainAdaptation> enhancedTerrainAdaptation,
            Optional<ResourceLocation> deadendPool,
            List<StructureModifier> modifiers
    ) {
        super(projection, name, maxCount, minRequiredDepth, maxPossibleDepth, isPriority, ignoreBounds, condition, enhancedTerrainAdaptation);
        this.template = template;
        this.processors = processors;
        this.deadendPool = deadendPool;
        this.modifiers = modifiers;
    }

    @Override
    public Vec3i getSize(StructureTemplateManager structureTemplateManager, Rotation rotation) {
        StructureTemplate structureTemplate = this.getTemplate(structureTemplateManager);
        return structureTemplate.getSize(rotation);
    }

    @Override
    public List<StructureTemplate.StructureBlockInfo> getShuffledJigsawBlocks(
            StructureTemplateManager structureTemplateManager,
            BlockPos blockPos,
            Rotation rotation,
            RandomSource randomSource
    ) {
        StructureTemplate structureTemplate = this.getTemplate(structureTemplateManager);
        ObjectArrayList<StructureTemplate.StructureBlockInfo> jigsawBlocks = structureTemplate.filterBlocks(blockPos, (new StructurePlaceSettings()).setRotation(rotation), Blocks.JIGSAW, true);
        Util.shuffle(jigsawBlocks, randomSource);
        return jigsawBlocks;
    }

    @Override
    public BoundingBox getBoundingBox(StructureTemplateManager structureTemplateManager, BlockPos blockPos, Rotation rotation) {
        StructureTemplate structureTemplate = this.getTemplate(structureTemplateManager);
        return structureTemplate.getBoundingBox((new StructurePlaceSettings()).setRotation(rotation), blockPos);
    }

    @Override
    public boolean place(StructureTemplateManager structureTemplateManager,
                         WorldGenLevel worldGenLevel,
                         StructureManager structureManager,
                         ChunkGenerator chunkGenerator,
                         BlockPos pos,
                         BlockPos pivotPos,
                         Rotation rotation,
                         BoundingBox boundingBox,
                         RandomSource randomSource,
                         boolean replaceJigsaws
    ) {
        StructureTemplate structureTemplate = this.getTemplate(structureTemplateManager);
        StructurePlaceSettings structurePlaceSettings = this.getSettings(rotation, boundingBox, replaceJigsaws);
        if (!structureTemplate.placeInWorld(worldGenLevel, pos, pivotPos, structurePlaceSettings, randomSource, 18)) {
            return false;
        } else {
            for(StructureTemplate.StructureBlockInfo $$13 : StructureTemplate.processBlockInfos(worldGenLevel, pos, pivotPos, structurePlaceSettings, this.getDataMarkers(structureTemplateManager, pos, rotation, false))) {
                this.handleDataMarker(worldGenLevel, $$13, pos, rotation, randomSource, boundingBox);
            }

            return true;
        }
    }

    public Optional<ResourceLocation> getDeadendPool() {
        return this.deadendPool;
    }

    public boolean hasModifiers() {
        return this.modifiers.size() > 0;
    }

    public StructureTemplate getTemplate(StructureTemplateManager structureTemplateManager) {
        return this.template.map(structureTemplateManager::getOrCreate, Function.identity());
    }

    public StructurePoolElementType<?> getType() {
        return ModJigsaw.CATACLYSM_ELEMENT;
    }

    public String toString() {
        return String.format("YungJigsawSingle[%s][%s][%s][%s]",
                this.name.orElse("<unnamed>"),
                this.template,
                this.maxCount.isPresent() ? maxCount.get() : "no max count",
                this.isPriority);
    }

    private StructurePlaceSettings getSettings(Rotation rotation, BoundingBox boundingBox, boolean replaceJigsaws) {
        StructurePlaceSettings structurePlaceSettings = new StructurePlaceSettings();
        structurePlaceSettings.setBoundingBox(boundingBox);
        structurePlaceSettings.setRotation(rotation);
        structurePlaceSettings.setKnownShape(true);
        structurePlaceSettings.setIgnoreEntities(false);
        structurePlaceSettings.addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
        structurePlaceSettings.setFinalizeEntities(true);
        if (!replaceJigsaws) {
            structurePlaceSettings.addProcessor(JigsawReplacementProcessor.INSTANCE);
        }

        this.processors.value().list().forEach(structurePlaceSettings::addProcessor);
        this.getProjection().getProcessors().forEach(structurePlaceSettings::addProcessor);
        return structurePlaceSettings;
    }

    private List<StructureTemplate.StructureBlockInfo> getDataMarkers(StructureTemplateManager structureTemplateManager, BlockPos blockPos, Rotation rotation, boolean isPositionLocal) {
        StructureTemplate structureTemplate = this.getTemplate(structureTemplateManager);
        List<StructureTemplate.StructureBlockInfo> structureBlocks = structureTemplate.filterBlocks(blockPos, (new StructurePlaceSettings()).setRotation(rotation), Blocks.STRUCTURE_BLOCK, isPositionLocal);
        List<StructureTemplate.StructureBlockInfo> dataBlocks = Lists.newArrayList();

        for(StructureTemplate.StructureBlockInfo block : structureBlocks) {
            StructureMode structureMode = StructureMode.valueOf(block.nbt.getString("mode"));
            if (structureMode == StructureMode.DATA) {
                dataBlocks.add(block);
            }
        }

        return dataBlocks;
    }

    public static <E extends CataclysmJigsawSinglePoolElement> RecordCodecBuilder<E, Holder<StructureProcessorList>> processorsCodec() {
        return StructureProcessorType.LIST_CODEC.fieldOf("processors").forGetter(element -> element.processors);
    }

    public static <E extends CataclysmJigsawSinglePoolElement> RecordCodecBuilder<E, Either<ResourceLocation, StructureTemplate>> templateCodec() {
        return TEMPLATE_CODEC.fieldOf("location").forGetter(($$0) -> $$0.template);
    }

    private static <T> DataResult<T> encodeTemplate(Either<ResourceLocation, StructureTemplate> either, DynamicOps<T> ops, T template) {
        Optional<ResourceLocation> optional = either.left();
        return !optional.isPresent() ? DataResult.error("Can not serialize a runtime pool element") : ResourceLocation.CODEC.encode(optional.get(), ops, template);
    }
}
