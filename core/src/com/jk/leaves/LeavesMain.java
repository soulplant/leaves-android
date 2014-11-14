package com.jk.leaves;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LeavesMain extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    private BitmapFont font;

    @Override
	public void create () {
        System.out.println(Gdx.graphics.getWidth() + "x" + Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        System.out.println("image: " + img.getWidth() + "x" + img.getHeight());
        System.out.println("hi");
        font = new BitmapFont();
        font.scale(10);
	}

	@Override
	public void render () {
        if (Gdx.input.isTouched()) {
            System.out.println("boo: " + Gdx.input.getX() + "x" + Gdx.input.getY());
        }
        fillColor(24, 190, 255);
		batch.begin();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (i != j) {
                    batch.draw(img, i * 257, j * 256);
                }
            }
        }
        font.draw(batch, "hey there", 400, 500);
        font.draw(batch, "hello", 400, 400);
		batch.end();
	}

    private void fillColor(int r, int g, int b) {
        Gdx.gl.glClearColor(r / 255f, g / 255f, b / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
