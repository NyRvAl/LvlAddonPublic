package de.lvl.nyrval;

import de.lvl.nyrval.events.MyTickEvent;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.SettingsElement;

import java.util.List;

public class LvlAddonMain extends LabyModAddon {
    @Override
    public void onEnable() {
        this.getApi().registerForgeListener(new MyTickEvent());
    }

    @Override
    public void loadConfig() {

    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {

    }
}
