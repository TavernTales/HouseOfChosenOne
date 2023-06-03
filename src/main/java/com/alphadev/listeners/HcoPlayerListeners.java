package com.alphadev.listeners;

import com.alphadev.Hoco;
import com.alphadev.HocoPlugin;
import com.alphadev.entities.HcoPlayer;
import com.alphadev.manager.HouseManager;
import com.alphadev.manager.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class HcoPlayerListeners implements Listener {
    private final Hoco hoco;
    private final PlayerManager playerManager;
    private final HouseManager houseManager;
    public HcoPlayerListeners(HocoPlugin plugin) {
        this.hoco = plugin.getInstance();
        this.playerManager = hoco.getPlayerManager();
        this.houseManager = hoco.getHouseManager();
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        HcoPlayer hcoPlayer = playerManager.getHocoPlayerFromUUID(p.getUniqueId());
        if(hcoPlayer==null){
            playerManager.saveNewPlayerDB(p);
        }
        playerManager.saveOnlinePlayer(p);

    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        playerManager.removeOnlinePlayer(p);
    }
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        HcoPlayer hcoPlayer = playerManager.getHocoPlayerFromUUID(e.getPlayer().getUniqueId());
        if(hcoPlayer==null||hcoPlayer.houseID()==null||hcoPlayer.houseID()==0)return;
    }
}
