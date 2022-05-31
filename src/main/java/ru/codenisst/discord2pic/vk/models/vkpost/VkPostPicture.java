package ru.codenisst.discord2pic.vk.models.vkpost;

public class VkPostPicture {

    private long id;
    private String text;
    private String urlContent;

    public VkPostPicture(long id, String text, String urlContent) {
        this.id = id;
        this.text = text;
        this.urlContent = urlContent;
    }

    @Override
    public String toString() {
        return "Пост id - " + id
                + "\nТекст поста - " + text
                + "\nСсылка на изображение - " + urlContent + "\n";
    }
}
