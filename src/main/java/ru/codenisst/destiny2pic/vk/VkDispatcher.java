package ru.codenisst.destiny2pic.vk;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import ru.codenisst.destiny2pic.vk.dao.PostDao;
import ru.codenisst.destiny2pic.vk.models.Post;
import ru.codenisst.destiny2pic.vk.servises.GroupParser;
import ru.codenisst.destiny2pic.vk.servises.VkWorker;

import java.util.List;

public class VkDispatcher {

    private final VkWorker worker;

    // "jdbc:sqlite:databasepost.sqlite"
    public VkDispatcher(String linkDatabase, String vkConfigPath) {
        worker = new VkWorker(
                new GroupParser(
                    new VkApiClient(
                        new HttpTransportClient()), vkConfigPath),
                    new PostDao(linkDatabase));
    }

    public List<Post> getNewPostsWithPictures() throws Exception {
        return worker.getAndSaveToDbNewPostsListWithPictures();
    }

    public boolean addGroup(String[] groupInfo) throws Exception {
        return worker.startWorkWithGroup(groupInfo);
    }

    public String getListGroupsAtWork() throws Exception {
        return worker.getListGroupsAtWork();
    }

    public String deleteGroup(String groupName) throws Exception {
       return worker.stopWorkWithGroup(groupName);
    }

    public void deleteAllGroup() {
        worker.stopWorkWithAllGroups();
    }

    public void continueWorkingWithGroupsInDispacher() throws Exception {
        worker.continueWorkingWithGroupsInWorker();
    }
}
