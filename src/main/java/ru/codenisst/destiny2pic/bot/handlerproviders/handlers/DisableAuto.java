package ru.codenisst.destiny2pic.bot.handlerproviders.handlers;

import org.javacord.api.entity.channel.TextChannel;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.speech.Phrase;

public class DisableAuto extends Thread {

    private final TextChannel channel;
    private final Configurator configurator;

    public DisableAuto(TextChannel channel, Configurator configurator) {
        this.channel = channel;
        this.configurator = configurator;
    }

    @Override
    public void run() {

        if (!configurator.isAutoWork()) {
            channel.sendMessage(Phrase.OFF_AUTO_ERROR.get());
            return;
        }

        configurator.setAutoWork(false);
        channel.sendMessage(Phrase.OFF_AUTO_CONFIRM.get());
    }
}
