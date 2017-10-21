package com.mobile.pixelbird.object;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.security.SecureRandom;

public class Pipe extends Scrollable {
    public static final int VERTICAL_GAP = 45;
    public static final int SKULL_WIDTH = 24;
    public static final int SKULL_HEIGHT = 11;

    private SecureRandom seed;
    private float groundY;
    private Rectangle skullUp, skullDown, barUp, barDown;

    public Pipe(float x, float y, int width, int height, float scrollSpeed, float groundY) {
        super(x, y, width, height, scrollSpeed);
        this.seed = new SecureRandom();
        this.skullUp = new Rectangle();
        this.skullDown = new Rectangle();
        this.barUp = new Rectangle();
        this.barDown = new Rectangle();
        this.groundY = groundY;
    }

    @Override
    public void reset(float x) {
        super.reset(x);
        this.height = seed.nextInt(90) + 15;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        barUp.set(position.x, position.y, width, height);
        barDown.set(position.x, position.y + height + VERTICAL_GAP, width,
                groundY - (position.y + height + VERTICAL_GAP));
        skullUp.set(position.x - (SKULL_WIDTH - width) / 2, position.y + height
                - SKULL_HEIGHT, SKULL_WIDTH, SKULL_HEIGHT);
        skullDown.set(position.x - (SKULL_WIDTH - width) / 2, barDown.y,
                SKULL_WIDTH, SKULL_HEIGHT);
    }

    public boolean collides(Bird bird) {
        return position.x < bird.getX() + bird.getWidth() && (
                Intersector.overlaps(bird.getBoundingCircle(), barUp) ||
                Intersector.overlaps(bird.getBoundingCircle(), barDown) ||
                Intersector.overlaps(bird.getBoundingCircle(), skullUp) ||
                Intersector.overlaps(bird.getBoundingCircle(), skullDown));
    }

    public Rectangle getSkullUp() {
        return skullUp;
    }

    public Rectangle getSkullDown() {
        return skullDown;
    }

    public Rectangle getBarUp() {
        return barUp;
    }

    public Rectangle getBarDown() {
        return barDown;
    }
}
