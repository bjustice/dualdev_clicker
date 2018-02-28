package com.dualdev.clicker.resource.model;

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
}
