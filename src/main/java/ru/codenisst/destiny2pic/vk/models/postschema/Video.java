package ru.codenisst.destiny2pic.vk.models.postschema;

import java.io.Serializable;
import java.util.ArrayList;

public class Video implements Serializable {
    private String access_key;
    private int can_comment;
    private int can_like;
    private int can_repost;
    private int can_subscribe;
    private int can_add_to_faves;
    private int can_add;
    private int date;
    private int duration;
    private ArrayList<Image> image;
    private int width;
    private int height;
    private int id;
    private int owner_id;
    private String title;
    private Restriction restriction;
    private String track_code;
    private String type;
    private int views;
    private int content_restricted;
    private int comments;
    private String description;
    private int local_views;
    private String platform;

    public String getAccess_key() {
        return access_key;
    }

    public int getCan_comment() {
        return can_comment;
    }

    public int getCan_like() {
        return can_like;
    }

    public int getCan_repost() {
        return can_repost;
    }

    public int getCan_subscribe() {
        return can_subscribe;
    }

    public int getCan_add_to_faves() {
        return can_add_to_faves;
    }

    public int getCan_add() {
        return can_add;
    }

    public int getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }

    public ArrayList<Image> getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getId() {
        return id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public String getTitle() {
        return title;
    }

    public Restriction getRestriction() {
        return restriction;
    }

    public String getTrack_code() {
        return track_code;
    }

    public String getType() {
        return type;
    }

    public int getViews() {
        return views;
    }

    public int getContent_restricted() {
        return content_restricted;
    }

    public int getComments() {
        return comments;
    }

    public String getDescription() {
        return description;
    }

    public int getLocal_views() {
        return local_views;
    }

    public String getPlatform() {
        return platform;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Video video = (Video) o;

        if (can_comment != video.can_comment) return false;
        if (can_like != video.can_like) return false;
        if (can_repost != video.can_repost) return false;
        if (can_subscribe != video.can_subscribe) return false;
        if (can_add_to_faves != video.can_add_to_faves) return false;
        if (can_add != video.can_add) return false;
        if (date != video.date) return false;
        if (duration != video.duration) return false;
        if (width != video.width) return false;
        if (height != video.height) return false;
        if (id != video.id) return false;
        if (owner_id != video.owner_id) return false;
        if (views != video.views) return false;
        if (content_restricted != video.content_restricted) return false;
        if (comments != video.comments) return false;
        if (local_views != video.local_views) return false;
        if (access_key != null ? !access_key.equals(video.access_key) : video.access_key != null) return false;
        if (image != null ? !image.equals(video.image) : video.image != null) return false;
        if (title != null ? !title.equals(video.title) : video.title != null) return false;
        if (restriction != null ? !restriction.equals(video.restriction) : video.restriction != null) return false;
        if (track_code != null ? !track_code.equals(video.track_code) : video.track_code != null) return false;
        if (type != null ? !type.equals(video.type) : video.type != null) return false;
        if (description != null ? !description.equals(video.description) : video.description != null) return false;
        return platform != null ? platform.equals(video.platform) : video.platform == null;
    }

    @Override
    public int hashCode() {
        int result = access_key != null ? access_key.hashCode() : 0;
        result = 31 * result + can_comment;
        result = 31 * result + can_like;
        result = 31 * result + can_repost;
        result = 31 * result + can_subscribe;
        result = 31 * result + can_add_to_faves;
        result = 31 * result + can_add;
        result = 31 * result + date;
        result = 31 * result + duration;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + width;
        result = 31 * result + height;
        result = 31 * result + id;
        result = 31 * result + owner_id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (restriction != null ? restriction.hashCode() : 0);
        result = 31 * result + (track_code != null ? track_code.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + views;
        result = 31 * result + content_restricted;
        result = 31 * result + comments;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + local_views;
        result = 31 * result + (platform != null ? platform.hashCode() : 0);
        return result;
    }
}
