package com.naoido.discordbot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class Ping implements Command {
    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public void prefixHandle(MessageReceivedEvent event) {
        event.getChannel().sendMessage("pong!").queue();
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
