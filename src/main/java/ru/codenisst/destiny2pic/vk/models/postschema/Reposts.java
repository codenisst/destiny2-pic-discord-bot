package ru.codenisst.destiny2pic.vk.models.postschema;

import java.io.Serializable;

public class Reposts implements Serializable {
    private int count;
    private int user_reposted;

    public int getCount() {
        return count;
    }

    public int getUser_reposted() {
        return user_reposted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reposts reposts = (Reposts) o;

        if (count != reposts.count) return false;
        return user_reposted == reposts.user_reposted;
    }

    @Override
    public int hashCode() {
        int result = count;
        result = 31 * result + user_reposted;
        return result;
    }
}
