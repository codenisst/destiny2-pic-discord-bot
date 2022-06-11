package ru.codenisst.destiny2pic.vk;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import ru.codenisst.destiny2pic.vk.dao.PostDao;
import ru.codenisst.destiny2pic.vk.models.Group;
import ru.codenisst.destiny2pic.vk.models.Post;
import ru.codenisst.destiny2pic.vk.servises.GroupWallParser;
import ru.codenisst.destiny2pic.vk.servises.VkWorker;

import java.util.List;

public class VkDispatcher {

    private final VkWorker worker;

    // "jdbc:sqlite:databasepost.sqlite"
    public VkDispatcher(String linkDatabase) {
        worker = new VkWorker(new GroupWallParser(new VkApiClient(
                new HttpTransportClient())), new PostDao(linkDatabase));
    }

    public List<Post> getNewPostsWithPictures() throws Exception {
        return worker.getAndSaveToDbNewPostsListWithPictures();
    }

    public void addGroup(Group group) {
        worker.startWorkWithGroup(group);
    }

    public void deleteGroup(Group group) {
        worker.stopWorkWithGroup(group);
    }

    public void deleteAllGroup() {
        worker.stopWorkWithAllGroups();
    }
}
