package com.alphadev.utils.config;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.utils.ChatColorUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigPlayers {
    private final Plugin plugin = HouseOfChosenOne.getPlugin();
    private final File playersFile = new File(plugin.getDataFolder(), "players/players.yml");
    private FileConfiguration playersConfiguration = new YamlConfiguration();
    private static ConfigPlayers instance;
    public ConfigPlayers() {
        createConnectionConfig();
    }

    private void createConnectionConfig() {
        try {
            if (!playersFile.exists()) {
                HouseOfChosenOne.logInfo("[HouseOfChosenOne] Players config file . . .");
                playersConfiguration.save(playersFile);
                HouseOfChosenOne.logInfo("[HouseOfChosenOne] Players config file: 100%");
            }
            playersConfiguration = YamlConfiguration.loadConfiguration(playersFile);
        } catch (Exception e) {
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Players file configuration error:\n" + e.getMessage(), e);
        }
    }

    public File getFile() {
        return playersFile;
    }

    public FileConfiguration getConfig() {
        return playersConfiguration;
    }

    public void saveChanges(){
        try {
            playersConfiguration.save(playersFile);
        } catch (Exception e) {
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Error to save File Players DB:\n" + e.getMessage(),e);
        }
    }


    public void createPlayersSection(Player player, String house, List<String> permissions) {
        playersConfiguration = YamlConfiguration.loadConfiguration(playersFile);

        ConfigurationSection configSection = playersConfiguration.getConfigurationSection("players");

        if(configSection == null)
            configSection = playersConfiguration.createSection("players");

        if(configSection.getConfigurationSection(player.getUniqueId().toString()) == null)
            configSection.createSection(player.getUniqueId().toString());

        ConfigurationSection playerSection = configSection.getConfigurationSection(player.getUniqueId().toString());

       if(playerSection == null){
           HouseOfChosenOne.logInfo("[HouseOfChosenOne] Error to save File Players: 'Section Not Found'");
           return;
       }

        if(!playerSection.contains("playerName"))
            playerSection.set("playerName",player.getName());

        if (!playerSection.contains("house"))
            playerSection.set("house", house);

        if (!playerSection.contains("permissions"))
            playerSection.set("permissions", permissions);

        if (!playerSection.contains("contribuitions"))
            playerSection.set("contribuitions", 0);

        try {
            playersConfiguration.save(playersFile);
        } catch (IOException e) {
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Error to save File Players: \n"+ e.getMessage(), e);
        }
    }
    public void createResetSection(Player player){
        playersConfiguration = YamlConfiguration.loadConfiguration(playersFile);
        ConfigurationSection configurationSection = playersConfiguration.getConfigurationSection("players");

        if(configurationSection == null)
            return;

        configurationSection.createSection(player.getUniqueId().toString());
        ConfigurationSection configurationPlayer = getConfiguration(player);
        configurationPlayer.set("playerName", player.getName());
        configurationPlayer.set("reset", System.currentTimeMillis()+ 2L * 24 * 60 * 60 * 1000);

        player.sendMessage(ChatColorUtil.boldText("Voc\u00EA deixou sua casa "));

        try {
            playersConfiguration.save(playersFile);
        } catch (IOException e) {
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Error to save File Players: \n"+ e.getMessage(), e);
        }
    }
    public void addContribuition(Player player, int amount){

        ConfigurationSection configurationPlayer = getConfiguration(player);
        configurationPlayer.set("contribuitions",configurationPlayer.getInt("contribuitions") + amount);

        try {
            playersConfiguration.save(playersFile);
        } catch (IOException e) {
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Error to save File Players: \n"+ e.getMessage(), e);
        }
    }

    public static ConfigPlayers getInstance() {
        if(instance == null)
            instance = new ConfigPlayers();
        return instance;
    }

    public ConfigurationSection getConfiguration(Player player){
        return (ConfigurationSection) playersConfiguration.get("players."+player.getUniqueId());
    }
}
