package com.dualdev.clicker.resource.initalizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Timer;
import com.dualdev.clicker.resource.model.WoodResource;

public class WoodInitializer {
    private float scale;
    private WoodResource woodResource;
    private Skin skin;

    private final static String UPGRADE_INCOME_BASE = "Upgrade Income\n Cost (w): ";
    private final static String UPGRADE_TAP_BASE = "Upgrade Tap\n Cost (w): ";
    private final static String WOOD_INCOME = "Wood Income: ";
    private final static String TOTAL_WOOD = "Total Wood: ";
    private final static String RESOURCE_NAME = "Wood Resource: ";
    private final static String GATHER_RESOURCE_NAME = "Chop Wood";

    public Table initializeWoodButtons(float s, WoodResource woodRes, Table masterWoodTable, Skin sk) {
        this.scale = s;
        this.woodResource = woodRes;
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
                int updatedTotal = woodResource.getAmountStored() + woodResource.getIdleIncome();
                woodResource.setAmountStored(updatedTotal);
                woodCountText.setText(TOTAL_WOOD + woodResource.getAmountStored());
                Gdx.app.log(RESOURCE_NAME,
                        "Income tick. amount: " + woodResource.getIdleIncome());
            }
        },1,1);
    }

    private TextField createWoodCountText() {
        TextField countArea = new TextArea(TOTAL_WOOD + woodResource.getAmountStored(), skin);
        countArea.setDisabled(true);
        return countArea;
    }

    private TextField createWoodIncomeText() {
        TextField incomeArea = new TextArea(WOOD_INCOME + woodResource.getIdleIncome(), skin);
        incomeArea.setDisabled(true);
        return incomeArea;
    }

    private TextButton createWoodClickUpgradeButton(final TextField woodCount) {
        final TextButton woodTapUpgradeButton =
                new TextButton(UPGRADE_TAP_BASE + woodResource.getTapUpgradeCost(), skin);

        woodTapUpgradeButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                int currentUpgradeCost = woodResource.getTapUpgradeCost();
                if(currentUpgradeCost <= woodResource.getAmountStored()) {
                    woodResource.upgradeTapReturn();
                    woodTapUpgradeButton.setText(UPGRADE_TAP_BASE + woodResource.getTapUpgradeCost());
                    woodResource.setAmountStored(woodResource.getAmountStored() - currentUpgradeCost);
                    woodCount.setText(TOTAL_WOOD + woodResource.getAmountStored());
                }
            }
        });
        return woodTapUpgradeButton;
    }


    private TextButton createWoodIncomeUpgradeButton(final TextField woodCount, final TextField woodIncome) {
        final TextButton woodIncomeUpgradeButton =
                new TextButton(UPGRADE_INCOME_BASE + woodResource.getIdleUpgradeCost() , skin);

        woodIncomeUpgradeButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                int currentUpgradeCost = woodResource.getIdleUpgradeCost();
                if(currentUpgradeCost <= woodResource.getAmountStored()) {
                    woodResource.upgradeIdleIncome();
                    woodIncome.setText(WOOD_INCOME + woodResource.getIdleIncome());
                    woodIncomeUpgradeButton.setText(UPGRADE_INCOME_BASE + woodResource.getIdleUpgradeCost());
                    woodResource.setAmountStored(woodResource.getAmountStored() - currentUpgradeCost);
                    woodCount.setText(TOTAL_WOOD + woodResource.getAmountStored());
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
                int newTotal = woodResource.getAmountStored()+woodResource.getTapReturn();
                woodResource.setAmountStored(newTotal);
                woodCount.setText(TOTAL_WOOD + newTotal);
            }
        });
        return woodTextButton;
    }
}
