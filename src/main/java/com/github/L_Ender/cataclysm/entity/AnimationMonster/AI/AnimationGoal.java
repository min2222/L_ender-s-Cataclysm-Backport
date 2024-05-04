package com.github.L_Ender.cataclysm.entity.AnimationMonster.AI;

import java.util.EnumSet;

import com.github.L_Ender.cataclysm.entity.AnimationMonster.Animation_Monster;
import com.github.L_Ender.lionfishapi.server.animation.Animation;
import com.github.L_Ender.lionfishapi.server.animation.IAnimatedEntity;

import net.minecraft.world.entity.ai.goal.Goal;

public abstract class AnimationGoal<T extends Animation_Monster & IAnimatedEntity> extends Goal {
    protected final T entity;

    protected AnimationGoal(T entity) {
        this(entity, true);
    }

    protected AnimationGoal(T entity, boolean interruptsAI) {
        this.entity = entity;
        if (interruptsAI) this.setFlags(EnumSet.of(Flag.MOVE,Flag.LOOK,Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        return this.test(this.entity.getAnimation());
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    //@Override
    //public boolean canContinueToUse() {
    //    return this.test(this.entity.getAnimation()) && this.entity.getAnimationTick() < this.entity.getAnimation().getDuration();
    //}

    protected abstract boolean test(Animation animation);
}