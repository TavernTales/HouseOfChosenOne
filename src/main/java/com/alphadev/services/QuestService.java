package com.alphadev.services;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.House;
import com.alphadev.entity.Quest;
import com.alphadev.enums.QuestTierEnum;
import com.alphadev.enums.QuestTypeEnum;
import com.alphadev.utils.ChatColorUtil;
import com.alphadev.utils.HelpUtils;
import com.alphadev.utils.ItemFactoryUtil;
import com.alphadev.utils.config.ConfigPlayers;
import com.alphadev.utils.config.ConfigQuests;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class QuestService {

    private static QuestService istance;

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


    public static QuestTypeEnum sortQuestType(){
        double percentChange = HelpUtils.sortPercent();

        ConfigurationSection questTiersSettings = new ConfigQuests().getQuestsConfigurationSettings();
        int deliveryPercent = questTiersSettings.contains("quest-type.delivery-percent") ? questTiersSettings.getInt("quest-type.delivery-percent") : 40;
        int hunterPercent = questTiersSettings.contains("quest-type.hunter-percent") ? questTiersSettings.getInt("quest-type.hunter-percent") : 20;
        int harvestPercent = questTiersSettings.contains("quest-type.harvest-percent") ? questTiersSettings.getInt("quest-type.harvest-percent") : 30;
        int pvpPercent = questTiersSettings.contains("quest-type.pvp-percent") ? questTiersSettings.getInt("quest-type.pvp-percent") : 5;

        if(percentChange < pvpPercent)
            return QuestTypeEnum.PVP;

        if(percentChange < hunterPercent)
            return QuestTypeEnum.HUNTER;

        if(percentChange < harvestPercent)
            return QuestTypeEnum.HARVEST;

        if(percentChange < deliveryPercent)
            return QuestTypeEnum.DELIVERY;

        return QuestTypeEnum.DEFEAT;
    }


    HashMap<String, List<ItemStack>> questBooks = new HashMap<>();
    public void generateDayliQuests(){
        List.of("zeronia", "vlarola", "frandhra", "nashor", "drakkaris").stream().forEach(houseName -> {

            House house = new House(HouseOfChosenOne.getConfigFile(),houseName);
            List<Quest> questList = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                Quest quest = new Quest();
                quest.setQuestTierEnum(sortQuestTier());
                quest.setQuestTypeEnum(sortQuestType());
                quest.setName(quest.getQuestTierEnum().getColor() + quest.getQuestTypeEnum().getName());
                quest.setHouse(house);
                setQuestData(quest);
                questList.add(quest);
            }
            questBooks.put(houseName, createMissionsBook(questList));
        });
    }

    private List<ItemStack> createMissionsBook(List<Quest> questList){
        return questList.stream().map( quest -> {
            ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();
            bookMeta.setTitle(quest.getName()+" - " +getTitleByType(quest));
            bookMeta.setAuthor(quest.getHouse().getName());
            bookMeta.setLore(List.of(ChatColorUtil.boldText(quest.getQuestTierEnum().getName(),quest.getQuestTierEnum().getColor())));
            List<String> pages = new ArrayList<>();
            pages.add(
                    ChatColorUtil.boldText(ChatColor.stripColor(quest.getName() + " - " +getTitleByType(quest)), quest.getQuestTierEnum().getColor())+"\n"+
                    ChatColorUtil.boldText(quest.getQuestTierEnum().getName(),quest.getQuestTierEnum().getColor())+"\n\n"+
                    getLoreByQuestType(quest.getQuestTypeEnum())+
                    ChatColorUtil.boldText("\nMiss\u00E3o:\n",ChatColor.DARK_GREEN)+
                    getMissionByType(quest));
            pages.add(ChatColorUtil.boldText("Recompensas",ChatColor.DARK_GREEN)+
                    ChatColorUtil.boldText("\nPontos:",ChatColor.GOLD)+"  "+ChatColorUtil.textColor(quest.getContibuitionPoints().toString(),ChatColor.GOLD));

            bookMeta.setPages(pages);
            writtenBook.setItemMeta(bookMeta);

            return  writtenBook;
        }).collect(Collectors.toList());
    }

    private String getLoreByQuestType(QuestTypeEnum questTypeEnum ){

        if(questTypeEnum.equals(QuestTypeEnum.DELIVERY))
            return "Preciso que me entregue um carregamento, \u00E9 um servi\u00E7o f\u00E1cil e eu acredito que voc\u00EA conseguir\u00E1 fazer.\n";
        if(questTypeEnum.equals(QuestTypeEnum.DEFEAT))
            return "Preciso que elimine algumas pestes da regi\u00E3o, \u00E9 um servi\u00E7o f\u00E1cil e eu acredito que voc\u00EA conseguir\u00E1 fazer.\n";
        if(questTypeEnum.equals(QuestTypeEnum.HARVEST))
            return "Preciso que colete algumas coisas para mim, \u00E9 um servi\u00E7o f\u00E1cil e eu acredito que voc\u00EA conseguir\u00E1 fazer.\n";
        if(questTypeEnum.equals(QuestTypeEnum.HUNTER))
            return "Preciso que cace alguns animais para mim, \u00E9 um servi\u00E7o f\u00E1cil e eu acredito que voc\u00EA conseguir\u00E1 fazer.\n";
        if(questTypeEnum.equals(QuestTypeEnum.PVP))
            return "Preciso que derrote alguns alvos, \u00E9 um servi\u00E7o f\u00E1cil e eu acredito que voc\u00EA conseguir\u00E1 fazer.\n";

        return "";
    }

    private String getTitleByType(Quest quest){
        if(quest.getItemRequired() != null)
            return quest.getItemRequired().getType().name();

        if(quest.getMobRequired() != null)
            return quest.getMobRequired().name();

        if(quest.getHaverstItemRequired() != null)
            return   quest.getHaverstItemRequired().name();

        return  "";
    }
    private  String getMissionByType(Quest quest){
     
        if(quest.getItemRequired() != null)
            return quest.getItemRequired().getType().name()+ ChatColor.DARK_GREEN +"        0/"+quest.getCountRequired();
        
        if(quest.getMobRequired() != null)
           return quest.getMobRequired().name()+ChatColor.DARK_GREEN +"        0/"+quest.getCountRequired();

        if(quest.getHaverstItemRequired() != null)
          return   quest.getHaverstItemRequired().name()+ChatColor.DARK_GREEN +"        0/"+quest.getCountRequired();

        return  "";
    }


    private void setQuestData(Quest quest){
        if(quest.getQuestTypeEnum().equals(QuestTypeEnum.DELIVERY)){
            quest.setItemRequired(new ItemStack(Material.POTATO));
        }

        if(quest.getQuestTypeEnum().equals(QuestTypeEnum.DEFEAT)){
            List<EntityType> entityTypes = getAllowedEntityTypetoDefeat(quest.getQuestTierEnum());
            quest.setMobRequired(entityTypes.get(0));
        }
        if(quest.getQuestTypeEnum().equals(QuestTypeEnum.HARVEST)){
            List<Material> materials = getAllowedHarvestItems(quest.getQuestTierEnum());
            quest.setHaverstItemRequired(materials.get(0));
        }
        if(quest.getQuestTypeEnum().equals(QuestTypeEnum.HUNTER)){
            List<EntityType> entityTypes = getAllowedAnimalsToHunt(quest.getQuestTierEnum());
            quest.setMobRequired(entityTypes.get(0));
        }

        if(quest.getQuestTypeEnum().equals(QuestTypeEnum.PVP)){
            List<EntityType> entityTypes = getAllowedEntityTypetoDefeat(QuestTierEnum.CURSED);
            quest.setMobRequired(entityTypes.get(0));
        }

        if(quest.getQuestTierEnum().equals(QuestTierEnum.COMMON))
            quest.setCountRequired(Math.round(Math.random() * 10 + 20));
        if(quest.getQuestTierEnum().equals(QuestTierEnum.UNCOMMON))
            quest.setCountRequired(Math.round(Math.random() * 10 + 20));
        if(quest.getQuestTierEnum().equals(QuestTierEnum.RARE))
            quest.setCountRequired(Math.round(Math.random() * 10 + 10));
        if(quest.getQuestTierEnum().equals(QuestTierEnum.LEGENDARY))
            quest.setCountRequired(Math.round(Math.random() * 10 + 5));
        if(quest.getQuestTierEnum().equals(QuestTierEnum.CURSED))
            quest.setCountRequired(Math.round(Math.random() * 10 + 20));


        Long contributorPoints = 0L;
       if(quest.getQuestTierEnum().equals(QuestTierEnum.COMMON))
           contributorPoints =  Math.round(Math.random() * 10 + 20);
       if(quest.getQuestTierEnum().equals(QuestTierEnum.UNCOMMON))
           contributorPoints =  Math.round(Math.random() * 20 + 25);
       if(quest.getQuestTierEnum().equals(QuestTierEnum.RARE))
           contributorPoints =  Math.round(Math.random() * 30 + 30);
       if(quest.getQuestTierEnum().equals(QuestTierEnum.LEGENDARY))
           contributorPoints =  Math.round(Math.random() * 60 + 40);
       if(quest.getQuestTierEnum().equals(QuestTierEnum.CURSED))
           contributorPoints =  Math.round(Math.random() * 30 + 30);

        quest.setContibuitionPoints(contributorPoints.intValue());
        quest.setCurrentTime(System.currentTimeMillis());

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

        Inventory inventory = Bukkit.createInventory( player, InventoryType.CHEST, ChatColorUtil.boldText("Miss\u00F5es: ", ChatColor.DARK_AQUA)+house.getName() );
        for (int i = 0; i <  questBooks.get(configurationSection.getString("house")).size(); i++) {
            inventory.setItem(i, questBooks.get(configurationSection.getString("house")).get(i));
        }

        player.openInventory(inventory);
    }
    public void createQuest(Player player){
        Inventory inventory = Bukkit.createInventory( player, InventoryType.ANVIL, ChatColorUtil.boldText("Nome da Miss\u00E3o", ChatColor.RED));
        inventory.addItem(ItemFactoryUtil.questNameItem());

        player.openInventory(inventory);
    }
    public void onInventoryClickEvent(InventoryClickEvent event){
        if(event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null)
         return;

        if(event.getCurrentItem().equals(ItemFactoryUtil.questCreate())){
            event.setCancelled(true);
            createQuest((Player) event.getWhoClicked());
        }
    }

    private  List<EntityType> getAllowedAnimalsToHunt(QuestTierEnum tierEnum){

        if(tierEnum.equals(QuestTierEnum.COMMON))
            return List.of(
                EntityType.COW,
                EntityType.PIG,
                EntityType.SHEEP,
                EntityType.CHICKEN,
                EntityType.COD
            );

        if(tierEnum.equals(QuestTierEnum.UNCOMMON))
            return List.of(
                EntityType.GOAT,
                EntityType.RABBIT,
                EntityType.SQUID,
                EntityType.SALMON,
                EntityType.WOLF
            );

        if(tierEnum.equals(QuestTierEnum.RARE))
            return List.of(
                EntityType.GLOW_SQUID,
                EntityType.AXOLOTL,
                EntityType.PANDA,
                EntityType.PARROT,
                EntityType.FOX,
                EntityType.OCELOT
            );

        if(tierEnum.equals(QuestTierEnum.LEGENDARY))
            return List.of(
               EntityType.MUSHROOM_COW,
               EntityType.FROG,
               EntityType.TURTLE,
               EntityType.POLAR_BEAR,
               EntityType.DOLPHIN,
               EntityType.TROPICAL_FISH,
               EntityType.PUFFERFISH
            );

        if(tierEnum.equals(QuestTierEnum.CURSED))
            return List.of(
                EntityType.HORSE,
                EntityType.CAT,
                EntityType.DONKEY,
                EntityType.MULE,
                EntityType.LLAMA
            );

        return new ArrayList();
    }

    private  List<Material> getAllowedHarvestItems(QuestTierEnum tierEnum){

        if(tierEnum.equals(QuestTierEnum.COMMON))
            return List.of(
                Material.WHEAT,
                Material.CARROT,
                Material.SUGAR_CANE,
                Material.POTATO,
                Material.BEETROOTS
            );

        if(tierEnum.equals(QuestTierEnum.UNCOMMON))
            return List.of(
                Material.SWEET_BERRY_BUSH,
                Material.MELON,
                Material.PUMPKIN,
                Material.CACTUS,
                Material.KELP_PLANT,
                Material.BROWN_MUSHROOM,
                Material.RED_MUSHROOM
            );

        if(tierEnum.equals(QuestTierEnum.RARE))
            return List.of(
                Material.SEA_PICKLE,
                Material.NETHER_WART,
                Material.WARPED_FUNGUS,
                Material.CRIMSON_FUNGUS,
                Material.BAMBOO
            );

        if(tierEnum.equals(QuestTierEnum.LEGENDARY))
            return List.of(
                Material.CHORUS_PLANT,
                Material.SPORE_BLOSSOM
            );

        if(tierEnum.equals(QuestTierEnum.CURSED))
            return List.of(
                Material.BLUE_ORCHID,
                Material.SUNFLOWER,
                Material.ROSE_BUSH,
                Material.LILAC,
                Material.PEONY,
                Material.CORNFLOWER,
                Material.LILY_OF_THE_VALLEY
            );


        return new ArrayList();
    }

    private List<EntityType> getAllowedEntityTypetoDefeat(QuestTierEnum tierEnum){

        if(tierEnum.equals(QuestTierEnum.COMMON))
            return List.of(
                    EntityType.ZOMBIE,
                    EntityType.SKELETON,
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
                    EntityType.GUARDIAN
            );

        if(tierEnum.equals(QuestTierEnum.UNCOMMON))
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
                    EntityType.WITHER_SKELETON,
                    EntityType.WITCH,
                    EntityType.CAVE_SPIDER
            );
        if(tierEnum.equals(QuestTierEnum.RARE))
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

        if(tierEnum.equals(QuestTierEnum.LEGENDARY))
            return List.of(
                    EntityType.ELDER_GUARDIAN,
                    EntityType.ENDER_DRAGON,
                    EntityType.WITHER,
                    EntityType.WARDEN,
                    EntityType.GIANT
            );

        if(tierEnum.equals(QuestTierEnum.CURSED))
            return List.of(
                    EntityType.PLAYER,
                    EntityType.VILLAGER
            );

        return new ArrayList<>();
    }


    public static QuestService getIstance(){
        if(istance == null)
            istance = new QuestService();

        return  istance;
    }
}
