package com.alphadev.utils.config;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.utils.HelpUtils;
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
    private final File settingsFile = new File(plugin.getDataFolder(), "quests/settings.yml");
    private FileConfiguration configurationSettings = new YamlConfiguration();
    private final File questSettingFile = new File(plugin.getDataFolder(), "quests/quests-settings.yml");
    private FileConfiguration questsConfiguration = new YamlConfiguration();

    private final File playerQuestFile = new File(plugin.getDataFolder(), "quests/players/tracker.yml");
    private FileConfiguration configurationPlayerQuestFile = new YamlConfiguration();

    public ConfigQuests() {
        createConnectionConfig();
    }

    private void createConnectionConfig() {
        try {
            if (!settingsFile.exists()) {
                HouseOfChosenOne.logInfo("[HouseOfChosenOne] Quests config file . . .");
                configurationSettings.save(settingsFile);
                HouseOfChosenOne.logInfo("[HouseOfChosenOne] Quests config file: 100%");
            }
            configurationSettings = YamlConfiguration.loadConfiguration(settingsFile);

            if (!questSettingFile.exists()) {
                HouseOfChosenOne.logInfo("[HouseOfChosenOne] Quests config file . . .");
                questsConfiguration.save(questSettingFile);
                HouseOfChosenOne.logInfo("[HouseOfChosenOne] Quests config file: 100%");
            }
            questsConfiguration = YamlConfiguration.loadConfiguration(questSettingFile);

            if(!playerQuestFile.exists()){
                HouseOfChosenOne.logInfo("[HouseOfChosenOne] Player Quests Tracker config file . . .");
                configurationPlayerQuestFile.save(playerQuestFile);
                HouseOfChosenOne.logInfo("[HouseOfChosenOne] Player Quests Tracker config file: 100%");
            }
            configurationPlayerQuestFile = YamlConfiguration.loadConfiguration(playerQuestFile);

            createConfigurationSettings();
            createConfigurationSettingsQuestTiers();
            createConfigurationSettingsQuestType();

            createQuestSettings();
        } catch (Exception e) {
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Quests file configuration error:\n" + e.getMessage(), e);
        }
    }

    private void createQuestSettings(){
        questsConfiguration = YamlConfiguration.loadConfiguration(questSettingFile);
        ConfigurationSection configurationSection = questsConfiguration.getConfigurationSection("settings-tier");

        if(configurationSection == null){
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Creating Quests Settings File Configuration . . .");
            configurationSection = questsConfiguration.createSection("settings-tier");
        }

        ConfigurationSection commonSectionTier = configurationSection.getConfigurationSection("common");
        ConfigurationSection uncommonSectionTier = configurationSection.getConfigurationSection("uncommon");
        ConfigurationSection rareSectionTier = configurationSection.getConfigurationSection("rare");
        ConfigurationSection legendarySectionTier = configurationSection.getConfigurationSection("legendary");

        if(commonSectionTier == null)
            commonSectionTier = configurationSection.createSection("common");

        setupCommonTier(commonSectionTier);

        if(uncommonSectionTier == null)
            uncommonSectionTier = configurationSection.createSection("uncommon");

        setupUncommonTier(uncommonSectionTier);

        if(rareSectionTier == null)
            rareSectionTier = configurationSection.createSection("rare");

        setupRareTier(rareSectionTier);

        if(legendarySectionTier == null)
            legendarySectionTier = configurationSection.createSection("legendary");

        setupLegendaryTier(legendarySectionTier);

        saveChanges();
    }


    private void setupCommonTier(ConfigurationSection commonSectionTier){

        if(!commonSectionTier.contains("min-progress"))
            commonSectionTier.set("min-progress", 30);

        if(!commonSectionTier.contains("aditional-rate-progress"))
            commonSectionTier.set("aditional-rate-progress", 20);

        if(!commonSectionTier.contains("min-points-reward"))
            commonSectionTier.set("min-points-reward", 50);

        if(!commonSectionTier.contains("aditional-rate-chance-reward"))
            commonSectionTier.set("aditional-rate-chance-reward", 100);

        if(HelpUtils.isNullOrEmpty(commonSectionTier.getList("hunter")))
            commonSectionTier.set("hunter", List.of("cow","pig","sheep","chicken","cod"));

        if(HelpUtils.isNullOrEmpty(commonSectionTier.getList("haverst")))
            commonSectionTier.set("haverst", List.of("wheat","carrot","sugar_cane","potatoes","beetroots"));

        if(HelpUtils.isNullOrEmpty(commonSectionTier.getList("delivery")))
            commonSectionTier.set("delivery", List.of());

        if(HelpUtils.isNullOrEmpty(commonSectionTier.getList("defeat")))
            commonSectionTier.set("defeat", List.of("skeleton","creeper","slime","enderman","drowned","husk","phanton","pillager","spider","stray","zombie_villager"));

        if(HelpUtils.isNullOrEmpty(commonSectionTier.getList("pvp")))
            commonSectionTier.set("pvp", List.of());

    }
    
    private void setupUncommonTier(ConfigurationSection uncommonSectionTier){

        if(!uncommonSectionTier.contains("min-progress"))
            uncommonSectionTier.set("min-progress", 20);

        if(!uncommonSectionTier.contains("aditional-rate-progress"))
            uncommonSectionTier.set("aditional-rate-progress", 20);

        if(!uncommonSectionTier.contains("min-points-reward"))
            uncommonSectionTier.set("min-points-reward", 80);

        if(!uncommonSectionTier.contains("aditional-rate-chance-reward"))
            uncommonSectionTier.set("aditional-rate-chance-reward", 100);

        if(HelpUtils.isNullOrEmpty(uncommonSectionTier.getList("hunter")))
            uncommonSectionTier.set("hunter", List.of("goat","rabbit","squid","salom","wolf"));

        if(HelpUtils.isNullOrEmpty(uncommonSectionTier.getList("haverst")))
            uncommonSectionTier.set("haverst", List.of("sweet_berry_bush","melon","pumpkin","cactus","kelp_plant","brown_mushroom","red_mushroom"));

        if(HelpUtils.isNullOrEmpty(uncommonSectionTier.getList("delivery")))
            uncommonSectionTier.set("delivery", List.of());

        if(HelpUtils.isNullOrEmpty(uncommonSectionTier.getList("defeat")))
            uncommonSectionTier.set("defeat", List.of("blaze","magma_cube","piglin_brue","endermite","ghast","hoglin","zombfied_piglin","zoglin","wither_skelenton","witch","cave_spider"));

        if(HelpUtils.isNullOrEmpty(uncommonSectionTier.getList("pvp")))
            uncommonSectionTier.set("pvp", List.of());

    }

    public void createQuestTrackerPlayer(Player player){
        ConfigurationSection configurationSection = configurationPlayerQuestFile.getConfigurationSection(String.valueOf(player.getUniqueId()));

        if(configurationSection==null)
            configurationSection = configurationPlayerQuestFile.createSection(String.valueOf(player.getUniqueId()));

        if(!configurationSection.contains("name"))
            configurationSection.set("name", player.getName());

        configurationSection.set("quest-timeout", System.currentTimeMillis());


        try {
            configurationPlayerQuestFile.save(playerQuestFile);
        } catch (IOException e) {
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Error to create Player Tracker:\n" + e.getMessage(),e);
        }
    }

    public ConfigurationSection getConfigurationPlayerQuestFile(Player player){
        return configurationPlayerQuestFile.getConfigurationSection(String.valueOf(player.getUniqueId()));
    }

    private void setupRareTier(ConfigurationSection rareSectionTier){

        if(!rareSectionTier.contains("min-progress"))
            rareSectionTier.set("min-progress", 15);

        if(!rareSectionTier.contains("aditional-rate-progress"))
            rareSectionTier.set("aditional-rate-progress", 10);

        if(!rareSectionTier.contains("min-points-reward"))
            rareSectionTier.set("min-points-reward", 150);

        if(!rareSectionTier.contains("aditional-rate-chance-reward"))
            rareSectionTier.set("aditional-rate-chance-reward", 100);

        if(HelpUtils.isNullOrEmpty(rareSectionTier.getList("hunter")))
            rareSectionTier.set("hunter", List.of("glow_squid","axolotl","panda","parrot","fox","ocelot"));

        if(HelpUtils.isNullOrEmpty(rareSectionTier.getList("haverst")))
            rareSectionTier.set("haverst", List.of("sea_pickle","nether_wart","crimson_fungus","bamboo"));

        if(HelpUtils.isNullOrEmpty(rareSectionTier.getList("delivery")))
            rareSectionTier.set("delivery", List.of());

        if(HelpUtils.isNullOrEmpty(rareSectionTier.getList("defeat")))
            rareSectionTier.set("defeat", List.of("endermite","evoker","guardian","pillager","shulker","vex","vindicator"));

        if(HelpUtils.isNullOrEmpty(rareSectionTier.getList("pvp")))
            rareSectionTier.set("pvp", List.of());

    }

    private void setupLegendaryTier(ConfigurationSection legendarySectionTier){

        if(!legendarySectionTier.contains("min-progress"))
            legendarySectionTier.set("min-progress", 10);

        if(!legendarySectionTier.contains("aditional-rate-progress"))
            legendarySectionTier.set("aditional-rate-progress", 10);

        if(!legendarySectionTier.contains("min-points-reward"))
            legendarySectionTier.set("min-points-reward", 250);

        if(!legendarySectionTier.contains("aditional-rate-chance-reward"))
            legendarySectionTier.set("aditional-rate-chance-reward", 100);

        if(HelpUtils.isNullOrEmpty(legendarySectionTier.getList("hunter")))
            legendarySectionTier.set("hunter", List.of("mushroom_cow","frog","turtle","polar_bear","dolphin","tropical_fish","pufferfish"));

        if(HelpUtils.isNullOrEmpty(legendarySectionTier.getList("haverst")))
            legendarySectionTier.set("haverst", List.of("chorus_plant","spore_blossom"));

        if(HelpUtils.isNullOrEmpty(legendarySectionTier.getList("delivery")))
            legendarySectionTier.set("delivery", List.of());

        if(HelpUtils.isNullOrEmpty(legendarySectionTier.getList("defeat")))
            legendarySectionTier.set("defeat", List.of("elder_guardian","ender_dragon","wither","warden","illusioner"));

        if(HelpUtils.isNullOrEmpty(legendarySectionTier.getList("pvp")))
            legendarySectionTier.set("pvp", List.of());

    }

    private  void  createConfigurationSettings(){
        configurationSettings = YamlConfiguration.loadConfiguration(settingsFile);
        ConfigurationSection configurationSection = configurationSettings.getConfigurationSection("settings");


        if(configurationSection == null){
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Creating Quests Settings File Configuration . . .");
            configurationSection = configurationSettings.createSection("settings");
        }

        if(!configurationSection.contains("queue-time-in-minutes"))
            configurationSection.set("queue-time-in-minutes", 10);

        if(!configurationSection.contains("quest-timeout-minutes"))
            configurationSection.set("quest-timeout-minutes", 30);

        saveChanges();
    }

    private  void  createConfigurationSettingsQuestTiers(){
        configurationSettings = YamlConfiguration.loadConfiguration(settingsFile);
        ConfigurationSection configurationSection = configurationSettings.getConfigurationSection("quest-tiers");


        if(configurationSection == null){
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Creating Quests Settings Quest Tiers File Configuration . . .");
            configurationSection = configurationSettings.createSection("quest-tiers");
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
        configurationSettings = YamlConfiguration.loadConfiguration(settingsFile);
        ConfigurationSection configurationSection = configurationSettings.getConfigurationSection("quest-type");


        if(configurationSection == null){
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Creating Quests Settings Quest Type File Configuration . . .");
            configurationSection = configurationSettings.createSection("quest-type");
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
    public FileConfiguration getConfigurationSettings() {
        return configurationSettings;
    }

    public FileConfiguration getQuestSettings(){
        return  questsConfiguration;
    }

    public void saveChanges(){
        try {
            configurationSettings.save(settingsFile);
            questsConfiguration.save(questSettingFile);
        } catch (Exception e) {
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Error to save File Quests settings:\n" + e.getMessage(),e);
        }
    }
}
