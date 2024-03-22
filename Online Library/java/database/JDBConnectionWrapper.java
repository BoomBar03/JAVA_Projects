package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBConnectionWrapper {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/"; // localhost -> 127.0.0.1
    private static final String USER = "root";
    private static final String PASSWORD = "RootPass1.";
    private static final int TIMEOUT = 5;

    private Connection connection;

    public JDBConnectionWrapper(String schema) {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL + schema, USER, PASSWORD);
            createBookTable();
            createEBookTable();
            createAudioBookTable();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean testConnection() throws SQLException {
        return connection.isValid(TIMEOUT);
    }

    public Connection getConnection() {
        return connection;
    }

    private void createBookTable() throws SQLException {
        Statement statement = connection.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS book(" +
                "id bigint NOT NULL AUTO_INCREMENT," +
                "author varchar(500) NOT NULL," +
                "title varchar(500) NOT NULL," +
                "publishedDate datetime DEFAULT NULL," +
                "PRIMARY KEY(id)," +
                "UNIQUE KEY id_UNIQUE(id)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";

        statement.execute(sql);
    }

    private void createEBookTable() throws SQLException {
        Statement statement = connection.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS ebook(" +
                "id bigint NOT NULL AUTO_INCREMENT," +
                "author varchar(500) NOT NULL," +
                "title varchar(500) NOT NULL," +
                "publishedDate datetime DEFAULT NULL," +
                "format varchar(50) NOT NULL," +
                "PRIMARY KEY(id)," +
                "UNIQUE KEY id_UNIQUE(id)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";

        statement.execute(sql);
    }

    private void createAudioBookTable() throws SQLException {
        Statement statement = connection.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS audiobook(" +
                "id bigint NOT NULL AUTO_INCREMENT," +
                "author varchar(500) NOT NULL," +
                "title varchar(500) NOT NULL," +
                "publishedDate datetime DEFAULT NULL," +
                "runTime int NOT NULL," +
                "PRIMARY KEY(id)," +
                "UNIQUE KEY id_UNIQUE(id)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";

        statement.execute(sql);
    }
}
