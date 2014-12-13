package com.jk.leaves;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by jameskozianski on 12/14/14.
 */
public class Particle extends Actor {

    private Texture texture;
    private float dx;
    private float dy;
    private float lifetime;
    private float ddx = 1f;
    private float ddy = 1f;

    public Particle(float x, float y, Vector2 delta, float lifetime, Texture texture) {
        this.dx = delta.x;
        this.dy = delta.y;
        this.lifetime = lifetime;
        setPosition(x, y);
        this.texture = texture;
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                setX(getX() - delta * Particle.this.dx);
                setY(getY() - delta * Particle.this.dy);
                Particle.this.dx = reduceBy(Particle.this.dx, ddx);
                Particle.this.dy = reduceBy(Particle.this.dy, ddy);
                return false;
            }
        });
    }

    private static float reduceBy(float x, float by) {
        if (x > 0) {
            x -= by;
            if (x < 0) {
                x = 0;
            }
        }
        if (x < 0) {
            x += by;
            if (x > 0) {
                x = 0;
            }
        }
        return x;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (getY() < -getHeight()) {
            remove();
        }
        if (lifetime > 0) {
            lifetime -= delta;
            if (lifetime <= 0) {
                remove();
            }
        }
    }
}
