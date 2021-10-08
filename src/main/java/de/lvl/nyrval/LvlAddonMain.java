package de.lvl.nyrval;

import de.lvl.nyrval.events.GrieferGamesServerJoinEvent;
import de.lvl.nyrval.events.MyToolTipEvent;
import de.lvl.nyrval.events.ServerLeaveEvent;
import de.lvl.nyrval.settings.Settings;
import de.lvl.nyrval.settings.SettingsElements;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.SettingsElement;

import java.io.IOException;
import java.util.List;

public class LvlAddonMain extends LabyModAddon {
    private static LvlAddonMain INSTANCE;
    private static boolean onServer =false;

    public static boolean isOnServer() {
        return onServer;
    }

    public static void setOnServer(boolean onServer) {
        LvlAddonMain.onServer = onServer;
    }


    public static LvlAddonMain getINSTANCE() {
        return INSTANCE;
    }

    Settings settings;
    @Override
    public void onEnable() {
        INSTANCE = this;

        this.getApi().registerForgeListener(new MyToolTipEvent());
            this.getApi().getEventManager().registerOnJoin(new GrieferGamesServerJoinEvent());
            this.getApi().getEventManager().registerOnQuit(new ServerLeaveEvent());
    }

    @Override
    public void loadConfig() {
         settings = new Settings(this.getConfig());
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        try {
            new SettingsElements().setElements(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
