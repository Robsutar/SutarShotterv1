package com.robsutar.sutarshotter.Timer;

import com.robsutar.sutarshotter.SutarShotter;
import com.robsutar.sutarshotter.scoreboard.ScrPlayerEvents;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerTimer implements Listener {
    @EventHandler
    public static void onPlayerMoves(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        SutarShotter.playerData.setTimer(player);
        if (SutarShotter.playerData.getTimer(player)%20==0){
            ScrPlayerEvents.createScoreboard(player);
        }
    }

    public static String getTimer(Player p){
        int totalSecs,sec,min,hour;
        int timer = SutarShotter.playerData.getTimer(p);
        totalSecs = timer/20;
        sec = totalSecs % 60;
        min = (totalSecs % 3600) / 60;
        hour = totalSecs / 3600;
        String smh = hour+":"+min+":"+ sec;
        return " "+ChatColor.GOLD+smh;
    }
}
