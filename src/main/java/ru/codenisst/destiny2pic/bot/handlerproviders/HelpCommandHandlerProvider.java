package ru.codenisst.destiny2pic.bot.handlerproviders;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Component;
import ru.codenisst.destiny2pic.bot.handlerproviders.handlers.GetHelp;
import ru.codenisst.destiny2pic.bot.listeners.Command;

@Component
public class HelpCommandHandlerProvider implements CommandHandlerProvider{

    private final Command command = Command.GET_HELP;

    @Override
    public Thread getHandler(TextChannel channel) {
        return new GetHelp(channel);
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
