package ru.codenisst.destiny2pic.bot.listeners;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import ru.codenisst.destiny2pic.bot.handlerproviders.handlers.DeleteGroups;
import ru.codenisst.destiny2pic.bot.speech.Phrase;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class GroupDeleteListener implements MessageCreateListener {


    private final VkDispatcher dispatcher;
    private final DeleteGroups command;
    private final List<String> namesOfDeletedGroups;

    public GroupDeleteListener(VkDispatcher dispatcher, DeleteGroups command) {
        this.dispatcher = dispatcher;
        this.command = command;
        this.namesOfDeletedGroups = new ArrayList<>();
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String message = event.getMessageContent();

        if (message.equals("!stop")) {
            StringJoiner stringJoiner = new StringJoiner(", ");
            if (namesOfDeletedGroups.size() > 0) {
                for (String group : namesOfDeletedGroups) {
                    stringJoiner.add(group);
                }
                event.getChannel().sendMessage("Группы " + stringJoiner + " были удалены!");
            } else {
                event.getChannel().sendMessage(Phrase.NOTHING_DELETE.get());
            }

            command.end(this);
            return;
        }

        if (!message.startsWith(Phrase.WHAT_GROUP_TO_DELETE.get())
                && !message.equals(Phrase.GROUP_IS_DELETED.get())
                && !message.equals(Phrase.GROUP_MISSING.get())) {
            try {
                namesOfDeletedGroups.add(dispatcher.deleteGroup(message));
                event.getChannel().sendMessage(Phrase.GROUP_IS_DELETED.get());
            } catch (Exception e) {
                event.getChannel().sendMessage(Phrase.GROUP_MISSING.get());
            }
        }
    }
}
