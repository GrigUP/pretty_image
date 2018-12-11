package org.tpu.database.models;

public class Image {
    private int id;
    private String localPath;
    private String webPath;
    private String tags;
    private String date;
    private int likes;
    private String accountName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getWebPath() {
        return webPath;
    }

    public void setWebPath(String webPath) {
        this.webPath = webPath;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", localPath='" + localPath + '\'' +
                ", webPath='" + webPath + '\'' +
                ", tags='" + tags + '\'' +
                ", date='" + date + '\'' +
                ", likes=" + likes +
                ", accountName='" + accountName + '\'' +
                '}';
    }
}
