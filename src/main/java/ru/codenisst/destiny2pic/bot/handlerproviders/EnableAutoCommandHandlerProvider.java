package ru.codenisst.destiny2pic.bot.handlerproviders;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.handlerproviders.handlers.EnableAuto;
import ru.codenisst.destiny2pic.bot.listeners.Command;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

import java.util.List;

@Component
public class EnableAutoCommandHandlerProvider implements CommandHandlerProvider{

    private final VkDispatcher dispatcher;
    private final Configurator configurator;
    private final Command command = Command.ENABLE_AUTO;

    @Autowired
    public EnableAutoCommandHandlerProvider(VkDispatcher dispatcher,
                                            Configurator configurator) {
        this.dispatcher = dispatcher;
        this.configurator = configurator;
    }

    @Override
    public Thread getHandler(TextChannel channel) {
        return new EnableAuto(channel, configurator, dispatcher);
    }

    @Override
    public Command getCommand() {
        return command;
    }

    @Override
    public boolean isCommandAllowed(MessageCreateEvent event) {
        List<Role> eventUserRoles = event.getMessage()
                .getAuthor()
                .asUser()
                .get()
                .getRoles(event
                        .getServer()
                        .get());
        for (Role role : eventUserRoles) {
            if (role.getName().equalsIgnoreCase(configurator.getMainRole())) {
                return true;
            }
        }
        return false;
    }
}
