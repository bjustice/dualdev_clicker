package com.dualdev.clicker.clickresources;

public class WoodResource implements ClickResource{

    private static int woodPerClick;
    private static int woodPerSecond;
    private static int amountStored;

    public WoodResource() {
        woodPerClick = 1;
        woodPerSecond = 0;
        amountStored = 0;
    }

    @Override
    public int getPerClickReturn() {
        return woodPerClick;
    }

    @Override
    public void setPerClickReturn(int val) {
        woodPerClick = val;
    }

    @Override
    public int getPerSecondIncome() {
        return woodPerSecond;
    }

    @Override
    public void setPerSecondIncome(int val) {
        woodPerSecond = val;
    }

    @Override
    public int getAmountStored() {
        return amountStored;
    }

    @Override
    public void setAmountStored(int val) {
        amountStored = val;
    }
}
