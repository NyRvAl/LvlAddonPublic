package de.lvl.nyrval;

import de.lvl.nyrval.events.GrieferGamesServerJoinEvent;
import de.lvl.nyrval.events.MyTickEvent;
import de.lvl.nyrval.events.ServerLeaveEvent;
import de.lvl.nyrval.license.CheckLicense;
import de.lvl.nyrval.settings.Settings;
import de.lvl.nyrval.settings.SettingsElements;
import net.labymod.api.LabyModAddon;
import net.labymod.main.LabyMod;
import net.labymod.settings.elements.SettingsElement;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class LvlAddonMain extends LabyModAddon {
    private static LvlAddonMain INSTANCE;
    private static CheckLicense checkLicenseINSTANCE;
    private static boolean onServer =false;

    public static boolean isOnServer() {
        return onServer;
    }

    public static void setOnServer(boolean onServer) {
        LvlAddonMain.onServer = onServer;
    }

    public static CheckLicense getCheckLicenseINSTANCE() {
        return checkLicenseINSTANCE;
    }

    public static LvlAddonMain getINSTANCE() {
        return INSTANCE;
    }

    Settings settings;
    @Override
    public void onEnable() {
        INSTANCE = this;
        checkLicenseINSTANCE = new CheckLicense();
        checkLicenseINSTANCE.isOkay();
        this.getApi().registerForgeListener(new MyTickEvent());
        if (checkLicenseINSTANCE.isPremium()) {
            this.getApi().getEventManager().registerOnJoin(new GrieferGamesServerJoinEvent());
            this.getApi().getEventManager().registerOnQuit(new ServerLeaveEvent());
        }
    }

    @Override
    public void loadConfig() {
         settings = new Settings(this.getConfig());
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        SettingsElements settingsElements = new SettingsElements();
        try {
            settingsElements.setElements(list);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
