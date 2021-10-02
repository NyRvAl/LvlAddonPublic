package de.lvl.nyrval.settings;

import com.google.gson.JsonObject;

public class Settings {
    private final JsonObject config;

    public static Settings getINSTANCE() {
        return INSTANCE;
    }

    public static final String ADDONON = "addonOn";
    private static Settings INSTANCE;

    public Settings(JsonObject config) {
        INSTANCE = this;
        this.config = config;
    }
    public void setAddonon(boolean On){
        config.addProperty(ADDONON,On);

    }
    public Boolean isAddonon(){
        return config.has(ADDONON)?config.get(ADDONON).getAsBoolean():false;
    }
}
