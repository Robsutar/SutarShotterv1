package com.robsutar.sutarshotter.commands;

import com.robsutar.sutarshotter.SutarShotter;
import com.robsutar.sutarshotter.scoreboard.ScrPlayerEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class SCommands implements CommandExecutor {
    private String commandError = SutarShotter.WARNING+"O comando doi diditado incorretamente!"+SutarShotter.CONVERSATION+" Siga o exemplo: "+ChatColor.BLUE;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)){ return true; }
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("ssave")){
            SutarShotter.playerData.save();
            player.sendMessage(SutarShotter.SUTARSHOTTER+"Saving files...");
        }
        else if (cmd.getName().equalsIgnoreCase("pos1")){
            SutarShotter.playerData.setPos1(player);
            int[] pos = SutarShotter.playerData.getPos1(player);
            String outpos = pos[0]+", "+pos[1]+", "+pos[2];
            player.sendMessage(SutarShotter.SUTARSHOTTER+SutarShotter.ALERT+"pos1: " +SutarShotter.CONVERSATION+ outpos);
        }
        else if (cmd.getName().equalsIgnoreCase("pos2")){
            SutarShotter.playerData.setPos2(player);
            int[] pos = SutarShotter.playerData.getPos2(player);
            String outpos = pos[0]+", "+pos[1]+", "+pos[2];
            player.sendMessage(SutarShotter.SUTARSHOTTER+SutarShotter.ALERT+"pos2: " +SutarShotter.CONVERSATION+ outpos);
        }
        else if (cmd.getName().equalsIgnoreCase("sreplace")){
            if((args.length<=2)){
                SCommandsMethods.sReplace(player,args[0],args[1]);
            }
        }
        else if (cmd.getName().equalsIgnoreCase("ssetprefix")){
            if(args.length == 3) {
                try {
                    Player prefixPlayer = Bukkit.getPlayer(args[0]);
                    ChatColor prefixColor = ChatColor.getByChar(args[2]);
                    String prefix = "";
                    if(String.valueOf(prefixColor) == "null") {
                        player.sendMessage(SutarShotter.CONGRATULATIONS + "Colorize!");
                        prefix = args[1] + " ";
                    } else {
                        prefix = ChatColor.getByChar(args[2]) + args[1] + " ";
                    }
                    player.sendMessage(SutarShotter.CONVERSATION + "Jogador: " + SutarShotter.WARNING + prefixPlayer.getName() + ChatColor.getByChar("a") + " prefixo: " + prefix);
                    SutarShotter.playerData.setPrefix(prefixPlayer, prefix);
                    ScrPlayerEvents.createScoreboard(player);
                } catch (IllegalArgumentException e) {
                    player.sendMessage(SutarShotter.ERROR);
                }
            } else {
                player.sendMessage(commandError + "/ssetprefix [jogador] [tag] [numero da cor]");
            }
       }

        return true;
    }
}
