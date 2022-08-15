package ru.codenisst.destiny2pic.bot.handlerproviders;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.codenisst.destiny2pic.bot.handlerproviders.handlers.ShowGroups;
import ru.codenisst.destiny2pic.bot.listeners.Command;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

@Component
public class ShowGroupsCommandHandlerProvider implements CommandHandlerProvider{

    private final VkDispatcher dispatcher;
    private final Command command = Command.SHOW_GROUPS;

    @Autowired
    public ShowGroupsCommandHandlerProvider(VkDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public Thread getHandler(TextChannel channel) {
        return new ShowGroups(channel, dispatcher);
    }

    @Override
    public Command getCommand() {
        return command;
    }

    @Override
    public boolean isCommandAllowed(MessageCreateEvent event) {
        return true;
    }
}
