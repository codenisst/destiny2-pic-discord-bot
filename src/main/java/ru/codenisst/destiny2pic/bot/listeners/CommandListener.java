package ru.codenisst.destiny2pic.bot.listeners;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.handlerproviders.CommandHandlerProvider;
import ru.codenisst.destiny2pic.bot.handlerproviders.handlers.UnknownCommand;
import ru.codenisst.destiny2pic.bot.speech.Phrase;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

@Component
public class CommandListener implements MessageCreateListener {

    @Resource(name = "handlerProviders")
    private final Map<String, CommandHandlerProvider> handlerProviders;
    private final Configurator configurator;

    @Autowired
    public CommandListener(Map<String, CommandHandlerProvider> handlerProviders,
                           Configurator configurator) {
        this.handlerProviders = handlerProviders;
        this.configurator = configurator;
    }

    @PostConstruct
    public void init() {
        configurator.enableCommandListener(this);
        for (String key : handlerProviders.keySet()) {
            handlerProviders.get(key).setCommandListener(this);
        }
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String[] command = event.getMessageContent().split(" ");
        TextChannel channel = event.getChannel();

        if (!command[0].matches("^!\\D+$")) {
            return;
        }

        CommandHandlerProvider handlerProvider = handlerProviders.get(command[0]);

        if (handlerProvider == null) {
            new UnknownCommand(event).start();
            return;
        }

        if (!handlerProvider.isCommandAllowed(event)) {
            channel.sendMessage(Phrase.NO_RIGHT.get());
            return;
        }

        handlerProvider.getHandler(channel).start();
    }
}
