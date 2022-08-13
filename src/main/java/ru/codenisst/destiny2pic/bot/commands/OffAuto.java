package ru.codenisst.destiny2pic.bot.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.speech.Phrase;

public class OffAuto extends Thread{

    private final MessageCreateEvent event;
    private final Configurator configurator;
    private final boolean isDev;

    public OffAuto(MessageCreateEvent event, Configurator configurator, boolean isDev) {
        this.event = event;
        this.configurator = configurator;
        this.isDev = isDev;
    }

    @Override
    public void run() {

        if(isDev) {

            if(!configurator.isAutoWork()) {
                event.getChannel().sendMessage(Phrase.OFF_AUTO_ERROR.get());
                return;
            }

            configurator.setAutoWork(false);
            event.getChannel().sendMessage(Phrase.OFF_AUTO_CONFIRM.get());
            return;
        }

        event.getChannel().sendMessage(Phrase.NO_RIGHT.get());
    }
}
