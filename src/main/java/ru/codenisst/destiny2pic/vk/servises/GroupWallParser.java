package ru.codenisst.destiny2pic.vk.servises;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.wall.GetFilter;
import com.vk.api.sdk.objects.wall.WallpostFull;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import ru.codenisst.destiny2pic.vk.models.Group;
import ru.codenisst.destiny2pic.vk.models.postbody.Item;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GroupWallParser {

    private final String ERROR = "Введены неверные данные пользователя для парсинга!\n" +
            "Проверьте файл vkConfig.properties!";
    private final VkApiClient vkApi;
    private List<Group> groups;
    private final UserActor user;
    private final Gson gson;
    private final int DEFAULT_COUNT = 25;

    public GroupWallParser(VkApiClient vkApi) {
        try {
            Properties config = new Properties();
            config.load(new FileInputStream("src/main/resources/config/vkConfig.properties"));
            this.vkApi = vkApi;
            this.groups = new ArrayList<>();
            user = new UserActor(Integer.parseInt(config.getProperty("userId")),
                    config.getProperty("access_token"));
            gson = new GsonBuilder().create();
        } catch (IOException e) {
            throw new RuntimeException(ERROR);
        }
    }

    public GroupWallParser(VkApiClient vkApi, List<Group> groups) {
        this(vkApi);
        this.groups = groups;
    }

    public List<Item> getListPosts() throws Exception {
        List<WallpostFull> wallpostFullList = getWallposts(groups);
        List<Item> result = new ArrayList<>();
        if (wallpostFullList.size() != 0) {
            for (WallpostFull postData : wallpostFullList) {
                Item post = gson.fromJson(postData.toString(), Item.class);
                result.add(post);
            }
            return result;
        }
        throw new Exception(ERROR + " : getListPosts()");
    }

    public void addGroup(Group group) {
        // если у группы нет id - получить id для этой группы

        this.groups.add(group);
    }

    public void removeGroup(Group group) {
        // удалить группу из списка групп парсера, у которой совпадает имя или id

        this.groups.remove(group);
    }

    public void removeAllGroups() {
        this.groups.clear();
    }

    // default - 25
    private List<WallpostFull> getWallposts(List<Group> groups) throws Exception {
        List<WallpostFull> result = new ArrayList<>();
        try {
            for (Group group : groups) {
                int postCount = (group.getParsedPostsQuantity() != 0)
                        ? group.getParsedPostsQuantity()
                        : DEFAULT_COUNT;
                GetResponse get = group.haveName()
                        ? getResponseByName(group, postCount)
                        : getResponseById(group, postCount);
                result.addAll(get.getItems());
            }
            return result;
        } catch (NullPointerException e) {
            throw new Exception("Список групп пуст!");
        }
    }

    private GetResponse getResponseById(Group group, int postCount) throws ClientException, ApiException {
        return vkApi.wall()
                .get(user)
                .ownerId(Integer.parseInt("-" + group.getId()))
                .count(postCount)
                .filter(GetFilter.OWNER)
                .execute();
    }

    private GetResponse getResponseByName(Group group, int postCount) throws ClientException, ApiException {
        return vkApi.wall()
                .get(user)
                .domain(group.getName())
                .count(postCount)
                .filter(GetFilter.OWNER)
                .execute();
    }
}
