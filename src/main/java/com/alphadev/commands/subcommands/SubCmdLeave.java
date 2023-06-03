package com.alphadev.commands.subcommands;

import com.alphadev.commands.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SubCmdLeave implements SubCommand {
    @Override
    public String getName() {
        return "leave";
    }

    @Override
    public String getDescription() {
        return "Use o comando para sair da casa atual";
    }

    @Override
    public String getSyntax() {
        return "/hco leave";
    }

    @Override
    public String getPermission() {
        return "hco.use";
    }

    @Override
    public List<String> getTabCompletion() {
        return null;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) return;
    }
}
