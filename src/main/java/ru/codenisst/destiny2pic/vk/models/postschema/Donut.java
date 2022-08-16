package ru.codenisst.destiny2pic.vk.models.postschema;

import java.io.Serializable;

public class Donut implements Serializable {
    private boolean is_donut;

    public boolean isIs_donut() {
        return is_donut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Donut donut = (Donut) o;

        return is_donut == donut.is_donut;
    }

    @Override
    public int hashCode() {
        return (is_donut ? 1 : 0);
    }
}
