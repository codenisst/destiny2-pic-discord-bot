package ru.codenisst.destiny2pic.vk.models.postbody;

import java.io.Serializable;

public class Size implements Serializable {
    private int height;
    private String url;
    private String type;
    private int width;

    public int getHeight() {
        return height;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Size size = (Size) o;

        if (height != size.height) return false;
        if (width != size.width) return false;
        if (url != null ? !url.equals(size.url) : size.url != null) return false;
        return type != null ? type.equals(size.type) : size.type == null;
    }

    @Override
    public int hashCode() {
        int result = height;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + width;
        return result;
    }
}
