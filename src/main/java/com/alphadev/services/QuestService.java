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
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class QuestService {

    private static QuestService istance;

    private ConfigQuests configQuests = new ConfigQuests();
    private  ConfigurationSection questSettings = configQuests.getQuestSettings();


    public static QuestTierEnum sortQuestTier(){
        double percentChange = HelpUtils.sortPercent();

        ConfigurationSection questTiersSettings = new ConfigQuests().getConfigurationSettings();
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

        ConfigurationSection questTiersSettings = new ConfigQuests().getConfigurationSettings();
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


    HashMap<String, Set<ItemStack>> questBooks = new HashMap<>();
    public void generateDayliQuests(){
        List.of("zeronia", "vlarola", "frandhra", "nashor", "drakkaris").forEach(houseName -> {

            House house = new House(HouseOfChosenOne.getConfigFile(),houseName);
            Set<Quest> questList = new HashSet<>();
            for (int i = 0; i < 10; i++) {
                Quest quest = new Quest();
                quest.setQuestTierEnum(sortQuestTier());
                quest.setQuestTypeEnum(sortQuestType());
                quest.setName(quest.getQuestTierEnum().getColor() + quest.getQuestTypeEnum().getName());
                quest.setHouse(house);
                setQuestData(quest);

                if((quest.getItemRequired() == null || quest.getItemRequired().isEmpty()) && (quest.getMobRequired() == null || quest.getMobRequired().isEmpty()) &&  (quest.getHaverstItemRequired() == null || quest.getHaverstItemRequired().isEmpty()))
                    continue;

                questList.add(quest);
            }
            questBooks.put(houseName, createMissionsBook(questList));
        });
    }
    public static boolean questBookIsConcluded(ItemStack book){
        if(book == null || book.getItemMeta() == null || book.getItemMeta().getLore() == null)
            return false;

        if(!ChatColor.stripColor(book.getItemMeta().getLore().get(0)).contains("Miss\u00E3o"))
            return false;

        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        String bookPage = bookMeta.getPage(2);

        return ChatColor.stripColor(bookPage).contains("Concluido");

    }
    public static void updateQuestionAmount(ItemStack book, Player player, int ammount){

        if(book == null || book.getItemMeta() == null || book.getItemMeta().getLore() == null)
            return;

        if(!ChatColor.stripColor(book.getItemMeta().getLore().get(0)).contains("Miss\u00E3o"))
            return;

        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        String bookPage = bookMeta.getPage(2);

        String baseProgress = bookPage.split("Progresso:")[0];
        String[] progress =  bookPage.split("Progresso:")[1].split("/");

        int currentAmmount =Integer.parseInt(progress[0].trim());
        final int maxAmmount = Integer.parseInt(progress[1].trim());

        if( currentAmmount >= maxAmmount){
            bookMeta.getLore().add(ChatColorUtil.boldText("Concluido", ChatColor.GREEN));
            book.setItemMeta(bookMeta);
            return;
        }

        currentAmmount = currentAmmount + ammount;

        bookMeta.setPage(2,baseProgress+"Progresso: "+String.format(" %s/%s", currentAmmount, maxAmmount));

        final int finalCurrentAmmount = currentAmmount;
        Arrays.stream(player.getInventory().getContents()).filter(itemStack -> !questBookIsConcluded(itemStack) ).forEach(itemStack -> {
            if(itemStack != null && itemStack.getItemMeta() != null  && itemStack.getType().equals(Material.WRITTEN_BOOK) &&
                    ((BookMeta) itemStack.getItemMeta()).getTitle().equalsIgnoreCase(bookMeta.getTitle())){

                if(finalCurrentAmmount >= maxAmmount){
                    player.sendMessage(ChatColorUtil.textColor("Voc\u00EA concluiu o objetivo da miss\u00E3o",ChatColor.GREEN));
                    player.getWorld().spawnEntity(player.getLocation(),EntityType.FIREWORK);
                    bookMeta.setPage(2, baseProgress + ChatColorUtil.boldText("Concluido", ChatColor.DARK_GREEN));

                }else{
                    player.sendMessage(ChatColorUtil.textColor("Voc\u00EA contribuiu com o objetivo da miss\u00E3o",ChatColor.GREEN));
                }
                itemStack.setItemMeta(bookMeta);
            }
        });

    }
    private Set<ItemStack> createMissionsBook(Set<Quest> questList){
        return questList.stream().map( quest -> {
            ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();
            bookMeta.setTitle(quest.getName()+" - " +getTitleByType(quest));
            bookMeta.setAuthor(quest.getHouse().getName());
            bookMeta.setLore(
                List.of(
                        ChatColorUtil.boldText("Miss\u00E3o", ChatColor.GOLD),
                        ChatColorUtil.textColor("  Rank: ", ChatColor.WHITE)+ChatColorUtil.textColor(quest.getQuestTierEnum().getName(),quest.getQuestTierEnum().getColor()),
                        ChatColorUtil.textColor("  Objetivo: ", ChatColor.WHITE)+ChatColorUtil.textColor(getTitleByType(quest), ChatColor.GREEN)
                )
            );
            List<String> pages = new ArrayList<>();
            pages.add(ChatColorUtil.boldText(getTitleByType(quest), quest.getQuestTierEnum().getColor())+"\n\n"+
                    ChatColorUtil.boldText(quest.getQuestTierEnum().getName(),quest.getQuestTierEnum().getColor()).toUpperCase()+"\n\n"+
                    getLoreByQuestType(quest.getQuestTypeEnum()));

            pages.add( ChatColorUtil.boldText("Miss\u00E3o:\n\n",ChatColor.DARK_GREEN) + ChatColorUtil.boldText(getMissionByType(quest,0)));
            pages.add(ChatColorUtil.boldText("Recompensas",ChatColor.DARK_GREEN)+
                    ChatColorUtil.boldText("\nPontos:",ChatColor.GOLD)+"  "+ChatColorUtil.textColor(quest.getContibuitionPoints().toString(),ChatColor.GOLD));
            bookMeta.setPages(pages);
            writtenBook.setItemMeta(bookMeta);

            return  writtenBook;
        }).collect(Collectors.toSet());
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

        return "Lore Inv\u00E1lida";
    }

    private String getTitleByType(Quest quest){

        if(quest.getItemRequired() != null)
            return quest.getItemRequired();

        if(quest.getMobRequired() != null)
            return quest.getMobRequired();

        if(quest.getHaverstItemRequired() != null)
            return  quest.getHaverstItemRequired();

        return  "T\u00EDtulo Inv\u00E1lido";
    }
    private  String getMissionByType(Quest quest, int ammout){

        return ChatColor.DARK_GREEN +"\nProgresso: "+ammout+"/"+quest.getCountRequired();
    }

    private Object getRandomIndex(List<?> list){
        if( list.isEmpty() )
            return "";

        return list.get(new Random().nextInt(0, list.size()));
    }

    private void setQuestData(Quest quest){
        if(quest.getQuestTypeEnum().equals(QuestTypeEnum.DELIVERY))
            quest.setItemRequired(getRandomIndex(getQuestTierList(quest.getQuestTierEnum(),"delivery")).toString());

        if(quest.getQuestTypeEnum().equals(QuestTypeEnum.DEFEAT))
            quest.setMobRequired(getRandomIndex(getQuestTierList(quest.getQuestTierEnum(),"defeat")).toString());

        if(quest.getQuestTypeEnum().equals(QuestTypeEnum.HARVEST))
            quest.setHaverstItemRequired(getRandomIndex(getQuestTierList(quest.getQuestTierEnum(),"haverst")).toString());

        if(quest.getQuestTypeEnum().equals(QuestTypeEnum.HUNTER))
            quest.setMobRequired(getRandomIndex(getQuestTierList(quest.getQuestTierEnum(),"hunter")).toString());

        if(quest.getQuestTypeEnum().equals(QuestTypeEnum.PVP))
            quest.setMobRequired(getRandomIndex(getQuestTierList(quest.getQuestTierEnum(),"pvp")).toString());

        quest.setCountRequired( Math.round(Math.random() * questSettings.getInt("settings-tier."+quest.getQuestTierEnum().toString().toLowerCase()+".aditional-rate-chance-reward") + questSettings.getInt("settings-tier."+quest.getQuestTierEnum().toString().toLowerCase().toLowerCase()+".min-points-reward")));
        quest.setContibuitionPoints((int) Math.round(Math.random() * questSettings.getInt("settings-tier."+quest.getQuestTierEnum().toString().toLowerCase()+".aditional-rate-chance-reward") + questSettings.getInt("settings-tier."+quest.getQuestTierEnum().toString().toLowerCase().toLowerCase()+".min-points-reward")));

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

        Inventory inventory = Bukkit.createInventory( player,54, ChatColorUtil.boldText("Miss\u00F5es: ", ChatColor.GOLD)+house.getName() );

        AtomicInteger index = new AtomicInteger();
        questBooks.get(configurationSection.getString("house")).forEach(itemStack -> {
            inventory.setItem(index.getAndIncrement(), ItemFactoryUtil.menuDivisor());
            inventory.setItem(index.getAndIncrement(), itemStack);
            inventory.setItem(index.getAndIncrement(), ItemFactoryUtil.menuDivisorGreen());
        } );

        player.openInventory(inventory);
    }

    private boolean getAvailableMenus(String guiName){
        if(guiName == null || guiName.isEmpty())
            return true;

        return !ChatColor.stripColor(guiName).contains("Miss\u00F5es");
    }

    public void onInventoryClickEvent(InventoryClickEvent event){
        if(event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null || getAvailableMenus(event.getView().getTitle()))
         return;


        if(event.getCurrentItem().getItemMeta().equals(ItemFactoryUtil.menuDivisor().getItemMeta()))
            event.setCancelled(true);


        if (event.getCurrentItem().getItemMeta().equals(ItemFactoryUtil.menuDivisorGreen().getItemMeta())){
            event.getWhoClicked().sendMessage("Teste");
            event.setCancelled(true);
        }


        if (event.getCurrentItem().isSimilar(new ItemStack(Material.WRITTEN_BOOK))){
            event.getWhoClicked().sendMessage("Teste 2");
            event.setCancelled(true);
        }
    }

    private List<?> getQuestTierList(QuestTierEnum tierEnum, String section){

        if(tierEnum.equals(QuestTierEnum.COMMON))
            return questSettings.getList("settings-tier.common."+section);

        if(tierEnum.equals(QuestTierEnum.UNCOMMON))
            return questSettings.getList("settings-tier.uncommon."+section);
        if(tierEnum.equals(QuestTierEnum.RARE))
            return questSettings.getList("settings-tier.rare."+section);

        if(tierEnum.equals(QuestTierEnum.LEGENDARY))
            return questSettings.getList("settings-tier.legendary."+section);

        if(tierEnum.equals(QuestTierEnum.CURSED))
            return questSettings.getList("settings-tier.cursed."+section);

        return new ArrayList();
    }

    public static QuestService getIstance(){
        if(istance == null)
            istance = new QuestService();

        return  istance;
    }
}
