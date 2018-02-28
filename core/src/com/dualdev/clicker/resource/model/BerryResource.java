package com.dualdev.clicker.resource.model;

public class BerryResource extends ClickResource {

    public BerryResource() {
        resourcePerTap = 0;
        idleIncome = 0;
        amountStored = 0;
        tapUpgradeCost = 50;
        idleUpgradeCost = 10;
        totalTapUpgrades = 0;
        totalIdleUpgrades = 0;
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
