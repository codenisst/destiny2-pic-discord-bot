package ru.codenisst.destiny2pic.bot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.listener.message.MessageCreateListener;
import ru.codenisst.destiny2pic.bot.listeners.*;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

import java.io.FileInputStream;
import java.util.Properties;

public class Configurator {

    private DiscordApi api;
    private Properties config;
    private final String DEFAULT_STATUS = "!help";
    private final ActivityType ACTIVITY = ActivityType.PLAYING;
    private boolean autoWork = false;
    private String mainRole;
    private VkDispatcher dispatcher;

    //"src/main/resources/config/botConfig.properties"
    public Configurator(String pathConfig, VkDispatcher dispatcher) {
        try (FileInputStream fileInputStream = new FileInputStream(pathConfig)) {
            config = new Properties();
            config.load(fileInputStream);
            mainRole = config.getProperty("mainRole");
            this.dispatcher = dispatcher;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Авторизация, подключение слушателей, установка статуса,
    // восстановление работы над группами из ранее добавленных, начало работы бота
    public void start() {
        login();
        setDefaultStatus();
        enableCommandListener();
        try {
            continueWorkingWithGroups();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Bot is started!");
    }

    public String getMainRole() {
        return mainRole;
    }

    public boolean isAutoWork() {
        return autoWork;
    }

    public void setAutoWork(boolean work) {
        autoWork = work;
    }

    public void setStatus(String newStatus) {
        api.updateActivity(ACTIVITY, newStatus);
    }

    public void enableCommandListener() {
        api.addListener(new CommandListener(this, dispatcher));
    }

    public void enableNewListener(MessageCreateListener listener) {
        api.addListener(listener);
    }

    public void disableListener(MessageCreateListener listener) {
        api.removeListener(listener);
    }

    public void disconnect() {
        System.out.println("Bot is stopped!");
        api.disconnect();
        System.exit(0);
    }

    private void login() {
        api = new DiscordApiBuilder().setToken(getToken()).login().join();
    }

    private void setDefaultStatus() {
        api.updateActivity(ACTIVITY, DEFAULT_STATUS);
    }

    private String getToken() {
        return config.getProperty("botToken");
    }

    private void continueWorkingWithGroups() throws Exception {
        dispatcher.continueWorkingWithGroupsInDispacher();
    }
}
