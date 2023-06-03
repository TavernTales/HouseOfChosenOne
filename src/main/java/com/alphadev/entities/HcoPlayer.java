package com.alphadev.entities;

import com.alphadev.api.annotation.HocoEntity;
import com.alphadev.api.entities.HocoPlayer;
import com.alphadev.enums.PlayerProfileEnum;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;
@HocoEntity("hco_players")
public record HcoPlayer(@BsonId UUID uuid,String playerName,Long houseID,Long timeout,PlayerProfileEnum type,Integer contribution,boolean isExiled) implements HocoPlayer {

    public HcoPlayer incrementContribution(int quantity){
        return new HcoPlayer(uuid(),playerName(),houseID(),timeout(),type(),contribution()+quantity,isExiled());
    }
    public HcoPlayer changeHouse(Long houseID){
        return new HcoPlayer(uuid(),playerName(),houseID,timeout(),type(),contribution(),isExiled());
    }
    public HcoPlayer setTimeout(Long timeout){
        return new HcoPlayer(uuid(),playerName(),houseID(),timeout,type(),contribution(),isExiled());
    }
    public HcoPlayer exilePlayer(){
        return new HcoPlayer(uuid(),playerName(),houseID(),timeout(),type(),contribution(),true);
    }
    @BsonIgnore
    @Override
    public Player getPlayerBukkit() {
        return Bukkit.getPlayer(uuid);
    }
    @BsonIgnore
    @Override
    public OfflinePlayer getOfflinePlayerBukkit() {
        return Bukkit.getOfflinePlayer(uuid);
    }
}