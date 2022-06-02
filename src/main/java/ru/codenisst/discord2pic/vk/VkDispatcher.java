package ru.codenisst.discord2pic.vk;

import ru.codenisst.discord2pic.vk.models.vkpost.VkPostContent;
import ru.codenisst.discord2pic.vk.servises.VkContent;

import java.util.List;

public class VkDispatcher {

    // Возвращает список уникальных (ранее неопубликованных и отсутствующих в базе данных)
    // объектов (id,text,contentUrl) из списка уникальных постов.
    public static List<VkPostContent> getNewPostsPicture() throws Exception {
        return VkContent.saveAndReturnNewUniquePostsPicture();
    }

}
