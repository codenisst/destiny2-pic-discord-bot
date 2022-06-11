package ru.codenisst.destiny2pic.bot.moves;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.speech.Phrase;
import ru.codenisst.destiny2pic.vk.VkDispatcher;
import ru.codenisst.destiny2pic.vk.models.Post;

import java.util.List;

public class AutoRequest {

    private final Configurator configurator;
    private final VkDispatcher vkDispatcher;

    public AutoRequest(Configurator configurator, VkDispatcher vkDispatcher) {
        this.configurator = configurator;
        this.vkDispatcher = vkDispatcher;
    }

    public void enableAutoSearchPictures(Request request, MessageCreateEvent event) {
        new Thread(() -> {
            try {
                while (configurator.isAutoWork()) {
                    List<Post> posts = vkDispatcher.getNewPostsWithPictures();
                    if (posts.size() > 0) {
                        new MessageBuilder().append(Phrase.NEW_POST.get()).send(event.getChannel());
                        request.createMessagePart(event, posts);
                    }
                    Thread.sleep(1800000);
                }
            } catch (Exception e) {
                event.getChannel().sendMessage(Phrase.ALARM.get() + e.getMessage());
            }
        }).start();
    }
}
