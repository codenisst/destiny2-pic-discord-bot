package ru.codenisst.discord2pic.vk.dao;

import com.google.gson.Gson;
import ru.codenisst.discord2pic.vk.models.vkpost.VkPostModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VkPostDao {

    private static final File saveFolder = new File("src/main/resources/database/vkposts");
    private static List<File> saveList;

    // чтение файлов из бд
    public static List<VkPostModel> getAllSavedPosts() {
        File[] tmpFileArray = saveFolder.listFiles();
        if (tmpFileArray != null) {
            saveList = Arrays.asList(tmpFileArray);
            List<VkPostModel> result = new ArrayList<>();
            for (File file : saveList) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file.getPath()))) {
                    result.add(new Gson().fromJson(reader.readLine(), VkPostModel.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
        return new ArrayList<>();
    }

    // запись файлов в бд (имя файла - id поста)
    public static void savePosts(List<VkPostModel> posts) {
        if (posts.size() > 0) {
            for (VkPostModel post : posts) {
                String fileName = post.getID() + ".json";
                try (BufferedWriter writer = new BufferedWriter(
                        new FileWriter(saveFolder.getPath() + "/" + fileName))) {
                    writer.write(new Gson().toJson(post));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
