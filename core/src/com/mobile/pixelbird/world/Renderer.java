package com.mobile.pixelbird.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mobile.pixelbird.helper.AssetLoader;
import com.mobile.pixelbird.object.Bird;

public class Renderer {
    private PixelWorld world;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private int height;
    private int minY;

    public Renderer(PixelWorld world, int height, int minY) {
        this.world = world;
        this.height = height;
        this.minY = minY;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(true, 136, height);
        this.shapeRenderer = new ShapeRenderer();
        this.shapeRenderer.setProjectionMatrix(camera.combined);
        this.batcher = new SpriteBatch();
        this.batcher.setProjectionMatrix(camera.combined);
    }

    public void render(float time) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Bird bird = world.getBird();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, minY + 66);
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, minY + 66, 136, 11);
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, minY + 77, 136, 52);
        shapeRenderer.end();

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(AssetLoader.background, 0, minY + 23, 136, 43);
        batcher.enableBlending();
        batcher.draw((TextureRegion) AssetLoader.birdAnimation.getKeyFrame(time),
                bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());
        batcher.end();
    }

}
