package ru.codenisst.destiny2pic.bot;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.listener.message.MessageCreateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

@Component
@Scope("singleton")
public class Configurator {

    private final DiscordApi api;
    private final ActivityType ACTIVITY = ActivityType.PLAYING;
    private boolean autoWork = false;
    private final String mainRole;
    private final VkDispatcher dispatcher;

    @Autowired
    public Configurator(DiscordApi api,
                        @Value("${mainRole}") String mainRole,
                        VkDispatcher dispatcher) {
        this.api = api;
        this.mainRole = mainRole;
        this.dispatcher = dispatcher;
    }

    // Авторизация, подключение слушателей, установка статуса,
    // восстановление работы над группами из ранее добавленных, начало работы бота
    public void start() {
        setDefaultStatus();
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

    public void enableCommandListener(MessageCreateListener messageCreateListener) {
        api.addListener(messageCreateListener);
    }

    public void enableNewListener(MessageCreateListener listener) {
        api.addListener(listener);
    }

    public void disableListener(MessageCreateListener listener) {
        api.removeListener(listener);
    }

    public void exit() {
        api.disconnect();
        System.out.println("Bot is stopped!");
        System.exit(0);
    }

    private void setDefaultStatus() {
        String defaultStatus = "!help";
        api.updateActivity(ACTIVITY, defaultStatus);
    }

    private void continueWorkingWithGroups() throws Exception {
        dispatcher.continueWorkingWithGroupsInDispatcher();
    }
}
