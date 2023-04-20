package com.alphadev.commands;

import com.alphadev.commands.services.AdminCommandService;
import com.alphadev.commands.services.BasicCommandService;
import com.alphadev.services.QuestService;
import com.alphadev.utils.HelpUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.List;

public class BasicCommandHandler implements Listener, CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player player))
            return false;

        /*if(command.getName().equalsIgnoreCase("quest") && args.length > 0){
           QuestService.getIstance().openQuestMenu(player);
            return true;
        }*/

        if(BasicCommandService.playerJoinInHouseCommand(player,command,args))
            return  true;

        if(AdminCommandService.playerLoadHouseOfChosenOne(player,command,args))
            return  true;

        if(AdminCommandService.playerChangeHouseTag(player,command,args))
            return  true;

        if(BasicCommandService.playerLeaveHouse(player,command,args))
            return  true;
        if(BasicCommandService.questTest(player,args))
            return  true;

        return BasicCommandService.playerTeleportToHouseLobby(player, command);
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return List.of();

        if(command.getName().equalsIgnoreCase("houseofchosenone") && args.length == 1)
            return List.of("join","leave","load","reload","tag");

        if(command.getName().equalsIgnoreCase("houseofchosenone") && args.length == 2 && (args[0].equalsIgnoreCase("join") || args[0].equalsIgnoreCase("tag")) )
            return HelpUtils.HOUSES;


        return List.of();
    }
}
