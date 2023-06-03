package com.alphadev.commands.subcommands;

import com.alphadev.commands.SubCommand;
import com.alphadev.utils.HelpUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SubCmdJoin implements SubCommand {
    @Override
    public String getName() {
        return "join";
    }

    @Override
    public String getDescription() {
        return "Use o comando para escolher sua casa";
    }

    @Override
    public String getSyntax() {
        return "/hoco join <nomeDaCasa>";
    }

    @Override
    public String getPermission() {
        return "hco.use";
    }

    @Override
    public List<String> getTabCompletion() {

        return HelpUtils.HOUSES;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) return;

    }
}
