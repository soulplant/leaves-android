package com.jk.leaves;

import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;

public class Test {
    public static void main(String[] args) {
        Bezier<Vector2> b = new Bezier<Vector2>(new Vector2(0, 0), new Vector2(0.5f, 1), new Vector2(1, 1));
        int steps = 100;
        for (int i = 0; i <= steps; i++) {
            float x = i / (float) steps;
            Vector2 out = new Vector2();
            b.valueAt(out, x);
            System.out.println(x + ":" + out);
        }
        Vector2 out = new Vector2();
        b.derivativeAt(out, 0.5f);
        System.out.println("derivative = " + out);
        System.out.println("HI");
    }
}
