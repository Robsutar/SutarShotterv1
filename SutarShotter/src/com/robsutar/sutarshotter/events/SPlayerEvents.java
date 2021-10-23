package com.robsutar.sutarshotter.events;

import com.robsutar.sutarshotter.SutarShotter;
import com.robsutar.sutarshotter.Timer.PlayerTimer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;


public class SPlayerEvents implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playername = player.getName();
        PlayerTickEvent playerTickEvent = new PlayerTickEvent(event.getPlayer());
        PlayerTimer.onPlayerTick(playerTickEvent);

        player.sendMessage(SutarShotter.SUTARSHOTTER + SutarShotter.CONVERSATION + "Olá " + playername + SutarShotter.CONVERSATION +
                "! Este plugin está em desenvolvimento, certifique-se de fazer um backup do mapa antes de usa-lo!"+ChatColor.WHITE+" O valor do tempo para ficar afk é de: "
        +ChatColor.RED+SutarShotter.getAfkTimer()+ChatColor.GRAY+", pode ser alterado em"+ChatColor.AQUA+" -> Pasta do servidor -> Plugins -> "+SutarShotter.SUTARSHOTTER
        +"-> Config -> AfkTimer");
    }
}


