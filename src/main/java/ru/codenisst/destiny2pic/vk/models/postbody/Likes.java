package ru.codenisst.destiny2pic.vk.models.postbody;

import java.io.Serializable;

public class Likes implements Serializable {
    private int can_like;
    private int count;
    private int user_likes;
    private int can_publish;

    public int getCan_like() {
        return can_like;
    }

    public int getCount() {
        return count;
    }

    public int getUser_likes() {
        return user_likes;
    }

    public int getCan_publish() {
        return can_publish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Likes likes = (Likes) o;

        if (can_like != likes.can_like) return false;
        if (count != likes.count) return false;
        if (user_likes != likes.user_likes) return false;
        return can_publish == likes.can_publish;
    }

    @Override
    public int hashCode() {
        int result = can_like;
        result = 31 * result + count;
        result = 31 * result + user_likes;
        result = 31 * result + can_publish;
        return result;
    }
}
