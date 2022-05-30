package ru.codenisst.discord2pic.vk.models.post;

public class Likes {

    private String canLike;
    private String canPublish;
    private long count;
    private long userLikes;

    public String getCanLike() {
        return canLike;
    }

    public void setCanLike(String value) {
        this.canLike = value;
    }

    public String getCanPublish() {
        return canPublish;
    }

    public void setCanPublish(String value) {
        this.canPublish = value;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long value) {
        this.count = value;
    }

    public long getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(long value) {
        this.userLikes = value;
    }
}
