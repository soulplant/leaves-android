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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class LeavesMain extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Group leaves;

    @Override
	public void create () {
        // TODO(koz): Make this pixel perfect with padding on the sides.
        camera = new OrthographicCamera(112, 160);
        camera.translate(112 / 2, 160 / 2);
        camera.update();
		batch = new SpriteBatch();
        img = new Texture("leaf.png");
        font = new BitmapFont();
        font.scale(3);
        leaves = new Group();
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector3 gameCoords = camera.unproject(new Vector3(screenX, screenY, 0));
                System.out.println("hi there " + gameCoords);
                leaves.addActor(new Leaf(gameCoords.x, gameCoords.y, img));
                return true;
            }
        });
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
