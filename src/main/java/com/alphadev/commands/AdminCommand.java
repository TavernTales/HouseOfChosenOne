package com.alphadev.commands;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.House;
import com.alphadev.services.QuestService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.Objects;

public class AdminCommand implements Listener, CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return null;

        if(command.getName().equalsIgnoreCase("citadel") && args.length == 1){
            return  List.of("set");
        }

        if(command.getName().equalsIgnoreCase("citadel") && args[0].equalsIgnoreCase("set")){
            return  List.of("zeronia", "vlarola", "frandhra", "nashor", "drakkaris");
        }

        if(command.getName().equalsIgnoreCase("quest") && args.length == 1)
            return  List.of("create","open");

        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player))
            return false;

        Player player = ((Player) sender).getPlayer();

        if(!player.hasPermission("hco.admin")){
            player.sendMessage("Voc\u00EA n\u00E3o tem permiss\u00E3o para executar esse comando.");
            return false;
        }

        if(command.getName().equalsIgnoreCase("quest") && args.length > 0){
            QuestService questService = QuestService.getIstance();

            switch (args[0].toLowerCase()){
                case "create" -> questService.questManagerPainel(player);
                case "open" -> questService.openQuestMenu(player);
                default -> questService.openQuestMenu(player);
            }
            return true;
        }


        if(command.getName().equalsIgnoreCase("citadel") && args.length > 0 && args[0].equalsIgnoreCase("set")){

            if(args[1] == null){
                player.sendMessage(ChatColor.RED+" Nome da casa inv\u00E1lido tente /city set <nome da casa>");
                player.sendMessage(ChatColor.RED+" casas dispon\u00EDveis: zeronia, vlarola, frandhra, nashor, drakkaris");
                return  false;
            }
            House house = new House( HouseOfChosenOne.getConfigFile(), args[1]);

            if(house.getName() == null){
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
