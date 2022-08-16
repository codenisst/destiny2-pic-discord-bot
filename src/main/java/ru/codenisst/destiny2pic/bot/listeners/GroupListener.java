package ru.codenisst.destiny2pic.bot.listeners;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import ru.codenisst.destiny2pic.bot.handlerproviders.handlers.AddGroups;
import ru.codenisst.destiny2pic.bot.speech.Phrase;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class GroupListener implements MessageCreateListener {

    private final VkDispatcher dispatcher;
    private final AddGroups command;
    private final List<String> addedGroups = new ArrayList<>();

    public GroupListener(VkDispatcher dispatcher, AddGroups command) {
        this.dispatcher = dispatcher;
        this.command = command;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String message = event.getMessageContent();
        String[] groupInfo = message.split(" : ");
        TextChannel channel = event.getChannel();

        if (message.equals("!stop")) {
            if (addedGroups.size() > 0) {
                StringJoiner stringJoiner = new StringJoiner("\n");
                for (String link : addedGroups) {
                    stringJoiner.add(link);
                }
                channel.sendMessage(Phrase.WATCHING.get() +
                        "```" + stringJoiner + "```");
            } else {
                channel.sendMessage(Phrase.NOT_FOUND_GROUP.get());
            }
            command.end(this);
            return;
        }

        if (!message.equalsIgnoreCase(Phrase.WHAT_GROUP_TO_ADD.get())
                && !message.equals(Phrase.GROUP_ALREADY_ADDED.get())
                && !message.equals(Phrase.GROUP_ADDED.get())
                && !message.equals(Phrase.INCORRECT_LINK.get())) {

            if (message.startsWith("https://vk.com/") && !groupInfo[0].contains(" ")) {

                try {
                    if (dispatcher.addGroup(groupInfo)) {
                        addedGroups.add(groupInfo[0]);
                        channel.sendMessage(Phrase.GROUP_ADDED.get());
                    } else {
                        channel.sendMessage(Phrase.GROUP_ALREADY_ADDED.get());
                    }
                } catch (Exception e) {
                    channel.sendMessage(Phrase.INCORRECT_LINK.get());
                }
            } else {
                channel.sendMessage(Phrase.INCORRECT_LINK.get());
            }
        }
    }
}
