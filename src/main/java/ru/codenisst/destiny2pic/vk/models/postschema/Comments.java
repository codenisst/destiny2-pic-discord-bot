package ru.codenisst.destiny2pic.vk.models.postschema;

import java.io.Serializable;

public class Comments implements Serializable {
    private int can_post;
    private int count;
    private boolean groups_can_post;

    public int getCan_post() {
        return can_post;
    }

    public int getCount() {
        return count;
    }

    public boolean isGroups_can_post() {
        return groups_can_post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comments comments = (Comments) o;

        if (can_post != comments.can_post) return false;
        if (count != comments.count) return false;
        return groups_can_post == comments.groups_can_post;
    }

    @Override
    public int hashCode() {
        int result = can_post;
        result = 31 * result + count;
        result = 31 * result + (groups_can_post ? 1 : 0);
        return result;
    }
}
