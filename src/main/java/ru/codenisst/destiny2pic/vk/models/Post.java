package ru.codenisst.destiny2pic.vk.models;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Post {

    private int ownerId;
    private int id;
    private String text;
    private List<Content> contentList;

    public Post(int ownerId, int id, String text, List<Content> contentList) {
        this.ownerId = ownerId;
        this.id = id;
        this.text = text;
        this.contentList = contentList;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<Content> getContentList() {
        return contentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (ownerId != post.ownerId) return false;
        if (id != post.id) return false;
        if (!Objects.equals(text, post.text)) return false;
        return Objects.equals(contentList, post.contentList);
    }

    @Override
    public int hashCode() {
        int result = ownerId;
        result = 31 * result + id;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (contentList != null ? contentList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",\n");
        if (contentList != null && contentList.size() > 0) {
            for (Content content:contentList) {
                stringJoiner.add(content.getType() + " " + content.getId() + " - " + content.getUrl());
            }
        }
        return "Пост id - " + id
                + "\nОткуда пост - " + ownerId
                + "\nТекст поста - " + text
                + "\nСсылки на контент:\n" + stringJoiner + "\n";
    }
}
