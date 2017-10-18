package com.mobile.pixelbird;

import com.badlogic.gdx.Game;
import com.mobile.pixelbird.helper.AssetLoader;
import com.mobile.pixelbird.screen.PixelScreen;

public class PixalBirdGame extends Game {

    @Override
    public void create() {
        AssetLoader.loadAssets();
        setScreen(new PixelScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.disposeAssets();
    }
}
