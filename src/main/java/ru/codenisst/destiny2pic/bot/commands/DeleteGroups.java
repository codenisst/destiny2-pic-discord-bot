package ru.codenisst.destiny2pic.bot.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.listeners.CommandListener;
import ru.codenisst.destiny2pic.bot.listeners.GroupDeleteListener;
import ru.codenisst.destiny2pic.bot.speech.Phrase;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

public class DeleteGroups extends Thread {

    private final MessageCreateEvent event;
    private final Configurator configurator;
    private final VkDispatcher dispatcher;
    private final CommandListener commandListener;
    private final boolean isDev;

    public DeleteGroups(MessageCreateEvent event, Configurator configurator, VkDispatcher dispatcher,
                        CommandListener commandListener, boolean isDev) {
        this.event = event;
        this.configurator = configurator;
        this.dispatcher = dispatcher;
        this.commandListener = commandListener;
        this.isDev = isDev;
    }

    @Override
    public void run() {

        if (isDev) {
            try {
                configurator.disableListener(commandListener);
                String groupsAtWork = "```" + dispatcher.getListGroupsAtWork() + "```";
                event.getChannel().sendMessage(Phrase.WHAT_GROUP_TO_DELETE.get() + groupsAtWork);

                GroupDeleteListener groupDeleteListener = new GroupDeleteListener(dispatcher, this);
                configurator.enableNewListener(groupDeleteListener);

            } catch (Exception e) {
                event.getChannel().sendMessage(e.getMessage());
            }

            return;
        }
        event.getChannel().sendMessage(Phrase.NO_RIGHT.get());
    }

    public void end(MessageCreateListener groupDeleteListener) {
        configurator.disableListener(groupDeleteListener);
        configurator.enableNewListener(commandListener);
    }
}
