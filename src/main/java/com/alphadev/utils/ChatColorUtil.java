package com.alphadev.utils;

import org.bukkit.ChatColor;

public class ChatColorUtil {

    private ChatColorUtil(){}
    public static String boldText(String msg){
        return ChatColor.BOLD+msg;
    }

    public static String boldText(String msg, ChatColor color){
        return color+""+ChatColor.BOLD+msg+ ChatColor.RESET;
    }

    public static  String textColor(String msg, ChatColor color){
        return  color+""+msg+ChatColor.RESET;
    }

}
