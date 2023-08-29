package com.naoido.discordbot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class Invite implements Command {
    // https://discord.com/developers/applications/${application_id}/oauth2/url-generator
    private static final String INVITE_URL = "https://discord.com/api/oauth2/authorize...";

    @Override
    public void prefixHandle(MessageReceivedEvent event) {
        event.getMessage().reply(INVITE_URL).queue();
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.reply(INVITE_URL).queue();
    }

    @Override
    public SlashCommandData getSlashCommandData() {
        return Commands.slash(getName(), "inviteURLを取得します。");
    }

    @Override
    public String getName() {
        return "invite";
    }
}
