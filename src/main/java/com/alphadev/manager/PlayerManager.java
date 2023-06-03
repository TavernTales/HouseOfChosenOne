package com.alphadev.manager;

import com.alphadev.Hoco;
import com.alphadev.api.entities.HocoHouse;
import com.alphadev.api.entities.HocoPlayer;
import com.alphadev.api.manager.HocoPlayerManager;
import com.alphadev.database.repositories.HcoPlayerRepository;
import com.alphadev.entities.HcoHouse;
import com.alphadev.entities.HcoPlayer;
import com.alphadev.enums.PlayerProfileEnum;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager implements HocoPlayerManager {
    private final HcoPlayerRepository playerRepository;
    private final HashMap<Player,HcoPlayer> onlinePlayersMap;
    private final ConcurrentHashMap<OfflinePlayer,HcoPlayer> allPlayers;
    private final Hoco hoco;
    public PlayerManager(Hoco hoco) {
        this.hoco = hoco;
        this.onlinePlayersMap = new HashMap<>();
        this.allPlayers = new ConcurrentHashMap<>();
        this.playerRepository = new HcoPlayerRepository();
        this.initialize();
    }
    public void initialize(){
        // Inicia todos os HcoPlayers que jogam no server.
        Arrays.stream(Bukkit.getOfflinePlayers()).forEach(p -> {
            HcoPlayer hcoPlayer = playerRepository.getById(p.getUniqueId());
            if(hcoPlayer != null) {
                allPlayers.put(p, hcoPlayer);
            }
        });
    }
    public void saveOnlinePlayer(Player p ){
        // Verifica se já tem o player no map
        if(onlinePlayersMap.containsKey(p))return;
        // Recupera do banco de dados e salva em cache
        HcoPlayer hcoPlayer = playerRepository.getById(p.getUniqueId());
        onlinePlayersMap.put(p,hcoPlayer);
    }
    public void removeOnlinePlayer(Player p ){
        // Remove o player do cache
        onlinePlayersMap.remove(p);
    }
    public void saveNewPlayerDB(Player p){
        // Cria o objeto HcoPlayer "padrão"
        HcoPlayer newPlayer = new HcoPlayer(p.getUniqueId(),p.getDisplayName(),null,0L, PlayerProfileEnum.CIVIL,0,true);
        // Salva no banco o no map.
        playerRepository.save(newPlayer);
        onlinePlayersMap.put(p,newPlayer);
    }
    public void incrementPlayerContribution(Player p, int contribution){
        // Recupera o HcoPlayer do map e salva novamente.
        HcoPlayer hcoPlayer = onlinePlayersMap.get(p);
        hcoPlayer.incrementContribution(contribution);
        onlinePlayersMap.put(p,hcoPlayer);
        //Atualiza o dado no banco de dados
        playerRepository.updatePlayerContribution(hcoPlayer.uuid(),contribution);
    }
    @Override
    public HcoPlayer getHocoPlayerFromUUID(UUID uuid) {
        // Busca o player direto do banco de dados
        return playerRepository.getById(uuid);
    }
    @Override
    public boolean playerIsExiled(UUID uuid) {
        HcoPlayer player = playerRepository.getById(uuid);
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
    public ArrayList<HcoPlayer> getAllPlayersFromDB() {
        return (ArrayList<HcoPlayer>) playerRepository.getAll();
    }

    @Override
    public ArrayList<HcoPlayer> getAllPlayersHouse(HocoHouse house) {
        List<HcoHouse> hcoPlayers = new ArrayList<>();
        return null;
    }

    @Override
    public ArrayList<? extends HocoPlayer> getAllPlayersHouse(String name) {
        return null;
    }

    @Override
    public ArrayList<? extends HocoPlayer> getAllPlayersHouse(Long id) {
        return null;
    }

    public HashMap<Player, HcoPlayer> getOnlinePlayersMap() {
        return onlinePlayersMap;
    }

    public ConcurrentHashMap<OfflinePlayer, HcoPlayer> getAllPlayers() {
        return allPlayers;
    }
}
