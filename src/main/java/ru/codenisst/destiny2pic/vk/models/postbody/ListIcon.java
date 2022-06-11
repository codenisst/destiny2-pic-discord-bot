package ru.codenisst.destiny2pic.vk.models.postbody;

import java.io.Serializable;

public class ListIcon implements Serializable {
    private String url;
    private int width;
    private int height;

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListIcon listIcon = (ListIcon) o;

        if (width != listIcon.width) return false;
        if (height != listIcon.height) return false;
        return url != null ? url.equals(listIcon.url) : listIcon.url == null;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }
}
