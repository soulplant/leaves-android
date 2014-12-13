package com.jk.leaves;

import com.badlogic.gdx.math.Vector2;

public class Util {
    public static Vector2 getRandomDirection() {
        Vector2 v = new Vector2();
        v.set((float) Math.random() - 0.5f, (float) Math.random() - 0.5f);
        v.nor();
        return v;
    }
}
