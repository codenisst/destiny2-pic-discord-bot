package ru.codenisst.destiny2pic.bot.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.bot.commands.moves.Request;
import ru.codenisst.destiny2pic.bot.speech.Phrase;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

public class Auto extends Thread{

    private final MessageCreateEvent event;
    private final Configurator configurator;
    private final VkDispatcher dispatcher;
    private final String[] command;
    private final boolean isDev;

    public Auto(MessageCreateEvent event, Configurator configurator,
                VkDispatcher dispatcher, String[] command, boolean isDev) {
        this.event = event;
        this.configurator = configurator;
        this.dispatcher = dispatcher;
        this.command = command;
        this.isDev = isDev;
    }


    @Override
    public void run() {

        if(isDev) {

            if (configurator.isAutoWork()) {
                event.getChannel().sendMessage(Phrase.AUTO_ERROR.get());
                return;
            }

            configurator.setAutoWork(true);

            int minutes = 1800000;

            if (command.length > 1) {
                try {
                    minutes = Integer.parseInt(command[1]) * 60000;
                } catch (NumberFormatException e) {
                    event.getChannel().sendMessage("Некорректно указано время таймера!");
                    return;
                }
            }

            event.getChannel().sendMessage("Я буду оповещать тебя о новостях каждые " +
                    minutes/60000 + " минут(ы)!");

            while(configurator.isAutoWork()) {
                try {

                    new Request(dispatcher).sendNewPictures(event);
                    Thread.sleep(minutes);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return;
        }

        event.getChannel().sendMessage(Phrase.NO_RIGHT.get());
    }
}
