package com.alphadev.commands;

import com.alphadev.commands.argument.ArgumentMatcher;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public abstract class MainCommand implements TabExecutor {
    protected final HashMap<String,SubCommand> subCommandHashMap = new HashMap<>();
    protected final String noPermissionMessage;
    protected final ArgumentMatcher argumentMatcher;
    public MainCommand(String noPermissionMessage, ArgumentMatcher argumentMatcher){
        this.noPermissionMessage=noPermissionMessage;
        this.argumentMatcher = argumentMatcher;
        registerSubCommands();
    }
    protected abstract void registerSubCommands();

    @Override
    public boolean onCommand(CommandSender sender,Command cmd, String label, String... args) {
        // Caso o jogador não informe nenhum subcomando retorna false.
        if (args.length == 0)
        {
            sender.sendMessage("Comando inválido ou comando incompleto");
            return false;
        }
        // Procura o subcomando no hashmap usando a key informada pelo primeiro argumento.
        SubCommand subCommand = subCommandHashMap.get(args[0]);
        // Retorna false se não achar nenhum subcomando
        if (subCommand==null) return false;
        // Verifica se o jogador que executou o comando possui permissão.
        if (sender.hasPermission(subCommand.getPermission()))
            subCommand.perform(sender,args);
        // Retorna mensagem de "erro de permissão" e false.
        else {
            sender.sendMessage(noPermissionMessage);
            return false;
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String... args) {
        if (args.length==0) return null;
        if (args.length==1){
            // Verifica se há apenas um argumento e retorna a lista de subcomandos disponíveis
            List<String> subCommandsTC = subCommandHashMap.keySet().stream().filter(sender::hasPermission).collect(Collectors.toList());
            return getMatchingStrings(subCommandsTC, args[args.length - 1], argumentMatcher);
        }
        // Obtém o subcomando correspondente, com base no argumento informado
        SubCommand subCommand = subCommandHashMap.values().stream().filter(sc-> sc.getName().equalsIgnoreCase(args[0])).findAny().orElse(null);
        // Verifica se o subcomando não foi encontrado
        if(subCommand == null){
            return null;
        }
        // Retorna a lista de conclusão de tabulação fornecida pelo subcomando
        if (args.length==2) return subCommand.getTabCompletion();
        return List.of();
    }
    private static List<String> getMatchingStrings (List<String> tabCompletions, String arg, ArgumentMatcher argumentMatcher)
    {
        if (tabCompletions == null || arg == null)
            return null;

        List<String> result = argumentMatcher.filter(tabCompletions, arg);

        Collections.sort(result);

        return result;
    }

    public void registerMainCommand(JavaPlugin main, String cmdName){
        PluginCommand cmd = main.getCommand(cmdName);
        assert cmd != null;
        cmd.setExecutor(this);
        cmd.setTabCompleter(this);
        cmd.setPermissionMessage(noPermissionMessage);
    }
}
