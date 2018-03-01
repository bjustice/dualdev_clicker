package com.dualdev.clicker.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.dualdev.clicker.resource.initalizer.IronInitializer;
import com.dualdev.clicker.resource.model.*;
import com.dualdev.clicker.screens.util.AbstractScreen;
import com.dualdev.clicker.screens.util.ClickerHeaders;

public class IronScreen extends AbstractScreen {

    private ResourceManager resourceManager;

    private Table bodyTable;
    private Stage stage;
    private Skin skin;
    private float scale;

    public IronScreen(ResourceManager rm) {
        resourceManager = rm;
    }

    @Override
    public void buildStage() {
        skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
        resourceManager.clearUITimers();

        Table fullViewTable = new Table();
        fullViewTable.setFillParent(true);
        Drawable background =
                new TextureRegionDrawable(new TextureRegion(new Texture("iron_background.png")));
        fullViewTable.setBackground(background);
        Table headerTable = ClickerHeaders.ironHeaders(resourceManager, skin);
        bodyTable = new Table();

        IronInitializer ironInitializer = new IronInitializer();
        bodyTable = ironInitializer.initializeIronButtons(resourceManager, bodyTable, skin);

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
