package com.dualdev.clicker.resource.model;

public class WoodResource extends ClickResource{

    public WoodResource() {
        resourcePerTap = 1;
        idleIncome = 0;
        amountStored = 0;
        tapUpgradeCost = 10;
        idleUpgradeCost = 25;
        totalTapUpgrades = 0;
        totalIdleUpgrades = 0;
    }

    public void upgradeTapReturn() {
        resourcePerTap = resourcePerTap + 1;
        tapUpgradeCost = tapUpgradeCost + 5 + (3 * totalTapUpgrades);
        totalTapUpgrades += 1;
    }

    public void upgradeIdleIncome() {
        idleIncome = idleIncome + 1 + totalIdleUpgrades;
        idleUpgradeCost = idleUpgradeCost + 10 + (10 * totalIdleUpgrades);
        totalIdleUpgrades += 1;
    }
}
