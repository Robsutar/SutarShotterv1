package com.robsutar.sutarshotter.files;

import com.robsutar.sutarshotter.SutarShotter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class PlayerData extends AbstractFile {
    public PlayerData (SutarShotter main){
        super(main,"playerdata.yml");
    }

    public void setPos(Player player,String path){
        int[] pos = new int[3];
        pos[0]=(int)(player.getLocation().getX());
        pos[1]=(int)(player.getLocation().getY());
        pos[2]=(int)(player.getLocation().getZ());
        String p1 = Arrays.toString(pos);
        config.set((player.getUniqueId().toString()+path),(Arrays.toString(pos)
                .replace("[","")).replace("]",""));
    }
    public int[] getPos(Player player,String path){
        String[] e = (String.valueOf(config.get((player.getUniqueId().toString()+path)))).split(", ");
        int[] pos = new int[e.length];
        for (int i = 0;i<e.length;i++){
            pos[i] = Integer.parseInt(e[i]);
        }
        return pos;
    }

    public void setPos1(Player player){ setPos(player,"-POS1-"); }

    public void setPos2(Player player){ setPos(player,"-POS2-"); }

    public int[] getPos1(Player player){ if (
        config.get((player.getUniqueId().toString()+"-POS1-"))==null){
        setPos1(player);
        }return getPos(player,"-POS1-"); }
    public int[] getPos2(Player player){ if (
            config.get((player.getUniqueId().toString()+"-POS2-"))==null){
        setPos2(player);
    }return getPos(player,"-POS2-"); }

    public void setTimer(Player player){
        if (config.get((player.getUniqueId().toString()+"-TIMER-"))==null){
            config.set((player.getUniqueId().toString()+"-TIMER-"), 1);
        }
        else
            config.set((player.getUniqueId().toString()+"-TIMER-"), getTimer(player)+1);
    }

    public int getTimer(Player player){
        if (config.get((player.getUniqueId().toString()+"-TIMER-"))==null){
            setTimer(player);
        }
        return (int)config.get((player.getUniqueId().toString()+"-TIMER-"));}

    public void setLastMove(Player player){
            config.set((player.getUniqueId().toString()+"-AFK-"), getTimer(player));
    }

    public int getLastMove(Player player){
        if (config.get((player.getUniqueId().toString()+"-AFK-"))==null){ setLastMove(player);
        }
        return (int)config.get((player.getUniqueId().toString()+"-AFK-"));}

    public void makeAfk(Player player,Boolean bool){
        config.set((player.getUniqueId().toString()+"-AFKBOOL-"), bool);
    }
    public boolean getAfkBool(Player player){
        if (config.get(player.getUniqueId().toString()+"-AFKBOOL-")==null){
            makeAfk(player,false);
        }
        return (boolean) config.get(player.getUniqueId().toString()+"-AFKBOOL-");
    }


    public void setPrefix(Player player,String args){
            config.set((player.getUniqueId().toString()+"-PREFIX-"), args);
    }

    public String getPrefix (Player player){
        if (config.get((player.getUniqueId().toString()+"-PREFIX-"))==null){
            setPrefix(player, ChatColor.GREEN+"");
        }
        return String.valueOf(config.get((player.getUniqueId().toString()+"-PREFIX-")));
    }

    public String getPlayerServerName(Player player){ return (String) config.get(player.getUniqueId().toString()); }
}
