package com.naoido.discordbot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public interface Command {
    void handle(SlashCommandInteractionEvent event);
    SlashCommandData getSlashCommandData();
    String getName();
}
