package ru.codenisst.destiny2pic.bot.handlerproviders.handlers;

import org.javacord.api.entity.channel.TextChannel;
import ru.codenisst.destiny2pic.bot.speech.Phrase;

public class GetHelp extends Thread{

    private final TextChannel channel;

    public GetHelp(TextChannel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        channel.sendMessage(Phrase.HELP.get());
    }
}
