package ru.codenisst.destiny2pic.bot.handlerproviders.handlers;

import org.javacord.api.entity.channel.TextChannel;
import ru.codenisst.destiny2pic.bot.handlerproviders.handlers.moves.Request;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

public class InspectNewPicture extends Thread {

    private final Request request;
    private final TextChannel channel;

    public InspectNewPicture(TextChannel channel, VkDispatcher dispatcher) {
        this.request = new Request(dispatcher);
        this.channel = channel;
    }

    @Override
    public void run() {
        request.sendNewPictures(channel);
    }
}
