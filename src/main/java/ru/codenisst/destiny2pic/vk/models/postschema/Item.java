package ru.codenisst.destiny2pic.vk.models.postschema;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable {
    private int id;
    private int from_id;
    private int owner_id;
    private int date;
    private int marked_as_ads;
    private String post_type;
    private String text;
    private ArrayList<Attachment> attachments;
    private PostSource post_source;
    private Comments comments;
    private Likes likes;
    private Reposts reposts;
    private Views views;
    private Donut donut;
    private double short_text_rate;
    private int carousel_offset;
    private String hash;

    public int getId() {
        return id;
    }

    public int getFrom_id() {
        return from_id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public int getDate() {
        return date;
    }

    public int getMarked_as_ads() {
        return marked_as_ads;
    }

    public String getPost_type() {
        return post_type;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public PostSource getPost_source() {
        return post_source;
    }

    public Comments getComments() {
        return comments;
    }

    public Likes getLikes() {
        return likes;
    }

    public Reposts getReposts() {
        return reposts;
    }

    public Views getViews() {
        return views;
    }

    public Donut getDonut() {
        return donut;
    }

    public double getShort_text_rate() {
        return short_text_rate;
    }

    public int getCarousel_offset() {
        return carousel_offset;
    }

    public String getHash() {
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        if (from_id != item.from_id) return false;
        if (owner_id != item.owner_id) return false;
        if (date != item.date) return false;
        if (marked_as_ads != item.marked_as_ads) return false;
        if (Double.compare(item.short_text_rate, short_text_rate) != 0) return false;
        if (carousel_offset != item.carousel_offset) return false;
        if (post_type != null ? !post_type.equals(item.post_type) : item.post_type != null) return false;
        if (text != null ? !text.equals(item.text) : item.text != null) return false;
        if (attachments != null ? !attachments.equals(item.attachments) : item.attachments != null) return false;
        if (post_source != null ? !post_source.equals(item.post_source) : item.post_source != null) return false;
        if (comments != null ? !comments.equals(item.comments) : item.comments != null) return false;
        if (likes != null ? !likes.equals(item.likes) : item.likes != null) return false;
        if (reposts != null ? !reposts.equals(item.reposts) : item.reposts != null) return false;
        if (views != null ? !views.equals(item.views) : item.views != null) return false;
        if (donut != null ? !donut.equals(item.donut) : item.donut != null) return false;
        return hash != null ? hash.equals(item.hash) : item.hash == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + from_id;
        result = 31 * result + owner_id;
        result = 31 * result + date;
        result = 31 * result + marked_as_ads;
        result = 31 * result + (post_type != null ? post_type.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (attachments != null ? attachments.hashCode() : 0);
        result = 31 * result + (post_source != null ? post_source.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (likes != null ? likes.hashCode() : 0);
        result = 31 * result + (reposts != null ? reposts.hashCode() : 0);
        result = 31 * result + (views != null ? views.hashCode() : 0);
        result = 31 * result + (donut != null ? donut.hashCode() : 0);
        temp = Double.doubleToLongBits(short_text_rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + carousel_offset;
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        return result;
    }
}
