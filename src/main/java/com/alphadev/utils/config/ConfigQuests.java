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

public class ConfigQuests {
    private final Plugin plugin = HouseOfChosenOne.getPlugin();
    private final File questsSettingsFile = new File(plugin.getDataFolder(), "quests/settings.yml");
    private final File questsFile = new File(plugin.getDataFolder(), "quests/quests.yml");
    private FileConfiguration questsConfiguration = new YamlConfiguration();

    public ConfigQuests() {
        createConnectionConfig();
    }

    private void createConnectionConfig() {
        try {
            if (!questsSettingsFile.exists()) {
                HouseOfChosenOne.logInfo("[HouseOfChosenOne] Quests config file . . .");
                questsConfiguration.save(questsSettingsFile);
                HouseOfChosenOne.logInfo("[HouseOfChosenOne] Quests config file: 100%");
            }
            questsConfiguration = YamlConfiguration.loadConfiguration(questsSettingsFile);
        } catch (Exception e) {
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Quests file configuration error:\n" + e.getMessage(), e);
        }
    }

    public File getFile() {
        return questsSettingsFile;
    }

    public FileConfiguration getConfig() {
        return questsConfiguration;
    }

    public void saveChanges(){
        try {
            questsConfiguration.save(questsSettingsFile);
        } catch (Exception e) {
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Error to save File Quests settings:\n" + e.getMessage(),e);
        }
    }


    public void createPlayersSection(Player player, String house, List<String> permissions) {
        questsConfiguration = YamlConfiguration.loadConfiguration(questsSettingsFile);
        ConfigurationSection configSection = questsConfiguration.createSection("players");

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
            questsConfiguration.save(questsSettingsFile);
        } catch (IOException e) {
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Error to save File Players: \n"+ e.getMessage(), e);
        }
    }
    public void createResetSection(Player player){
        questsConfiguration = YamlConfiguration.loadConfiguration(questsSettingsFile);
        ConfigurationSection configurationSection = questsConfiguration.getConfigurationSection("players");

        if(configurationSection == null)
            return;

        configurationSection.createSection(player.getUniqueId().toString());
        ConfigurationSection configurationPlayer = getConfiguration(player);
        configurationPlayer.set("playerName", player.getName());
        configurationPlayer.set("reset", System.currentTimeMillis()+ 2L * 24 * 60 * 60 * 1000);

        player.sendMessage(ChatColorUtil.boldText("Voc\u00EA deixou sua casa "));

        try {
            questsConfiguration.save(questsSettingsFile);
        } catch (IOException e) {
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Error to save File Players: \n"+ e.getMessage(), e);
        }
    }


    public ConfigurationSection getConfiguration(Player player){
        return (ConfigurationSection) questsConfiguration.get("players."+player.getUniqueId());
    }
}
