package ru.codenisst.destiny2pic.vk.models;

import ru.codenisst.destiny2pic.vk.servises.ContentType;

import java.util.Objects;

public class Content {

    private int id;
    private String type;
    private String url;

    public Content(int id, String type, String url) {
        this.id = id;
        this.type = type;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Content content = (Content) o;

        if (id != content.id) return false;
        if (!Objects.equals(type, content.type)) return false;

        if (this.type.equals(ContentType.PHOTO.get())) {
            String[] urlArray = url.split("/");
            String[] contentUrlArray = content.url.split("/");

            return Objects.equals(urlArray[urlArray.length - 1],
                    contentUrlArray[contentUrlArray.length - 1]);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
