package com.naoido.discordbot.command;

import com.naoido.discordbot.JDACore;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public interface Command {
    default boolean isPrefixCommand(String commandLine) { return commandLine.equals(JDACore.prefix + getName()); };
    void prefixHandle(MessageReceivedEvent event);
    void handle(SlashCommandInteractionEvent event);
    SlashCommandData getSlashCommandData();
    String getName();
}
