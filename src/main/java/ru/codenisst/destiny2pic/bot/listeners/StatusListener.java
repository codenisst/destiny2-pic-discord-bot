package ru.codenisst.destiny2pic.bot.listeners;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.handlerproviders.handlers.SetStatus;
import ru.codenisst.destiny2pic.bot.speech.Phrase;

public class StatusListener implements MessageCreateListener {

    private final Configurator configurator;
    private final SetStatus status;

    public StatusListener(Configurator configurator, SetStatus status) {
        this.configurator = configurator;
        this.status = status;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        if (!event.getMessageContent().equals(Phrase.PLAYING.get())) {
            configurator.setStatus(event.getMessageContent());
            event.getChannel().sendMessage("Теперь я играю в " +
                    event.getMessageContent() + " \uD83D\uDE0E");
            status.end(this);
        }
    }
}
