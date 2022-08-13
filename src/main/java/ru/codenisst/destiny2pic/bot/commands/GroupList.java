package ru.codenisst.destiny2pic.bot.commands;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import ru.codenisst.destiny2pic.bot.speech.Phrase;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

public class GroupList extends Thread {

    private final MessageCreateEvent event;
    private final VkDispatcher dispatcher;
    private final boolean isDev;

    public GroupList(MessageCreateEvent event, VkDispatcher dispatcher, boolean isDev) {
        this.event = event;
        this.dispatcher = dispatcher;
        this.isDev = isDev;
    }

    @Override
    public void run() {

        if (isDev) {
            try {
                String groupsAtWork = dispatcher.getListGroupsAtWork();
                new MessageBuilder().append(Phrase.GROUP_INFO.get())
                        .append("\n```" + groupsAtWork + "```")
                        .send(event.getChannel());
            } catch (Exception e) {
                event.getChannel().sendMessage(e.getMessage());
            }
            return;
        }
        event.getChannel().sendMessage(Phrase.NO_RIGHT.get());
    }
}
