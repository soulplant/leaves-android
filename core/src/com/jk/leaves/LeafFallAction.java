package com.jk.leaves;

import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;

/**
 * Created by jameskozianski on 11/16/14.
 */
public class LeafFallAction extends Action {
    private final float width;
    private final float dip1;
    private final float dip2;
    private final float duration;
    private float elapsed;
    private Bezier<Vector2> curve;

    public LeafFallAction(float width, float dip1, float dip2, float duration) {
        this.width = width;
        this.dip1 = dip1;
        this.dip2 = dip2;
        this.duration = duration;
        this.elapsed = 0;
    }

    @Override
    public void restart() {
        elapsed = 0;
        curve = null;
    }

    @Override
    public boolean act(float delta) {
        if (curve == null) {
            Vector2 start = new Vector2(getActor().getX(), getActor().getY());
            Vector2 end = new Vector2(start.x + width, start.y - dip1);
            Vector2 mid = new Vector2(start.x + (width * 0.50f), start.y - dip2);
            curve = new Bezier<Vector2>(start, mid, end);
        }
        elapsed += delta;
        if (elapsed > duration) {
            elapsed = duration;
        }
        float p = elapsed / duration;
        Vector2 out = new Vector2();
        curve.valueAt(out, easeInOut(p, 0, 1, 1.1f));
        getActor().setPosition(out.x, out.y);
        return elapsed == duration;
    }

    public static float easeInOut (float t, float b, float c, float d) {
        if ((t/=d/2) < 1) return c/2*t*t*t + b;
        return c/2*((t-=2)*t*t + 2) + b;
    }
}
