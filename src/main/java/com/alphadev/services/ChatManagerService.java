package com.alphadev.services;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.utils.ChatColorUtil;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;


public class ChatManagerService {

    private ChatManagerService(){}
    private static final Set<UUID> PLAYERS_IN_GLOBAL_CHAT = new HashSet<>();

    private static final ConfigurationSection CHAT_SETTINGS = HouseOfChosenOne.getConfigFile().getConfigurationSection("chat-settings");
    public static final Long LOCAL_DISTANCE_LIMIT;

    static {
        assert CHAT_SETTINGS != null;
        LOCAL_DISTANCE_LIMIT = CHAT_SETTINGS.getLong("distance-chat-local-limit");
    }

    public static void addPlayerToGlobalChat(Player player){
        PLAYERS_IN_GLOBAL_CHAT.add(player.getUniqueId());
    }
    public static void removePlayerToGlobalChat(Player player){
        PLAYERS_IN_GLOBAL_CHAT.remove(player.getUniqueId());
    }

    public static Set<UUID> getPlayersInGlobalChat(){
        return  PLAYERS_IN_GLOBAL_CHAT;
    }

    public static void onPlayerReceiveMessages(AsyncPlayerChatEvent event){
        Player sender = event.getPlayer();
        event.setFormat(getMessagePrefix(sender) + sender.getDisplayName() + ChatColorUtil.textColor(" >> ", ChatColor.GREEN) + ChatColor.translateAlternateColorCodes('&',event.getMessage()));

        final Iterator<Player> iterator = event.getRecipients().iterator();
        while (iterator.hasNext()) {
            final Player recipient = iterator.next();

            boolean localLimite = chatLocalIsLimiteOutOfBoundsOrWorld(recipient, sender);

            if(playerHasOpOrPermission(recipient) && localLimite ){
                recipient.sendMessage(ChatColor.GRAY + ChatColor.stripColor(event.getFormat()));
                iterator.remove();
                continue;
            }

            if(localLimite){
                iterator.remove();
            }
        }
    }

    public  static  void onPlayerQuitEvent(PlayerQuitEvent event){
        removePlayerToGlobalChat(event.getPlayer());
    }


    public static String getMessagePrefix(Player player){
        if(getPlayersInGlobalChat().contains(player.getUniqueId()))
            return ChatColorUtil.textColor("Global", ChatColor.GREEN)+" | ";
        
        return ChatColorUtil.textColor("Local", ChatColor.GRAY)+" | ";
    }
    public static boolean chatLocalIsLimiteOutOfBoundsOrWorld(Player player, Player sender){
        if(getPlayersInGlobalChat().contains(sender.getUniqueId()))
            return  false;
        return !sender.getWorld().getName().equalsIgnoreCase(player.getWorld().getName()) || player.getLocation().distance(sender.getLocation()) > LOCAL_DISTANCE_LIMIT;
    }

    public static boolean playerHasOpOrPermission(Player player){
        return player.hasPermission("hco.admin");
    }
}
