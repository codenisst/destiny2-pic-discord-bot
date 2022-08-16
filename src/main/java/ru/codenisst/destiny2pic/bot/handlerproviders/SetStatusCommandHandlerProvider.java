package ru.codenisst.destiny2pic.bot.handlerproviders;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.handlerproviders.handlers.SetStatus;
import ru.codenisst.destiny2pic.bot.listeners.Command;
import ru.codenisst.destiny2pic.bot.listeners.CommandListener;

import java.util.List;

@Component
public class SetStatusCommandHandlerProvider implements CommandHandlerProvider{

    private final Configurator configurator;
    private CommandListener commandListener;
    private final Command command = Command.SET_STATUS;

    @Autowired
    public SetStatusCommandHandlerProvider(Configurator configurator) {
        this.configurator = configurator;
    }

    @Override
    public void setCommandListener(CommandListener commandListener) {
        this.commandListener = commandListener;
    }

    @Override
    public Thread getHandler(TextChannel channel) {
        return new SetStatus(channel, configurator, commandListener);
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
