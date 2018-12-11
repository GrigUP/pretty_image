package org.tpu.database.dao;

import org.tpu.database.models.Image;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO {
    private Connection connection;
    private String tableName = "images";
    public ImageDAO(Connection connection) {
        this.connection = connection;
    }

    public void likeImages(int id) {
        try(Statement statement = connection.createStatement()) {
            statement.execute(String.format("UPDATE %s SET likes=likes+1 WHERE id='%d';",
                    tableName, id));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void dislikeImages(int id) {
        try(Statement statement = connection.createStatement()) {
            statement.execute(String.format("UPDATE %s SET likes=likes-1 WHERE id='%d';",
                    tableName, id));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void createImage(Image image) {
        String localPath = image.getLocalPath();
        String webPath = image.getWebPath();
        String tags = image.getTags();
        String accountName = image.getAccountName();

        /**
         * todo
         */
        String date = image.getDate();
        int likes = image.getLikes();

        try(Statement statement = connection.createStatement()) {
            statement.execute(String.format("INSERT INTO %s VALUE (NULL, '%s', '%s', '%s', '%s', '%s', '%s');",
                    tableName, webPath, localPath, date, likes, tags, accountName));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Image readImageById(int id) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s WHERE id='%d';", tableName, id))) {

            if(resultSet.next()) {
                return buildImageByResultSet(resultSet);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Image> readAllImage() {
        List<Image> imageList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s;", tableName))) {

            while(resultSet.next()) {
                imageList.add(buildImageByResultSet(resultSet));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return imageList;
    }

    public void deleteImageById(int id) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("DELETE FROM %s WHERE id=%d;", tableName, id));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Image buildImageByResultSet(ResultSet resultSet) throws SQLException {
        Image result = new Image();

        result.setId(resultSet.getInt("id"));
        result.setLocalPath(resultSet.getString("localPath"));
        result.setWebPath(resultSet.getString("webPath"));
        result.setTags(resultSet.getString("tags"));
        result.setDate(resultSet.getString("date"));
        result.setLikes(resultSet.getInt("likes"));
        result.setAccountName(resultSet.getString("accountName"));

        return result;
    }
}
