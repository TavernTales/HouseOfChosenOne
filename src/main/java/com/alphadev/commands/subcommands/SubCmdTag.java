package com.alphadev.commands.subcommands;

import com.alphadev.commands.SubCommand;
import com.alphadev.utils.HelpUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SubCmdTag implements SubCommand {
    @Override
    public String getName() {
        return "tag";
    }

    @Override
    public String getDescription() {
        return "Use o comando para alterar a tag atual da casa.";
    }

    @Override
    public String getSyntax() {
        return "/hco tag <nomeDaCasa>";
    }

    @Override
    public String getPermission() {
        return null;
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
