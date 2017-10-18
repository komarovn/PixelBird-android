package com.mobile.pixelbird.world;

import com.mobile.pixelbird.object.Bird;

public class PixelWorld {
    private Bird bird;

    public PixelWorld(int minY) {
        this.bird = new Bird(33, minY - 5, 17, 22);
    }

    public void update(float delta) {
        bird.update(delta);
    }

    public Bird getBird() {
        return bird;
    }
}
