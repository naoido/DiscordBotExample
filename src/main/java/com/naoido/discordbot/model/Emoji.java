package com.naoido.discordbot.model;

import java.util.Arrays;

public enum Emoji {
    SATELLITE("\uD83D\uDCE1");

    private final String unicode;

    Emoji(String unicode) {
        this.unicode = unicode;
    }

    public static net.dv8tion.jda.api.entities.emoji.Emoji fromName(String name) {
        return net.dv8tion.jda.api.entities.emoji.Emoji.fromUnicode(Arrays.stream(values()).filter(emoji -> emoji.name().toLowerCase().equals(name)).findFirst().get().unicode);
    }

    public String getName() {
        return this.name().toLowerCase();
    }

    @Override
    public String toString() {
        return this.unicode;
    }
}
