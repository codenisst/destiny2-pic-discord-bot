package ru.codenisst.destiny2pic.vk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.codenisst.destiny2pic.vk.models.Post;
import ru.codenisst.destiny2pic.vk.servises.VkWorker;

import java.util.List;

@Component("dispatcher")
public class VkDispatcher {

    private final VkWorker worker;

    @Autowired
    public VkDispatcher(VkWorker worker) {
        this.worker = worker;
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

    public void continueWorkingWithGroupsInDispatcher() throws Exception {
        worker.continueWorkingWithGroupsInWorker();
    }
}
