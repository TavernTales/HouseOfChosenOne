package com.alphadev;

import com.alphadev.commands.AdminCommand;
import com.alphadev.commands.BasicCommand;
import com.alphadev.events.*;
import com.alphadev.schedules.QuestSchedulesService;
import com.alphadev.services.ScoreBoardService;
import com.alphadev.utils.ChatColorUtil;
import com.alphadev.utils.config.ConfigFile;
import com.alphadev.utils.config.ConfigPlayers;
import com.alphadev.utils.config.ConfigQuests;
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

        configFile = new ConfigFile();
        configPlayers = new ConfigPlayers();
        configQuests = new ConfigQuests();

        MongoProvider.getInstance();

    }

    @Override
    public void onEnable() {
        super.onEnable();

        pluginManager.registerEvents(new SignChangeEventListener(),this);
        pluginManager.registerEvents(new PlayerInteractEventListener(), this);
        pluginManager.registerEvents(new PlayerRespawnEventListener(), this);
        pluginManager.registerEvents(new PlayerJoinEventListener(), this);
        pluginManager.registerEvents(new PlayerMoveEventListener(), this);
        pluginManager.registerEvents(new InventoryInteractEventListener(), this);
        pluginManager.registerEvents(new PlayerKillEntityEventListener(), this);
        pluginManager.registerEvents(new PlayerBreakEventListener(), this);


        Objects.requireNonNull(getCommand("houseofchosenone")).setExecutor(new BasicCommand());
        Objects.requireNonNull(getCommand("lobby")).setExecutor(new BasicCommand());
        Objects.requireNonNull(getCommand("citadel")).setExecutor(new AdminCommand());
        Objects.requireNonNull(getCommand("quest")).setExecutor(new AdminCommand());

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
