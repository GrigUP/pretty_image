package org.tpu.database.dao;

import org.tpu.database.models.Account;
import org.tpu.database.models.AccountType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountsDAO {
    private Connection connection;
    private String tableName = "accounts";
    public AccountsDAO(Connection connection) {
        this.connection = connection;
    }

    public Account readAccount(String email) {
        Account account = null;
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s WHERE email = '%s';", tableName, email))) {

            if(resultSet.next()) {
                account = buildAccountByResultSet(resultSet);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return account;
    }

    public List<Account> readAllAccount() {
        List<Account> accountList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s;", tableName))) {

            while(resultSet.next()) {
                accountList.add(buildAccountByResultSet(resultSet));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return accountList;
    }

    public void createAccount(Account account) {
        String fname = account.getFname();
        String lname = account.getLname();
        String email = account.getEmail();
        String password = account.getPassword();
        String accountType = account.getAccountType().name();

        try(Statement statement = connection.createStatement()) {
            statement.execute(String.format("INSERT INTO %s VALUE (NULL, '%s', '%s', '%s', '%s', '%s');",
                    tableName, fname, lname, email, password, accountType));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteAccountById(int id) {
        try(Statement statement = connection.createStatement()) {
            statement.execute(String.format("DELETE FROM %s WHERE id = '%d';", tableName, id));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Account buildAccountByResultSet(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setFname(resultSet.getString("fname"));
        account.setLname(resultSet.getString("lname"));
        account.setEmail(resultSet.getString("email"));
        account.setPassword(resultSet.getString("password"));

        switch (resultSet.getString("type")) {
            case ("administration"):
                account.setAccountType(AccountType.administration);
                break;
            case ("user"):
                account.setAccountType(AccountType.user);
                break;
            case ("moderator"):
                account.setAccountType(AccountType.moderator);
                break;

            default:
                account.setAccountType(null);
        }

        return account;
    }
}
