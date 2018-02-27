package com.dualdev.clicker.resource.initalizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Timer;
import com.dualdev.clicker.resource.model.StoneResource;

public class StoneInitializer {
    private float scale;
    private StoneResource stoneResource;
    private Skin skin;

    private final static String UPGRADE_INCOME_BASE = "Upgrade Income\n Cost (s): ";
    private final static String UPGRADE_TAP_BASE = "Upgrade Tap\n Cost (s): ";
    private final static String STONE_INCOME = "Stone Income: ";
    private final static String TOTAL_STONE = "Total Stone: ";
    private final static String RESOURCE_NAME = "Stone Resource: ";
    private final static String GATHER_RESOURCE_NAME = "Mine Stone";

    public Table initializeStoneButtons(float s, StoneResource stoneRes, Table masterStoneTable, Skin sk) {
        this.scale = s;
        this.stoneResource = stoneRes;
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
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                int updatedTotal = stoneResource.getAmountStored() + stoneResource.getIdleIncome();
                stoneResource.setAmountStored(updatedTotal);
                stoneCountText.setText(TOTAL_STONE + stoneResource.getAmountStored());
                Gdx.app.log(RESOURCE_NAME,
                        "Income tick. amount: " + stoneResource.getIdleIncome());
            }
        },1,1);
    }

    private TextField createStoneCountText() {
        TextField countArea = new TextArea(TOTAL_STONE + stoneResource.getAmountStored(), skin);
        countArea.setDisabled(true);
        return countArea;
    }

    private TextField createStoneIncomeText() {
        TextField incomeArea = new TextArea(STONE_INCOME + stoneResource.getIdleIncome(), skin);
        incomeArea.setDisabled(true);
        return incomeArea;
    }

    private TextButton createStoneClickUpgradeButton(final TextField stoneCount) {
        final TextButton stoneTapUpgradeButton =
                new TextButton(UPGRADE_TAP_BASE + stoneResource.getTapUpgradeCost(), skin);

        stoneTapUpgradeButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                int currentUpgradeCost = stoneResource.getTapUpgradeCost();
                if(currentUpgradeCost <= stoneResource.getAmountStored()) {
                    stoneResource.upgradeTapReturn();
                    stoneTapUpgradeButton.setText(UPGRADE_TAP_BASE + stoneResource.getTapUpgradeCost());
                    stoneResource.setAmountStored(stoneResource.getAmountStored() - currentUpgradeCost);
                    stoneCount.setText(TOTAL_STONE + stoneResource.getAmountStored());
                }
            }
        });
        return stoneTapUpgradeButton;
    }


    private TextButton createStoneIncomeUpgradeButton(final TextField stoneCount, final TextField stoneIncome) {
        final TextButton stoneIncomeUpgradeButton =
                new TextButton(UPGRADE_INCOME_BASE + stoneResource.getIdleUpgradeCost() , skin);

        stoneIncomeUpgradeButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                int currentUpgradeCost = stoneResource.getIdleUpgradeCost();
                if(currentUpgradeCost <= stoneResource.getAmountStored()) {
                    stoneResource.upgradeIdleIncome();
                    stoneIncome.setText(STONE_INCOME + stoneResource.getIdleIncome());
                    stoneIncomeUpgradeButton.setText(UPGRADE_INCOME_BASE + stoneResource.getIdleUpgradeCost());
                    stoneResource.setAmountStored(stoneResource.getAmountStored() - currentUpgradeCost);
                    stoneCount.setText(TOTAL_STONE + stoneResource.getAmountStored());
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
                int newTotal = stoneResource.getAmountStored()+ stoneResource.getTapReturn();
                stoneResource.setAmountStored(newTotal);
                stoneCount.setText(TOTAL_STONE + newTotal);
            }
        });
        return stoneTextButton;
    }
}
