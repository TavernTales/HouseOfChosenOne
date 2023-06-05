package com.alphadev.manager;

import com.alphadev.api.entities.IHOCOHouse;
import com.alphadev.api.entities.IHOCOPlayer;
import com.alphadev.api.manager.IHOCOPlayerManager;
import com.alphadev.database.repositories.PlayerRepository;
import com.alphadev.entities.HCOHouse;
import com.alphadev.entities.HCOPlayer;
import com.alphadev.enums.PlayerProfileEnum;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager implements IHOCOPlayerManager {
    private final PlayerRepository playerRepository;
    private final HashMap<Player, HCOPlayer> onlinePlayersMap;
    private final ConcurrentHashMap<OfflinePlayer, HCOPlayer> allPlayers;
    public PlayerManager() {
        this.onlinePlayersMap = new HashMap<>();
        this.allPlayers = new ConcurrentHashMap<>();
        this.playerRepository = new PlayerRepository();
        this.initialize();
    }
    public void initialize(){
        // Inicia todos os HcoPlayers que jogam no server.
        Arrays.stream(Bukkit.getOfflinePlayers()).forEach(p -> {
            HCOPlayer hcoPlayer = playerRepository.getById(p.getUniqueId());
            if(hcoPlayer != null) {
                allPlayers.put(p, hcoPlayer);
            }
        });
    }
    public void saveOnlinePlayer(Player p ){
        // Verifica se já tem o player no map
        if(onlinePlayersMap.containsKey(p))return;
        // Recupera do banco de dados e salva em cache
        HCOPlayer hcoPlayer = playerRepository.getById(p.getUniqueId());
        onlinePlayersMap.put(p,hcoPlayer);
    }
    public void removeOnlinePlayer(Player p ){
        // Remove o player do cache
        onlinePlayersMap.remove(p);
    }
    public void saveNewPlayerDB(Player p){
        // Cria o objeto HcoPlayer "padrão"
        HCOPlayer newPlayer = new HCOPlayer(p.getUniqueId(),p.getDisplayName(),null,0L, PlayerProfileEnum.CIVIL,0,true);
        // Salva no banco o no map.
        playerRepository.save(newPlayer);
        onlinePlayersMap.put(p,newPlayer);
    }
    public void incrementPlayerContribution(Player p, int contribution){
        // Recupera o HcoPlayer do map e salva novamente.
        HCOPlayer hcoPlayer = onlinePlayersMap.get(p);
        hcoPlayer.incrementContribution(contribution);
        onlinePlayersMap.put(p,hcoPlayer);
        //Atualiza o dado no banco de dados
        playerRepository.updatePlayerContribution(hcoPlayer.uuid(),contribution);
    }
    @Override
    public HCOPlayer getHocoPlayerFromUUID(UUID uuid) {
        // Busca o player direto do banco de dados
        return playerRepository.getById(uuid);
    }
    @Override
    public boolean playerIsExiled(UUID uuid) {
        HCOPlayer player = playerRepository.getById(uuid);
        if (player !=null){
            return player.isExiled();
        }
        return true;
    }

    @Override
    public boolean playerIsExiled(Player player) {
        return onlinePlayersMap.get(player).isExiled();
    }

    @Override
    public HashSet<OfflinePlayer> getAllPlayer() {
        return new HashSet<>(allPlayers.keySet());
    }

    @Override
    public ArrayList<HCOPlayer> getAllPlayersFromDB() {
        return (ArrayList<HCOPlayer>) playerRepository.getAll();
    }

    @Override
    public ArrayList<HCOPlayer> getAllPlayersHouse(IHOCOHouse house) {
        List<HCOHouse> hcoPlayers = new ArrayList<>();
        return null;
    }

    @Override
    public ArrayList<? extends IHOCOPlayer> getAllPlayersHouse(String name) {
        return null;
    }

    @Override
    public ArrayList<? extends IHOCOPlayer> getAllPlayersHouse(Long id) {
        return null;
    }

    public HashMap<Player, HCOPlayer> getOnlinePlayersMap() {
        return onlinePlayersMap;
    }

    public ConcurrentHashMap<OfflinePlayer, HCOPlayer> getAllPlayers() {
        return allPlayers;
    }
}
