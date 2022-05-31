package ru.codenisst.discord2pic;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import reactor.core.publisher.Mono;
import ru.codenisst.discord2pic.vk.models.vkpost.VkPostPicture;
import ru.codenisst.discord2pic.vk.servises.VkDispatcher;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Bot {

    public static void main(String[] args) throws Exception {
        for (VkPostPicture post:VkDispatcher.getNewPostsPicture()) {
            System.out.println(post);
        }
    }

    // Авторизация бота
    private static void login() throws IOException {

        Properties config = new Properties();
        config.load(new FileInputStream("src/main/resources/config/botConfig.properties"));

        try {
            DiscordClient client = DiscordClient.create(config.getProperty("botToken"));
            Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) -> Mono.empty());

            System.out.println("Bot is started.");

            login.block();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}