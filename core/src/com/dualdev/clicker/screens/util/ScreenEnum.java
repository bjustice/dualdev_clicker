package com.dualdev.clicker.screens.util;

import com.dualdev.clicker.resource.model.ResourceManager;
import com.dualdev.clicker.screens.*;

public enum ScreenEnum {

    START_SPLASH {
        public AbstractScreen getScreen(ResourceManager resourceManager, Object... params) {
            return new StartSplashScreen();
        }
    },
    WOOD_SCREEN {
        public AbstractScreen getScreen(ResourceManager resourceManager, Object... params) {
            return new WoodScreen(resourceManager);
        }
    },
    BERRY_SCREEN {
        public AbstractScreen getScreen(ResourceManager resourceManager, Object... params) {
            return new BerryScreen(resourceManager);
        }
    },
    STONE_SCREEN {
        public AbstractScreen getScreen(ResourceManager resourceManager, Object... params) {
            return new StoneScreen(resourceManager);
        }
    },
    IRON_SCREEN {
        public AbstractScreen getScreen(ResourceManager resourceManager, Object... params) {
            return new IronScreen(resourceManager);
        }
    };

    public abstract AbstractScreen getScreen(ResourceManager resourceManager, Object... params);
}