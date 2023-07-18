package me.petterim1.limitedeol;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerLoginEvent;
import cn.nukkit.plugin.PluginBase;

public class Plugin extends PluginBase implements Listener {

    private int minProtocol;
    private String outdatedGame;

    public void onEnable() {
        saveDefaultConfig();
        minProtocol = getConfig().getInt("minProtocol");
        outdatedGame = getConfig().getString("outdatedGame");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPreLogin(PlayerLoginEvent e) {
        if (e.getPlayer().protocol < minProtocol && !e.getPlayer().hasPermission("eol.bypass")) {
            e.setKickMessage(outdatedGame);
            e.setCancelled(true);
        }
    }
}
