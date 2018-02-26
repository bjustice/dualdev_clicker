package com.dualdev.clicker.screens.util;

import com.dualdev.clicker.screens.*;
import com.dualdev.clicker.screens.util.AbstractScreen;

public enum ScreenEnum {

    START_SPLASH {
        public AbstractScreen getScreen(Object... params) {
            return new StartSplashScreen();
        }
    },
    WOOD_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new WoodScreen();
        }
    },
    BERRY_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new BerryScreen();
        }
    },
    STONE_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new StoneScreen();
        }
    },
    IRON_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new IronScreen();
        }
    };

    public abstract AbstractScreen getScreen(Object... params);
}