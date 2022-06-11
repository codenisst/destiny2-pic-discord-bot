package ru.codenisst.destiny2pic.bot.moves;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import ru.codenisst.destiny2pic.bot.speech.Phrase;
import ru.codenisst.destiny2pic.vk.VkDispatcher;
import ru.codenisst.destiny2pic.vk.models.Content;
import ru.codenisst.destiny2pic.vk.models.Post;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Request {

    private VkDispatcher vkDispatcher;

    public Request( VkDispatcher vkDispatcher) {
        this.vkDispatcher = vkDispatcher;
    }

    public void sendNewPictures(MessageCreateEvent event) {
        try {
            List<Post> posts = vkDispatcher.getNewPostsWithPictures();
            if (posts.size() > 0) {
                new MessageBuilder().append(Phrase.NEW_POST.get()).send(event.getChannel());
                createMessagePart(event, posts);
            } else {
                new MessageBuilder().append(Phrase.NOT_FOUND.get())
                        .send(event.getChannel()).thenAccept(message ->
                                message.addReaction("\uD83D\uDE22"));
            }
        } catch (Exception e) {
            event.getChannel().sendMessage(Phrase.ALARM.get() + e.getMessage());
        }
    }

    public void createMessagePart(MessageCreateEvent event, List<Post> posts) throws IOException {
        for (Post post : posts) {
            MessageBuilder messagePost = new MessageBuilder().
                    append(Phrase.DELIMITER.get()).append(post.getText());
            List<Content> contents = post.getContentList();
            for (Content content : contents) {
                messagePost.addAttachment(ImageIO.read(new URL(content.getUrl())), "image.jpg");
            }
            messagePost.send(event.getChannel()).thenAccept(message ->
                    message.addReactions("\t\uD83D\uDE0D", "\uD83E\uDD2E"));
        }
    }
}
