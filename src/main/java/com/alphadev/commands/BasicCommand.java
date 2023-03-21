package com.alphadev.commands;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.House;
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
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BasicCommand implements Listener, CommandExecutor, TabCompleter {

    private static HashMap<UUID, Integer> scheduleTaskPlayer = new HashMap<>();
    private final List<String> HOUSES = List.of("zeronia", "vlarola", "frandhra", "nashor", "drakkaris");
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player))
            return false;

        Player player = ((Player) sender).getPlayer();

        if(command.getName().equalsIgnoreCase("houseofchosenone") && args.length > 1 && args[0].equalsIgnoreCase("join")){

            House house = new House( HouseOfChosenOne.getConfigFile(), args[1]);

            if(house.getHouse() == null)
                return  false;

            ConfigurationSection configurationSection = new ConfigPlayers().getConfiguration(player);
            if(configurationSection != null && configurationSection.getString("house") != null){
                player.sendMessage(ChatColorUtil.boldText("Voc\u00EA j\u00E1 est\u00E1 em uma casa",ChatColor.RED));
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
           player.sendMessage(ChatColorUtil.boldText("Parab\u00E9ns voc\u00EA entrou na casa "+ChatColor.RESET+ ChatColor.GREEN+house.getHouse()));

            if(house.getLocation() != null)
                player.teleport(house.getLocation());

           player.sendTitle(ChatColorUtil.boldText(house.getHouse()),"",10,20,10);
           ScoreBoardService.setPlayerHouseScoreBoardTag(player);

           if(HouseOfChosenOne.getPlugin().getServer().getPluginManager().getPlugin("GriefPrevention") == null)
               return  true;

           GriefPrevention griefPrevention =  GriefPrevention.instance;
           if(griefPrevention != null ) {
               ConfigurationSection settingsFile = new ConfigFile().getConfig().getConfigurationSection("settings");

               if(settingsFile != null && settingsFile.getInt("api-grief-prevention-block-bonus") > 0){
                   DataStore dataStore = griefPrevention.dataStore;
                   PlayerData playerData = dataStore.getPlayerData(player.getUniqueId());
                   playerData.setBonusClaimBlocks(playerData.getBonusClaimBlocks() + settingsFile.getInt("api-grief-prevention-block-bonus"));
                   dataStore.savePlayerData(player.getUniqueId(), playerData);
               }
           }


           return true;
        }

        if( command.getName().equalsIgnoreCase("houseofchosenone") && args.length > 0 && (args[0].equalsIgnoreCase("load") || args[0].equalsIgnoreCase("reload"))){

            if(!player.hasPermission("hco.admin")){
                player.sendMessage("Voc\u00EA n\u00E3o tem permiss\u00E3o para usar este comando.");
                return  false;
            }

            HouseOfChosenOne.loadConfigurations();
            player.sendMessage("[HCO] Configura\u00E7\u00F5es recarregadas ...");
            return true;
        }

        if( command.getName().equalsIgnoreCase("houseofchosenone") && args.length > 1 && args[0].equalsIgnoreCase("tag")){

            if(!player.hasPermission("hco.admin")){
                player.sendMessage("Voc\u00EA n\u00E3o tem permiss\u00E3o para usar este comando.");
                return  false;
            }

            if(args[1] == null || args[1].isEmpty() || !HOUSES.contains(args[1])){
                player.sendMessage("Nome da casa inv\u00E1lido");
                return  false;
            }

            if( args.length < 3 || args[2] == null || args[2].isEmpty() || Bukkit.getServer().getOnlinePlayers().stream().filter(pl -> pl.getName().equalsIgnoreCase(args[2])).count() > 0){
                player.sendMessage("Tag inv\u00E1lida");
                return  false;
            }

            new ConfigFile().changeTag(args[2], args[1]);
            HouseOfChosenOne.loadConfigurations();

            player.sendMessage("Tag de "+ args[1] + " alterada para "+ ChatColor.translateAlternateColorCodes('&', args[2]));
            return true;
        }

        if(args.length > 0 && args[0].equalsIgnoreCase("leave")){

            ConfigurationSection configurationSection = new ConfigPlayers().getConfiguration(player);
            if(configurationSection == null || configurationSection.getString("house") == null) {
                return  false;
            }

            HouseOfChosenOne.getPlayerConfig().createResetSection(player);
            House house = new House( HouseOfChosenOne.getConfigFile(),configurationSection.getString("house"));

            ScoreBoardService.removePlayerFromHouseTeam(player,house);

            if(HouseOfChosenOne.getPlugin().getServer().getPluginManager().getPlugin("GriefPrevention") == null)
                return  true;

            GriefPrevention griefPrevention =  GriefPrevention.instance;
            if(griefPrevention != null ) {
                ConfigurationSection settingsFile = new ConfigFile().getConfig().getConfigurationSection("settings");

                if(settingsFile != null && settingsFile.getInt("api-grief-prevention-block-bonus") > 0){
                    DataStore dataStore = griefPrevention.dataStore;
                    PlayerData playerData = dataStore.getPlayerData(player.getUniqueId());
                    playerData.setBonusClaimBlocks(playerData.getBonusClaimBlocks() - settingsFile.getInt("api-grief-prevention-block-bonus"));
                    dataStore.savePlayerData(player.getUniqueId(), playerData);
                }

            }

            return true;
        }


        if (command.getName().equalsIgnoreCase("lobby")){
            PlayerMoveService.removePlayerTracker(player);

            long startTime = System.currentTimeMillis();

          if(scheduleTaskPlayer.get(player.getUniqueId()) != null)
              Bukkit.getScheduler().cancelTask(scheduleTaskPlayer.get(player.getUniqueId()).intValue());

            scheduleTaskPlayer.put(player.getUniqueId(),  Bukkit.getScheduler().scheduleSyncRepeatingTask(HouseOfChosenOne.getPlugin(), ()->{

                long endTime = System.currentTimeMillis();
                long secondsRemaning = Math.round(((startTime+11000) - endTime)/1000);

                player.sendTitle("Retornando ao Lobby","N\u00E3o se mova "+secondsRemaning+" Segundos",5 , 20, 5);

                if(secondsRemaning <= 0 ){
                    ConfigurationSection configurationSection = new ConfigPlayers().getConfiguration(player);
                    if(configurationSection != null && configurationSection.getString("house") != null) {
                        House house = new House(HouseOfChosenOne.getConfigFile(), configurationSection.getString("house"));
                        player.teleport(house.getLocation());
                        player.sendTitle(ChatColorUtil.boldText(house.getHouse()),"",10,20,10);
                    }
                    Bukkit.getScheduler().cancelTask(scheduleTaskPlayer.get(player.getUniqueId()).intValue());
                }

                if(  PlayerMoveService.isPlayerInMovement(player)){
                    player.sendTitle("Teleport Cancelado","",10 , 20, 10);
                    Bukkit.getScheduler().cancelTask(scheduleTaskPlayer.get(player.getUniqueId()).intValue());
                }

            }, 0L,20L));
        }

        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return List.of();

        if(command.getName().equalsIgnoreCase("houseofchosenone") && args.length == 1)
            return List.of("join","leave","load","reload","tag");

        if(command.getName().equalsIgnoreCase("houseofchosenone") && args.length == 2 && (args[0].equalsIgnoreCase("join") || args[0].equalsIgnoreCase("tag")) )
            return HOUSES;


        return List.of();
    }
}
