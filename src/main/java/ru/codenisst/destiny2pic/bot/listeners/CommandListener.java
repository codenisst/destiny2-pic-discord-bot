package ru.codenisst.destiny2pic.bot.listeners;

import org.javacord.api.entity.permission.Role;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.commands.*;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

import java.util.List;

public class CommandListener implements MessageCreateListener {

    private final Configurator configurator;
    private final VkDispatcher dispatcher;

    public CommandListener(Configurator configurator, VkDispatcher dispatcher) {
        this.configurator = configurator;
        this.dispatcher = dispatcher;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String[] command = event.getMessageContent().split(" ");

        if (command[0].matches("^!\\D+$")) {

            switch (command[0]) {
                case "!picture" -> new Picture(event, dispatcher).start();

                case "!groupList" -> new GroupList(event, dispatcher, isDev(event)).start();

                case "!addGroups" ->
                        new AddGroups(event, configurator, dispatcher, this, isDev(event)).start();

                case "!deleteGroups" -> new DeleteGroups(event, configurator, dispatcher,
                        this, isDev(event)).start();

                case "!deleteAllGroups" -> new DeleteAllGroups(event, dispatcher, isDev(event)).start();

                case "!auto" -> new Auto(event, configurator, dispatcher, command, isDev(event)).start();

                case "!offAuto" -> new OffAuto(event, configurator, isDev(event)).start();

                case "!status" -> new Status(event, configurator, this, isDev(event)).start();

                case "!disconnect" -> new Disconnect(event, configurator, isDev(event)).start();

                case "!help" -> new Help(event).start();

                default -> new UnknownCommand(event).start();
            }
        }
    }

    private boolean isDev(MessageCreateEvent event) {
        List<Role> eventUserRoles = event.getMessage()
                .getAuthor()
                .asUser()
                .get()
                .getRoles(event
                        .getServer()
                        .get());
        for (Role role : eventUserRoles) {
            if (role.getName().equalsIgnoreCase(configurator.getMainRole())) {
                return true;
            }
        }
        return false;
    }
}
