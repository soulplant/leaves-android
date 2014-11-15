package com.jk.leaves;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RemoveActorAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by jameskozianski on 11/15/14.
 */
public class Leaf extends Actor {

    private final Sprite sprite;

    public Leaf(float x, float y, Texture image) {
        sprite = new Sprite(image);
        setPosition(x, y);
        setSize(image.getWidth(), image.getHeight());

        Action fall = Actions.forever(new SequenceAction(new SwingAction(1, 1, this), new SwingAction(-1, 1, this)));
        addAction(fall);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(getX(), getY());
        sprite.draw(batch);
    }
}
