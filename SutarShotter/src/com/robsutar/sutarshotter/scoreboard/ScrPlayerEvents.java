package com.robsutar.sutarshotter.scoreboard;

import com.robsutar.sutarshotter.SutarShotter;
import com.robsutar.sutarshotter.Timer.PlayerTimer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.*;

public class ScrPlayerEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        try {
            createScoreboard(e.getPlayer());
            updateScoreboard();
        } catch (IllegalArgumentException exception){
            System.out.println(ChatColor.RED+"ScrPlauerEvents playerjoin event error");
        }
    }

    @EventHandler
    public void onPlayerLeaves(PlayerQuitEvent e){
        updateScoreboard();
    }

    @SuppressWarnings("deprecation")
    public static void createScoreboard(Player p){
        if (p!=null){
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = manager.getNewScoreboard();
            Objective objective = board.registerNewObjective("Stats","dummy");
            objective.setDisplayName("Stats");
            objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);

            Team dev = board.registerNewTeam("dev");
            dev.addPlayer(p);
            dev.setPrefix(SutarShotter.playerData.getPrefix(p));
            dev.setSuffix(PlayerTimer.getTimer(p));

            Score score = objective.getScore("players:");
            score.setScore(Bukkit.getOnlinePlayers().size());
            p.setScoreboard(board);
        }
    }

    public static void updateScoreboard() {
        for (Player online : Bukkit.getOnlinePlayers()){
            Score score = online.getScoreboard().getObjective(DisplaySlot.PLAYER_LIST).getScore("players");
            score.setScore(Bukkit.getOnlinePlayers().size());
        }
    }

}
