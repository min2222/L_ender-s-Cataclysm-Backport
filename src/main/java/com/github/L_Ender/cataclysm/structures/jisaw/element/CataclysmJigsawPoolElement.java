package com.github.L_Ender.cataclysm.structures.jisaw.element;

import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;

import com.github.L_Ender.cataclysm.structures.jisaw.condition.StructureCondition;
import com.github.L_Ender.cataclysm.structures.jisaw.condition.StructureConditionType;
import com.github.L_Ender.cataclysm.structures.jisaw.context.StructureContext;
import com.github.L_Ender.cataclysm.world.structures.terrainadaptation.EnhancedTerrainAdaptation;
import com.github.L_Ender.cataclysm.world.structures.terrainadaptation.EnhancedTerrainAdaptationType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

/**
 * Custom {@link StructurePoolElement} with support for many additional settings.
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class CataclysmJigsawPoolElement extends StructurePoolElement {
    /**
     * The name of this piece.
     * Solely used to uniquely identify pieces for the sake of tracking max_count.
     */
    public final Optional<String> name;

    /**
     * The maximum possible number of pieces with this name.
     * If this setting is used, the 'name' MUST be specified.
     */
    public final Optional<Integer> maxCount;

    /**
     * The minimum required depth (in jigsaw pieces) from the structure start
     * at which this piece can spawn.
     *
     * @deprecated Use {@link DepthCondition} instead.
     */
    @Deprecated
    public final Optional<Integer> minRequiredDepth;

    /**
     * The maximum allowed depth (in jigsaw pieces) from the structure start
     * at which this piece will no longer spawn.
     *
     * @deprecated Use {@link DepthCondition} instead.
     */
    @Deprecated
    public final Optional<Integer> maxPossibleDepth;

    /**
     * Whether this is a priority piece.
     * Priority pieces attempt to generate before all other pieces in the pool,
     * regardless of weight.
     */
    public final boolean isPriority;

    /**
     * Whether this piece should ignore the usual piece boundary checks.
     * Enabling this allows this piece to spawn while overlapping other pieces.
     */
    public final boolean ignoreBounds;

    /**
     * Optional condition required for this piece to spawn.
     * Can be any {@link StructureCondition}, including compound conditions
     * ({@link AnyOfCondition}, {@link AllOfCondition})
     */
    public final StructureCondition condition;

    /**
     * Optional enhanced terrain adaptation specific to this piece.
     * Takes priority over the structure's enhanced terrain adaptation if specified.
     */
    public final Optional<EnhancedTerrainAdaptation> enhancedTerrainAdaptation;

    public CataclysmJigsawPoolElement(
            StructureTemplatePool.Projection projection,
            Optional<String> name,
            Optional<Integer> maxCount,
            Optional<Integer> minRequiredDepth,
            Optional<Integer> maxPossibleDepth,
            boolean isPriority,
            boolean ignoreBounds,
            StructureCondition condition,
            Optional<EnhancedTerrainAdaptation> enhancedTerrainAdaptation
    ) {
        super(projection);
        this.name = name;
        this.maxCount = maxCount;
        this.minRequiredDepth = minRequiredDepth;
        this.maxPossibleDepth = maxPossibleDepth;
        this.isPriority = isPriority;
        this.ignoreBounds = ignoreBounds;
        this.condition = condition;
        this.enhancedTerrainAdaptation = enhancedTerrainAdaptation;
    }

    /* GETTERS */

    public Optional<String> getName() {
        return name;
    }

    public Optional<Integer> getMaxCount() {
        return maxCount;
    }

    @Deprecated
    public Optional<Integer> getMinRequiredDepth() {
        return minRequiredDepth;
    }

    @Deprecated
    public Optional<Integer> getMaxPossibleDepth() {
        return maxPossibleDepth;
    }

    public boolean isPriorityPiece() {
        return isPriority;
    }

    public boolean ignoresBounds() {
        return ignoreBounds;
    }

    public StructureCondition getCondition() {
        return condition;
    }

    public Optional<EnhancedTerrainAdaptation> getEnhancedTerrainAdaptation() {
        return enhancedTerrainAdaptation;
    }

    /* HELPERS */

    public boolean isAtValidDepth(int depth) {
        boolean isAtMinRequiredDepth = minRequiredDepth.isEmpty() || minRequiredDepth.get() <= depth;
        boolean isAtMaxAllowableDepth = maxPossibleDepth.isEmpty() || maxPossibleDepth.get() >= depth;
        return isAtMinRequiredDepth && isAtMaxAllowableDepth;
    }

    public boolean passesConditions(StructureContext ctx) {
        return this.condition.passes(ctx);
    }

    /* CODECS */

    public static <E extends CataclysmJigsawPoolElement> RecordCodecBuilder<E, Optional<String>> nameCodec() {
        return Codec.STRING.optionalFieldOf("name").forGetter(CataclysmJigsawPoolElement::getName);
    }

    public static <E extends CataclysmJigsawPoolElement> RecordCodecBuilder<E, Optional<Integer>> maxCountCodec() {
        return ExtraCodecs.NON_NEGATIVE_INT.optionalFieldOf("max_count").forGetter(CataclysmJigsawPoolElement::getMaxCount);
    }

    public static <E extends CataclysmJigsawPoolElement> RecordCodecBuilder<E, Optional<Integer>> minRequiredDepthCodec() {
        return ExtraCodecs.NON_NEGATIVE_INT.optionalFieldOf("min_required_depth").forGetter(CataclysmJigsawPoolElement::getMinRequiredDepth);
    }

    public static <E extends CataclysmJigsawPoolElement> RecordCodecBuilder<E, Optional<Integer>> maxPossibleDepthCodec() {
        return ExtraCodecs.NON_NEGATIVE_INT.optionalFieldOf("max_possible_depth").forGetter(CataclysmJigsawPoolElement::getMaxPossibleDepth);
    }

    public static <E extends CataclysmJigsawPoolElement> RecordCodecBuilder<E, Boolean> isPriorityCodec() {
        return Codec.BOOL.optionalFieldOf("is_priority", false).forGetter(CataclysmJigsawPoolElement::isPriorityPiece);
    }

    public static <E extends CataclysmJigsawPoolElement> RecordCodecBuilder<E, Boolean> ignoreBoundsCodec() {
        return Codec.BOOL.optionalFieldOf("ignore_bounds", false).forGetter(CataclysmJigsawPoolElement::ignoresBounds);
    }

    public static <E extends CataclysmJigsawPoolElement> RecordCodecBuilder<E, StructureCondition> conditionCodec() {
        return StructureConditionType.CONDITION_CODEC.optionalFieldOf("condition", StructureCondition.ALWAYS_TRUE).forGetter(CataclysmJigsawPoolElement::getCondition);
    }

    public static <E extends CataclysmJigsawPoolElement> RecordCodecBuilder<E, Optional<EnhancedTerrainAdaptation>> enhancedTerrainAdaptationCodec() {
        return EnhancedTerrainAdaptationType.ADAPTATION_CODEC.optionalFieldOf("enhanced_terrain_adaptation").forGetter(CataclysmJigsawPoolElement::getEnhancedTerrainAdaptation);
    }
}