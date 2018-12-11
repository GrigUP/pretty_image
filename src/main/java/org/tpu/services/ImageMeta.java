package org.tpu.services;

import org.apache.commons.fileupload.FileItem;

import java.util.List;

public class ImageMeta {
    private List<FileItem> fileItems;
    private String root;
    private String webRoot;

    public ImageMeta(List<FileItem> fileItems, String root, String webRoot) {
        this.fileItems = fileItems;
        this.root = root;
        this.webRoot = webRoot;
    }

    public List<FileItem> getFileItems() {
        return fileItems;
    }

    public void setFileItems(List<FileItem> fileItems) {
        this.fileItems = fileItems;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getWebRoot() {
        return webRoot;
    }

    public void setWebRoot(String webRoot) {
        this.webRoot = webRoot;
    }

    @Override
    public String toString() {
        return "ImageMeta{" +
                "fileItems=" + fileItems +
                ", root='" + root + '\'' +
                ", webRoot='" + webRoot + '\'' +
                '}';
    }
}
