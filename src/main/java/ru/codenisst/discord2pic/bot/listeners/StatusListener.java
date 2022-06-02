package ru.codenisst.discord2pic.bot.listeners;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import ru.codenisst.discord2pic.bot.Configurator;

public class StatusListener implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        Configurator.setStatus(event.getMessageContent());
        event.getChannel().sendMessage("Теперь я играю в " + event.getMessageContent() + " \uD83D\uDE0E");
        event.deleteMessage();
        Configurator.disableListener(this);
    }
}
