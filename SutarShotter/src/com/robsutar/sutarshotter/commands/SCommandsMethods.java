package com.robsutar.sutarshotter.commands;

import com.robsutar.sutarshotter.SutarShotter;
import com.sun.source.doctree.UnknownBlockTagTree;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import javax.lang.model.type.UnknownTypeException;
import java.util.Locale;
import java.util.Objects;
import java.util.zip.DataFormatException;

public class SCommandsMethods {
    public static void sReplace(Player player, String args1,String args2){

        int[] pos1 = SutarShotter.playerData.getPos1(player);
        int [] pos2 = SutarShotter.playerData.getPos2(player);

        if (pos1[0]>pos2[0]){ int tmp = pos2[0];  pos2[0]=pos1[0];  pos1[0]=tmp; }
        if (pos1[1]>pos2[1]){ int tmp = pos2[1];  pos2[1]=pos1[1];  pos1[1]=tmp; }
        if (pos1[2]>pos2[2]){ int tmp = pos2[2];  pos2[2]=pos1[2];  pos1[2]=tmp; }

        int[][][] blockpos = new int[Math.abs(pos1[0]-pos2[0])][Math.abs(pos1[1]-pos2[1])][Math.abs(pos1[2]-pos2[2])];


        for (int x = pos1[0];x<pos2[0]+1;x++){
            for (int y = pos1[1];y<pos2[1]+1;y++){
                for (int z = pos1[2];z<pos2[2]+1;z++){
                    int fX=x,fY=y,fZ=z;
                    World world = player.getWorld();

                    if (x<0){fX=x-1;} if (y<0){fY=y-1;} if (z<0){fZ=z-1;}
                    Location location = new Location(world,fX,fY,fZ);
                    Block blocki = location.getBlock();

                    if (!(blocki.getType()==Material.AIR)){
                        world.playEffect(location,Effect.SMOKE,1,50);
                        String blockData = String.valueOf(blocki.getBlockData());
                        blockData =blockData.replace("CraftBlockData{","");
                        blockData =blockData.replace("}","");
                        //args1 = checkArgs(args1,player);
                        //args2 = checkArgs(args2,player);
                        blockData = blockData.replace(args1,args2);
                        blockData = checkArgs(blockData,player);


                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        String command = "setblock "+(int)location.getX()+" "+(int)(location.getY())+" "+(int)location.getZ()+" "+blockData;
                        player.performCommand(command);
                        /*
                        placeB(blockData.replace(args1+"s",args2),location,blocki,args1,args2,player);
                        placeB(blockData.replace(args1,args2+"s"),location,blocki,args1,args2,player);
                        placeB(blockData.replace(args1,args2),location,blocki,args1,args2,player);

                         */
                    }
                }
            }
            if (x==pos2[0]-1){
                player.sendMessage(ChatColor.GOLD+"["+args1+"] ["+args2+"]");
            }
        }
    }
    private static String checkArgs (String args,Player player){
        String argsi = "";
        if (args.contains("[")){
            String[] x = args.split("\\[");
            args = x[0];
            argsi = "["+x[1];
        }
        String argsX = (args.toUpperCase(Locale.ROOT)).replace("MINECRAFT:","");

        if (Material.getMaterial(argsX)!=null){
            //player.sendMessage((ChatColor.BLUE+"1"));
        }
        else if (Material.getMaterial(argsX+"S")!=null){
            args = args+"s";
            //player.sendMessage((ChatColor.BLUE+"2"));
        }
        else if (Material.getMaterial(argsX+"_PLANKS")!=null){
            args = args+"_planks";
            //player.sendMessage((ChatColor.BLUE+"3"));
        }


        //player.sendMessage(ChatColor.GOLD+args+ChatColor.BLUE+argsi);
        return args+argsi;
    }

    /*
    private static void placeB (String blockData, Location location, Block blocki, String args1, String args2,Player player){

        if (blockData.contains("planks")){
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = "setblock "+(int)location.getX()+" "+(int)(location.getY())+" "+(int)location.getZ()+" "
                    +(blockData).replace("_planks","");
            player.performCommand(command);
        }

        if (true){
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = "setblock "+(int)location.getX()+" "+(int)(location.getY())+" "+(int)location.getZ()+" "
                    +(blockData+"_planks");
            player.performCommand(command);
        }

        if (1==1){
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = "setblock "+(int)location.getX()+" "+(int)(location.getY())+" "+(int)location.getZ()+" "
                    +(blockData).replace("brick","bricks");
            player.performCommand(command);
        }

        if (false){
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = "setblock "+(int)location.getX()+" "+(int)(location.getY())+" "+(int)location.getZ()+" "
                    +(blockData+"s");
            player.performCommand(command);
        }

        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String command = "setblock "+(int)location.getX()+" "+(int)(location.getY())+" "+(int)location.getZ()+" "+blockData;
        player.performCommand(command);
    }

     */
}
