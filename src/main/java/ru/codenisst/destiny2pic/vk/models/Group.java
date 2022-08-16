package ru.codenisst.destiny2pic.vk.models;

public class Group {

    private final String name;
    private final String nameLink;
    private final int id;
    private final String contentType;
    private final int quantityParsedPosts;

    public Group(String name, int id, String nameLink, String contentType, int quantityParsedPosts) {
        this.name = name;
        this.id = id;
        this.nameLink = nameLink;
        this.contentType = contentType;
        this.quantityParsedPosts = quantityParsedPosts;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getNameLink() {
        return nameLink;
    }

    public String getContentType() {
        return contentType;
    }

    public int getParsedPostsQuantity() {
        return quantityParsedPosts;
    }

    public boolean haveName() {
        return nameLink != null && !nameLink.equals("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != group.id) return false;
        if (quantityParsedPosts != group.quantityParsedPosts) return false;
        if (nameLink != null ? !nameLink.equals(group.nameLink) : group.nameLink != null) return false;
        return contentType != null ? contentType.equals(group.contentType) : group.contentType == null;
    }

    @Override
    public int hashCode() {
        int result = nameLink != null ? nameLink.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + (contentType != null ? contentType.hashCode() : 0);
        result = 31 * result + quantityParsedPosts;
        return result;
    }
}
