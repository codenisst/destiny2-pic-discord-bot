package ru.codenisst.discord2pic;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import reactor.core.publisher.Mono;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Bot {

    public static void main(String[] args) throws IOException {
        login();
    }

    private static void login() throws IOException {

        Properties config = new Properties();
        config.load(new FileInputStream("src/main/resources/config/botData.properties"));

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