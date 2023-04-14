package com.alphadev.services;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerMoveService {
    private  PlayerMoveService(){

    }
    private static final List<Player> inMoviment = new ArrayList<>();


    public static void trackPlayerMove(Player player){
        if(inMoviment.contains(player))
            return;

        inMoviment.add(player);
    }

    public static void removePlayerTracker(Player player){
        inMoviment.remove(player);
    }

    public static boolean isPlayerInMovement(Player player){
        return inMoviment.contains(player);
    }

}
