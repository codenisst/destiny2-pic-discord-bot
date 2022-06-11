package ru.codenisst.destiny2pic.bot.listeners;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.speech.Phrase;
import ru.codenisst.destiny2pic.vk.VkDispatcher;
import ru.codenisst.destiny2pic.vk.models.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class GroupListener implements MessageCreateListener {

    private final VkDispatcher dispatcher;
    private final Configurator configurator;
    private final List<String> namesAndIds = new ArrayList<>();
    private final List<String> messages = new ArrayList<>();

    public GroupListener(Configurator configurator) {
        this.dispatcher = configurator.getDispatcher();
        this.configurator = configurator;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        if (!event.getMessageContent().equalsIgnoreCase(Phrase.GROUP_ADD.get())
                && !event.getMessageContent().equals(Phrase.GROUP_ALREADY_ADDED.get())
                && !messages.contains(event.getMessageContent())) {

            event.deleteMessage();

            String[] groupAndCountPosts = event.getMessageContent().split(" : ");

            if (groupAndCountPosts.length == 2) {

                String[] groupLink = groupAndCountPosts[0].split("/");
                String idOrName = groupLink[groupLink.length - 1];

                if (idOrName.matches("club\\d+$") || idOrName.matches("public\\d+$")) {

                    int id = Integer.parseInt(idOrName.replaceAll("^[a-z]+", ""));
                    if (!namesAndIds.contains(String.valueOf(id))) {
                        Group group = new Group(id, Integer.parseInt(groupAndCountPosts[1]));
                        dispatcher.addGroup(group);
                        namesAndIds.add(String.valueOf(id));
                        String message = "Группа с id - " + id + " добавлена!";
                        messages.add(message);
                        event.getChannel().sendMessage(message);
                    } else {
                        event.getChannel().sendMessage(Phrase.GROUP_ALREADY_ADDED.get());
                    }

                } else {

                    String name = idOrName;
                    if (!namesAndIds.contains(name)) {
                        Group group = new Group(name, Integer.parseInt(groupAndCountPosts[1]));
                        dispatcher.addGroup(group);
                        namesAndIds.add(name);
                        String message = "Группа " + name + " добавлена!";
                        messages.add(message);
                        event.getChannel().sendMessage(message);
                    } else {
                        event.getChannel().sendMessage(Phrase.GROUP_ALREADY_ADDED.get());
                    }
                }
            }
        }

        if (event.getMessageContent().equals("!stop") && namesAndIds.size() > 0) {
            event.deleteMessage();
            StringJoiner stringJoiner = new StringJoiner(", ");
            for (String name : namesAndIds) {
                stringJoiner.add(name);
            }
            event.getChannel().sendMessage("Группа(-ы) " + stringJoiner + " добавлена(-ы)!");
            configurator.disableListener(this);
        }
    }
}
