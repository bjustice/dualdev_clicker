package com.dualdev.clicker.resource.initalizer;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Timer;
import com.dualdev.clicker.resource.model.BerryResource;

public class BerryInitializer {
    private float scale;
    private BerryResource berryResource;
    private Skin skin;

    private final static String UPGRADE_INCOME_BASE = "Upgrade Income\n Cost (w) ";
    private final static String UPGRADE_TAP_BASE = "Upgrade Tap\n Cost (w)";
    private final static String BERRY_INCOME = "Berry Income: ";
    private final static String TOTAL_BERRIES = "Total Berries";

    public Table initializeBerryButtons(float s, BerryResource berryRes, Table masterBerryTable, Skin sk) {
        this.scale = s;
        this.berryResource = berryRes;
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

        masterBerryTable.row().pad(0,5,0,5);
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
        Timer.schedule(new Timer.Task() {
            @Override public void run() {
                int updateTotal = berryResource.getAmountStored() + berryResource.getIdleIncome();
                berryResource.setAmountStored(updateTotal);
                berryCountText.setText(TOTAL_BERRIES + berryResource.getAmountStored());
                Gdx.app.log( "Berry Resource",
                        "Income tick. amount: " + berryResource.getIdleIncome());
            }

        }, 1, 1);
    }

    private TextField createBerryCountText() {
        TextField countArea = new TextArea(TOTAL_BERRIES + berryResource.getAmountStored(), skin);
        countArea.setDisabled(true);
        return countArea;
    }

    private TextField createBerryIncomeText() {
        TextField incomeArea = new TextArea(BERRY_INCOME + berryResource.getIdleIncome(), skin);
        incomeArea.setDisabled(true);
        return incomeArea;
    }

    private TextButton createBerryClickUpgradeButton(final TextField berryCount) {
        final TextButton berryTapUpgradeButton =
                new TextButton(UPGRADE_TAP_BASE + berryResource.getTapUpgradeCost(), skin);

        berryTapUpgradeButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button){
                int currentUpgradeCost = berryResource.getTapUpgradeCost();
                if(currentUpgradeCost <= berryResource.getAmountStored()) {
                    berryResource.upgradeTapReturn();
                    berryTapUpgradeButton.setText(UPGRADE_TAP_BASE + berryResource.getTapUpgradeCost());
                    berryResource.setAmountStored(berryResource.getAmountStored() - currentUpgradeCost);
                    berryCount.setText(TOTAL_BERRIES + berryResource.getAmountStored());
                }
            }
        });
        return berryTapUpgradeButton;
    }

    private TextButton createBerryIncomeUpgradeButton(final TextField berryCount, final TextField berryIncome) {
        final TextButton berryIncomeUpgradeButton =
                new TextButton(UPGRADE_INCOME_BASE + berryResource.getIdleUpgradeCost(), skin);

        berryIncomeUpgradeButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) { return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                int currentUpgradeCost = berryResource.getIdleUpgradeCost();
                if(currentUpgradeCost <= berryResource.getAmountStored())  {
                    berryResource.upgradeIdleIncome();
                    berryIncome.setText(BERRY_INCOME + berryResource.getIdleIncome());
                    berryIncomeUpgradeButton.setText(UPGRADE_INCOME_BASE + berryResource.getIdleUpgradeCost());
                    berryResource.setAmountStored(berryResource.getAmountStored() - currentUpgradeCost);
                    berryCount.setText(TOTAL_BERRIES + berryResource.getAmountStored());
                }
            }
        });
        return berryIncomeUpgradeButton;
    }

    private TextButton createBerryClickButton(final TextField berryCount)  {
        TextButton berryTextButton = new TextButton("Pick Berries", skin);
        berryTextButton.setHeight(80f/scale);
        berryTextButton.setWidth(20f/scale);

        berryTextButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                int newTotal = berryResource.getAmountStored() + berryResource.getTapReturn();
                berryResource.setAmountStored(newTotal);
                berryCount.setText(TOTAL_BERRIES + newTotal);
            }
        });
        return berryTextButton;
    }
}
