package ru.codenisst.destiny2pic.bot.handlerproviders;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.codenisst.destiny2pic.bot.handlerproviders.handlers.InspectNewPicture;
import ru.codenisst.destiny2pic.bot.listeners.Command;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

@Component
public class InspectNewPictureCommandHandlerProvider implements CommandHandlerProvider {

    private final VkDispatcher dispatcher;
    private final Command command = Command.INSPECT_NEW_PICTURE;

    @Autowired
    public InspectNewPictureCommandHandlerProvider(VkDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
    @Override
    public Thread getHandler(TextChannel channel) {
        return new InspectNewPicture(channel, dispatcher);
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
