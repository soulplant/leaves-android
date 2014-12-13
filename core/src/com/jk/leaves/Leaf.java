package com.jk.leaves;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import java.util.Map;

/**
 * Created by jameskozianski on 11/15/14.
 */
public class Leaf extends Actor {

    private Stage stage;

    private static class ChangeTextureAction extends Action {
        private Texture texture;

        public ChangeTextureAction(Texture texture) {
            this.texture = texture;
        }

        @Override
        public boolean act(float delta) {
            ((Leaf) getActor()).setTexture(texture);
            return true;
        }
    }

    private void setTexture(Texture texture) {
        sprite.setTexture(texture);
    }

    private final Sprite sprite;

    public Leaf(final Stage stage, float direction, float x, float y, final Map<String, Texture> textures) {
        this.stage = stage;
        boolean order = MathUtils.randomBoolean();
        Texture image = textures.get("leaf");
        Texture image2 = textures.get("leaf2");
        sprite = new Sprite(order ? image : image2);
        setTouchable(Touchable.enabled);
        setPosition(x, y);
        setSize(image.getWidth(), image.getHeight());

        Action fallLeft = new LeafFallAction(direction * 50, 10, 15, 1);
        Action fallRight = new LeafFallAction(direction * -50, 10, 15, 1);
        Action setImage1 = new ChangeTextureAction(image2);
        Action setImage2 = new ChangeTextureAction(image);
        Action fall = Actions.forever(new SequenceAction(
                fallLeft,
                order ? setImage1 : setImage2,
                fallRight,
                order ? setImage2 : setImage1
        ));
        addAction(fall);
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Vector2 v1 = Util.getRandomDirection().scl(30);
                Vector2 v2 = Util.getRandomDirection().scl(40);
                stage.addActor(new Particle(getX(), getY(), v1, 0, textures.get("half-leaf")));
                stage.addActor(new Particle(getX(), getY(), v2, 0, textures.get("half-leaf2")));
                Leaf.this.remove();
                return true;
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (getY() < -getHeight()) {
            remove();
        }
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(getX(), getY());
        sprite.draw(batch);
    }
}
