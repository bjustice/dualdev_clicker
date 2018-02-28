package com.dualdev.clicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.dualdev.clicker.resource.model.*;
import com.dualdev.clicker.screens.util.ScreenEnum;
import com.dualdev.clicker.screens.util.ScreenManager;

import java.util.ArrayList;
import java.util.List;


public class ClickerMain extends Game {

	@Override
	public void create () {
        ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().showScreen(new ResourceManager(),ScreenEnum.START_SPLASH);
	}

	@Override
	public void dispose () {
	}
}