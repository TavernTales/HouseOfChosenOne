package com.alphadev.services;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.Quest;
import com.alphadev.utils.HelpUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class QuestManager {
    private static final Plugin plugin = HouseOfChosenOne.getPlugin();
    public static final Map<UUID, Quest> quests = new HashMap<>();

    // Gera um template para a quest aleátoria
    public static Quest generateTemplateQuest(){
        Quest quest = new Quest();
        quest.setUUID(UUID.randomUUID());
        quest.setQuestTypeEnum(HelpUtils.getRandomQuestType());
        quest.setQuestTierEnum(HelpUtils.getRandomQuestTier());
        quest.setCountRequired(HelpUtils.getRandomCountRequired());
        quest.setContibuitionPoints(HelpUtils.getRandomContributionPoints());
        quest.setVault(HelpUtils.getRandomVault());
        quest.setCurrentTime(System.currentTimeMillis());
        quest.setComplete(false);
        if(quest.getQuestTypeEnum().getId()<=3) {
            EntityType entityType = HelpUtils.generateEntityObjective(quest.getQuestTierEnum().getId(), quest.getQuestTypeEnum().getId());
            quest.setMobObjective(entityType);
        }
        else quest.setItemObjective(HelpUtils.generateItemObjective(quest.getQuestTierEnum().getId(), quest.getQuestTypeEnum().getId()));
        return quest;
    }
    public static ItemStack createQuestBook(Quest quest) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();
        assert meta != null;
        meta.setTitle(quest.getQuestTypeEnum().getName().concat(" - "+quest.getQuestTierEnum().getName()));
        meta.setAuthor(Bukkit.getPlayer(quest.getOwner()).getName());
        List<String> pages = new ArrayList<>();
        pages.add(HelpUtils.setPageBook(quest.getQuestTypeEnum().getId()));
        String s = quest.getMobObjective() == null ? quest.getItemObjective().getType().name() : quest.getMobObjective().name();
        pages.add("Quantidade: "+quest.getCountRequired().toString()+" de " + s);
        // Adicione mais informações sobre a quest aqui
        meta.setPages(pages);

        // Adiciona o UUID da quest ao PersistentDataContainer do livro
        NamespacedKey key = new NamespacedKey(plugin, "questUUID");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, quest.getUUID().toString());

        book.setItemMeta(meta);
        quests.put(quest.getOwner(),quest);
        return book;
    }
    //Verifica se o jogador possui o livro de quest
    public static boolean hasQuestBook(Player player, Quest quest) {
        ItemStack[] contents = player.getInventory().getContents();
        for (ItemStack item : contents) {
            if (item != null && item.getType() == Material.WRITTEN_BOOK) {
                // Verifique se o livro possui um valor no PersistentDataContainer com a chave correspondente ao UUID da quest
                BookMeta meta = (BookMeta) item.getItemMeta();
                NamespacedKey key = new NamespacedKey(plugin, "questUUID");
                assert meta != null;
                if (meta.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
                    String uuid = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
                    assert uuid != null;
                    if (uuid.equals(quest.getUUID().toString())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    // Decrementa a contagem da quest
    public static void questDecrementCount(Quest quest){
        quest.setCountRequired(quest.getCountRequired()-1);
    }
    // Cria uma nova quest e a adiciona ao mapa de quests
    public static void addQuest(Quest quest,Player p){
        quests.put(p.getUniqueId(),quest);
    }
}
