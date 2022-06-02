package ru.codenisst.discord2pic.vk.models.vkpost;

import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class VkPostContent {

    private long id;
    private String text;
    private List<String> urlContent;

    public VkPostContent(long id, String text, List<String> urlContent) {
        this.id = id;
        this.text = text;
        this.urlContent = urlContent;
        Collections.reverse(urlContent);
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<String> getUrlContent() {
        return urlContent;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",\n");
        for (String url : urlContent) {
            stringJoiner.add(url);
        }
        return "Пост id - " + id
                + "\nТекст поста - " + text
                + "\nСсылка на изображение - " + stringJoiner + "\n";
    }
}
