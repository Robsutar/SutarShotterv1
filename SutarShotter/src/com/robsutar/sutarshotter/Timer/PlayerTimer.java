package com.robsutar.sutarshotter.Timer;

import com.robsutar.sutarshotter.SutarShotter;
import com.robsutar.sutarshotter.events.PlayerTickEvent;
import com.robsutar.sutarshotter.scoreboard.ScrPlayerEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerTimer implements Listener {
    @EventHandler
    public static void onPlayerMoves(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        SutarShotter.playerData.setLastMove(player);
    }

    @EventHandler
    public static void onPlayerTick(PlayerTickEvent event) {
        if (event.getPlayer()!=null) {
            Player player = event.getPlayer();
            if(!isAfk(player)) {
                SutarShotter.playerData.setTimer(player);
                if (SutarShotter.playerData.getAfkBool(player)) {
                    player.sendMessage(SutarShotter.SUTARSHOTTER + SutarShotter.CONVERSATION + "Não estás mais afk");
                    SutarShotter.playerData.makeAfk(player, false);
                } else{SutarShotter.playerData.makeAfk(player, false);}
            } else if (!SutarShotter.playerData.getAfkBool(player)) {
                player.sendMessage(SutarShotter.SUTARSHOTTER + SutarShotter.CONVERSATION + "Estás afk");
                SutarShotter.playerData.makeAfk(player,true);
            }
            if(SutarShotter.playerData.getTimer(player) % 20 == 0) {
                ScrPlayerEvents.createScoreboard(player);
            }
            Bukkit.getScheduler().runTaskLater(SutarShotter.getPlugin(SutarShotter.class), () ->
                    onPlayerTick(event), 1);
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
    public static boolean isAfk(Player p ){
        int timer = SutarShotter.playerData.getTimer(p);
        int afkTimer = SutarShotter.playerData.getLastMove(p);
        if(timer>(afkTimer+SutarShotter.getAfkTimer())){
            return true;
        }
        return false;
    }
}
