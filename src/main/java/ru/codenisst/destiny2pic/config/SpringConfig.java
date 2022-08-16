package ru.codenisst.destiny2pic.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import ru.codenisst.destiny2pic.bot.handlerproviders.CommandHandlerProvider;
import ru.codenisst.destiny2pic.vk.models.Group;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan("ru.codenisst.destiny2pic")
@PropertySource("classpath:config/botConfig.properties")
@PropertySources({
        @PropertySource("classpath:config/botConfig.properties"),
        @PropertySource("classpath:config/vkConfig.properties"),
        @PropertySource("classpath:config/dbConfig.properties")})
public class SpringConfig {

    @Bean
    public VkApiClient getVkApiClient() {
        return new VkApiClient(getHttpTransportClient());
    }

    @Bean
    public UserActor getUserActor(@Value("${userId}") int userId,
                                  @Value("${accessToken}") String accessToken) {
        return new UserActor(userId, accessToken);
    }

    @Bean
    public HttpTransportClient getHttpTransportClient() {
        return new HttpTransportClient();
    }

    @Bean
    public Gson getGson() {
        return new GsonBuilder().create();
    }

    @Bean
    public List<Group> getGroupList() {
        return new ArrayList<>();
    }

    @Bean
    public Connection getConnection(@Value("${dbUrl}") String dbUrl)
            throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(dbUrl);
    }

    @Bean
    public DiscordApi getDiscordApi(@Value("${botToken}") String botToken) {
        return new DiscordApiBuilder().setToken(botToken).login().join();
    }

    @Bean("handlerProviders")
    public Map<String, CommandHandlerProvider> getHandlerProviders(List<CommandHandlerProvider> handlerProviders) {
        Map<String, CommandHandlerProvider> resultBean = new HashMap<>();

        for (CommandHandlerProvider provider:handlerProviders) {
            resultBean.put(provider.getCommand().getCommandName(), provider);
        }

        return resultBean;
    }
}
