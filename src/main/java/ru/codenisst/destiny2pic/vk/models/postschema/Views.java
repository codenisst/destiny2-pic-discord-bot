package ru.codenisst.destiny2pic.vk.models.postschema;

import java.io.Serializable;

public class Views implements Serializable {
    private int count;

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Views views = (Views) o;

        return count == views.count;
    }

    @Override
    public int hashCode() {
        return count;
    }
}
