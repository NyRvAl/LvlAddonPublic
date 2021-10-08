package de.lvl.nyrval.settings;

import de.lvl.nyrval.LvlAddonMain;

import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.HeaderElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;


import java.io.IOException;
import java.util.List;

public class SettingsElements {
    public void setElements(List<SettingsElement> list) throws IOException {
        BooleanElement addonOn = new BooleanElement("Addon §a§lON§e§l/§4§lOFF", new ControlElement.IconData(Material.BEACON), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean accepted) {
                Settings.getINSTANCE().setAddonOn(accepted);
                LvlAddonMain.getINSTANCE().saveConfig();

            }
        }, Settings.getINSTANCE().isAddonOn());
        list.add(new HeaderElement("LevelAddon by NyRvAl"));

        list.add(addonOn);


    }
}
