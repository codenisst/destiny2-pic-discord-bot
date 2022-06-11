package ru.codenisst.destiny2pic.vk.models.postbody;

import java.io.Serializable;

public class Attachment implements Serializable {
    private String type;
    private Photo photo;
    private Video video;
    private Audio audio;

    public String getType() {
        return type;
    }

    public Photo getPhoto() {
        return photo;
    }

    public Video getVideo() {
        return video;
    }

    public Audio getAudio() {
        return audio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attachment that = (Attachment) o;

        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (photo != null ? !photo.equals(that.photo) : that.photo != null) return false;
        if (video != null ? !video.equals(that.video) : that.video != null) return false;
        return audio != null ? audio.equals(that.audio) : that.audio == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (video != null ? video.hashCode() : 0);
        result = 31 * result + (audio != null ? audio.hashCode() : 0);
        return result;
    }
}
