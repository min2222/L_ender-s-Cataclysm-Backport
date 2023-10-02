package com.github.L_Ender.cataclysm.entity.BossMonsters.AI;

import com.github.L_Ender.cataclysm.entity.AnimationMonster.Animation_Monster;
import com.github.L_Ender.cataclysm.entity.BossMonsters.Boss_monster;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;

import java.util.EnumSet;

public class SimpleAnimationGoal<T extends Animation_Monster & IAnimatedEntity> extends AnimationGoal<T> {

    private final Animation animation;

    public SimpleAnimationGoal(T entity, Animation animation) {
        super(entity);
        this.animation = animation;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP, Flag.LOOK));
    }

    @Override
    protected boolean test(Animation animation) {
        return animation == this.animation;
    }


}