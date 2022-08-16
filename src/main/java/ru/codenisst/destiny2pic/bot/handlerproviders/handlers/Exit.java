package ru.codenisst.destiny2pic.bot.handlerproviders.handlers;

import org.javacord.api.entity.channel.TextChannel;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.speech.Phrase;

public class Exit extends Thread {

    private final TextChannel channel;
    private final Configurator configurator;

    public Exit(TextChannel channel, Configurator configurator) {
        this.channel = channel;
        this.configurator = configurator;
    }

    @Override
    public void run() {

            try {
                channel.sendMessage(Phrase.DISCONNECT.get());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            configurator.exit();
    }
}
