package de.lvl.nyrval.events;

import de.lvl.nyrval.LvlAddonMain;
import de.lvl.nyrval.settings.Settings;
import net.labymod.main.LabyMod;
import net.labymod.utils.Consumer;
import net.labymod.utils.ServerData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrieferGamesServerJoinEvent implements Consumer<ServerData> {
    Pattern ggPattern = Pattern.compile("\\w*griefergames.net");
    @Override
    public void accept(ServerData serverData) {
        Matcher matcher = ggPattern.matcher(serverData.getIp().toLowerCase());
        if (matcher.find()){
            LabyMod.getInstance().displayMessageInChat("§4§l[§e§lLevelAddon§4§l]§b§l ist derzeit "+ (Settings.getINSTANCE().isAddonOn()?"aktiviert":"deaktiviert")+".");
        LvlAddonMain.setOnServer(true);
        }
    }
}
