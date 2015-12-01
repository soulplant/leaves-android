package com.jk.leaves;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.HashMap;
import java.util.Map;

public class LeavesMain extends ApplicationAdapter {
    private Stage stage;

    @Override
	public void create () {
        final Texture img = new Texture("leaf.png");
        final Texture img2 = new Texture("leaf2.png");
        final Map<String, Texture> textures = new HashMap<String, Texture>();
        loadTexture(textures, "leaf");
        loadTexture(textures, "leaf2");
        loadTexture(textures, "half-leaf");
        loadTexture(textures, "half-leaf2");
        stage = new Stage(new FitViewport(112, 160));
        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println(x + ", " + y + " touched");
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        Gdx.input.setInputProcessor(stage);
        Action generateLeaves = Actions.forever(new SequenceAction(new RandomDelayAction(0.25f, 0.75f), new Action() {
            @Override
            public boolean act(float delta) {
                stage.addActor(new Leaf(stage, MathUtils.randomBoolean() ? -1 : 1, MathUtils.random(112), 160, textures));
                return true;
            }
        }));
        stage.addAction(generateLeaves);
	}

    private void loadTexture(Map<String, Texture> textures, String s) {
        textures.put(s, new Texture(s + ".png"));
    }

    @Override
	public void render () {
        fillColor(24, 190, 255);
        stage.act();
        stage.draw();
	}

    private void fillColor(int r, int g, int b) {
        Gdx.gl.glClearColor(r / 255f, g / 255f, b / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
