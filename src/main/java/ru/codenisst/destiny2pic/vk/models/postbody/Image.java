package ru.codenisst.destiny2pic.vk.models.postbody;

import java.io.Serializable;

public class Image implements Serializable {
    private String url;
    private int width;
    private int height;
    private int with_padding;

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getWith_padding() {
        return with_padding;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (width != image.width) return false;
        if (height != image.height) return false;
        if (with_padding != image.with_padding) return false;
        return url != null ? url.equals(image.url) : image.url == null;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + width;
        result = 31 * result + height;
        result = 31 * result + with_padding;
        return result;
    }
}
