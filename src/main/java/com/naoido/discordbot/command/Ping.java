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
        long start = System.currentTimeMillis();
        event.getMessage().reply("Just a moment...").queue(m -> {
            m.editMessage(":satellite: Ping: " + (System.currentTimeMillis() - start) + "ms").queue();
        });
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        long start = System.currentTimeMillis();
        event.reply("Just a moment...").queue(m -> {
                m.editOriginal(":satellite: Ping: " + (System.currentTimeMillis() - start) + "ms").queue();
        });
    }

    @Override
    public SlashCommandData getSlashCommandData() {
        return Commands.slash("ping", "pingコマンド");
    }
}
