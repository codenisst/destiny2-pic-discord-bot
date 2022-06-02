package ru.codenisst.discord2pic.bot.moves;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import ru.codenisst.discord2pic.bot.Configurator;
import ru.codenisst.discord2pic.bot.speech.Phrase;
import ru.codenisst.discord2pic.vk.VkDispatcher;
import ru.codenisst.discord2pic.vk.models.vkpost.VkPostContent;

import java.util.List;

public class Auto {

    public static void enableAutoWork(MessageCreateEvent event) {
        new Thread(() -> {
            try {
                while (Configurator.isAutoWork()) {
                    List<VkPostContent> newPic = VkDispatcher.getNewPostsPicture();
                    if (newPic.size() > 0) {
                        new MessageBuilder().append(Phrase.NEW_POST.get()).send(event.getChannel());
                        Request.createMessagePart(event, newPic);
                    }
                    Thread.sleep(1800000);
                }
            } catch (Exception e) {
                event.getChannel().sendMessage(Phrase.ALARM.get() + e.getMessage());
            }
        }).start();
    }
}
