package com.mobile.pixelbird.screen;

import com.badlogic.gdx.Gdx;
import com.mobile.pixelbird.helper.InputHandler;
import com.mobile.pixelbird.world.PixelWorld;
import com.mobile.pixelbird.world.Renderer;

public class PixelScreen implements com.badlogic.gdx.Screen {
    private PixelWorld world;
    private Renderer renderer;

    private float runTime = 0.0f;

    public PixelScreen() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        this.world = new PixelWorld((int) (gameHeight / 2));
        this.renderer = new Renderer(world, (int) gameHeight, (int) (gameHeight / 2));

        Gdx.input.setInputProcessor(new InputHandler(world.getBird()));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        this.runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
