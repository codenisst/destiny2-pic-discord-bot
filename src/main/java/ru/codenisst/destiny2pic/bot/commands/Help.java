package ru.codenisst.destiny2pic.bot.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import ru.codenisst.destiny2pic.bot.speech.Phrase;

public class Help extends Thread{

    private final MessageCreateEvent event;

    public Help(MessageCreateEvent event) {
        this.event = event;
    }

    @Override
    public void run() {

        event.getChannel().sendMessage(Phrase.HELP.get());

    }
}
