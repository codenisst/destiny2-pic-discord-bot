package ru.codenisst.destiny2pic.bot.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import ru.codenisst.destiny2pic.bot.commands.moves.Request;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

public class Picture extends Thread {

    private final Request request;
    private final MessageCreateEvent event;

    public Picture(MessageCreateEvent event, VkDispatcher dispatcher) {
        this.request = new Request(dispatcher);
        this.event = event;
    }

    @Override
    public void run() {

        request.sendNewPictures(event);
    }
}
