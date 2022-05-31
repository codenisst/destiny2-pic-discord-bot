package ru.codenisst.discord2pic.vk.servises.vk;

import ru.codenisst.discord2pic.vk.dao.VkPostDao;
import ru.codenisst.discord2pic.vk.models.vkpost.VkPostModel;
import ru.codenisst.discord2pic.vk.models.vkpost.VkPostPicture;

import java.util.ArrayList;
import java.util.List;

public class VkPicture {

    // Получение списка новых постов с изображениями и сохранение их в БД
    public static List<VkPostPicture> saveAndReturnUniquePostsPicture() throws Exception {
        List<VkPostModel> saved = VkPostDao.getAllSavedPosts();
        List<VkPostModel> resultModel = new ArrayList<>();
        List<VkPostPicture> resultPosts = new ArrayList<>();
        List<Long> postsId = new ArrayList<>();
        for (VkPostModel post:saved) {
            postsId.add(post.getID());
        }
        for (VkPostModel post:VkParserWall.getListVkPosts()) {
            if(!postsId.contains(post.getID())) {
                resultModel.add(post);
                resultPosts.add(new VkPostPicture(post.getID(), post.getText(), post.getAttachments()[0]
                        .getPhoto().getSizes()[5].getURL()));
            }
        }
        VkPostDao.savePosts(resultModel);
        return resultPosts;
    }
}
