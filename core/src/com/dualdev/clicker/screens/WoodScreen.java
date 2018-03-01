package com.dualdev.clicker.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.dualdev.clicker.resource.initalizer.WoodInitializer;
import com.dualdev.clicker.resource.model.*;
import com.dualdev.clicker.screens.util.AbstractScreen;
import com.dualdev.clicker.screens.util.ClickerHeaders;

public class WoodScreen extends AbstractScreen {

    private ResourceManager resourceManager;

    private Table bodyTable;
    private Stage stage;
    private Skin skin;
    private float scale;

    public WoodScreen(ResourceManager rm) {
        resourceManager = rm;
    }

    @Override
    public void buildStage() {
        skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));

		Table fullViewTable = new Table();
		fullViewTable.setFillParent(true);
		Drawable background =
                new TextureRegionDrawable(new TextureRegion(new Texture("wood_background.jpg")));
        fullViewTable.setBackground(background);
		Table headerTable = ClickerHeaders.woodHeaders(resourceManager, skin);
		bodyTable = new Table();

        WoodInitializer woodInitializer = new WoodInitializer();
        bodyTable = woodInitializer.initializeWoodButtons(scale, resourceManager, bodyTable, skin);

        fullViewTable.add(headerTable);
        fullViewTable.row().pad(10,0,10,0);
        fullViewTable.add(bodyTable);
        super.addActor(fullViewTable);
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
