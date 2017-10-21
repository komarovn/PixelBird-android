package com.mobile.pixelbird.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mobile.pixelbird.helper.AssetLoader;
import com.mobile.pixelbird.object.Bird;
import com.mobile.pixelbird.object.Grass;
import com.mobile.pixelbird.object.Pipe;
import com.mobile.pixelbird.object.ScrollHandler;

public class Renderer {
    private PixelWorld world;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private Animation animation;
    private TextureRegion background, grass;
    private TextureRegion birdCenter, birdDown, birdUp;
    private TextureRegion skullUp, skullDown, bar;

    private SpriteBatch batcher;

    private Bird bird;
    private ScrollHandler scroller;
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3;
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

        initObjects();
        initAssets();
    }

    private void initObjects() {
        this.bird = this.world.getBird();
        this.scroller = world.getScroller();
        this.frontGrass = scroller.getFrontGrass();
        this.backGrass = scroller.getBackGrass();
        this.pipe1 = scroller.getPipe1();
        this.pipe2 = scroller.getPipe2();
        this.pipe3 = scroller.getPipe3();
    }

    private void initAssets() {
        this.background = AssetLoader.background;
        this.grass = AssetLoader.grass;
        this.birdCenter = AssetLoader.bird;
        this.birdDown = AssetLoader.birdDown;
        this.birdUp = AssetLoader.birdUp;
        this.skullUp = AssetLoader.skullUp;
        this.skullDown = AssetLoader.skullDown;
        this.bar = AssetLoader.bar;
        this.animation = AssetLoader.birdAnimation;
    }

    public void render(float time) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        batcher.draw(background, 0, minY + 23, 136, 43);
        drawGrass();
        drawPipes();
        batcher.enableBlending();
        drawSkulls();
        batcher.draw(bird.shouldNotFlap() ? birdCenter : (TextureRegion) animation.getKeyFrame(time),
                bird.getX(), bird.getY(), bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
                bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
        String score = String.valueOf(world.getScore());
        AssetLoader.shadow.draw(batcher, score, (136 / 2) - (3 * score.length()), 22);
        AssetLoader.font.draw(batcher, score, (136 / 2) - (3 * score.length() - 1), 21);
        batcher.end();
    }

    private void drawGrass() {
        batcher.draw(grass, frontGrass.getX(), frontGrass.getY(), frontGrass.getWidth(), frontGrass.getHeight());
        batcher.draw(grass, backGrass.getX(), backGrass.getY(), backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawSkulls() {
        batcher.draw(skullUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        batcher.draw(skullUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

        batcher.draw(skullUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
    }

    private void drawPipes() {
        batcher.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        batcher.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), minY + 66 - (pipe1.getHeight() + 45));

        batcher.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        batcher.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), minY + 66 - (pipe2.getHeight() + 45));

        batcher.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        batcher.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), minY + 66 - (pipe3.getHeight() + 45));
    }


}
