package com.alphadev.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class ItemFactoryUtil {

    public static ItemStack menuDivisor(){
        ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColorUtil.textColor("X", ChatColor.GRAY));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack questCreate(){
        ItemStack itemStack = new ItemStack(Material.PAPER, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColorUtil.textColor("Nova Miss\u00E3o", ChatColor.GREEN));

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
    public static ItemStack questNameItem(){
        ItemStack itemStack = new ItemStack(Material.PAPER, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColorUtil.textColor("Digite o nome", ChatColor.GREEN));
        itemMeta.setLore(List.of("QUEST_NAME"));
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack aprove(){
        ItemStack itemStack = new ItemStack(Material.LIME_DYE, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColorUtil.textColor("Aprovar", ChatColor.GREEN));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack cancel(){
        ItemStack itemStack = new ItemStack(Material.BARRIER, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColorUtil.textColor("Cancelar", ChatColor.RED));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }


    public static ItemStack edit(){
        ItemStack itemStack = new ItemStack(Material.WRITABLE_BOOK, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColorUtil.textColor("Editar", ChatColor.GREEN));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack house( String house,  ChatColor houseColor, Material item, List<String> lore){

        ItemStack itemStack = new ItemStack(item, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Objects.requireNonNull(itemMeta).setDisplayName(ChatColorUtil.textColor(house,houseColor));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(2);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
