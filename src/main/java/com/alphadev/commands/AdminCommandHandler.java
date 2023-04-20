package com.alphadev.commands;

import com.alphadev.commands.services.AdminCommandService;
import com.alphadev.utils.HelpUtils;
import java.util.Collections;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.List;

public class AdminCommandHandler implements Listener, CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return Collections.emptyList();

        if(command.getName().equalsIgnoreCase("citadel") && args.length == 1){
            return  List.of("set");
        }

        if(command.getName().equalsIgnoreCase("citadel") && args[0].equalsIgnoreCase("set")){
            return HelpUtils.HOUSES;
        }

        if(command.getName().equalsIgnoreCase("quest") && args.length == 1)
            return  List.of("create","open");

        return Collections.emptyList();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player player) || !AdminCommandService.playerHasAdminPermission(player))
            return false;


        return AdminCommandService.citadelDefineCommand(player, command, args);
    }




}
