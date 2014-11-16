package com.jk.leaves;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import java.util.Map;

/**
 * Created by jameskozianski on 11/16/14.
 */
public class Giblet extends Actor {
    private final float x;
    private final float y;
    private final Map<String, Texture> textures;
    private final Sprite sprite;

    public Giblet(int direction, float x, float y, Map<String, Texture> textures) {
        this.x = x;
        this.y = y;
        this.textures = textures;
        Texture image = textures.get(direction < 0 ? "half-leaf" : "half-leaf2");
        sprite = new Sprite(image);
        sprite.setBounds(x, y, image.getWidth(), image.getHeight());
        setBounds(x, y, image.getWidth(), image.getHeight());
        addAction(new SequenceAction(Actions.moveBy(direction * 10, -20, 0.33f), Actions.removeActor()));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setX(getX());
        sprite.setY(getY());
        sprite.draw(batch, parentAlpha);
    }
}
