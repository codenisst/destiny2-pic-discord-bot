package ru.codenisst.destiny2pic.bot.handlerproviders.handlers;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.listener.message.MessageCreateListener;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.listeners.CommandListener;
import ru.codenisst.destiny2pic.bot.listeners.GroupListener;
import ru.codenisst.destiny2pic.bot.speech.Phrase;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

public class AddGroups extends Thread {

    private final TextChannel channel;
    private final Configurator configurator;
    private final VkDispatcher dispatcher;
    private final CommandListener commandListener;

    public AddGroups(TextChannel channel, Configurator configurator, VkDispatcher dispatcher,
                     CommandListener commandListener) {
        this.channel = channel;
        this.configurator = configurator;
        this.dispatcher = dispatcher;
        this.commandListener = commandListener;
    }

    @Override
    public void run() {
        configurator.disableListener(commandListener);
        channel.sendMessage(Phrase.WHAT_GROUP_TO_ADD.get());
        configurator.enableNewListener(new GroupListener(dispatcher, this));
    }

    public void end(MessageCreateListener groupListener) {
        configurator.disableListener(groupListener);
        configurator.enableNewListener(commandListener);
    }
}
