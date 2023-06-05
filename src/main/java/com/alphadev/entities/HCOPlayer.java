package com.alphadev.entities;

import com.alphadev.api.annotation.HOCOEntity;
import com.alphadev.api.entities.IHOCOPlayer;
import com.alphadev.enums.PlayerProfileEnum;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;
@HOCOEntity("hco_players")
public record HCOPlayer(@BsonId UUID uuid, String playerName, Long houseID, Long timeout, PlayerProfileEnum type, Integer contribution, boolean isExiled) implements IHOCOPlayer {

    public HCOPlayer incrementContribution(int quantity){
        return new HCOPlayer(uuid(),playerName(),houseID(),timeout(),type(),contribution()+quantity,isExiled());
    }
    public HCOPlayer changeHouse(Long houseID){
        return new HCOPlayer(uuid(),playerName(),houseID,timeout(),type(),contribution(),isExiled());
    }
    public HCOPlayer setTimeout(Long timeout){
        return new HCOPlayer(uuid(),playerName(),houseID(),timeout,type(),contribution(),isExiled());
    }
    public HCOPlayer exilePlayer(){
        return new HCOPlayer(uuid(),playerName(),houseID(),timeout(),type(),contribution(),true);
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