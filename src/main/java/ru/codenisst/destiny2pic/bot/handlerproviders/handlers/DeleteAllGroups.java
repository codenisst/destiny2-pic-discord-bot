package ru.codenisst.destiny2pic.bot.handlerproviders.handlers;

import org.javacord.api.entity.channel.TextChannel;
import ru.codenisst.destiny2pic.bot.speech.Phrase;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

public class DeleteAllGroups extends Thread {

    private final TextChannel channel;
    private final VkDispatcher dispatcher;

    public DeleteAllGroups(TextChannel channel, VkDispatcher dispatcher) {
        this.channel = channel;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {

            try {
                dispatcher.getListGroupsAtWork();
            } catch (Exception e) {
                channel.sendMessage(e.getMessage());
                return;
            }

            dispatcher.deleteAllGroup();
            channel.sendMessage(Phrase.ALL_GROUPS_REMOVED.get());
    }
}
