package com.dualdev.clicker.resource.model;

public abstract class ClickResource {

    protected int resourcePerTap;
    protected int idleIncome;
    protected int amountStored;
    protected int tapUpgradeCost;
    protected int idleUpgradeCost;
    protected int totalTapUpgrades;
    protected int totalIdleUpgrades;

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
