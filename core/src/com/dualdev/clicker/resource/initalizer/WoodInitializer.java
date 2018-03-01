package com.dualdev.clicker.resource.initalizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Timer;
import com.dualdev.clicker.resource.helpers.Resource;
import com.dualdev.clicker.resource.model.ResourceManager;

public class WoodInitializer {
    private float scale;
    private ResourceManager resourceManager;
    private Skin skin;

    private final static String UPGRADE_INCOME_BASE = "Upgrade Income\n Cost (w): ";
    private final static String UPGRADE_TAP_BASE = "Upgrade Tap\n Cost (w): ";
    private final static String WOOD_INCOME = "Wood Income: ";
    private final static String TOTAL_WOOD = "Total Wood: ";
    private final static String RESOURCE_NAME = "Wood Resource: ";
    private final static String GATHER_RESOURCE_NAME = "Chop Wood";

    public Table initializeWoodButtons(float s, final ResourceManager rm, Table masterWoodTable, Skin sk) {
        this.scale = s;
        this.resourceManager = rm;
        this.skin = sk;

        final TextField woodCountText = createWoodCountText();
        TextField woodIncomeText = createWoodIncomeText();
        TextButton woodResourceButton = createWoodClickButton(woodCountText);
        TextButton woodClickUpgradeButton = createWoodClickUpgradeButton(woodCountText);
        TextButton woodIncomeUpgradeButton = createWoodIncomeUpgradeButton(woodCountText, woodIncomeText);

        Table statsTable = new Table();
        statsTable.add(woodCountText).pad(0,5,0,5);
        statsTable.add(woodIncomeText);
        masterWoodTable.add(statsTable);

        masterWoodTable.row().pad(5,0,5,0);
        masterWoodTable.add(woodResourceButton).fillX();
        masterWoodTable.row().pad(5,0,5,0);

        Table upgradeTable = new Table();
        upgradeTable.add(woodClickUpgradeButton).pad(0,5,0,5);
        upgradeTable.add(woodIncomeUpgradeButton);
        masterWoodTable.add(upgradeTable);

        scheduleIncomeUpdate(woodCountText);
        return masterWoodTable;
    }

    private void scheduleIncomeUpdate(final TextField woodCountText) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                int updatedTotal =
                        resourceManager.getAmountStored(Resource.WOOD) + resourceManager.getIdleIncome(Resource.WOOD);
                resourceManager.setAmountStored(Resource.WOOD, updatedTotal);
                woodCountText.setText(TOTAL_WOOD + resourceManager.getAmountStored(Resource.WOOD));
                Gdx.app.log(RESOURCE_NAME,
                        "Income tick. amount: " + resourceManager.getIdleIncome(Resource.WOOD));
            }
        },1,1);
    }

    private TextField createWoodCountText() {
        TextField countArea = new TextArea(TOTAL_WOOD + resourceManager.getAmountStored(Resource.WOOD), skin);
        countArea.setDisabled(true);
        return countArea;
    }

    private TextField createWoodIncomeText() {
        TextField incomeArea = new TextArea(WOOD_INCOME + resourceManager.getIdleIncome(Resource.WOOD), skin);
        incomeArea.setDisabled(true);
        return incomeArea;
    }

    private TextButton createWoodClickUpgradeButton(final TextField woodCount) {
        final TextButton woodTapUpgradeButton =
                new TextButton(UPGRADE_TAP_BASE + resourceManager.getTapUpgradeCost(Resource.WOOD), skin);

        woodTapUpgradeButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                int currentUpgradeCost = resourceManager.getTapUpgradeCost(Resource.WOOD);
                if(currentUpgradeCost <= resourceManager.getAmountStored(Resource.WOOD)) {
                    resourceManager.upgradeTapReturn(Resource.WOOD);
                    woodTapUpgradeButton.setText(UPGRADE_TAP_BASE + resourceManager.getTapUpgradeCost(Resource.WOOD));
                    resourceManager.setAmountStored(Resource.WOOD,
                            resourceManager.getAmountStored(Resource.WOOD) - currentUpgradeCost);
                    woodCount.setText(TOTAL_WOOD + resourceManager.getAmountStored(Resource.WOOD));
                }
            }
        });
        return woodTapUpgradeButton;
    }


    private TextButton createWoodIncomeUpgradeButton(final TextField woodCount, final TextField woodIncome) {
        final TextButton woodIncomeUpgradeButton =
                new TextButton(UPGRADE_INCOME_BASE + resourceManager.getIdleUpgradeCost(Resource.WOOD) , skin);

        woodIncomeUpgradeButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                int currentUpgradeCost = resourceManager.getIdleUpgradeCost(Resource.WOOD);
                if(currentUpgradeCost <= resourceManager.getAmountStored(Resource.WOOD)) {
                    resourceManager.upgradeIdleIncome(Resource.WOOD);
                    woodIncome.setText(WOOD_INCOME + resourceManager.getIdleIncome(Resource.WOOD));
                    woodIncomeUpgradeButton.setText(UPGRADE_INCOME_BASE + resourceManager.getIdleUpgradeCost(Resource.WOOD));
                    resourceManager.setAmountStored(Resource.WOOD,resourceManager.getAmountStored(Resource.WOOD) - currentUpgradeCost);
                    woodCount.setText(TOTAL_WOOD + resourceManager.getAmountStored(Resource.WOOD));
                }
            }
        });
        return woodIncomeUpgradeButton;
    }

    private TextButton createWoodClickButton(final TextField woodCount) {
        TextButton woodTextButton = new TextButton(GATHER_RESOURCE_NAME, skin);

        woodTextButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                int newTotal = resourceManager.getAmountStored(Resource.WOOD) + resourceManager.getTapReturn(Resource.WOOD);
                resourceManager.setAmountStored(Resource.WOOD, newTotal);
                woodCount.setText(TOTAL_WOOD + newTotal);
            }
        });
        return woodTextButton;
    }
}
