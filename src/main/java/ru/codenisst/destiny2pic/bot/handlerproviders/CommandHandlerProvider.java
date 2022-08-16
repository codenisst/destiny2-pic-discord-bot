package ru.codenisst.destiny2pic.bot.handlerproviders;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.event.message.MessageCreateEvent;
import ru.codenisst.destiny2pic.bot.listeners.Command;
import ru.codenisst.destiny2pic.bot.listeners.CommandListener;

public interface CommandHandlerProvider {

    Thread getHandler(TextChannel channel);
    Command getCommand();
    boolean isCommandAllowed(MessageCreateEvent event);
    default void setCommandListener(CommandListener commandListener){}
}
