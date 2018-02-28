package com.dualdev.clicker.resource.model;

public class BerryResource implements ClickResource {

    private static int berriesPerTap;
    private static int berriesPerSecond;
    private static int amountStored;
    private static int tapUpgradeCost;
    private static int idleUpgradeCost;
    private static int totalTapUpgrades;
    private static int totalIdleUpgrades;

    public BerryResource() {
        berriesPerTap = 0;
        berriesPerSecond = 1;
        amountStored = 0;
        tapUpgradeCost = 50;
        idleUpgradeCost = 10;
        totalTapUpgrades = 0;
        totalIdleUpgrades = 1;

    }

    @Override
    public int getTapReturn() {
        return berriesPerTap;
    }

    @Override
    public void setTapReturn(int val) {
        berriesPerTap = val;
    }

    @Override
    public void upgradeTapReturn() {
        berriesPerTap = berriesPerTap + 1;
        tapUpgradeCost = tapUpgradeCost + 10 + (2 * totalTapUpgrades);
        totalTapUpgrades += 1;
    }

    @Override
    public int getIdleIncome() {
        return berriesPerSecond;
    }

    @Override
    public void setIdleIncome(int val) {
        berriesPerSecond = val;
    }

    @Override
    public void upgradeIdleIncome() {
        berriesPerSecond = berriesPerSecond + 1;
        idleUpgradeCost = idleUpgradeCost + 25 + (10 * totalIdleUpgrades);
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
