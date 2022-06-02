package ru.codenisst.discord2pic.vk.servises;

import ru.codenisst.discord2pic.vk.dao.VkPostDao;
import ru.codenisst.discord2pic.vk.models.vkpost.body.Attachment;
import ru.codenisst.discord2pic.vk.models.vkpost.body.Size;
import ru.codenisst.discord2pic.vk.models.vkpost.VkPostModel;
import ru.codenisst.discord2pic.vk.models.vkpost.VkPostContent;

import java.util.ArrayList;
import java.util.List;

public class VkContent {

    // Получение списка новых постов с изображениями и сохранение их в БД
    public static List<VkPostContent> saveAndReturnNewUniquePostsPicture() throws Exception {
        // получили посты из вк
        List<VkPostModel> got = VkParserWall.getListVkPosts();
        // получили id новых постов
        List<Long> uniquePostId = getUniquePostId(got, VkPostDao.getAllSavedPosts());

        // получили список новых постов с изображениями
        List<VkPostModel> unique = new ArrayList<>();
        for (VkPostModel gotPost : got) {
            if (uniquePostId.contains(gotPost.getID()) && postHasPhotos(gotPost)) {
                unique.add(gotPost);
            }
        }

        // если есть новые посты с изображениями - сохраняем их
        if (unique.size() > 0) {
            VkPostDao.savePosts(unique);
        }

        // формируем из новых постов список контента (изображения)
        List<VkPostContent> result = new ArrayList<>();
        for (VkPostModel uniquePost : unique) {
            // получение ссылок на изображения в посте
            List<String> urls = new ArrayList<>();
            for (Attachment attachment : uniquePost.getAttachments()) {
                if (attachment.getType().equalsIgnoreCase("photo")) {
                    Size[] sizesPic = attachment.getPhoto().getSizes();
                    sortedSize(sizesPic);
                    urls.add(sizesPic[0].getURL());
                }
            }
            if (urls.size() != 0) {
                result.add(new VkPostContent(uniquePost.getID(), uniquePost.getText(), urls));
            }
        }
        return result;
    }

    // возвращает список id уникальных (новых) постов
    private static List<Long> getUniquePostId(List<VkPostModel> got, List<VkPostModel> saved) {
        // получаем список id всех сохраненных постов
        List<Long> savedId = new ArrayList<>();
        for (VkPostModel savedPost : saved) {
            savedId.add(savedPost.getID());
        }

        // получаем список id всех уникальных постов
        List<Long> result = new ArrayList<>();
        for (VkPostModel gotPost : got) {
            long gotPostId = gotPost.getID();
            if (!savedId.contains(gotPostId)) {
                result.add(gotPostId);
            }
        }
        return result;
    }

    // сортировка качеств изображений во вложении поста
    private static void sortedSize(Size[] tmp) {
        for (int i = tmp.length - 1; i > 0; i--) {
            Size tmpSize;
            if (tmp[i].getHeight() > tmp[i - 1].getHeight()) {
                tmpSize = tmp[i - 1];
                tmp[i - 1] = tmp[i];
                tmp[i] = tmpSize;
            }
        }
    }

    // возвращает true, если в после есть изображения
    private static boolean postHasPhotos(VkPostModel post) {
        Attachment[] tmp = post.getAttachments();
        if (tmp != null && tmp.length > 0) {
            for (Attachment att : post.getAttachments()) {
                if (att.getType().equalsIgnoreCase("photo")) {
                    return true;
                }
            }
        }
        return false;
    }
}
