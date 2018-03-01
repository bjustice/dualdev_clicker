package com.dualdev.clicker.resource.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.dualdev.clicker.resource.helpers.Resource;

public class ResourceManager {
    private WoodResource woodResource;
    private BerryResource berryResource;
    private StoneResource stoneResource;
    private IronResource ironResource;
    private Timer incomeTimer;

    public ResourceManager() {
        woodResource = new WoodResource();
        berryResource = new BerryResource();
        stoneResource = new StoneResource();
        ironResource = new IronResource();
        createTimer();
    }

    public void clearUITimers() {
        woodResource.getUITimer().clear();
        berryResource.getUITimer().clear();
        stoneResource.getUITimer().clear();
        ironResource.getUITimer().clear();
    }

    private void createTimer() {
        woodResource.setUITimer(new Timer());
        berryResource.setUITimer(new Timer());
        stoneResource.setUITimer(new Timer());
        ironResource.setUITimer(new Timer());

        incomeTimer = new Timer();
        incomeTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                int updatedWood =
                        getAmountStored(Resource.WOOD) + getIdleIncome(Resource.WOOD);
                setAmountStored(Resource.WOOD, updatedWood);

                int updatedBerries =
                        getAmountStored(Resource.BERRIES) + getIdleIncome(Resource.BERRIES);
                setAmountStored(Resource.BERRIES, updatedBerries);

                int updatedStone =
                        getAmountStored(Resource.STONE) + getIdleIncome(Resource.STONE);
                setAmountStored(Resource.STONE, updatedStone);

                int updateIron =
                        getAmountStored(Resource.IRON) + getIdleIncome(Resource.IRON);
                setAmountStored(Resource.IRON, updateIron);

                Gdx.app.log("GLOBAL INCOME TICK",
                        "Wood amount: " + getIdleIncome(Resource.WOOD) +
                                "\nBerries amount: " + getIdleIncome(Resource.BERRIES) +
                                "\nStone amount: " + getIdleIncome(Resource.STONE) +
                                "\nIron amount: " + getIdleIncome(Resource.IRON));
            }
        },1,1);
    }

    public WoodResource getWoodResource() {
        return woodResource;
    }

    public BerryResource getBerryResource() {
        return berryResource;
    }

    public StoneResource getStoneResource() { return stoneResource; }

    public IronResource getIronResource() {
        return ironResource;
    }

    public int getTapReturn(Resource resource) {
        switch(resource){
            case WOOD:
                return woodResource.getTapReturn();
            case BERRIES:
                return berryResource.getTapReturn();
            case STONE:
                return stoneResource.getTapReturn();
            case IRON:
                return ironResource.getTapReturn();
            default:
                return -1;
        }
    }

    public void setTapReturn(Resource resource, int amount) {
        switch(resource){
            case WOOD:
                woodResource.setTapReturn(amount);
                break;
            case BERRIES:
                berryResource.setTapReturn(amount);
                break;
            case STONE:
                stoneResource.setTapReturn(amount);
                break;
            case IRON:
                ironResource.setTapReturn(amount);
                break;
        }
    }

    public int getIdleIncome(Resource resource) {
        switch(resource){
            case WOOD:
                return woodResource.getIdleIncome();
            case BERRIES:
                return berryResource.getIdleIncome();
            case STONE:
                return stoneResource.getIdleIncome();
            case IRON:
                return ironResource.getIdleIncome();
            default:
                return -1;
        }
    }

    public void setIdleIncome(Resource resource, int amount) {
        switch(resource){
            case WOOD:
                woodResource.setIdleIncome(amount);
                break;
            case BERRIES:
                berryResource.setIdleIncome(amount);
                break;
            case STONE:
                stoneResource.setIdleIncome(amount);
                break;
            case IRON:
                ironResource.setIdleIncome(amount);
                break;
        }
    }

    public int getAmountStored(Resource resource) {
        switch(resource){
            case WOOD:
                return woodResource.getAmountStored();
            case BERRIES:
                return berryResource.getAmountStored();
            case STONE:
                return stoneResource.getAmountStored();
            case IRON:
                return ironResource.getAmountStored();
            default:
                return -1;
        }
    }

    public void setAmountStored(Resource resource, int amount) {
        switch(resource){
            case WOOD:
                woodResource.setAmountStored(amount);
                break;
            case BERRIES:
                berryResource.setAmountStored(amount);
                break;
            case STONE:
                stoneResource.setAmountStored(amount);
                break;
            case IRON:
                ironResource.setAmountStored(amount);
                break;
        }
    }

    public int getTapUpgradeCost(Resource resource) {
        switch(resource){
            case WOOD:
                return woodResource.getTapUpgradeCost();
            case BERRIES:
                return berryResource.getTapUpgradeCost();
            case STONE:
                return stoneResource.getTapUpgradeCost();
            case IRON:
                return ironResource.getTapUpgradeCost();
            default:
                return -1;
        }
    }

    public int getIdleUpgradeCost(Resource resource) {
        switch(resource){
            case WOOD:
                return woodResource.getIdleUpgradeCost();
            case BERRIES:
                return berryResource.getIdleUpgradeCost();
            case STONE:
                return stoneResource.getIdleUpgradeCost();
            case IRON:
                return ironResource.getIdleUpgradeCost();
            default:
                return -1;
        }
    }

    public void upgradeTapReturn(Resource resource) {
        switch(resource) {
            case WOOD:
                woodResource.upgradeTapReturn();
                break;
            case BERRIES:
                berryResource.upgradeTapReturn();
                break;
            case STONE:
                stoneResource.upgradeTapReturn();
                break;
            case IRON:
                ironResource.upgradeTapReturn();
                break;
        }
    }

    public void upgradeIdleIncome(Resource resource) {
        switch(resource) {
            case WOOD:
                woodResource.upgradeIdleIncome();
                break;
            case BERRIES:
                berryResource.upgradeIdleIncome();
                break;
            case STONE:
                stoneResource.upgradeIdleIncome();
                break;
            case IRON:
                ironResource.upgradeIdleIncome();
                break;
        }
    }

    public Timer getUITimer(Resource resource) {
        switch (resource) {
            case WOOD:
                return woodResource.getUITimer();
            case BERRIES:
                return berryResource.getUITimer();
            case STONE:
                return stoneResource.getUITimer();
            case IRON:
                return ironResource.getUITimer();
            default:
                return null;
        }
    }
}
