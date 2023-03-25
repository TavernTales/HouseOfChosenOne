package com.alphadev.utils.config;

import com.alphadev.HouseOfChosenOne;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ConfigQuests {
    private final Plugin plugin = HouseOfChosenOne.getPlugin();
    private final File questsSettingsFile = new File(plugin.getDataFolder(), "quests/settings.yml");
    private final File questsFile = new File(plugin.getDataFolder(), "quests/quests.yml");
    private FileConfiguration questsConfigurationSettings = new YamlConfiguration();
    private FileConfiguration questsConfiguration = new YamlConfiguration();
    public ConfigQuests() {
        createConnectionConfig();
    }

    private void createConnectionConfig() {
        try {
            if (!questsSettingsFile.exists()) {
                HouseOfChosenOne.logInfo("[HouseOfChosenOne] Quests config file . . .");
                questsConfigurationSettings.save(questsSettingsFile);
                HouseOfChosenOne.logInfo("[HouseOfChosenOne] Quests config file: 100%");
            }
            questsConfigurationSettings = YamlConfiguration.loadConfiguration(questsSettingsFile);

            if (!questsFile.exists()) {
                HouseOfChosenOne.logInfo("[HouseOfChosenOne] Quests config file . . .");
                questsConfiguration.save(questsFile);
                HouseOfChosenOne.logInfo("[HouseOfChosenOne] Quests config file: 100%");
            }
            questsConfiguration = YamlConfiguration.loadConfiguration(questsFile);

            createConfigurationSettings();
            createConfigurationSettingsQuestTiers();
            createConfigurationSettingsQuestType();

        } catch (Exception e) {
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Quests file configuration error:\n" + e.getMessage(), e);
        }
    }

    private  void  createConfigurationSettings(){
        questsConfigurationSettings = YamlConfiguration.loadConfiguration(questsSettingsFile);
        ConfigurationSection configurationSection = questsConfigurationSettings.getConfigurationSection("settings");


        if(configurationSection == null){
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Creating Quests Settings File Configuration . . .");
            configurationSection = questsConfigurationSettings.createSection("settings");
        }

        if(!configurationSection.contains("queue-time-in-minutes"))
            configurationSection.set("queue-time-in-minutes", 10);


        saveChanges();
    }

    private  void  createConfigurationSettingsQuestTiers(){
        questsConfigurationSettings = YamlConfiguration.loadConfiguration(questsSettingsFile);
        ConfigurationSection configurationSection = questsConfigurationSettings.getConfigurationSection("quest-tiers");


        if(configurationSection == null){
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Creating Quests Settings Quest Tiers File Configuration . . .");
            configurationSection = questsConfigurationSettings.createSection("quest-tiers");
        }

        if(!configurationSection.contains("common-percent"))
            configurationSection.set("common-percent", -1);

        if(!configurationSection.contains("uncommon-percent"))
            configurationSection.set("uncommon-percent", 50);

        if(!configurationSection.contains("rare-percent"))
            configurationSection.set("rare-percent", 20);

        if(!configurationSection.contains("legendary-percent"))
            configurationSection.set("legendary-percent", 5);

        if(!configurationSection.contains("cursed-percent"))
            configurationSection.set("cursed-percent", 15);

        saveChanges();
    }

    private  void  createConfigurationSettingsQuestType(){
        questsConfigurationSettings = YamlConfiguration.loadConfiguration(questsSettingsFile);
        ConfigurationSection configurationSection = questsConfigurationSettings.getConfigurationSection("quest-type");


        if(configurationSection == null){
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Creating Quests Settings Quest Type File Configuration . . .");
            configurationSection = questsConfigurationSettings.createSection("quest-type");
        }

        if(!configurationSection.contains("delivery-percent"))
            configurationSection.set("delivery-percent", 40);

        if(!configurationSection.contains("defeat-percent"))
            configurationSection.set("defeat-percent", 50);

        if(!configurationSection.contains("hunter-percent"))
            configurationSection.set("hunter-percent", 20);

        if(!configurationSection.contains("harvest-percent"))
            configurationSection.set("harvest-percent", 30);

        if(!configurationSection.contains("pvp-percent"))
            configurationSection.set("pvp-percent", 5);

        saveChanges();
    }

    public File getQuestsSettingsFile() {
        return questsSettingsFile;
    }
    public FileConfiguration getQuestsConfigurationSettings() {
        return questsConfigurationSettings;
    }

    public void saveChanges(){
        try {
            questsConfigurationSettings.save(questsSettingsFile);
            questsConfiguration.save(questsFile);
        } catch (Exception e) {
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Error to save File Quests settings:\n" + e.getMessage(),e);
        }
    }
}
