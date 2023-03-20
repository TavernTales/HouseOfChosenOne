package com.alphadev.commands;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.House;
import com.alphadev.services.QuestService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.Objects;

public class AdminCommand implements Listener, CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player))
            return false;

        Player player = ((Player) sender).getPlayer();



        if(command.getName().equalsIgnoreCase("quest") && args.length > 0){
            QuestService questService = new QuestService();

            switch (args[0].toLowerCase()){
                case "create" -> questService.questManagerPainel(player);
                default -> questService.openQuestMenu(player);
            }

        }


        if(args.length > 0 && args[0].equalsIgnoreCase("set")){

            if(args[1] == null){
                player.sendMessage(ChatColor.RED+" Nome da casa inv\u00E1lido tente /city set <nome da casa>");
                player.sendMessage(ChatColor.RED+" casas dispon\u00EDveis: zeronia, vlarola, frandhra, nashor, drakkaris");
                return  false;
            }
            House house = new House( HouseOfChosenOne.getConfigFile(), args[1]);

            if(house.getHouse() == null){
                player.sendMessage(ChatColor.RED+" Nome da casa inv\u00E1lido tente /city set <nome da casa>");
                player.sendMessage(ChatColor.RED+" casas dispon\u00EDveis: zeronia, vlarola, frandhra, nashor, drakkaris");
                return  false;
            }

           HouseOfChosenOne.createHouseLocation(args[1].toLowerCase(), Objects.requireNonNull(player).getLocation());
            player.sendMessage(ChatColor.AQUA+" Coordenadas adicionadas a casa "+args[1]);
            return true;
        }



        return false;
    }




}
