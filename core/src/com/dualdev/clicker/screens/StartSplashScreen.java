package com.dualdev.clicker.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.TimeUtils;
import com.dualdev.clicker.screens.util.AbstractScreen;
import com.dualdev.clicker.screens.util.ScreenEnum;
import com.dualdev.clicker.screens.util.ScreenManager;

public class StartSplashScreen extends AbstractScreen {
    private long showTime;

    public StartSplashScreen() {
    }

    @Override
    public void buildStage() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (TimeUtils.timeSinceMillis(showTime) > 1500) {
            ScreenManager.getInstance().showScreen(ScreenEnum.WOOD_SCREEN);
        }
    }

    @Override
    public void show() {
        showTime = TimeUtils.millis();
    }

    @Override
    public void resize(int width, int height) {
        getViewport().update(width, height, true);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() { }
}
