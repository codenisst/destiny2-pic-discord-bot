package ru.codenisst.destiny2pic.bot.handlerproviders.handlers;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.listener.message.MessageCreateListener;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.listeners.CommandListener;
import ru.codenisst.destiny2pic.bot.listeners.GroupDeleteListener;
import ru.codenisst.destiny2pic.bot.speech.Phrase;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

public class DeleteGroups extends Thread {

    private final TextChannel channel;
    private final Configurator configurator;
    private final VkDispatcher dispatcher;
    private final CommandListener commandListener;

    public DeleteGroups(TextChannel channel, Configurator configurator, VkDispatcher dispatcher,
                        CommandListener commandListener) {
        this.channel = channel;
        this.configurator = configurator;
        this.dispatcher = dispatcher;
        this.commandListener = commandListener;
    }

    @Override
    public void run() {

        try {
            configurator.disableListener(commandListener);
            String groupsAtWork = "```" + dispatcher.getListGroupsAtWork() + "```";
            channel.sendMessage(Phrase.WHAT_GROUP_TO_DELETE.get() + groupsAtWork);

            GroupDeleteListener groupDeleteListener = new GroupDeleteListener(dispatcher, this);
            configurator.enableNewListener(groupDeleteListener);

        } catch (Exception e) {
            channel.sendMessage(e.getMessage());
        }

    }

    public void end(MessageCreateListener groupDeleteListener) {
        configurator.disableListener(groupDeleteListener);
        configurator.enableNewListener(commandListener);
    }
}
