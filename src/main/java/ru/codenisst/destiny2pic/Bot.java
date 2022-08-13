package ru.codenisst.destiny2pic;

import ru.codenisst.destiny2pic.bot.Configurator;
import ru.codenisst.destiny2pic.vk.VkDispatcher;

public class Bot {

    public static void main(String[] args) {
        VkDispatcher dispatcher = new VkDispatcher(
                "jdbc:sqlite:databasepost.sqlite",
                "src/main/resources/config/vkConfig.properties");

        Configurator configurator = new Configurator(
                "src/main/resources/config/botConfig.properties", dispatcher);

        configurator.start();
    }
}
