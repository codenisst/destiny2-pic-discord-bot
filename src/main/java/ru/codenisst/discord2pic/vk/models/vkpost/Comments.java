package ru.codenisst.discord2pic.vk.models.vkpost;

public class Comments {

    private String canPost;
    private long count;
    private boolean groupsCanPost;

    public String getCanPost() {
        return canPost;
    }

    public void setCanPost(String value) {
        this.canPost = value;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long value) {
        this.count = value;
    }

    public boolean getGroupsCanPost() {
        return groupsCanPost;
    }

    public void setGroupsCanPost(boolean value) {
        this.groupsCanPost = value;
    }
}
