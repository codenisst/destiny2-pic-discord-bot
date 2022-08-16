package ru.codenisst.destiny2pic.bot.handlerproviders.handlers;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.listener.message.MessageCreateListener;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.listeners.CommandListener;
import ru.codenisst.destiny2pic.bot.listeners.StatusListener;
import ru.codenisst.destiny2pic.bot.speech.Phrase;

public class SetStatus extends Thread{

    private final TextChannel channel;
    private final Configurator configurator;
    private final CommandListener commandListener;

    public SetStatus(TextChannel channel, Configurator configurator, CommandListener commandListener) {
        this.channel = channel;
        this.configurator = configurator;
        this.commandListener = commandListener;
    }

    @Override
    public void run() {
            channel.sendMessage(Phrase.PLAYING.get());
            configurator.disableListener(commandListener);
            configurator.enableNewListener(new StatusListener(configurator, this));
    }

    public void end(MessageCreateListener listener) {
        configurator.disableListener(listener);
        configurator.enableNewListener(commandListener);
    }
}
