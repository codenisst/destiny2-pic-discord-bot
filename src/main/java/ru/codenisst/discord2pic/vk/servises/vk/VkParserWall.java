package ru.codenisst.discord2pic.vk.servises.vk;

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
import ru.codenisst.discord2pic.vk.models.vkpost.VkPostModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//  Парсер постов со стены паблика в VK
public class VkParserWall {

    // Конфигурация с данными пользователя для парсинга
    private static final Properties CONFIG = new Properties();
    private static final String ERROR = "Введены неверные данные пользователя для парсинга!\n" +
            "Проверьте файл vkConfig.properties!";

    static {
        try {
            FileInputStream input = new FileInputStream(
                    "src/main/resources/config/vkConfig.properties");
            CONFIG.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Отправляет GET запрос, используя конфигурацию. Возвращает список JSON-записей
    // из группы в VK (по умолчанию - 25 штук)
    private static List<WallpostFull> sendGet() {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        UserActor user = new UserActor(Integer.parseInt(CONFIG.getProperty("userId")),
                CONFIG.getProperty("access_token"));
        try {
            GetResponse get = vk.wall().get(user).domain(CONFIG.getProperty("domain"))
                    .count(25).filter(GetFilter.OWNER).execute();
            return get.getItems();
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Возвращает список моделей VK-постов из списка JSON-записей
    public static List<VkPostModel> getListVkPosts() throws Exception {
        List<VkPostModel> result = new ArrayList<>();
        List<WallpostFull> wallpostFullList;
        if ((wallpostFullList = sendGet()) == null) {
            throw new Exception(ERROR);
        }
        for (WallpostFull postData : wallpostFullList) {
            Gson gson = new GsonBuilder().create();
            JsonObject jObject = (JsonObject) new JsonParser().parse(postData.toString());
            VkPostModel post = gson.fromJson(jObject, VkPostModel.class);
            result.add(post);
        }
        return result;
    }
}
