package com.alphadev.commands.argument;

import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class StartArgumentMatcher implements IArgumentMatcher {

    @Override
    public List<String> filter(List<String> tabCompletions, String argument) {
        List<String> result = new ArrayList<>();

        StringUtil.copyPartialMatches(argument, tabCompletions, result);

        return result;
    }
}
