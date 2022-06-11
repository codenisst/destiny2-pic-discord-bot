package ru.codenisst.destiny2pic.vk.models.postbody;

import java.io.Serializable;
import java.util.ArrayList;

public class Photo implements Serializable {
    private int album_id;
    private int date;
    private int id;
    private int owner_id;
    private String access_key;
    private ArrayList<Size> sizes;
    private String text;
    private int user_id;
    private boolean has_tags;

    public int getAlbum_id() {
        return album_id;
    }

    public int getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public String getAccess_key() {
        return access_key;
    }

    public ArrayList<Size> getSizes() {
        return sizes;
    }

    public String getText() {
        return text;
    }

    public int getUser_id() {
        return user_id;
    }

    public boolean isHas_tags() {
        return has_tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (album_id != photo.album_id) return false;
        if (date != photo.date) return false;
        if (id != photo.id) return false;
        if (owner_id != photo.owner_id) return false;
        if (user_id != photo.user_id) return false;
        if (has_tags != photo.has_tags) return false;
        if (access_key != null ? !access_key.equals(photo.access_key) : photo.access_key != null) return false;
        if (sizes != null ? !sizes.equals(photo.sizes) : photo.sizes != null) return false;
        return text != null ? text.equals(photo.text) : photo.text == null;
    }

    @Override
    public int hashCode() {
        int result = album_id;
        result = 31 * result + date;
        result = 31 * result + id;
        result = 31 * result + owner_id;
        result = 31 * result + (access_key != null ? access_key.hashCode() : 0);
        result = 31 * result + (sizes != null ? sizes.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + user_id;
        result = 31 * result + (has_tags ? 1 : 0);
        return result;
    }
}
