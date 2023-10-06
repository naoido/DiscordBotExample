package com.naoido.discordbot;

import com.naoido.discordbot.command.manager.CommandManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class JDACore {
    public static JDA jda;
    public static final String PREFIX = "!";
    private static final String TOKEN;

    static {
        // "/src/main/java/resources/private.properties" を読み込み"token"を取得
        Properties properties = new Properties();
        try {
            properties.load(JDACore.class.getResourceAsStream("/private.properties"));
            TOKEN = properties.getProperty("token");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // JDAをBuild
        jda = JDABuilder.createDefault(TOKEN)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT) // getContentDisplay() を有効化
                .addEventListeners(new CommandManager())
                .setActivity(Activity.playing("Testing..."))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .build();

        CommandManager.registerCommands();
    }
}
