package com.alphadev.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface SubCommand
{
    String getName ();
    String getDescription ();
    String getSyntax ();
    String getPermission ();
    List<String> getTabCompletion ();
    void perform (CommandSender sender, String[] args);
}