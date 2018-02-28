package com.dualdev.clicker.screens.util;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.dualdev.clicker.resource.model.ResourceManager;

import java.util.ArrayList;
import java.util.List;

public class ClickerHeaders {


    private static boolean woodEnabled;
    private static boolean berryEnabled;
    private static boolean stoneEnabled;
    private static boolean ironEnabled;
    private static ResourceManager resourceManager;
    private static Skin skin;

    public static  Table woodHeaders(ResourceManager rm, Skin s) {
        skin = s;
        resourceManager = rm;
        woodEnabled = false;
        berryEnabled = true;
        stoneEnabled = true;
        ironEnabled = true;
        return createHeaderTable();
    }

    public static  Table berryHeaders(ResourceManager rm, Skin s) {
        skin = s;
        resourceManager = rm;
        woodEnabled = true;
        berryEnabled = false;
        stoneEnabled = true;
        ironEnabled = true;
        return createHeaderTable();
    }

    public static  Table stoneHeaders(ResourceManager rm, Skin s) {
        skin = s;
        resourceManager = rm;
        woodEnabled = true;
        berryEnabled = true;
        stoneEnabled = false;
        ironEnabled = true;
        return createHeaderTable();
    }

    public static  Table ironHeaders(ResourceManager rm, Skin s) {
        skin = s;
        resourceManager = rm;
        woodEnabled = true;
        berryEnabled = true;
        stoneEnabled = true;
        ironEnabled = false;
        return createHeaderTable();
    }

    private static  Table createHeaderTable() {
        Table headerTable = new Table();
        List<TextButton> textButtons = new ArrayList<TextButton>();

        TextButton woodButton = new TextButton("Wood", skin);
        if(woodEnabled) {
            woodButton.addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    ScreenManager.getInstance().showScreen(resourceManager, ScreenEnum.WOOD_SCREEN);
                }
            });
        }
        TextButton berryButton = new TextButton("Berries", skin);
        if(berryEnabled) {
            berryButton.addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    ScreenManager.getInstance().showScreen(resourceManager, ScreenEnum.BERRY_SCREEN);
                }
            });
        }
        TextButton stoneButton = new TextButton("Stone", skin);
        if(stoneEnabled) {
            stoneButton.addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    ScreenManager.getInstance().showScreen(resourceManager, ScreenEnum.STONE_SCREEN);
                }
            });
        }
        TextButton ironButton = new TextButton("Iron", skin);
        if(ironEnabled) {
            ironButton.addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    ScreenManager.getInstance().showScreen(resourceManager,  ScreenEnum.IRON_SCREEN);
                }
            });
        }
        textButtons.add(woodButton);
        textButtons.add(berryButton);
        textButtons.add(stoneButton);
        textButtons.add(ironButton);

        for(TextButton currentButton : textButtons){
            headerTable.add(currentButton).pad(0,2.5f,0,2.5f);
        }
        return headerTable;
    }

}
