package ru.codenisst.discord2pic.bot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.listener.message.MessageCreateListener;
import ru.codenisst.discord2pic.bot.listeners.CommandListener;

import java.io.FileInputStream;
import java.util.Properties;

public class Configurator {

    private static DiscordApi api;
    private static Properties config;
    private static final String DEFAULT_STATUS = "поиск картинощек!";
    private static final ActivityType ACTIVITY = ActivityType.PLAYING;
    private static boolean autoWork = false;
    private static String mainRole;

    static {
        try (FileInputStream fileInputStream = new FileInputStream(
                "src/main/resources/config/botConfig.properties")) {
            config = new Properties();
            config.load(fileInputStream);
            mainRole = config.getProperty("mainRole");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Авторизация, подключение слушателей, установка статуса и начало работы бота
    public static void start() {
        login();
        setDefaultStatus();
        enableListeners();
        System.out.println("Bot is started!");
    }

    private static void login() {
        api = new DiscordApiBuilder().setToken(getToken()).login().join();
    }

    private static void setDefaultStatus() {
        api.updateActivity(ACTIVITY, DEFAULT_STATUS);
    }

    private static String getToken() {
        return config.getProperty("botToken");
    }

    public static void setStatus(String newStatus) {
        api.updateActivity(ACTIVITY, newStatus);
    }

    public static boolean isAutoWork() {
        return autoWork;
    }

    public static String getMainRole() {
        return mainRole;
    }

    private static void enableListeners() {
        api.addListener(new CommandListener());
    }

    public static void setAutoWork(boolean work) {
        autoWork = work;
    }

    public static void enableNewListener(MessageCreateListener listener) {
        api.addListener(listener);
    }

    public static void disableListener(MessageCreateListener listener) {
        api.removeListener(listener);
    }

    public static void disconnect() {
        System.out.println("Bot is stopped!");
        api.disconnect();
        System.exit(0);
    }
}
