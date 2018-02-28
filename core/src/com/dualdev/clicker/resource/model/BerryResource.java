package com.dualdev.clicker.resource.model;

public class BerryResource extends ClickResource {

    public BerryResource() {
        resourcePerTap = 0;
        idleIncome = 1;
        amountStored = 0;
        tapUpgradeCost = 50;
        idleUpgradeCost = 10;
        totalTapUpgrades = 0;
        totalIdleUpgrades = 1;

    }

    public void upgradeTapReturn() {
        resourcePerTap = resourcePerTap + 1;
        tapUpgradeCost = tapUpgradeCost + 10 + (2 * totalTapUpgrades);
        totalTapUpgrades += 1;
    }

    public void upgradeIdleIncome() {
        idleIncome = idleIncome + 1;
        idleUpgradeCost = idleUpgradeCost + 25 + (10 * totalIdleUpgrades);
        totalIdleUpgrades += 1;
    }
}
