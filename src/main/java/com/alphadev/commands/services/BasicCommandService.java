package com.alphadev.commands.services;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.House;
import com.alphadev.events.quest.QuestStartEvent;
import com.alphadev.services.PlayerMoveService;
import com.alphadev.services.ScoreBoardService;
import com.alphadev.utils.ChatColorUtil;
import com.alphadev.utils.config.ConfigFile;
import com.alphadev.utils.config.ConfigPlayers;
import me.ryanhamshire.GriefPrevention.DataStore;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class BasicCommandService {


    private BasicCommandService(){}
    private static final HashMap<UUID, Integer> scheduleTaskPlayer = new HashMap<>();
    private static long ELEVEN_SECONDS_IN_MILLIS = 11000;
    private static long MILLIS_TO_SECONDS = 1000;

    public static boolean playerJoinInHouseCommand(Player player, Command command, String[] args){


        if(!command.getName().equalsIgnoreCase("houseofchosenone") || args.length  <= 0  || !args[0].equalsIgnoreCase("join")){
            return false;
        }

        House house = new House( HouseOfChosenOne.getConfigFile(), args[1]);

        if(house.getName() == null)
            return  false;

        ConfigurationSection configurationSection = new ConfigPlayers().getConfiguration(player);
        if(configurationSection != null && configurationSection.getString("house") != null){
            player.sendMessage(ChatColorUtil.boldText("Voc\u00EA j\u00E1 est\u00E1 em uma casa", ChatColor.RED));
            player.sendMessage("Execute o comando '/hco leave' para sair da sua casa atual. mas voc\u00EA perder\u00E1 todo o seu progresso e sofrer\u00E1 uma penalidade de 48Horas para entrar na pr\u00F3xima casa.");
            return false;
        }

        if(configurationSection != null && configurationSection.getString("reset") != null){
            long resetTimeMillis =  configurationSection.getLong("reset");
            long currentTimeMillis = System.currentTimeMillis();

            if(resetTimeMillis > currentTimeMillis ){
                player.sendMessage(ChatColorUtil.boldText("Voc\u00EA ainda n\u00E3o pode entrar em uma nova casa",ChatColor.RED));
                player.sendMessage("Tempo para entrar em uma casa nova \u00E9 de: "+ (resetTimeMillis - currentTimeMillis)  / 60 / 60 / 1000 + " horas" );
                return false;
            }
        }

        HouseOfChosenOne.getPlayerConfig().createPlayersSection(player,args[1],house.getPermissions());
        player.sendMessage(ChatColorUtil.boldText("Parab\u00E9ns voc\u00EA entrou na casa "+ChatColor.RESET+ ChatColor.GREEN+house.getName()));

        if(house.getLocation() != null)
            player.teleport(house.getLocation());

        player.sendTitle(ChatColorUtil.boldText(house.getName()),"",10,20,10);
        ScoreBoardService.setPlayerHouseScoreBoardTag(player);

        griefPreventionIntegrationSetClaimMBlockBonus(player, false);

        return true;
    }


    public static boolean playerTeleportToHouseLobby(Player player, Command command){

        if (!command.getName().equalsIgnoreCase("lobby"))
            return  false;

        PlayerMoveService.removePlayerTracker(player);

        final long startTime = System.currentTimeMillis();

        if(scheduleTaskPlayer.get(player.getUniqueId()) != null)
            Bukkit.getScheduler().cancelTask(scheduleTaskPlayer.get(player.getUniqueId()));

        scheduleTaskPlayer.put(player.getUniqueId(),  Bukkit.getScheduler().scheduleSyncRepeatingTask(HouseOfChosenOne.getPlugin(), ()->{

            long endTime = System.currentTimeMillis();
            long secondsRemaning = ((startTime + ELEVEN_SECONDS_IN_MILLIS  ) - endTime) / MILLIS_TO_SECONDS;

            player.sendTitle("Retornando ao Lobby","N\u00E3o se mova "+secondsRemaning+" Segundos",5 , 20, 5);

            if(secondsRemaning <= 0 ){
                ConfigurationSection configurationSection = new ConfigPlayers().getConfiguration(player);
                if(configurationSection != null && configurationSection.getString("house") != null) {
                    House house = new House(HouseOfChosenOne.getConfigFile(), configurationSection.getString("house"));
                    player.teleport(house.getLocation());
                    player.sendTitle(ChatColorUtil.boldText(house.getName()),"",10,20,10);
                }
                Bukkit.getScheduler().cancelTask(scheduleTaskPlayer.get(player.getUniqueId()));
            }

            if(  PlayerMoveService.isPlayerInMovement(player)){
                player.sendTitle("Teleport Cancelado","",10 , 20, 10);
                Bukkit.getScheduler().cancelTask(scheduleTaskPlayer.get(player.getUniqueId()));
            }

        }, 0L,20L));

        return true;
    }


    public static boolean playerLeaveHouse(Player player, Command command, String[] args){

        if(!command.getName().equalsIgnoreCase("houseofchosenone") || args == null || args.length <= 0 || !args[0].equalsIgnoreCase("leave"))
            return  false;

        ConfigurationSection configurationSection = new ConfigPlayers().getConfiguration(player);
        if(configurationSection == null || configurationSection.getString("house") == null) {
            return  false;
        }

        HouseOfChosenOne.getPlayerConfig().createResetSection(player);
        House house = new House( HouseOfChosenOne.getConfigFile(),configurationSection.getString("house"));

        ScoreBoardService.removePlayerFromHouseTeam(player,house);

        griefPreventionIntegrationSetClaimMBlockBonus(player, true);

        return true;
    }


    private static void griefPreventionIntegrationSetClaimMBlockBonus(Player player, boolean remove){
        if(HouseOfChosenOne.getPlugin().getServer().getPluginManager().getPlugin("GriefPrevention") == null)
            return;

        GriefPrevention griefPrevention =  GriefPrevention.instance;

        if(griefPrevention != null ) {
            ConfigurationSection settingsFile = new ConfigFile().getConfig().getConfigurationSection("settings");

            if(settingsFile != null && settingsFile.getInt("api-grief-prevention-block-bonus") > 0){
                DataStore dataStore = griefPrevention.dataStore;
                PlayerData playerData = dataStore.getPlayerData(player.getUniqueId());

                if(!remove)
                    playerData.setBonusClaimBlocks(playerData.getBonusClaimBlocks() + settingsFile.getInt("api-grief-prevention-block-bonus"));
                else
                    playerData.setBonusClaimBlocks(playerData.getBonusClaimBlocks() - settingsFile.getInt("api-grief-prevention-block-bonus"));

                dataStore.savePlayerData(player.getUniqueId(), playerData);
            }
        }
    }
    public static boolean questTest(Player sender, String... args){
        if(!args[0].equalsIgnoreCase("start"))return false;
        Bukkit.getScheduler().runTaskAsynchronously(HouseOfChosenOne.getPlugin(), () -> Bukkit.getPluginManager().callEvent(new QuestStartEvent(sender)));
        return true;
    }

}
