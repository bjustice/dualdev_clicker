package com.dualdev.clicker.resource.model;

import com.dualdev.clicker.resource.helpers.Resource;

public class ResourceManager {
    private WoodResource woodResource;
    private BerryResource berryResource;
    private StoneResource stoneResource;
    private IronResource ironResource;

    public ResourceManager() {
        woodResource = new WoodResource();
        berryResource = new BerryResource();
        stoneResource = new StoneResource();
        ironResource = new IronResource();
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
        switch(resource){
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
        switch(resource){
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
}
