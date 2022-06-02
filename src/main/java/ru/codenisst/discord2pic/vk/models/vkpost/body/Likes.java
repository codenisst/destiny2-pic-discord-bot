package ru.codenisst.discord2pic.vk.models.vkpost.body;

public class Likes {

    private String canLike;
    private String canPublish;
    private long count;
    private long userLikes;

    public String getCanLike() {
        return canLike;
    }

    public String getCanPublish() {
        return canPublish;
    }

    public long getCount() {
        return count;
    }

    public long getUserLikes() {
        return userLikes;
    }
}
