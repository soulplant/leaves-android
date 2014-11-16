package com.jk.leaves;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class LeavesMain extends ApplicationAdapter {
    private Stage stage;

    @Override
	public void create () {
        final Texture img = new Texture("leaf.png");
        final Texture img2 = new Texture("leaf2.png");
        stage = new Stage(new FitViewport(112, 160));
        Gdx.input.setInputProcessor(stage);
        Action generateLeaves = Actions.forever(new SequenceAction(new RandomDelayAction(0.25f, 0.75f), new Action() {
            @Override
            public boolean act(float delta) {
                stage.addActor(new Leaf(MathUtils.randomBoolean() ? -1 : 1, MathUtils.random(112), 160, img, img2));
                return true;
            }
        }));
        stage.addAction(generateLeaves);
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
