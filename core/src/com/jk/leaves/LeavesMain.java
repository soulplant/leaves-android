package com.jk.leaves;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class LeavesMain extends ApplicationAdapter {
	SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private Group leaves;

    @Override
	public void create () {
        // TODO(koz): Make this pixel perfect with padding on the sides.
        camera = new OrthographicCamera(112, 160);
        camera.translate(112 / 2, 160 / 2);
        camera.update();
		batch = new SpriteBatch();
        final Texture img = new Texture("leaf.png");
        final Texture img2 = new Texture("leaf2.png");
        font = new BitmapFont();
        font.scale(3);
        leaves = new Group();
//        Gdx.input.setInputProcessor(new InputAdapter() {
//            @Override
//            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//                Vector3 gameCoords = camera.unproject(new Vector3(screenX, screenY, 0));
//                System.out.println("hi there " + gameCoords);
//                leaves.addActor(new Leaf(MathUtils.randomBoolean() ? -1 : 1, gameCoords.x, gameCoords.y, img, img2));
//                return true;
//            }
//        });
        Action generateLeaves = Actions.forever(new SequenceAction(new RandomDelayAction(0.25f, 0.75f), new Action() {
            @Override
            public boolean act(float delta) {
                leaves.addActor(new Leaf(MathUtils.randomBoolean() ? -1 : 1, MathUtils.random(112), 160, img, img2));
                return true;
            }
        }));
        leaves.addAction(generateLeaves);
	}

    @Override
	public void render () {
        fillColor(24, 190, 255);
        leaves.act(Gdx.graphics.getDeltaTime());
        batch.setProjectionMatrix(camera.combined);
		batch.begin();
        leaves.draw(batch, 1);
        font.draw(batch, "hello, world !@#%%", 200, 400);
		batch.end();
	}

    private void fillColor(int r, int g, int b) {
        Gdx.gl.glClearColor(r / 255f, g / 255f, b / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
