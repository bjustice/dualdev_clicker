package com.dualdev.clicker.resource.initalizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Timer;
import com.dualdev.clicker.resource.helpers.Resource;
import com.dualdev.clicker.resource.model.BerryResource;
import com.dualdev.clicker.resource.model.ResourceManager;

public class BerryInitializer {
    private ResourceManager resourceManager;
    private Skin skin;

    private final static String UPGRADE_INCOME_BASE = "Upgrade Income\n Cost (w) ";
    private final static String UPGRADE_TAP_BASE = "Upgrade Tap\n Cost (w)";
    private final static String BERRY_INCOME = "Berry Income: ";
    private final static String TOTAL_BERRIES = "Total Berries: ";
    private final static String RESOURCE_NAME = "Berry Resource: ";
    private final static String GATHER_RESOURCE_NAME = "Pick Berries";

    public Table initializeBerryButtons(final ResourceManager rm, Table masterBerryTable, Skin sk) {
        this.resourceManager = rm;
        this.skin  = sk;

        final TextField berryCountText = createBerryCountText();
        TextField berryIncomeText = createBerryIncomeText();
        TextButton berryResourceButton = createBerryClickButton(berryCountText);
        TextButton berryClickUpgradeButton = createBerryClickUpgradeButton(berryCountText);
        TextButton berryIncomeUpgradeButton = createBerryIncomeUpgradeButton(berryCountText, berryIncomeText);

        Table statsTable = new Table();
        statsTable.add(berryCountText).pad(0, 5, 0, 5);
        statsTable.add(berryIncomeText);
        masterBerryTable.add(statsTable);

        masterBerryTable.row().pad(5,0,5,0);
        masterBerryTable.add(berryResourceButton).fillX();
        masterBerryTable.row().pad(5, 0, 5, 0);

        Table upgradeTable = new Table();
        upgradeTable.add(berryClickUpgradeButton).pad(0, 5, 0, 5);
        upgradeTable.add(berryIncomeUpgradeButton);
        masterBerryTable.add(upgradeTable);

        scheduleIncomeUpdate(berryCountText);
        return masterBerryTable;
    }

    private void scheduleIncomeUpdate(final TextField berryCountText){
        resourceManager.getUITimer(Resource.BERRIES).scheduleTask(new Timer.Task() {
            @Override public void run() {
                berryCountText.setText(TOTAL_BERRIES + resourceManager.getAmountStored(Resource.BERRIES));
                Gdx.app.log(RESOURCE_NAME,
                        "UI TICK");
            }
        }, 1, 1);
    }

    private TextField createBerryCountText() {
        TextField countArea = new TextArea(TOTAL_BERRIES + resourceManager.getAmountStored(Resource.BERRIES), skin);
        countArea.setDisabled(true);
        return countArea;
    }

    private TextField createBerryIncomeText() {
        TextField incomeArea = new TextArea(BERRY_INCOME + resourceManager.getIdleIncome(Resource.BERRIES), skin);
        incomeArea.setDisabled(true);
        return incomeArea;
    }

    private TextButton createBerryClickUpgradeButton(final TextField berryCount) {
        final TextButton berryTapUpgradeButton =
                new TextButton(UPGRADE_TAP_BASE + resourceManager.getTapUpgradeCost(Resource.BERRIES), skin);

        berryTapUpgradeButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button){
                int currentUpgradeCost = resourceManager.getTapUpgradeCost(Resource.BERRIES);
                if(currentUpgradeCost <= resourceManager.getAmountStored(Resource.BERRIES)) {
                    resourceManager.upgradeTapReturn(Resource.BERRIES);
                    berryTapUpgradeButton.setText(UPGRADE_TAP_BASE + resourceManager.getTapUpgradeCost(Resource.BERRIES));
                    resourceManager.setAmountStored(Resource.BERRIES,resourceManager.getAmountStored(Resource.BERRIES) - currentUpgradeCost);
                    berryCount.setText(TOTAL_BERRIES + resourceManager.getAmountStored(Resource.BERRIES));
                }
            }
        });
        return berryTapUpgradeButton;
    }

    private TextButton createBerryIncomeUpgradeButton(final TextField berryCount, final TextField berryIncome) {
        final TextButton berryIncomeUpgradeButton =
                new TextButton(UPGRADE_INCOME_BASE + resourceManager.getIdleUpgradeCost(Resource.BERRIES), skin);

        berryIncomeUpgradeButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) { return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                int currentUpgradeCost = resourceManager.getIdleUpgradeCost(Resource.BERRIES);
                if(currentUpgradeCost <= resourceManager.getAmountStored(Resource.BERRIES))  {
                    resourceManager.upgradeIdleIncome(Resource.BERRIES);
                    berryIncome.setText(BERRY_INCOME + resourceManager.getIdleIncome(Resource.BERRIES));
                    berryIncomeUpgradeButton.setText(UPGRADE_INCOME_BASE + resourceManager.getIdleUpgradeCost(Resource.BERRIES));
                    resourceManager.setAmountStored(Resource.BERRIES,resourceManager.getAmountStored(Resource.BERRIES) - currentUpgradeCost);
                    berryCount.setText(TOTAL_BERRIES + resourceManager.getAmountStored(Resource.BERRIES));
                }
            }
        });
        return berryIncomeUpgradeButton;
    }

    private TextButton createBerryClickButton(final TextField berryCount)  {
        TextButton berryTextButton = new TextButton(GATHER_RESOURCE_NAME, skin);

        berryTextButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                int newTotal = resourceManager.getAmountStored(Resource.BERRIES) + resourceManager.getTapReturn(Resource.BERRIES);
                resourceManager.setAmountStored(Resource.BERRIES, newTotal);
                berryCount.setText(TOTAL_BERRIES + newTotal);
            }
        });
        return berryTextButton;
    }
}
