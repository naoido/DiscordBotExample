package com.naoido.discordbot.command.manager;

import com.naoido.discordbot.command.Command;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import static com.naoido.discordbot.JDACore.jda;

public class CommandManager extends ListenerAdapter {
    private static final Set<Command> commands = new HashSet<>();
    private static final Logger logger = Logger.getLogger(CommandManager.class.getName());

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        for (Command command: commands) {
            if (command.getName().equalsIgnoreCase(event.getName())) {
                command.handle(event);
            }
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String cmd = event.getMessage().getContentDisplay().split(" ")[0];
        for (Command command: commands) {
            if (command.isPrefixCommand(cmd)) {
                command.prefixHandle(event);
            }
        }
    }

    public static void registerCommands() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // com.naoido.discordbot.command 内のクラスをすべて取得し、Commandインターフェースを実装していたら$commandsに格納する。
        String packageName = Command.class.getPackage().getName();
        URL url = ClassLoader.getSystemClassLoader().getResource(packageName.replace(".", "/"));

        assert url != null;
        File file = new File(url.getPath());

        Set<SlashCommandData> slashCommandData = new HashSet<>();
        for (String f : Objects.requireNonNull(file.list())) {
            if (f.matches(".+\\.class$")) {
                Class<?> clazz = Class.forName(packageName + "." + f.replace(".class", ""));
                if (Command.class.isAssignableFrom(clazz) && !clazz.getSimpleName().equals("Command")) {
                    Command command = (Command) clazz.cast(clazz.getDeclaredConstructor().newInstance());
                    commands.add(command);
                    if (command.getSlashCommandData() != null) slashCommandData.add(command.getSlashCommandData());
                    logger.info("コマンド" + clazz.getSimpleName() + "を登録しました。");
                }
            }
        }

        logger.info("コマンドをdiscordに登録中です。");
        jda.updateCommands().addCommands(slashCommandData).complete();
        logger.info("コマンド登録完了しました。");
    }
}
