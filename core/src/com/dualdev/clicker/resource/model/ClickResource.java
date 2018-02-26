package com.dualdev.clicker.resource.model;

public interface ClickResource {

    int getTapReturn();

    void setTapReturn(int val);

    void upgradeTapReturn();

    int getIdleIncome();

    void setIdleIncome(int val);

    void upgradeIdleIncome();

    int getAmountStored();

    void setAmountStored(int val);

    int getTapUpgradeCost();

    int getIdleUpgradeCost();
}
