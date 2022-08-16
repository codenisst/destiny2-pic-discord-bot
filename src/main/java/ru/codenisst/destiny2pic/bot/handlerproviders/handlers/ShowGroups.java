package ru.codenisst.destiny2pic.bot.handlerproviders.handlers;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import ru.codenisst.destiny2pic.bot.speech.Phrase;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

public class ShowGroups extends Thread {

    private final TextChannel channel;
    private final VkDispatcher dispatcher;

    public ShowGroups(TextChannel channel, VkDispatcher dispatcher) {
        this.channel = channel;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {

        try {
            String groupsAtWork = dispatcher.getListGroupsAtWork();
            new MessageBuilder().append(Phrase.GROUP_INFO.get())
                    .append("\n```" + groupsAtWork + "```")
                    .send(channel);
        } catch (Exception e) {
            channel.sendMessage(e.getMessage());
        }
    }
}
