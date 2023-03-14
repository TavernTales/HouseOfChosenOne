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


        ConfigurationSection configurationSection = new ConfigPlayers().getConfiguration(player);
        if(configurationSection != null && configurationSection.getString("house") != null) {
            House house = new House( HouseOfChosenOne.getConfigFile(),configurationSection.getString("house"));

            switch (Objects.requireNonNull(configurationSection.getString("house"))){
                case "zeronia" -> setTeamSettings(zeroniaTeam,player, ChatColor.RED, house);
                case "vlarola" -> setTeamSettings(vlarolaTeam,player, ChatColor.BLUE, house);
                case "frandhra" -> setTeamSettings(frandhraTeam, player, ChatColor.GREEN, house);
                case "nashor" -> setTeamSettings(nashorTeam, player, ChatColor.DARK_RED, house);
                case "drakkaris" -> setTeamSettings(drakkarisTeam, player, ChatColor.DARK_GRAY, house);
                default -> {}
            }
        }
    }

    public static void  removePlayerFromHouseTeam(Player player){
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

        player.setPlayerListName(player.getName());
        player.setCustomName(player.getName());
        player.setCustomNameVisible(true);
    }

    private static void setTeamSettings(Team team, Player player, ChatColor houseColor, House house){
        team.addEntry(player.getName());
        player.setPlayerListName(player.getName() + ChatColorUtil.boldText(ChatColorUtil.boldText(" \u25A0 ",houseColor),houseColor));
        player.setCustomName( player.getPlayerListName() + ChatColorUtil.boldText(ChatColorUtil.boldText(" \u25A0 ",houseColor),houseColor));
        player.setCustomNameVisible(true);
    }

}
