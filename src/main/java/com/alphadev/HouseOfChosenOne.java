package com.alphadev;

import com.alphadev.commands.AdminCommandHandler;
import com.alphadev.commands.BasicCommandHandler;
import com.alphadev.events.*;
import com.alphadev.listeners.EntityTypeQuestListener;
import com.alphadev.listeners.QuestCoreListener;
import com.alphadev.services.ScoreBoardService;
import com.alphadev.utils.ChatColorUtil;
import com.alphadev.utils.HelpUtils;
import com.alphadev.utils.config.ConfigFile;
import com.alphadev.utils.config.ConfigPlayers;

import com.alphadev.utils.config.mongo.HouseConfigurationBuilder;
import com.alphadev.utils.connection.MongoProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class HouseOfChosenOne extends JavaPlugin {


    private final PluginManager pluginManager = this.getServer().getPluginManager();
    private static ConfigFile configFile;
    private static ConfigPlayers configPlayers;

    @Override
    public void onLoad() {
        super.onLoad();

        broadcast(ChatColorUtil.boldText("======================", ChatColor.GOLD));
        broadcast(ChatColorUtil.boldText("[HouseOfChosenOne] ", ChatColor.GREEN));
        broadcast(ChatColorUtil.textColor(" Loaded Version: "+getPlugin().getDescription().getVersion(), ChatColor.GREEN));
        broadcast(ChatColorUtil.textColor(" Author: "+getPlugin().getDescription().getAuthors(), ChatColor.GREEN));
        broadcast(ChatColorUtil.textColor(" Description: \n "+ChatColor.GRAY+getPlugin().getDescription().getDescription(), ChatColor.GREEN));
        broadcast(ChatColorUtil.boldText("======================", ChatColor.GOLD));

        loadConfigs();

        MongoProvider.getInstance();

    }

    @Override
    public void onEnable() {
        super.onEnable();
        pluginManager.registerEvents(new EntityTypeQuestListener(),this);
        pluginManager.registerEvents(new HousesEventHandler(),this);
        pluginManager.registerEvents(new QuestEventHandler(), this);
        pluginManager.registerEvents(new PlayerEventHandler(), this);
        pluginManager.registerEvents(new QuestCoreListener(), this);

        getCommand("questTest").setExecutor(new BasicCommandHandler());
        Objects.requireNonNull(getCommand("houseofchosenone")).setExecutor(new BasicCommandHandler());
        Objects.requireNonNull(getCommand("lobby")).setExecutor(new BasicCommandHandler());
        Objects.requireNonNull(getCommand("citadel")).setExecutor(new AdminCommandHandler());
        Objects.requireNonNull(getCommand("quest")).setExecutor(new BasicCommandHandler());

        Objects.requireNonNull(getCommand("global")).setExecutor(new BasicCommandHandler());
        Objects.requireNonNull(getCommand("local")).setExecutor(new BasicCommandHandler());
        Objects.requireNonNull(getCommand("whisper")).setExecutor(new BasicCommandHandler());
        Objects.requireNonNull(getCommand("reply")).setExecutor(new BasicCommandHandler());

        Objects.requireNonNull(getCommand("waystone")).setExecutor(new BasicCommandHandler());

        new HouseConfigurationBuilder();
        loadConfigs();
        //new QuestSchedulesService().questScheduleDaily();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Bukkit.getServer().getScheduler().cancelTasks(this);
        HandlerList.unregisterAll();
    }

    public static void loadPlayerStats(){
        loadConfigs();
        reloadPlayerStats();
    }

    private static void loadConfigs(){
        configFile = new ConfigFile();
        configPlayers = new ConfigPlayers();
    }

    public static void reloadPlayerStats(){
        Bukkit.getServer().getOnlinePlayers().forEach(player -> ScoreBoardService.removePlayerFromHouseTeam(player,null));
        loadPlayerStats();
        Bukkit.getScheduler().scheduleSyncDelayedTask(HouseOfChosenOne.getPlugin(), () -> Bukkit.getServer().getOnlinePlayers().forEach(ScoreBoardService::setPlayerHouseScoreBoardTag), 1);
    }

    public static Plugin getPlugin(){
        return getPlugin(HouseOfChosenOne.class);
    }
    public static void logInfo(String msg){
        HouseOfChosenOne.getPlugin().getServer().getLogger().info(msg);
    }
    public static void logInfo(String msg, Exception e){
        HouseOfChosenOne.getPlugin().getServer().getLogger().info(msg);
        e.printStackTrace();
    }

    public static void broadcast(String msg){
        HouseOfChosenOne.getPlugin().getServer().broadcastMessage(msg);
    }

    public static ConfigurationSection getConfigFile() {
        return configFile.getConfig();
    }

    public static ConfigFile configFile(){
        return  configFile;
    }

    public static ConfigPlayers configPlayersFile(){
        return  configPlayers;
    }

    public  static void  createHouseLocation(String houseName, Location location){
        configFile.createLocationSection(houseName, location);
    }
    public static ConfigPlayers getPlayerConfig(){return  configPlayers;}

}
