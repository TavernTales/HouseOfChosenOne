package com.alphadev;

import com.alphadev.commands.AdminCommandHandler;
import com.alphadev.commands.BasicCommandHandler;
import com.alphadev.events.*;
import com.alphadev.schedules.QuestSchedulesService;
import com.alphadev.services.ScoreBoardService;
import com.alphadev.utils.ChatColorUtil;
import com.alphadev.utils.config.ConfigFile;
import com.alphadev.utils.config.ConfigPlayers;
import com.alphadev.utils.config.ConfigQuests;

import com.alphadev.utils.connection.MongoProvider;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
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
    private static ConfigQuests configQuests;

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
//
//        MongoClient mongoClient = MongoClients.create("mongodb+srv://hoc_db:123456!@hoc.oxosawo.mongodb.net/test");
//        MongoDatabase database = mongoClient.getDatabase("localMongoDB");
//
//        database.createCollection("customers");

        MongoProvider.getInstance();

    }

    @Override
    public void onEnable() {
        super.onEnable();

        pluginManager.registerEvents(new HousesEventHandler(),this);
        pluginManager.registerEvents(new QuestEventHandler(), this);
        pluginManager.registerEvents(new PlayerEventHandler(), this);

        Objects.requireNonNull(getCommand("houseofchosenone")).setExecutor(new BasicCommandHandler());
        Objects.requireNonNull(getCommand("lobby")).setExecutor(new BasicCommandHandler());
        Objects.requireNonNull(getCommand("citadel")).setExecutor(new AdminCommandHandler());
        Objects.requireNonNull(getCommand("quest")).setExecutor(new AdminCommandHandler());

        loadConfigs();
        new QuestSchedulesService().questScheduleDaily();
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
        configQuests = new ConfigQuests();
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
