package com.alphadev;

import com.alphadev.commands.AdminCommand;
import com.alphadev.commands.BasicCommand;
import com.alphadev.events.*;
import com.alphadev.utils.ChatColorUtil;
import com.alphadev.utils.config.ConfigFile;
import com.alphadev.utils.config.ConfigPlayers;
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
        loadConfigurations();

        broadcast(ChatColorUtil.boldText("======================", ChatColor.GOLD));
        broadcast(ChatColorUtil.boldText("[HouseOfChosenOne] ", ChatColor.GREEN));
        broadcast(ChatColorUtil.textColor(" Loaded Version: "+getPlugin().getDescription().getVersion(), ChatColor.GREEN));
        broadcast(ChatColorUtil.textColor(" Author: "+getPlugin().getDescription().getAuthors(), ChatColor.GREEN));
        broadcast(ChatColorUtil.textColor(" Description: \n "+ChatColor.GRAY+getPlugin().getDescription().getDescription(), ChatColor.GREEN));
        broadcast(ChatColorUtil.boldText("======================", ChatColor.GOLD));
    }

    @Override
    public void onEnable() {
        super.onEnable();

        pluginManager.registerEvents(new SignChangeEventListener(),this);
        pluginManager.registerEvents(new PlayerInteractEventListener(), this);
        pluginManager.registerEvents(new PlayerRespawnEventListener(), this);
        pluginManager.registerEvents(new PlayerJoinEventListener(), this);
        pluginManager.registerEvents(new PlayerMoveEventListener(), this);

        Objects.requireNonNull(getCommand("houseofchosenone")).setExecutor(new BasicCommand());
        Objects.requireNonNull(getCommand("lobby")).setExecutor(new BasicCommand());
        Objects.requireNonNull(getCommand("citadel")).setExecutor(new AdminCommand());
    }

    @Override
    public void onDisable() {
        super.onDisable();

        HandlerList.unregisterAll();
    }

    private void loadConfigurations(){
        configFile = new ConfigFile();
        configPlayers = new ConfigPlayers();
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
        return  new ConfigFile().getConfig();
    }

    public  static void  createHouseLocation(String houseName, Location location){
        configFile.createLocationSection(houseName, location);
    }

    public static ConfigPlayers getPlayerConfig(){return  configPlayers;}

}
