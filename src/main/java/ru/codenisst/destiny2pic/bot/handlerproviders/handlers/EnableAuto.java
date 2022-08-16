package ru.codenisst.destiny2pic.bot.handlerproviders.handlers;

import org.javacord.api.entity.channel.TextChannel;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.handlerproviders.handlers.moves.Request;
import ru.codenisst.destiny2pic.bot.speech.Phrase;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

public class EnableAuto extends Thread {

    private final TextChannel channel;
    private final Configurator configurator;
    private final VkDispatcher dispatcher;

    public EnableAuto(TextChannel channel, Configurator configurator, VkDispatcher dispatcher) {
        this.channel = channel;
        this.configurator = configurator;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {

        if (configurator.isAutoWork()) {
            channel.sendMessage(Phrase.AUTO_ERROR.get());
            return;
        }

        configurator.setAutoWork(true);

        channel.sendMessage("Я буду оповещать тебя о новых постах с пикчами каждые 30 минут!");

        while (configurator.isAutoWork()) {
            try {

                new Request(dispatcher).sendNewPictures(channel);
                Thread.sleep(1800000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
