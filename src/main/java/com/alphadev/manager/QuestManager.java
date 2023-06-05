package com.alphadev.manager;

import com.alphadev.HOCOPlugin;
import com.alphadev.entities.HCOQuest;
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
    public static final Map<UUID, HCOQuest> quests = new HashMap<>();
    private static final Plugin plugin = HOCOPlugin.getPlugin();

    // Gera um template para a quest aleátoria
    public static HCOQuest generateTemplateQuest() {
        HCOQuest hcoQuest = new HCOQuest();
        hcoQuest.setUUID(UUID.randomUUID());
        hcoQuest.setQuestTypeEnum(HelpUtils.getRandomQuestType());
        hcoQuest.setQuestTierEnum(HelpUtils.getRandomQuestTier());
        hcoQuest.setCountRequired(HelpUtils.getRandomCountRequired());
        hcoQuest.setContibuitionPoints(HelpUtils.getRandomContributionPoints());
        hcoQuest.setVault(HelpUtils.getRandomVault());
        hcoQuest.setCurrentTime(System.currentTimeMillis());
        hcoQuest.setComplete(false);
        if (hcoQuest.getQuestTypeEnum().getId() <= 3) {
            EntityType entityType = HelpUtils.generateEntityObjective(hcoQuest.getQuestTierEnum().getId(), hcoQuest.getQuestTypeEnum().getId());
            hcoQuest.setMobObjective(entityType);
        } else
            hcoQuest.setItemObjective(HelpUtils.generateItemObjective(hcoQuest.getQuestTierEnum().getId(), hcoQuest.getQuestTypeEnum().getId()));
        return hcoQuest;
    }

    public static ItemStack createQuestBook(HCOQuest hcoQuest) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();
        assert meta != null;
        meta.setTitle(hcoQuest.getQuestTypeEnum().getName().concat(" - " + hcoQuest.getQuestTierEnum().getName()));
        meta.setAuthor(Bukkit.getPlayer(hcoQuest.getOwner()).getName());
        List<String> pages = new ArrayList<>();
        pages.add(HelpUtils.setPageBook(hcoQuest.getQuestTypeEnum().getId()));
        String s = hcoQuest.getMobObjective() == null ? hcoQuest.getItemObjective().getType().name() : hcoQuest.getMobObjective().name();
        pages.add("Quantidade: " + hcoQuest.getCountRequired().toString() + " de " + s);
        // Adicione mais informações sobre a quest aqui
        meta.setPages(pages);

        // Adiciona o UUID da quest ao PersistentDataContainer do livro
        NamespacedKey key = new NamespacedKey(plugin, "questUUID");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, hcoQuest.getUUID().toString());

        book.setItemMeta(meta);
        quests.put(hcoQuest.getOwner(), hcoQuest);
        return book;
    }

    //Verifica se o jogador possui o livro de quest
    public static boolean hasQuestBook(Player player, HCOQuest hcoQuest) {
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
                    if (uuid.equals(hcoQuest.getUUID().toString())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Decrementa a contagem da quest
    public static void questDecrementCount(HCOQuest hcoQuest) {
        hcoQuest.setCountRequired(hcoQuest.getCountRequired() - 1);
    }

    // Cria uma nova quest e a adiciona ao mapa de quests
    public static void addQuest(HCOQuest hcoQuest, Player p) {
        quests.put(p.getUniqueId(), hcoQuest);
    }
}