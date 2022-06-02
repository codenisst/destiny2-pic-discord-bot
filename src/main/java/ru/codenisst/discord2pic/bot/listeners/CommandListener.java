package ru.codenisst.discord2pic.bot.listeners;

import org.javacord.api.entity.permission.Role;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import ru.codenisst.discord2pic.bot.Configurator;
import ru.codenisst.discord2pic.bot.moves.Auto;
import ru.codenisst.discord2pic.bot.moves.Request;
import ru.codenisst.discord2pic.bot.speech.Phrase;

import java.util.List;

public class CommandListener implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        switch (event.getMessageContent()) {
            case "!picture": {
                event.deleteMessage();
                Request.sendNewPictures(event);
                break;
            }
            case "!auto": {
                event.deleteMessage();
                if (isDev(event)) {
                    if (Configurator.isAutoWork()) {
                        event.getChannel().sendMessage(Phrase.AUTO_ERROR.get());
                    } else {
                        Configurator.setAutoWork(true);
                        Auto.enableAutoWork(event);
                        event.getChannel().sendMessage(Phrase.AUTO_CONFIRM.get());
                    }
                    break;
                }
                event.getChannel().sendMessage(Phrase.NO_RIGHT.get());
                break;
            }
            case "!offAuto": {
                event.deleteMessage();
                if (isDev(event)) {
                    if (!Configurator.isAutoWork()) {
                        event.getChannel().sendMessage(Phrase.OFF_AUTO_ERROR.get());
                    } else {
                        Configurator.setAutoWork(false);
                        event.getChannel().sendMessage(Phrase.OFF_AUTO_CONFIRM.get());
                    }
                    break;
                }
                event.getChannel().sendMessage(Phrase.NO_RIGHT.get());
                break;
            }
            case "!status": {
                event.deleteMessage();
                if (isDev(event)) {
                    Configurator.enableNewListener(new StatusListener());
                    break;
                }
                event.getChannel().sendMessage(Phrase.NO_RIGHT.get());
                break;
            }
            case "!disconnect": {
                event.deleteMessage();
                if (isDev(event)) {
                    try {
                        event.getChannel().sendMessage("\uD83D\uDCA4");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Configurator.disconnect();
                    break;
                }
                event.getChannel().sendMessage(Phrase.NO_RIGHT.get());
                break;
            }
        }
    }

    private boolean isDev(MessageCreateEvent event) {
        List<Role> eventUserRoles = event.getMessage().getAuthor().asUser().get().getRoles(event.getServer().get());
        for (Role role : eventUserRoles) {
            if (role.getName().equalsIgnoreCase(Configurator.getMainRole())) {
                return true;
            }
        }
        return false;
    }
}
