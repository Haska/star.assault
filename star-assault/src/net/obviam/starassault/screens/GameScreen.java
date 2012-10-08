package net.obviam.starassault.screens;

import net.obviam.starassault.controller.WorldController;
import net.obviam.starassault.model.World;
import net.obviam.starassault.view.WorldRenderer;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class GameScreen implements Screen, InputProcessor {

	private World 			world;
	private WorldRenderer 	renderer;
	private WorldController	controller;
	
	private int width, height;
	
	
	public void show() {
		world = new World();
		renderer = new WorldRenderer(world, true);
		controller = new WorldController(world);
		Gdx.input.setInputProcessor(this);
	}

	
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		controller.update(delta);
		renderer.render();
	}
	
	
	public void resize(int width, int height) {
		renderer.setSize(width, height);
		this.width = width;
		this.height = height;
	}

	
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	
	public void pause() {
		// TODO Auto-generated method stub
	}

	
	public void resume() {
		// TODO Auto-generated method stub
	}

	
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}

	// * InputProcessor methods ***************************//

	
	public boolean keyDown(int keycode) {
		if (keycode == Keys.LEFT)
			controller.leftPressed();
		if (keycode == Keys.RIGHT)
			controller.rightPressed();
		if (keycode == Keys.Z)
			controller.jumpPressed();
		if (keycode == Keys.X)
			controller.firePressed();
		return true;
	}

	
	public boolean keyUp(int keycode) {
		if (keycode == Keys.LEFT)
			controller.leftReleased();
		if (keycode == Keys.RIGHT)
			controller.rightReleased();
		if (keycode == Keys.Z)
			controller.jumpReleased();
		if (keycode == Keys.X)
			controller.fireReleased();
		return true;
	}

	
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (!Gdx.app.getType().equals(ApplicationType.Android))
			return false;
		if (x < width / 2 && y > height / 2) {
			controller.leftPressed();
		}
		if (x > width / 2 && y > height / 2) {
			controller.rightPressed();
		}
		return true;
	}

	
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!Gdx.app.getType().equals(ApplicationType.Android))
			return false;
		if (x < width / 2 && y > height / 2) {
			controller.leftReleased();
		}
		if (x > width / 2 && y > height / 2) {
			controller.rightReleased();
		}
		return true;
	}

	
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		controller.leftReleased();
		controller.rightReleased();
		return true;
	}

	
	public boolean touchMoved(int x, int y) {
		controller.leftReleased();
		controller.rightReleased();
		return true;	}

	
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
