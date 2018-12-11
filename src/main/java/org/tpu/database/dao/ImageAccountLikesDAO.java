package org.tpu.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ImageAccountLikesDAO {
    private Connection connection;
    private String tableName = "account_image_likes";

    public ImageAccountLikesDAO(Connection connection) {
        this.connection = connection;
    }

    public String changeLikeForImage(int accountId, int imageId) {
        String result = null;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s WHERE (account_id=%d && image_id=%d);", tableName, accountId, imageId))) {

            if(resultSet.next()) {
                deleteLikeForImage(accountId, imageId);
                result = "deleted";
                return result;
            }

            addLikeForImage(accountId, imageId);
            result = "added";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private void deleteLikeForImage(int accountId, int imageId) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("DELETE FROM %s WHERE account_id='%d' && image_id='%d';", tableName, accountId, imageId));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void addLikeForImage(int accountId, int imageId) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("INSERT INTO %s VALUES (NULL, %d, %d);", tableName, accountId, imageId));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
