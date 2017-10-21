package com.mobile.pixelbird.object;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Bird {
    private Circle boundingCircle;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private float rotation;
    private int width;
    private int height;

    private boolean isAlive;

    public Bird(float x, float y, int width, int height) {
        this.height = height;
        this.width = width;
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.acceleration = new Vector2(0, 460);
        this.boundingCircle = new Circle();
        this.isAlive = true;
    }

    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y > 200) {
            velocity.y = 200;
        }

        position.add(velocity.cpy().scl(delta));
        boundingCircle.set(position.x + 9, position.y + 6, 6.5f);

        if (velocity.y < 0) {
            rotation -= 600 * delta;
            if (rotation < -20) {
                rotation = -20;
            }
        }

        if (isFalling() || !isAlive) {
            rotation += 480 * delta;
            if (rotation > 90) {
                rotation = 90;
            }
        }
    }

    public void onClick() {
        if (isAlive) {
            this.velocity.y = -140;
        }
    }

    public void die() {
        isAlive = false;
        velocity.y = 0;
    }

    public void decelerate() {
        acceleration.y = 0;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isFalling() {
        return velocity.y > 110;
    }

    public boolean shouldNotFlap() {
        return velocity.y > 70 || !isAlive;
    }

}
