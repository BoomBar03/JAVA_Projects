package repository.user;
import model.User;
import model.builder.UserBuilder;
import model.validator.Notification;
import repository.security.RightsRolesRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.USER;

public class UserRepositoryMySQL implements UserRepository {

    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;


    public UserRepositoryMySQL(Connection connection, RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String fetchAllUsersSql = "SELECT * FROM `" + USER + "`";
            ResultSet userResultSet = statement.executeQuery(fetchAllUsersSql);

            while (userResultSet.next()) {
                User user = new User();
                user.setId(userResultSet.getLong("id"));
                user.setUsername(userResultSet.getString("username"));
                user.setPassword(userResultSet.getString("password"));
                user.setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    // SQL Injection Attacks should not work after fixing functions
    // Be careful that the last character in sql injection payload is an empty space
    // alexandru.ghiurutan95@gmail.com' and 1=1; --
    // ' or username LIKE '%admin%'; --

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) {

        Notification<User> findByUsernameAndPasswordNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();

            String fetchUserSql =
                    "Select * from `" + USER + "` where `username`=\'" + username + "\' and `password`=\'" + password + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next())
            {
                User user = new UserBuilder()
                        .setId(userResultSet.getLong("id"))
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                        .build();

                findByUsernameAndPasswordNotification.setResult(user);
            } else {
                findByUsernameAndPasswordNotification.addError("Invalid username or password!");
                return findByUsernameAndPasswordNotification;
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
            findByUsernameAndPasswordNotification.addError("Something is wrong with the Database!");
        }

        return findByUsernameAndPasswordNotification;
    }

    @Override
    public boolean save(User user) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long userId = rs.getLong(1);
            user.setId(userId);

            rightsRolesRepository.addRolesToUser(user, user.getRoles());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existsByUsername(String email) {
        try {
            Statement statement = connection.createStatement();

            String fetchUserSql =
                    "Select * from `" + USER + "` where `username`=\'" + email + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            return userResultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Notification<User> findById(Long id) {
        Notification<User> findByIdNotification = new Notification<>();

        try {
            Statement statement = connection.createStatement();

            String fetchUserSql =
                    "SELECT * FROM `" + USER + "` WHERE `id` = " + id;
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);

            if (userResultSet.next()) {
                User user = new UserBuilder()
                        .setId(userResultSet.getLong("id"))
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .setRoles(rightsRolesRepository.findRolesForUser(id))
                        .build();

                findByIdNotification.setResult(user);
            } else {
                findByIdNotification.addError("User not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            findByIdNotification.addError("Something is wrong with the Database!");
        }

        return findByIdNotification;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            PreparedStatement updateUserStatement = connection.prepareStatement(
                    "UPDATE " + USER + " SET username = ?, password = ? WHERE id = ?");

            updateUserStatement.setString(1, user.getUsername());
            updateUserStatement.setString(2, user.getPassword());
            updateUserStatement.setLong(3, user.getId());

            int rowsAffected = updateUserStatement.executeUpdate();

            if (rowsAffected > 0) {
                rightsRolesRepository.addRolesToUser(user, user.getRoles());
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeById(Long id) {
        try {
            PreparedStatement removeUserStatement = connection.prepareStatement(
                    "DELETE FROM " + USER + " WHERE id = ?");
            removeUserStatement.setLong(1, id);

            int rowsAffected = removeUserStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}