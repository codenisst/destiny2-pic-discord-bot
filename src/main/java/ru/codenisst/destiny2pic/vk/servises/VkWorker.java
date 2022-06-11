package ru.codenisst.destiny2pic.vk.servises;

import ru.codenisst.destiny2pic.vk.dao.PostDao;
import ru.codenisst.destiny2pic.vk.models.Content;
import ru.codenisst.destiny2pic.vk.models.Group;
import ru.codenisst.destiny2pic.vk.models.Post;
import ru.codenisst.destiny2pic.vk.models.postbody.Attachment;
import ru.codenisst.destiny2pic.vk.models.postbody.Item;
import ru.codenisst.destiny2pic.vk.models.postbody.Size;

import java.util.ArrayList;
import java.util.List;

public class VkWorker {

    private final GroupWallParser parser;
    private final PostDao dao;

    public VkWorker(GroupWallParser parser, PostDao dao) {
        this.parser = parser;
        this.dao = dao;
    }

    public List<Post> getAndSaveToDbNewPostsListWithPictures() throws Exception {
        List<Post> newVkPosts = getPostsListWithSpecifiedContent(parser.getListPosts(),
                ContentType.PHOTO.get());

        List<Post> savedVkPostsWithPictures = dao.getAllSavedPostsWithPictures();

        List<Post> uniquePostsWithPictures = new ArrayList<>();
        for (Post post:newVkPosts) {
            if (!savedVkPostsWithPictures.contains(post)) {
                uniquePostsWithPictures.add(post);
            }
        }

        if (uniquePostsWithPictures.size() > 0) {
            dao.savePosts(uniquePostsWithPictures);
        }

        return uniquePostsWithPictures;
    }

    public void startWorkWithGroup(Group group) {
        parser.addGroup(group);
    }

    public void stopWorkWithGroup(Group group) {
        parser.removeGroup(group);
        dao.removeAllFromGroups(group);
    }

    public void stopWorkWithAllGroups() {
        parser.removeAllGroups();
        dao.dropDb();
    }

    private List<Post> getPostsListWithSpecifiedContent(List<Item> items, String typeContent) {
        List<Post> result = new ArrayList<>();

        for (Item item:items) {
            List<Attachment> attachments = item.getAttachments();

            if(attachments != null && attachments.size() > 0) {

                for (Attachment attachment:attachments) {
                    if(attachment.getType().equalsIgnoreCase(typeContent)) {
                        result.add(new Post(-item.getOwner_id(), item.getId(),
                                item.getText(), getContentList(item, typeContent)));
                        break;
                    }
                }
            }
        }

        return result;
    }

    private List<Content> getContentList(Item post, String typeContent) {
        List<Content> content = new ArrayList<>();

        List<Attachment> attachments = post.getAttachments();
        if (attachments != null && attachments.size() > 0) {

            int contentID = 0;
            for (Attachment attachment : attachments) {

                switch (attachment.getType()){
                    case "photo": {
                        ArrayList<Size> attachmentSizes = attachment.getPhoto().getSizes();
                        attachmentSizes.sort((o1, o2) -> o2.getHeight() - o1.getHeight());
                        content.add(new Content(++contentID, typeContent, attachmentSizes.get(0).getUrl()));
                        break;
                    }
                }
            }
        }
        return content;
    }
}
