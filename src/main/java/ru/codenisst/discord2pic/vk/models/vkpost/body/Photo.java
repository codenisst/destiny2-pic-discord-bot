package ru.codenisst.discord2pic.vk.models.vkpost.body;

public class Photo {

    private String accessKey;
    private long albumID;
    private long date;
    private long id;
    private long ownerID;
    private long postID;
    private Size[] sizes;
    private String text;
    private long userID;
    private boolean hasTags;

    public String getAccessKey() {
        return accessKey;
    }

    public long getAlbumID() {
        return albumID;
    }

    public long getDate() {
        return date;
    }

    public long getID() {
        return id;
    }

    public long getOwnerID() {
        return ownerID;
    }

    public long getPostID() {
        return postID;
    }

    public Size[] getSizes() {
        return sizes;
    }

    public String getText() {
        return text;
    }

    public long getUserID() {
        return userID;
    }

    public boolean getHasTags() {
        return hasTags;
    }
}
