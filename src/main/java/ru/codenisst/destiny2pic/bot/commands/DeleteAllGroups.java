package ru.codenisst.destiny2pic.bot.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import ru.codenisst.destiny2pic.bot.speech.Phrase;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

public class DeleteAllGroups extends Thread {

    private final MessageCreateEvent event;
    private final VkDispatcher dispatcher;
    private final boolean isDev;

    public DeleteAllGroups(MessageCreateEvent event, VkDispatcher dispatcher, boolean isDev) {
        this.event = event;
        this.dispatcher = dispatcher;
        this.isDev = isDev;
    }

    @Override
    public void run() {

        if (isDev) {

            try {
                dispatcher.getListGroupsAtWork();
            } catch (Exception e) {
                event.getChannel().sendMessage(e.getMessage());
                return;
            }

            dispatcher.deleteAllGroup();
            event.getChannel().sendMessage(Phrase.ALL_GROUPS_REMOVED.get());
            return;
        }

        event.getChannel().sendMessage(Phrase.NO_RIGHT.get());
    }
}
