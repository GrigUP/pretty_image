package org.tpu.services;

import org.apache.commons.fileupload.FileItem;
import org.tpu.database.dao.ImageAccountLikesDAO;
import org.tpu.database.dao.ImageDAO;
import org.tpu.database.interfaces.DBFactory;
import org.tpu.database.models.Image;

import java.io.File;
import java.util.List;
import java.util.Random;

public class ImageService {
    private DBFactory factory;
    private Random random;

    public ImageService(DBFactory factory) {
        this.factory = factory;
        random = new Random();
    }

    public String changeLike(int accountId, int imageId) {
        ImageAccountLikesDAO imageAccountLikesDAO = new ImageAccountLikesDAO(factory.connect());
        String result = imageAccountLikesDAO.changeLikeForImage(accountId, imageId);
        ImageDAO imageDAO = new ImageDAO(factory.connect());
        switch (result) {
            case "added":
                imageDAO.likeImages(imageId);
                break;
            case "deleted":
                imageDAO.dislikeImages(imageId);
                break;
        }
        factory.close();
        return result;
    }

    public List<Image> readAll() {
        ImageDAO imageDAO = new ImageDAO(factory.connect());
        List<Image> imageList = imageDAO.readAllImage();
        factory.close();
        return imageList;
    }

    public void createOnDB(Image obj) {
        ImageDAO dao = new ImageDAO(factory.connect());
        dao.createImage(obj);
        factory.close();
    }

    public void deleteById(int id) {
        ImageDAO dao = new ImageDAO(factory.connect());
        Image image = dao.readImageById(id);
        deleteFromFileSystem(image);
        dao.deleteImageById(id);
        factory.close();
    }

    public Image createOnFileSystem(Object meta) {
        ImageMeta imageMeta = null;
        if (meta instanceof ImageMeta)
            imageMeta = (ImageMeta) meta;
        else {
            /**
             * todo
             * Exception
             */
        }

        System.out.println(imageMeta);
        String imageRoot = imageMeta.getRoot();
        String imageWebRoot = imageMeta.getWebRoot();
        List<FileItem> items = imageMeta.getFileItems();
        try {
            Image image = buildPicture(imageRoot, items);
            image.setWebPath(imageWebRoot + getFileNameByPath(image.getLocalPath()));
            return image;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private void deleteFromFileSystem(Image image) {
        String localPath = image.getLocalPath();
        File file = new File(localPath);
        if (file.exists()) {
            System.out.println("File exists");
            if (file.delete()) {
                System.out.println("deleted");
            }
        }
    }

    private String getFileExtension(FileItem fileItem) {
        String fileName = fileItem.getName();
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private String getFileNameByPath(String path) {
        System.out.println(path.substring(path.lastIndexOf("/") + 1 ));
        return path.substring(path.lastIndexOf("/")+1);
    }

    private Image buildPicture(String imageRoot, List<FileItem> items) throws Exception {
        Image resultImage = new Image();
        for (FileItem fileItem: items) {
            switch (fileItem.getFieldName()) {
                case("file"):
                    String path = processUploadedFile(imageRoot, fileItem);
                    if (path == null) {
                        return null;
                    }
                    resultImage.setLocalPath(path);
                    break;
                case("tags"):
                    resultImage.setTags(fileItem.getString());
                    break;
            }
        }

        resultImage.setDate(getCurrentTime());

        return resultImage;
    }

    private String processUploadedFile(String imageRoot, FileItem item) throws Exception {
        String extension = getFileExtension(item);
        String fileName = random.nextInt(10000) + extension;
        String path = imageRoot + fileName;
        File uploadedFile = new File(path);

        if (!uploadedFile.exists()) {
            if (uploadedFile.createNewFile()) {
                item.write(uploadedFile);
                return path;
            } else {
                System.out.println("cant create");
            }

        }

        return null;
    }

    private String getCurrentTime() {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);

        return currentTime;
    }

}
