package de.lvl.nyrval.events;

import de.lvl.nyrval.LvlAddonMain;
import net.labymod.utils.Consumer;
import net.labymod.utils.ServerData;

public class ServerLeaveEvent implements Consumer<ServerData> {
    @Override
    public void accept(ServerData serverData) {
        LvlAddonMain.setOnServer(false);
    }
}
