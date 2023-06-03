package com.alphadev.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class ItemFactoryUtil {

    private ItemFactoryUtil(){}
    public static ItemStack menuDivisor(){
        ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColorUtil.textColor("X", ChatColor.GRAY));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack menuDivisorGreen(){
        ItemStack itemStack = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColorUtil.textColor("=", ChatColor.GREEN));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack menuDivisorRed(){
        ItemStack itemStack = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColorUtil.textColor("N\u00E3o Dispon\u00EDvel", ChatColor.RED));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack questCreate(){
        ItemStack itemStack = new ItemStack(Material.PAPER, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColorUtil.textColor("Novas Miss\u00F5es Di\u00E1rias", ChatColor.GREEN));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack conclueMissions(){
        ItemStack itemStack = new ItemStack(Material.GREEN_TERRACOTTA, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColorUtil.textColor("Concluir Miss\u00F5es", ChatColor.GREEN));

        itemMeta.setLore(List.of(
                ChatColorUtil.textColor("Clique para entregar itens em miss\u00F5es de entregas",ChatColor.BLUE),
                ChatColorUtil.textColor("Finalize miss\u00F5es j\u00E1 concluidas",ChatColor.BLUE))
        );

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


    public static ItemStack invisibleNext(){
        ItemStack itemStack = new ItemStack(Material.PAPER, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        Objects.requireNonNull(itemMeta).setCustomModelData(1);
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColorUtil.textColor("Avan\u00E7ar",ChatColor.GREEN));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack invisibleBack(){
        ItemStack itemStack = new ItemStack(Material.PAPER, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        Objects.requireNonNull(itemMeta).setCustomModelData(1);
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColorUtil.textColor("Retornar",ChatColor.GREEN));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack invisibleConfirm(){
        ItemStack itemStack = new ItemStack(Material.PAPER, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        Objects.requireNonNull(itemMeta).setCustomModelData(1);
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColorUtil.textColor("Confirmar",ChatColor.GREEN));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
