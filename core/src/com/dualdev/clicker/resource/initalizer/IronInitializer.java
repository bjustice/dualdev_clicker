package com.dualdev.clicker.resource.initalizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Timer;
import com.dualdev.clicker.resource.model.IronResource;

public class IronInitializer {
    private float scale;
    private IronResource ironResource;
    private Skin skin;

    private final static String UPGRADE_INCOME_BASE = "Upgrade Income\n Cost (i): ";
    private final static String UPGRADE_TAP_BASE = "Upgrade Tap\n Cost (i): ";
    private final static String IRON_INCOME = "Iron Income: ";
    private final static String TOTAL_IRON = "Total Iron: ";
    private final static String RESOURCE_NAME = "Iron Resource: ";
    private final static String GATHER_RESOURCE_NAME = "Mine Iron";

    public Table initializeIronButtons(float s, final IronResource ironRes, Table masterIronTable, Skin sk) {
        this.scale = s;
        this.ironResource = ironRes;
        this.skin = sk;

        final TextField ironCountText = createIronCountText();
        TextField ironIncomeText = createIronIncomeText();
        TextButton ironClickButton = createIronClickButton(ironCountText);
        TextButton ironClickUpgradeButton = createIronClickUpgradeButton(ironCountText);
        TextButton ironIncomeUpgradeButton = createIronIncomeUpgradeButton(ironCountText, ironIncomeText);

        Table statsTable = new Table();
        statsTable.add(ironCountText).pad(0,5,0,5);
        statsTable.add(ironIncomeText);
        masterIronTable.add(statsTable);

        masterIronTable.row().pad(5,0,5,0);
        masterIronTable.add(ironClickButton).fillX();
        masterIronTable.row().pad(5,0,5,0);

        Table upgradeTable = new Table();
        upgradeTable.add(ironClickUpgradeButton).pad(0,5,0,5);
        upgradeTable.add(ironIncomeUpgradeButton);
        masterIronTable.add(upgradeTable);

        scheduleIncomeUpdate(ironCountText);
        return masterIronTable;
    }

    private void scheduleIncomeUpdate(final TextField ironCountText) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                int updatedTotal = ironResource.getAmountStored() + ironResource.getIdleIncome();
                ironResource.setAmountStored(updatedTotal);
                ironCountText.setText(TOTAL_IRON + ironResource.getAmountStored());
                Gdx.app.log(RESOURCE_NAME,
                        "Income tick. amount: " + ironResource.getIdleIncome());
            }
        },1,1);
    }

    private TextField createIronCountText() {
        TextField countArea = new TextArea(TOTAL_IRON + ironResource.getAmountStored(), skin);
        countArea.setDisabled(true);
        return countArea;
    }

    private TextField createIronIncomeText() {
        TextField incomeArea = new TextArea(IRON_INCOME + ironResource.getIdleIncome(), skin);
        incomeArea.setDisabled(true);
        return incomeArea;
    }

    private TextButton createIronClickUpgradeButton(final TextField ironCount) {
        final TextButton ironTapUpgradeButton =
                new TextButton(UPGRADE_TAP_BASE + ironResource.getTapUpgradeCost(), skin);

        ironTapUpgradeButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                int currentUpgradeCost = ironResource.getTapUpgradeCost();
                if(currentUpgradeCost <= ironResource.getAmountStored()) {
                    ironResource.upgradeTapReturn();
                    ironTapUpgradeButton.setText(UPGRADE_TAP_BASE + ironResource.getTapUpgradeCost());
                    ironResource.setAmountStored(ironResource.getAmountStored() - currentUpgradeCost);
                    ironCount.setText(TOTAL_IRON + ironResource.getAmountStored());
                }
            }
        });
        return ironTapUpgradeButton;
    }


    private TextButton createIronIncomeUpgradeButton(final TextField ironCount, final TextField ironIncome) {
        final TextButton ironIncomeUpgradeButton =
                new TextButton(UPGRADE_INCOME_BASE + ironResource.getIdleUpgradeCost() , skin);

        ironIncomeUpgradeButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                int currentUpgradeCost = ironResource.getIdleUpgradeCost();
                if(currentUpgradeCost <= ironResource.getAmountStored()) {
                    ironResource.upgradeIdleIncome();
                    ironIncome.setText(IRON_INCOME + ironResource.getIdleIncome());
                    ironIncomeUpgradeButton.setText(UPGRADE_INCOME_BASE + ironResource.getIdleUpgradeCost());
                    ironResource.setAmountStored(ironResource.getAmountStored() - currentUpgradeCost);
                    ironCount.setText(TOTAL_IRON + ironResource.getAmountStored());
                }
            }
        });
        return ironIncomeUpgradeButton;
    }

    private TextButton createIronClickButton(final TextField ironCount) {
        TextButton ironTextButton = new TextButton(GATHER_RESOURCE_NAME, skin);

        ironTextButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                int newTotal = ironResource.getAmountStored()+ ironResource.getTapReturn();
                ironResource.setAmountStored(newTotal);
                ironCount.setText(TOTAL_IRON + newTotal);
            }
        });
        return ironTextButton;
    }
}
