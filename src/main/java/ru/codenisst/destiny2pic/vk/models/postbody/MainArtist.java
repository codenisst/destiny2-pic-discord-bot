package ru.codenisst.destiny2pic.vk.models.postbody;

import java.io.Serializable;

public class MainArtist implements Serializable {
    private String name;
    private String domain;
    private String id;

    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MainArtist that = (MainArtist) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (domain != null ? !domain.equals(that.domain) : that.domain != null) return false;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
