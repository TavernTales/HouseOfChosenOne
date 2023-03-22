package com.alphadev.services;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.House;
import com.alphadev.enums.QuestTierEnum;
import com.alphadev.utils.ChatColorUtil;
import com.alphadev.utils.HelpUtils;
import com.alphadev.utils.ItemFactoryUtil;
import com.alphadev.utils.config.ConfigPlayers;
import com.alphadev.utils.config.ConfigQuests;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class QuestService {

    private List<EntityType> getAllowedEntityTypeForKilling(QuestTierEnum tierEnum){

        if(tierEnum.getId().equals(QuestTierEnum.COMMON))
            return List.of(
                    EntityType.ZOMBIE,
                    EntityType.SKELETON,
                    EntityType.CAVE_SPIDER,
                    EntityType.CREEPER,
                    EntityType.SLIME,
                    EntityType.ENDERMAN,
                    EntityType.DROWNED,
                    EntityType.HUSK,
                    EntityType.PHANTOM,
                    EntityType.PILLAGER,
                    EntityType.SPIDER,
                    EntityType.STRAY,
                    EntityType.ZOMBIE_VILLAGER,
                    EntityType.GUARDIAN,
                    EntityType.WITCH
            );

        if(tierEnum.getId().equals(QuestTierEnum.UNCOMMON))
            return List.of(
                    EntityType.BLAZE,
                    EntityType.MAGMA_CUBE,
                    EntityType.PIGLIN_BRUTE,
                    EntityType.PIGLIN,
                    EntityType.ENDERMITE,
                    EntityType.GHAST,
                    EntityType.HOGLIN,
                    EntityType.ZOMBIFIED_PIGLIN,
                    EntityType.ZOGLIN,
                    EntityType.WITHER_SKELETON
            );
        if(tierEnum.getId().equals(QuestTierEnum.RARE))
            return List.of(
                    EntityType.RAVAGER,
                    EntityType.ENDERMITE,
                    EntityType.EVOKER,
                    EntityType.GUARDIAN,
                    EntityType.ILLUSIONER,
                    EntityType.PILLAGER,
                    EntityType.SHULKER,
                    EntityType.VEX,
                    EntityType.VINDICATOR
            );

        if(tierEnum.getId().equals(QuestTierEnum.LEGENDARY))
            return List.of(
                    EntityType.ELDER_GUARDIAN,
                    EntityType.ENDER_DRAGON,
                    EntityType.WITHER,
                    EntityType.WARDEN,
                    EntityType.GIANT
            );

        if(tierEnum.getId().equals(QuestTierEnum.CURSED))
            return List.of(
                   EntityType.PLAYER,
                   EntityType.VILLAGER
            );

        return new ArrayList<>();
    }

    public static QuestTierEnum sortQuestTier(){
        double percentChange = HelpUtils.sortPercent();

        ConfigurationSection questTiersSettings = new ConfigQuests().getQuestsConfigurationSettings();
        int legendaryPercent = questTiersSettings.contains("quest-tiers.legendary-percent") ? questTiersSettings.getInt("quest-tiers.legendary-percent") : 5;
        int rarePercent = questTiersSettings.contains("quest-tiers.rare-percent") ? questTiersSettings.getInt("quest-tiers.rare-percent") : 20;
        int uncommonPercent = questTiersSettings.contains("quest-tiers.uncommon-percent") ? questTiersSettings.getInt("quest-tiers.uncommon-percent") : 50;
        //int commonPercent =questTiersSettings.contains("quest-tiers.common-percent") ? questTiersSettings.getInt("quest-tiers.common-percent") : 80;

        if(percentChange < legendaryPercent)
            return QuestTierEnum.LEGENDARY;

        if(percentChange < rarePercent)
            return QuestTierEnum.RARE;

        if(percentChange < uncommonPercent)
            return QuestTierEnum.UNCOMMON;

        return QuestTierEnum.COMMON;
    }



    private void generateDayliQuests(){

    }


    public void questManagerPainel(Player player){
        ConfigurationSection configurationSection = new ConfigPlayers().getConfiguration(player);
        if(configurationSection == null || configurationSection.getString("house") == null)
            return;

        House house = new House(HouseOfChosenOne.getConfigFile() ,configurationSection.getString("house"));

        Inventory inventory = Bukkit.createInventory( player, InventoryType.CHEST, ChatColorUtil.boldText("Gerenciador de Miss\u00F5es", ChatColor.DARK_AQUA));
        inventory.addItem(ItemFactoryUtil.questCreate());

        player.openInventory(inventory);
    }

    public void openQuestMenu(Player player){
        ConfigurationSection configurationSection = new ConfigPlayers().getConfiguration(player);
        if(configurationSection == null || configurationSection.getString("house") == null)
            return;

        House house = new House(HouseOfChosenOne.getConfigFile() ,configurationSection.getString("house"));

        player.openInventory(Bukkit.createInventory( player, InventoryType.CHEST, ChatColorUtil.boldText("Miss\u00F5es: ", ChatColor.DARK_AQUA)+house.getHouse() ));
    }


    public void createQuest(Player player){
        Inventory inventory = Bukkit.createInventory( player, InventoryType.ANVIL, ChatColorUtil.boldText("Nome da Miss\u00E3o", ChatColor.RED));
        inventory.addItem(ItemFactoryUtil.questNameItem());

        player.openInventory(inventory);
    }

    public void createNameQuest(Player player, String questName){
        player.sendMessage(questName);
    }

    public void onInventoryClickEvent(InventoryClickEvent event){
        if(event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null)
         return;

        if(event.getCurrentItem().equals(ItemFactoryUtil.questCreate())){
            event.setCancelled(true);
            createQuest((Player) event.getWhoClicked());
        }
    }

}
