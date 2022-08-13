package ru.codenisst.destiny2pic.bot.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.listeners.CommandListener;
import ru.codenisst.destiny2pic.bot.listeners.StatusListener;
import ru.codenisst.destiny2pic.bot.speech.Phrase;

public class Status extends Thread{

    private final MessageCreateEvent event;
    private final Configurator configurator;
    private final CommandListener commandListener;
    private final boolean isDev;

    public Status(MessageCreateEvent event, Configurator configurator, CommandListener commandListener, boolean isDev) {
        this.event = event;
        this.configurator = configurator;
        this.commandListener = commandListener;
        this.isDev = isDev;
    }

    @Override
    public void run() {

        if(isDev) {
            event.getChannel().sendMessage(Phrase.PLAYING.get());
            configurator.disableListener(commandListener);
            configurator.enableNewListener(new StatusListener(configurator, this));

            return;
        }

        event.getChannel().sendMessage(Phrase.NO_RIGHT.get());
    }

    public void end(MessageCreateListener listener) {
        configurator.disableListener(listener);
        configurator.enableNewListener(commandListener);
    }
}
