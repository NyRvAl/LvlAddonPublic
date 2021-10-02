package de.lvl.nyrval.settings;

import com.google.common.io.Resources;
import de.lvl.nyrval.LvlAddonMain;
import de.lvl.nyrval.license.CheckLicense;
import de.lvl.nyrval.license.Version;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.HeaderElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SettingsElements {
    public static String version = "";
    public void setElements(List<SettingsElement> list) throws IOException, ParseException {
        URL url = Resources.getResource("addon.json");

        String text = Resources.toString(url, StandardCharsets.UTF_8);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(text);
        version = json.get("version").toString();

        BooleanElement addonOn = new BooleanElement("Addon §a§lON§e§l/§4§lOFF", new ControlElement.IconData(Material.BEACON), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                Settings.getINSTANCE().setAddonon(aBoolean);

                if (LvlAddonMain.getCheckLicenseINSTANCE().getVersion().equals(Version.PREMIUM))
                    LvlAddonMain.getINSTANCE().saveConfig();
            }
        },Settings.getINSTANCE().isAddonon());
        if (LvlAddonMain.getCheckLicenseINSTANCE().getVersion().equals(Version.FREE)){
            list.add(new HeaderElement("LevelAddon Free Version v"+version));
        }
        else
            list.add(new HeaderElement("LevelAddon Premium Version v"+version));

        list.add(addonOn);


    }
}
