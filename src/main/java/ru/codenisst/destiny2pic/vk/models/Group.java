package ru.codenisst.destiny2pic.vk.models;

import java.util.Objects;

public class Group {

    private String name;
    private int id;
    private final int quantityParsedPosts;

    public Group(String name, int quantityParsedPosts) {
        this.name = name;
        this.quantityParsedPosts = quantityParsedPosts;
    }

    public Group(int id, int quantityParsedPosts) {
        this.id = id;
        this.quantityParsedPosts = quantityParsedPosts;
    }

    public String getName() {
        return name;
    }

    public int getParsedPostsQuantity() {
        return quantityParsedPosts;
    }

    public int getId() {
        return id;
    }

    public boolean haveName() {
        return name != null && !name.equals("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != group.id) return false;
        if (quantityParsedPosts != group.quantityParsedPosts) return false;
        return Objects.equals(name, group.name);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + quantityParsedPosts;
        return result;
    }
}
