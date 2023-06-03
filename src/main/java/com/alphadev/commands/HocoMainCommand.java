package com.alphadev.commands;

import com.alphadev.commands.argument.StartArgumentMatcher;
import com.alphadev.commands.subcommands.SubCmdJoin;
import com.alphadev.commands.subcommands.SubCmdLeave;
import com.alphadev.commands.subcommands.SubCmdTag;

public class HocoMainCommand extends MainCommand{
    public HocoMainCommand() {
        super("Desculpe, você não possui permissão para esse comando.", new StartArgumentMatcher());
    }
    @Override
    protected void registerSubCommands() {
        //Registra os subcomandos no hashmap com key=> "nome do subcomando" value=> subcomando
        subCommandHashMap.put("join",new SubCmdJoin());
        subCommandHashMap.put("leave",new SubCmdLeave());
        subCommandHashMap.put("tag",new SubCmdTag());
    }
}
