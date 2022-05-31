package ru.codenisst.discord2pic.vk.servises;

import ru.codenisst.discord2pic.vk.models.vkpost.VkPostPicture;
import ru.codenisst.discord2pic.vk.servises.vk.VkPicture;

import java.util.List;

public class VkDispatcher {

    // Возвращает список уникальных (ранее неопубликованных и отсутствующих в базе данных)
    // объектов (id,text,contentUrl) из списка уникальных постов.
    public static List<VkPostPicture> getNewPostsPicture() throws Exception {
        return VkPicture.saveAndReturnUniquePostsPicture();
    }

}
