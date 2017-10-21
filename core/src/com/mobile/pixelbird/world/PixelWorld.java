package com.mobile.pixelbird.world;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mobile.pixelbird.object.Bird;
import com.mobile.pixelbird.object.ScrollHandler;

public class PixelWorld {
    private Bird bird;
    private Rectangle ground;

    private ScrollHandler scroller;

    public PixelWorld(int minY) {
        this.bird = new Bird(33, minY - 5, 22, 17);
        this.scroller = new ScrollHandler(minY + 66);
        this.ground = new Rectangle(0, minY + 66, 136, 11);
    }

    public void update(float delta) {
        if (delta > 0.15f) {
            delta = 0.15f;
        }
        bird.update(delta);
        scroller.update(delta);

        if (scroller.collides(bird) && bird.isAlive()) {
            scroller.stop();
            bird.die();
        }

        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scroller.stop();
            bird.die();
            bird.decelerate();
        }
    }

    public Bird getBird() {
        return bird;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }
}
