package ru.codenisst.discord2pic.bot.moves;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import ru.codenisst.discord2pic.bot.speech.Phrase;
import ru.codenisst.discord2pic.vk.VkDispatcher;
import ru.codenisst.discord2pic.vk.models.vkpost.VkPostContent;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Request {

    public static void sendNewPictures(MessageCreateEvent event) {
        try {
            List<VkPostContent> newPic = VkDispatcher.getNewPostsPicture();
            if (newPic.size() > 0) {
                new MessageBuilder().append(Phrase.NEW_POST.get()).send(event.getChannel());
                createMessagePart(event, newPic);
            } else {
                new MessageBuilder().append(Phrase.NOT_FOUND.get())
                        .send(event.getChannel()).thenAccept(message ->
                                message.addReaction("\uD83D\uDE22"));
            }
        } catch (Exception e) {
            event.getChannel().sendMessage(Phrase.ALARM.get() + e.getMessage());
        }
    }

    static void createMessagePart(MessageCreateEvent event, List<VkPostContent> newPic) throws IOException {
        for (VkPostContent post : newPic) {
            MessageBuilder messagePost = new MessageBuilder().
                    append(Phrase.DELIMITER.get()).append(post.getText());
            List<String> urls = post.getUrlContent();
            for (String url : urls) {
                messagePost.addAttachment(ImageIO.read(new URL(url)), "image.jpg");
            }
            messagePost.send(event.getChannel()).thenAccept(message ->
                    message.addReactions("\t\uD83D\uDE0D", "\uD83E\uDD2E"));
        }
    }
}
