package com.dualdev.clicker.resource.model;

public abstract class ClickResource {

    protected static int resourcePerTap;
    protected static int idleIncome;
    protected static int amountStored;
    protected static int tapUpgradeCost;
    protected static int idleUpgradeCost;
    protected static int totalTapUpgrades;
    protected static int totalIdleUpgrades;

    public int getTapReturn() {
        return resourcePerTap;
    }

    public void setTapReturn(int val) {
        resourcePerTap = val;
    }

    public int getIdleIncome() {
        return idleIncome;
    }

    public void setIdleIncome(int val) {
        idleIncome = val;
    }

    public int getAmountStored() {
        return amountStored;
    }

    public void setAmountStored(int val) {
        amountStored = val;
    }

    public int getTapUpgradeCost() {
        return tapUpgradeCost;
    }

    public int getIdleUpgradeCost() {
        return idleUpgradeCost;
    }
}
