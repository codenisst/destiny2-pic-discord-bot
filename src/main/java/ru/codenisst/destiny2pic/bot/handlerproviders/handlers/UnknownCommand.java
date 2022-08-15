package ru.codenisst.destiny2pic.bot.handlerproviders.handlers;

import org.javacord.api.event.message.MessageCreateEvent;
import ru.codenisst.destiny2pic.bot.speech.Phrase;

public class UnknownCommand extends Thread{

    private final MessageCreateEvent event;

    public UnknownCommand(MessageCreateEvent event) {
        this.event = event;
    }

    @Override
    public void run() {
        event.getChannel().sendMessage(Phrase.UNKNOWN_COMMAND.get());
    }
}
