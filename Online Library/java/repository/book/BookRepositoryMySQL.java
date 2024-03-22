package repository.book;

import model.AudioBook;
import model.Book;
import model.EBook;
import model.builder.BookBuilder;
import repository.book.BookRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryMySQL implements BookRepository {
    private final Connection connection;

    public BookRepositoryMySQL(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Book> bookFindAll() {
        String sql = "SELECT * FROM book;";

        List<Book> books = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                books.add(getBookFromResultSet(resultSet));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> ebookFindAll() {
        String sql = "SELECT * FROM ebook;";

        List<Book> books = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                books.add(getBookFromResultSet(resultSet));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return books;
    }
    public List<Book> audiobookFindAll() {
        String sql = "SELECT * FROM audiobook;";

        List<Book> books = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                books.add(getBookFromResultSet(resultSet));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Optional<Book> bookFindById(Long id) {
        String sql = "SELECT * FROM book WHERE id = ?";
        Optional<Book> book = Optional.empty();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                book = Optional.of(getBookFromResultSet(resultSet));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return book;
    }

    public Optional<Book> ebookFindById(Long id) {
        String sql = "SELECT * FROM ebook WHERE id = ?";
        Optional<Book> book = Optional.empty();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                book = Optional.of(getBookFromResultSet(resultSet));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return book;
    }

    public Optional<Book> audiobookFindById(Long id) {
        String sql = "SELECT * FROM audiobook WHERE id = ?";
        Optional<Book> book = Optional.empty();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                book = Optional.of(getBookFromResultSet(resultSet));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return book;
    }

    /**
     *
     * How to reproduce a sql injection attack on insert statement
     *
     *
     * 1) Uncomment the lines below and comment out the PreparedStatement part
     * 2) For the Insert Statement DROP TABLE SQL Injection attack to succeed we will need multi query support to be added to our connection
     * Add to JDBConnectionWrapper the following flag after the DB_URL + schema concatenation: + "?allowMultiQueries=true"
     * 3) book.setAuthor("', '', null); DROP TABLE book; -- "); // this will delete the table book
     * 3*) book.setAuthor("', '', null); SET FOREIGN_KEY_CHECKS = 0; SET GROUP_CONCAT_MAX_LEN=32768; SET @tables = NULL; SELECT GROUP_CONCAT('`', table_name, '`') INTO @tables FROM information_schema.tables WHERE table_schema = (SELECT DATABASE()); SELECT IFNULL(@tables,'dummy') INTO @tables; SET @tables = CONCAT('DROP TABLE IF EXISTS ', @tables); PREPARE stmt FROM @tables; EXECUTE stmt; DEALLOCATE PREPARE stmt; SET FOREIGN_KEY_CHECKS = 1; --"); // this will delete all tables. You are not required to know the table name anymore.
     * 4) Run the program. You will get an exception on findAll() method because the test_library.book table does not exist anymore
     */


    // ALWAYS use PreparedStatement when USER INPUT DATA is present
    // DON'T CONCATENATE Strings!

    @Override
    public boolean saveBook(Book book) {
        String sql = "INSERT INTO book VALUES(null, ?, ?, ?, ?, ?);";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setDate(3, java.sql.Date.valueOf(book.getPublishedDate()));
            preparedStatement.setLong(4, book.getStock());
            preparedStatement.setFloat(5, book.getPrice());

            int rowsInserted = preparedStatement.executeUpdate();

            return (rowsInserted != 1) ? false : true;

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveEBook(EBook eBook) {
        String sql = "INSERT INTO ebook VALUES(null, ?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, eBook.getAuthor());
            preparedStatement.setString(2, eBook.getTitle());
            preparedStatement.setDate(3, java.sql.Date.valueOf(eBook.getPublishedDate()));
            preparedStatement.setString(4, eBook.getFormat());

            int rowsInserted = preparedStatement.executeUpdate();

            return (rowsInserted != 1);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveAudioBook(AudioBook audioBook) {
        String sql = "INSERT INTO audiobook VALUES(null, ?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, audioBook.getAuthor());
            preparedStatement.setString(2, audioBook.getTitle());
            preparedStatement.setDate(3, java.sql.Date.valueOf(audioBook.getPublishedDate()));
            preparedStatement.setInt(4, audioBook.getRunTime());

            int rowsInserted = preparedStatement.executeUpdate();

            return (rowsInserted != 1);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void bookRemoveAll() {
        String sql = "TRUNCATE table book;";

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void ebookRemoveAll() {
        String sql = "TRUNCATE table ebook;";

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void audiobookRemoveAll() {
        String sql = "TRUNCATE table audiobook;";

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        BookBuilder bookBuilder;

        switch (resultSet.getMetaData().getTableName(1)) {
            case "ebook":
                bookBuilder = new BookBuilder<>(new EBook())
                        .setFormat
                                (resultSet.getString("format"));
                break;
            case "audiobook":
                bookBuilder = new BookBuilder<>(new AudioBook())
                        .setRunTime(resultSet.getInt("runTime"));
                break;
            default:
                bookBuilder = new BookBuilder<>(new Book())
                        .setStock(resultSet.getLong("stock"))
                        .setPrice(resultSet.getFloat("price"));
        }

        return bookBuilder
                .setId(resultSet.getLong("id"))
                .setAuthor(resultSet.getString("author"))
                .setTitle(resultSet.getString("title"))
                .setPublishedDate(new java.sql.Date((resultSet.getDate("publishedDate")).getTime()).toLocalDate())
                .build();
    }

    public boolean removeBook(Long id) {
        String sql = "DELETE FROM book WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();

            return rowsDeleted == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBook(Book book) {
        String sql = "UPDATE book SET author = ?, title = ?, publishedDate = ?, stock = ?, price = ? WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setDate(3, java.sql.Date.valueOf(book.getPublishedDate()));
            preparedStatement.setLong(4, book.getStock());
            preparedStatement.setFloat(5, book.getPrice());
            preparedStatement.setLong(6, book.getId());

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
