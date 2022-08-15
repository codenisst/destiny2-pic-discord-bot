package ru.codenisst.destiny2pic.vk.servises;

import com.google.gson.Gson;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.groups.responses.GetByIdObjectLegacyResponse;
import com.vk.api.sdk.objects.wall.GetFilter;
import com.vk.api.sdk.objects.wall.WallpostFull;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.codenisst.destiny2pic.vk.models.groupschema.GroupItem;
import ru.codenisst.destiny2pic.vk.models.Group;
import ru.codenisst.destiny2pic.vk.models.postschema.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Component("parser")
public class GroupParser {

    private final VkApiClient vkApi;
    private List<Group> groups;
    private final UserActor user;
    private final Gson gson;

    @Autowired
    public GroupParser(VkApiClient vkApi,
                       List<Group> groupList,
                       UserActor userActor,
                       Gson gson) {
        this.vkApi = vkApi;
        this.groups = groupList;
        this.user = userActor;
        this.gson = gson;
    }

    // возвращает список постов из групп в работе
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
        throw new Exception("Список групп пуст!");
    }

    // добавляет группу в работу
    public boolean addGroup(String... groupInfo) {
        Group newGroup = new Group(groupInfo[0], Integer.parseInt(groupInfo[1]), groupInfo[2],
                groupInfo[3], Integer.parseInt(groupInfo[4]));
        if (!groups.contains(newGroup)) {
            return groups.add(newGroup);
        }
        return false;
    }

    // удаляет группу из работы
    public int removeGroup(String groupName) {
        Group result = null;
        for (Group group : groups) {
            if (groupName.equals(group.getName())) {
                result = group;
                groups.remove(group);
                break;
            }
        }
        return result.getId();
    }

    public boolean groupIsFound(String groupName) {
        for (Group group : groups) {
            if (groupName.equals(group.getName())) {
                return true;
            }
        }
        return false;
    }

    // спрашиваем у вк уник ссылку группы
    public String getGroupNameLink(String groupId) throws ClientException, ApiException {
        //вернуть уникальное имя в url группы по ее id
        GetByIdObjectLegacyResponse obj = getGroupInfo(groupId);
        GroupItem group = gson.fromJson(obj.toString(), GroupItem.class);
        return group.getScreen_name();
    }

    public int getGroupId(String groupNameLink) throws ClientException, ApiException {
        //вернуть id группы по ее имени в url
        GetByIdObjectLegacyResponse obj = getGroupInfo(groupNameLink);
        GroupItem group = gson.fromJson(obj.toString(), GroupItem.class);
        return group.getId();
    }

    public String getGroupName(String groupId) throws ClientException, ApiException {
        GetByIdObjectLegacyResponse obj = getGroupInfo(groupId);
        GroupItem group = gson.fromJson(obj.toString(), GroupItem.class);
        return group.getName();
    }

    public void removeAllGroups() {
        this.groups.clear();
    }

    public String getListGroupsAtWork() throws Exception {
        if (groups.size() > 0) {
            StringJoiner stringJoiner = new StringJoiner(" \n");
            for (Group group : groups) {
                stringJoiner.add(group.getName());
            }
            return stringJoiner.toString();
        }
        throw new Exception("Нет групп в работе");
    }

    private GetByIdObjectLegacyResponse getGroupInfo(String id) throws ClientException, ApiException {
        return vkApi.groups()
                .getByIdObjectLegacy(user)
                .groupId(id)
                .execute()
                .get(0);
    }

    private List<WallpostFull> getWallposts(List<Group> groups) throws Exception {
        if (groups.size() > 0) {
            List<WallpostFull> result = new ArrayList<>();
            for (Group group : groups) {
                int defaultCount = 5;
                int postCount = (group.getParsedPostsQuantity() != 0)
                        ? group.getParsedPostsQuantity()
                        : defaultCount;
                GetResponse get = group.haveName()
                        ? getResponseByName(group, postCount)
                        : getResponseById(group, postCount);
                result.addAll(get.getItems());
            }
            return result;
        }
        throw new Exception("Список групп пуст!");
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
                .domain(group.getNameLink())
                .count(postCount)
                .filter(GetFilter.OWNER)
                .execute();
    }
}
