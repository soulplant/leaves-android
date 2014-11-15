package com.jk.leaves;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by jameskozianski on 11/15/14.
 */
public class SwingAction extends Action {
    private Vector2 initialPosition;
    private float elapsed;
    private int direction;
    private float duration;
    private float width;

    public SwingAction(int direction, float duration, Actor actor) {
        this.direction = direction;
        this.duration = duration;
        this.width = (float) (2 * Math.sqrt(5));
        reset();
        setActor(actor);
    }

    @Override
    public void restart() {
        reset();
    }

    @Override
    public void reset() {
        this.elapsed = 0;
        this.initialPosition = null;
    }

    @Override
    public boolean act(float delta) {
        if (initialPosition == null) {
            initialPosition = new Vector2(actor.getX(), actor.getY());
            System.out.println("initial position = " + initialPosition);
        }
        elapsed += delta;
        if (elapsed > duration) {
            elapsed = duration;
        }
        Vector2 p = initialPosition.cpy().add(getCurrentPosition());
        getActor().setPosition(p.x, p.y);
        if (elapsed == duration) {
            return true;
        }
        return false;
    }

    private Vector2 getCurrentPosition() {
        float x = (elapsed / duration) * width * 0.75f;
        float y = (float) (Math.pow(Math.sqrt(5) - x, 2) - 5);
        return new Vector2(direction * x * 5, y);
    }
}
