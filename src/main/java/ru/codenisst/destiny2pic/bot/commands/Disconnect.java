package ru.codenisst.destiny2pic.bot.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.speech.Phrase;

public class Disconnect extends Thread {

    private final MessageCreateEvent event;
    private final Configurator configurator;
    private final boolean isDev;

    public Disconnect(MessageCreateEvent event, Configurator configurator, boolean isDev) {
        this.event = event;
        this.configurator = configurator;
        this.isDev = isDev;
    }

    @Override
    public void run() {

        if (isDev) {

            try {
                event.getChannel().sendMessage(Phrase.DISCONNECT.get());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            configurator.disconnect();

            return;
        }

        event.getChannel().sendMessage(Phrase.NO_RIGHT.get());
    }
}
