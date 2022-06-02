package ru.codenisst.discord2pic.vk.models.vkpost;

import ru.codenisst.discord2pic.vk.models.vkpost.body.Attachment;
import ru.codenisst.discord2pic.vk.models.vkpost.body.Comments;
import ru.codenisst.discord2pic.vk.models.vkpost.body.Donut;
import ru.codenisst.discord2pic.vk.models.vkpost.body.Likes;
import ru.codenisst.discord2pic.vk.models.vkpost.body.PostSource;
import ru.codenisst.discord2pic.vk.models.vkpost.body.Reposts;
import ru.codenisst.discord2pic.vk.models.vkpost.body.Views;

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

    public Comments getComments() {
        return comments;
    }

    public String getMarkedAsAds() {
        return markedAsAds;
    }

    public double getShortTextRate() {
        return shortTextRate;
    }

    public String getHash() {
        return hash;
    }

    public Attachment[] getAttachments() {
        return attachments;
    }

    public long getDate() {
        return date;
    }

    public long getFromID() {
        return fromID;
    }

    public long getID() {
        return id;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public Likes getLikes() {
        return likes;
    }

    public long getOwnerID() {
        return ownerID;
    }

    public PostSource getPostSource() {
        return postSource;
    }

    public String getPostType() {
        return postType;
    }

    public Reposts getReposts() {
        return reposts;
    }

    public long getSignerID() {
        return signerID;
    }

    public String getText() {
        return text;
    }

    public Views getViews() {
        return views;
    }
}

