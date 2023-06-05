package com.alphadev.commands.argument;

import java.util.List;

public interface IArgumentMatcher
{
    List<String> filter (List<String> tabCompletions, String argument);
}