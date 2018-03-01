package com.dualdev.clicker.resource.initalizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Timer;
import com.dualdev.clicker.resource.helpers.Resource;
import com.dualdev.clicker.resource.model.ResourceManager;
import com.dualdev.clicker.resource.model.StoneResource;

public class StoneInitializer {
    private ResourceManager resourceManager;
    private Skin skin;

    private final static String UPGRADE_INCOME_BASE = "Upgrade Income\n Cost (s): ";
    private final static String UPGRADE_TAP_BASE = "Upgrade Tap\n Cost (s): ";
    private final static String STONE_INCOME = "Stone Income: ";
    private final static String TOTAL_STONE = "Total Stone: ";
    private final static String RESOURCE_NAME = "Stone Resource: ";
    private final static String GATHER_RESOURCE_NAME = "Mine Stone";

    public Table initializeStoneButtons(final ResourceManager rm, Table masterStoneTable, Skin sk) {
        this.resourceManager = rm;
        this.skin = sk;

        final TextField stoneCountText = createStoneCountText();
        TextField stoneIncomeText = createStoneIncomeText();
        TextButton stoneClickButton = createStoneClickButton(stoneCountText);
        TextButton stoneClickUpgradeButton = createStoneClickUpgradeButton(stoneCountText);
        TextButton stoneIncomeUpgradeButton = createStoneIncomeUpgradeButton(stoneCountText, stoneIncomeText);

        Table statsTable = new Table();
        statsTable.add(stoneCountText).pad(0,5,0,5);
        statsTable.add(stoneIncomeText);
        masterStoneTable.add(statsTable);

        masterStoneTable.row().pad(5,0,5,0);
        masterStoneTable.add(stoneClickButton).fillX();
        masterStoneTable.row().pad(5,0,5,0);

        Table upgradeTable = new Table();
        upgradeTable.add(stoneClickUpgradeButton).pad(0,5,0,5);
        upgradeTable.add(stoneIncomeUpgradeButton);
        masterStoneTable.add(upgradeTable);

        scheduleIncomeUpdate(stoneCountText);
        return masterStoneTable;
    }

    private void scheduleIncomeUpdate(final TextField stoneCountText) {
        resourceManager.getUITimer(Resource.STONE).scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                stoneCountText.setText(TOTAL_STONE + resourceManager.getAmountStored(Resource.STONE));
                Gdx.app.log(RESOURCE_NAME,
                        "UI TICK");
            }
        },1,1);
    }

    private TextField createStoneCountText() {
        TextField countArea = new TextArea(TOTAL_STONE + resourceManager.getAmountStored(Resource.STONE), skin);
        countArea.setDisabled(true);
        return countArea;
    }

    private TextField createStoneIncomeText() {
        TextField incomeArea = new TextArea(STONE_INCOME + resourceManager.getIdleIncome(Resource.STONE), skin);
        incomeArea.setDisabled(true);
        return incomeArea;
    }

    private TextButton createStoneClickUpgradeButton(final TextField stoneCount) {
        final TextButton stoneTapUpgradeButton =
                new TextButton(UPGRADE_TAP_BASE + resourceManager.getTapUpgradeCost(Resource.STONE), skin);

        stoneTapUpgradeButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                int currentUpgradeCost = resourceManager.getTapUpgradeCost(Resource.STONE);
                if(currentUpgradeCost <= resourceManager.getAmountStored(Resource.STONE)) {
                    resourceManager.upgradeTapReturn(Resource.STONE);
                    stoneTapUpgradeButton.setText(UPGRADE_TAP_BASE + resourceManager.getTapUpgradeCost(Resource.STONE));
                    resourceManager.setAmountStored(Resource.STONE,resourceManager.getAmountStored(Resource.STONE) - currentUpgradeCost);
                    stoneCount.setText(TOTAL_STONE + resourceManager.getAmountStored(Resource.STONE));
                }
            }
        });
        return stoneTapUpgradeButton;
    }


    private TextButton createStoneIncomeUpgradeButton(final TextField stoneCount, final TextField stoneIncome) {
        final TextButton stoneIncomeUpgradeButton =
                new TextButton(UPGRADE_INCOME_BASE + resourceManager.getIdleUpgradeCost(Resource.STONE) , skin);

        stoneIncomeUpgradeButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                int currentUpgradeCost = resourceManager.getIdleUpgradeCost(Resource.STONE);
                if(currentUpgradeCost <= resourceManager.getAmountStored(Resource.STONE)) {
                    resourceManager.upgradeIdleIncome(Resource.STONE);
                    stoneIncome.setText(STONE_INCOME + resourceManager.getIdleIncome(Resource.STONE));
                    stoneIncomeUpgradeButton.setText(UPGRADE_INCOME_BASE + resourceManager.getIdleUpgradeCost(Resource.STONE));
                    resourceManager.setAmountStored(Resource.STONE,resourceManager.getAmountStored(Resource.STONE) - currentUpgradeCost);
                    stoneCount.setText(TOTAL_STONE + resourceManager.getAmountStored(Resource.STONE));
                }
            }
        });
        return stoneIncomeUpgradeButton;
    }

    private TextButton createStoneClickButton(final TextField stoneCount) {
        TextButton stoneTextButton = new TextButton(GATHER_RESOURCE_NAME, skin);

        stoneTextButton.addListener(new InputListener() {

            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                int newTotal = resourceManager.getAmountStored(Resource.STONE)+ resourceManager.getTapReturn(Resource.STONE);
                resourceManager.setAmountStored(Resource.STONE, newTotal);
                stoneCount.setText(TOTAL_STONE + newTotal);
            }
        });
        return stoneTextButton;
    }
}
