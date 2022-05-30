package ru.codenisst.discord2pic.vk.models.post;

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

    public void setAccessKey(String value) {
        this.accessKey = value;
    }

    public long getAlbumID() {
        return albumID;
    }

    public void setAlbumID(long value) {
        this.albumID = value;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long value) {
        this.date = value;
    }

    public long getID() {
        return id;
    }

    public void setID(long value) {
        this.id = value;
    }

    public long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(long value) {
        this.ownerID = value;
    }

    public long getPostID() {
        return postID;
    }

    public void setPostID(long value) {
        this.postID = value;
    }

    public Size[] getSizes() {
        return sizes;
    }

    public void setSizes(Size[] value) {
        this.sizes = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String value) {
        this.text = value;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long value) {
        this.userID = value;
    }

    public boolean getHasTags() {
        return hasTags;
    }

    public void setHasTags(boolean value) {
        this.hasTags = value;
    }
}
