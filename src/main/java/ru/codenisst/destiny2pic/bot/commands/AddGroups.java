package ru.codenisst.destiny2pic.bot.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.listeners.CommandListener;
import ru.codenisst.destiny2pic.bot.listeners.GroupListener;
import ru.codenisst.destiny2pic.bot.speech.Phrase;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

public class AddGroups extends Thread {

    private final MessageCreateEvent event;
    private final Configurator configurator;
    private final VkDispatcher dispatcher;
    private final CommandListener commandListener;
    private final boolean isDev;

    public AddGroups(MessageCreateEvent event, Configurator configurator, VkDispatcher dispatcher,
                     CommandListener commandListener, boolean isDev){
        this.event = event;
        this.configurator = configurator;
        this.dispatcher = dispatcher;
        this.commandListener = commandListener;
        this.isDev = isDev;
    }

    @Override
    public void run() {

        if (isDev) {
            configurator.disableListener(commandListener);
            event.getChannel().sendMessage(Phrase.WHAT_GROUP_TO_ADD.get());
            configurator.enableNewListener(new GroupListener(dispatcher, this));

            return;
        }
        event.getChannel().sendMessage(Phrase.NO_RIGHT.get());
    }

    public void end(MessageCreateListener groupListener) {
        configurator.disableListener(groupListener);
        configurator.enableNewListener(commandListener);
    }
}
