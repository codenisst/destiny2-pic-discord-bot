package ru.codenisst.destiny2pic.bot.listeners;

import org.javacord.api.entity.permission.Role;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.moves.AutoRequest;
import ru.codenisst.destiny2pic.bot.moves.Request;
import ru.codenisst.destiny2pic.bot.speech.Phrase;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

import java.util.List;

public class CommandListener implements MessageCreateListener {

    private final Configurator configurator;
    private final AutoRequest autoRequest;
    private final Request request;

    public CommandListener(Configurator configurator, VkDispatcher dispatcher) {
        this.configurator = configurator;
        this.autoRequest = new AutoRequest(configurator, dispatcher);
        this.request = new Request(dispatcher);
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        switch (event.getMessageContent()) {
            case "!picture": {
                event.deleteMessage();
                request.sendNewPictures(event);
                break;
            }
            case "!addGroup": {
                event.deleteMessage();
                if (isDev(event)) {
                    try {
                        event.getChannel().sendMessage(Phrase.GROUP_ADD.get());
                        Thread.sleep(2000);
                        configurator.enableNewListener(new GroupListener(configurator));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                event.getChannel().sendMessage(Phrase.NO_RIGHT.get());
                break;
            }
            case "!auto": {
                event.deleteMessage();
                if (isDev(event)) {
                    if (configurator.isAutoWork()) {
                        event.getChannel().sendMessage(Phrase.AUTO_ERROR.get());
                    } else {
                        configurator.setAutoWork(true);
                        autoRequest.enableAutoSearchPictures(request, event);
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
                    if (!configurator.isAutoWork()) {
                        event.getChannel().sendMessage(Phrase.OFF_AUTO_ERROR.get());
                    } else {
                        configurator.setAutoWork(false);
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
                    event.getChannel().sendMessage(Phrase.PLAYING.get());
                    configurator.enableNewListener(new StatusListener(configurator));
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
                    configurator.disconnect();
                    break;
                }
                event.getChannel().sendMessage(Phrase.NO_RIGHT.get());
                break;
            }
            case "!help": {
                event.deleteMessage();
                event.getChannel().sendMessage(Phrase.HELP.get());
                break;
            }
        }
    }

    private boolean isDev(MessageCreateEvent event) {
        List<Role> eventUserRoles = event.getMessage().getAuthor().asUser().get().getRoles(event.getServer().get());
        for (Role role : eventUserRoles) {
            if (role.getName().equalsIgnoreCase(configurator.getMainRole())) {
                return true;
            }
        }
        return false;
    }
}
