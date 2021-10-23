package com.robsutar.sutarshotter.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerTickEvent extends Event {
    private Player p;

    public PlayerTickEvent (Player player){
        p = player;
    }

    public Player getPlayer(){return p;}

    static HandlerList handlers = new HandlerList();
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
