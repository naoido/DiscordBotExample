package com.naoido.discordbot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class Ping implements Command {
    private static Ping INSTANCE;
    public static Ping getInstance() {
        if (INSTANCE == null) INSTANCE = new Ping();
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.reply("pong!").queue();
    }

    @Override
    public SlashCommandData getSlashCommandData() {
        return Commands.slash("ping", "pingコマンド");
    }
}
