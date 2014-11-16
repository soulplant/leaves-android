package com.jk.leaves;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;

/**
 * Created by jameskozianski on 11/16/14.
 */
public class RandomDelayAction extends Action {
    private final float min;
    private final float max;
    private float duration;
    private float elapsed;

    public RandomDelayAction(float min, float max) {
        this.min = min;
        this.max = max;
        restart();
    }

    @Override
    public boolean act(float delta) {
        elapsed += delta;
        return elapsed >= duration;
    }

    @Override
    public void restart() {
        duration = MathUtils.random(min, max);
        elapsed = 0;
    }
}
