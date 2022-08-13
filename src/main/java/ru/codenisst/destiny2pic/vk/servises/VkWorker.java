package ru.codenisst.destiny2pic.vk.servises;

import ru.codenisst.destiny2pic.vk.dao.PostDao;
import ru.codenisst.destiny2pic.vk.models.Content;
import ru.codenisst.destiny2pic.vk.models.Post;
import ru.codenisst.destiny2pic.vk.models.postschema.Attachment;
import ru.codenisst.destiny2pic.vk.models.postschema.Item;
import ru.codenisst.destiny2pic.vk.models.postschema.Size;

import java.util.ArrayList;
import java.util.List;

public class VkWorker {

    private final GroupParser parser;
    private final PostDao dao;

    public VkWorker(GroupParser parser, PostDao dao) {
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

    public boolean startWorkWithGroup(String[] groupInfo) throws Exception {

        // разбираем ссылку, дабы узнать, содержит она id или содержит уникальную ссылку
        String[] link = groupInfo[0].split("/");
        String groupNameOrId = link[link.length - 1];

        int id;
        String nameLink;
        String name;

        try {

            if (groupNameOrId.matches("^club\\d+")) {

                id = Integer.parseInt(groupNameOrId.replace("club", ""));
                nameLink = parser.getGroupNameLink(String.valueOf(id));
                name = parser.getGroupName(String.valueOf(id));

            } else if (groupNameOrId.matches("^public\\d+")) {

                id = Integer.parseInt(groupNameOrId.replace("public", ""));
                nameLink = parser.getGroupNameLink(String.valueOf(id));
                name = parser.getGroupName(String.valueOf(id));

            } else {

                id = parser.getGroupId(groupNameOrId);
                nameLink = groupNameOrId;
                name = parser.getGroupName(String.valueOf(id));

            }

            switch (groupInfo.length) {
                // если введена только ссылка
                case 1 -> {
                    return parser.addGroup(name, String.valueOf(id), nameLink,
                            ContentType.PHOTO.get(), String.valueOf(0));
                }
                // если введена ссылка с количеством или типом
                case 2 -> {
                    try {
                        int quantityPost = Integer.parseInt(groupInfo[1]);
                        return parser.addGroup(name, String.valueOf(id), nameLink,
                                ContentType.PHOTO.get(), String.valueOf(quantityPost));
                    } catch (NumberFormatException e) {
                        return parser.addGroup(name, String.valueOf(id), nameLink,
                                groupInfo[1], String.valueOf(0));
                    }
                }
                // если введена ссылка с количеством и типом
                case 3 -> {
                    return parser.addGroup(name, String.valueOf(id), nameLink,
                            groupInfo[2], groupInfo[1]);
                }
            }

        } catch (Exception e) {
            throw new Exception("Некорректные данные");
        }

        return false;
    }


    public String stopWorkWithGroup(String groupName) throws Exception {
        if (parser.groupIsFound(groupName)) {
            int id = parser.removeGroup(groupName);
            dao.removeAllFromGroup(id);
            return groupName;
        } else {
            throw new Exception("Таких групп в работе нет!");
        }
    }

    public String getListGroupsAtWork() throws Exception {
        return parser.getListGroupsAtWork();
    }

    // вызывается при запуске бота. Добавляет в работу группы из бд.
    public void continueWorkingWithGroupsInWorker() throws Exception {
        List<String> groupsIdsFromDB = dao.getAllGroupsIdsFromDatabase();
        List<String> groupsLinksFromDB = new ArrayList<>();

        for (String groupId:groupsIdsFromDB) {
            groupsLinksFromDB.add("https://vk.com/club" + groupId);
        }

        for (String link:groupsLinksFromDB) {
            String[] groupInfoFromDB = new String[] {link};
            try {
                startWorkWithGroup(groupInfoFromDB);
            } catch (Exception e) {
                throw new Exception("Ошибка добавления в работу группы " + link +  " из БД!");
            }
        }
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
                    default:
                }
            }
        }
        return content;
    }
}
