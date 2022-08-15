package ru.codenisst.destiny2pic.bot.handlerproviders.handlers.moves;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import ru.codenisst.destiny2pic.bot.speech.Phrase;
import ru.codenisst.destiny2pic.vk.VkDispatcher;
import ru.codenisst.destiny2pic.vk.models.Content;
import ru.codenisst.destiny2pic.vk.models.Post;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Request {

    private final VkDispatcher vkDispatcher;

    public Request(VkDispatcher vkDispatcher) {
        this.vkDispatcher = vkDispatcher;
    }

    public void sendNewPictures(TextChannel channel) {
        try {
            List<Post> posts = vkDispatcher.getNewPostsWithPictures();
            if (posts.size() > 0) {
                new MessageBuilder().append(Phrase.NEW_POST.get()).send(channel);
                createMessagePart(channel, posts);
            } else {
                new MessageBuilder().append(Phrase.NOT_FOUND.get())
                        .send(channel).thenAccept(message ->
                                message.addReaction("\uD83D\uDE22"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            channel.sendMessage(Phrase.ALARM.get() + e.getMessage());
        }
    }

    private void createMessagePart(TextChannel channel, List<Post> posts) throws IOException {
        for (Post post : posts) {
            MessageBuilder messagePost = new MessageBuilder().
                    append(Phrase.DELIMITER.get()).append(post.getText());
            List<Content> contents = post.getContentList();
            for (Content content : contents) {
                messagePost.addAttachment(ImageIO.read(new URL(content.getUrl())), "image.jpg");
            }
            messagePost.send(channel).thenAccept(message ->
                    message.addReactions("\t\uD83D\uDE0D", "\uD83E\uDD2E"));
        }
    }
}
