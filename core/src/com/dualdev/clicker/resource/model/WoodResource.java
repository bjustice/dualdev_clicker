package com.dualdev.clicker.resource.model;

public class WoodResource implements ClickResource{

    private static int woodPerTap;
    private static int woodPerSecond;
    private static int amountStored;
    private static int tapUpgradeCost;
    private static int idleUpgradeCost;
    private static int totalTapUpgrades;
    private static int totalIdleUpgrades;

    public WoodResource() {
        woodPerTap = 1;
        woodPerSecond = 0;
        amountStored = 0;
        tapUpgradeCost = 10;
        idleUpgradeCost = 25;
        totalTapUpgrades = 0;
        totalIdleUpgrades = 0;
    }

    @Override
    public int getTapReturn() {
        return woodPerTap;
    }

    @Override
    public void setTapReturn(int val) {
        woodPerTap = val;
    }

    @Override
    public void upgradeTapReturn() {
        woodPerTap = woodPerTap + 1;
        tapUpgradeCost = tapUpgradeCost + 5 + (3 * totalTapUpgrades);
        totalTapUpgrades += 1;
    }

    @Override
    public int getIdleIncome() {
        return woodPerSecond;
    }

    @Override
    public void setIdleIncome(int val) {
        woodPerSecond = val;
    }

    @Override
    public void upgradeIdleIncome() {
        woodPerSecond = woodPerSecond + 1;
        idleUpgradeCost = idleUpgradeCost + 10 + (10 * totalIdleUpgrades);
        totalIdleUpgrades += 1;
    }

    @Override
    public int getAmountStored() {
        return amountStored;
    }

    @Override
    public void setAmountStored(int val) {
        amountStored = val;
    }

    @Override
    public int getTapUpgradeCost() {
        return tapUpgradeCost;
    }

    @Override
    public int getIdleUpgradeCost() {
        return idleUpgradeCost;
    }

}
