package ru.codenisst.discord2pic.vk.models.vkpost;

public class Reposts {

    private long count;
    private long userReposted;

    public long getCount() {
        return count;
    }

    public void setCount(long value) {
        this.count = value;
    }

    public long getUserReposted() {
        return userReposted;
    }

    public void setUserReposted(long value) {
        this.userReposted = value;
    }
}
