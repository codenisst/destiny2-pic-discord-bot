package ru.codenisst.discord2pic.vk.models.vkpost;

public class VkPostModel {

    private Donut donut;
    private Comments comments;
    private String markedAsAds;
    private double shortTextRate;
    private String hash;
    private Attachment[] attachments;
    private long date;
    private long fromID;
    private long id;
    private boolean isFavorite;
    private Likes likes;
    private long ownerID;
    private PostSource postSource;
    private String postType;
    private Reposts reposts;
    private long signerID;
    private String text;
    private Views views;

    public Donut getDonut() {
        return donut;
    }

    public void setDonut(Donut value) {
        this.donut = value;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments value) {
        this.comments = value;
    }

    public String getMarkedAsAds() {
        return markedAsAds;
    }

    public void setMarkedAsAds(String value) {
        this.markedAsAds = value;
    }

    public double getShortTextRate() {
        return shortTextRate;
    }

    public void setShortTextRate(double value) {
        this.shortTextRate = value;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String value) {
        this.hash = value;
    }

    public Attachment[] getAttachments() {
        return attachments;
    }

    public void setAttachments(Attachment[] value) {
        this.attachments = value;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long value) {
        this.date = value;
    }

    public long getFromID() {
        return fromID;
    }

    public void setFromID(long value) {
        this.fromID = value;
    }

    public long getID() {
        return id;
    }

    public void setID(long value) {
        this.id = value;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean value) {
        this.isFavorite = value;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes value) {
        this.likes = value;
    }

    public long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(long value) {
        this.ownerID = value;
    }

    public PostSource getPostSource() {
        return postSource;
    }

    public void setPostSource(PostSource value) {
        this.postSource = value;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String value) {
        this.postType = value;
    }

    public Reposts getReposts() {
        return reposts;
    }

    public void setReposts(Reposts value) {
        this.reposts = value;
    }

    public long getSignerID() {
        return signerID;
    }

    public void setSignerID(long value) {
        this.signerID = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String value) {
        this.text = value;
    }

    public Views getViews() {
        return views;
    }

    public void setViews(Views value) {
        this.views = value;
    }
}

