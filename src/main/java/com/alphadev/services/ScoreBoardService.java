package com.alphadev.services;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.House;
import com.alphadev.utils.ChatColorUtil;
import com.alphadev.utils.config.ConfigPlayers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class ScoreBoardService {

    private static final Scoreboard SCOREBOARD = Bukkit.getScoreboardManager().getMainScoreboard();
    private static Team zeroniaTeam = SCOREBOARD.getTeam("zeronia");
    private static  Team vlarolaTeam = SCOREBOARD.getTeam("vlarola");
    private static  Team frandhraTeam = SCOREBOARD.getTeam("frandhra");
    private static  Team nashorTeam  = SCOREBOARD.getTeam("nashor");
    private static  Team drakkarisTeam = SCOREBOARD.getTeam("drakkaris");


    public static void setPlayerHouseScoreBoardTag(Player player){


        ConfigurationSection configurationSection = new ConfigPlayers().getConfiguration(player);
        if(configurationSection != null && configurationSection.getString("house") != null) {
            House house = new House( HouseOfChosenOne.getConfigFile(),configurationSection.getString("house"));

            removePlayerFromHouseTeam(player,house);

            switch (Objects.requireNonNull(configurationSection.getString("house"))){
                case "zeronia" -> setTeamSettings(zeroniaTeam,player, house);
                case "vlarola" -> setTeamSettings(vlarolaTeam,player, house);
                case "frandhra" -> setTeamSettings(frandhraTeam, player,  house);
                case "nashor" -> setTeamSettings(nashorTeam, player, house);
                case "drakkaris" -> setTeamSettings(drakkarisTeam, player, house);
                default -> {}
            }
        }
    }

    public static void  removePlayerFromHouseTeam(Player player, House house){
        if(zeroniaTeam == null)
            zeroniaTeam = SCOREBOARD.registerNewTeam("zeronia");

        if(vlarolaTeam == null)
            vlarolaTeam = SCOREBOARD.registerNewTeam("vlarola");

        if( frandhraTeam == null)
            frandhraTeam = SCOREBOARD.registerNewTeam("frandhra");

        if(nashorTeam == null)
            nashorTeam = SCOREBOARD.registerNewTeam("nashor");

        if( drakkarisTeam == null)
            drakkarisTeam = SCOREBOARD.registerNewTeam("drakkaris");

        zeroniaTeam.removeEntry(player.getName());
        vlarolaTeam.removeEntry(player.getName());
        frandhraTeam.removeEntry(player.getName());
        nashorTeam.removeEntry(player.getName());
        drakkarisTeam.removeEntry(player.getName());

        if(player.getPlayerListName().contains(ChatColor.translateAlternateColorCodes('&',house.getTag()))){
            player.setPlayerListName(player.getPlayerListName().replace(ChatColor.translateAlternateColorCodes('&',house.getTag()),""));
        }

    }

    private static void setTeamSettings(Team team, Player player, House house){
        team.addEntry(player.getName());
        player.setPlayerListName(player.getPlayerListName() +" "+ ChatColor.translateAlternateColorCodes('&',house.getTag()));
    }

}
