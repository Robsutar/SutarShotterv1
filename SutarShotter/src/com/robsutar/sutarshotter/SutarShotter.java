package com.robsutar.sutarshotter;

import com.robsutar.sutarshotter.Timer.PlayerTimer;
import com.robsutar.sutarshotter.commands.SCommands;
import com.robsutar.sutarshotter.events.SPlayerEvents;
import com.robsutar.sutarshotter.files.PlayerData;
import com.robsutar.sutarshotter.scoreboard.ScrPlayerEvents;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class SutarShotter extends JavaPlugin {

    public static PlayerData playerData;

    public static ChatColor CONVERSATION = ChatColor.AQUA;
    public static ChatColor ALERT = ChatColor.YELLOW;
    public static ChatColor WARNING = ChatColor.RED;
    public static ChatColor CONGRATULATIONS = ChatColor.GREEN;

    public static String SUTARSHOTTER = ChatColor.GOLD + "[SutarShotter] "+CONVERSATION;
    public static String GOODCREDITS = ChatColor.GREEN + " is enabled, Robsutar sent a hug";
    public static String BADCRETODS = ChatColor.RED + " saving files... Robsutar send a bye";
    public static String ERROR = ChatColor.RED+"Ocorreu um erro, lamentamos";

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()){
            getDataFolder().mkdirs();
        }
        playerData = new PlayerData(this);

        SCommands commands = new SCommands();
        getCommand("pos1").setExecutor(commands);
        getCommand("pos2").setExecutor(commands);
        getCommand("sreplace").setExecutor(commands);
        getCommand("ssetprefix").setExecutor(commands);
        getCommand("ssave").setExecutor(commands);

        getServer().getPluginManager().registerEvents(new SPlayerEvents(),this);
        getServer().getPluginManager().registerEvents(new ScrPlayerEvents(),this);
        getServer().getPluginManager().registerEvents(new PlayerTimer(),this);

        getServer().getConsoleSender().sendMessage(SUTARSHOTTER+GOODCREDITS);
    }

    @Override
    public void onDisable() {
        playerData.save();
        getServer().getConsoleSender().sendMessage(SUTARSHOTTER+BADCRETODS);
    }
}
