package ru.codenisst.discord2pic.vk.servises;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.wall.WallpostFull;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import com.vk.api.sdk.objects.wall.GetFilter;
import ru.codenisst.discord2pic.vk.models.post.VkPost;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class VkParserWall {

    private static FileInputStream input;
    private static Properties config;

    static {
        try {
            input = new FileInputStream("src/main/resources/config/vkData.properties");
            config = new Properties();
            config.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<WallpostFull> sendGet() {

        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);

        UserActor user = new UserActor(Integer.parseInt(config.getProperty("userId")),
                config.getProperty("access_token"));
        try {
            GetResponse get = vk.wall().get(user).domain(config.getProperty("domain")).count(50)
                    .filter(GetFilter.OWNER).execute();
            return get.getItems();
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<VkPost> getListVkPosts() {
        List<VkPost> result = new ArrayList<>();
        for (WallpostFull postData:sendGet()) {
            Gson gson = new GsonBuilder().create();
            JsonObject jObject = (JsonObject) new JsonParser().parse(postData.toString());
            VkPost post = gson.fromJson(jObject, VkPost.class);
            result.add(post);
        }
        return result;
    }
}
