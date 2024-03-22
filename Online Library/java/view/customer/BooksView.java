package view.customer;

import database.DatabaseConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.AudioBook;
import model.Book;
import model.EBook;
import model.builder.BookBuilder;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import service.book.BookService;
import service.book.BookServiceImpl;

import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class BooksView {

    private TableView booksTable = new TableView();
    private TableView<Book> eBooksTable = new TableView<>();
    private TableView<Book> audioBooksTable = new TableView<>();

    public BooksView(Stage booksStage) {

        booksStage.setTitle("Book Store");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 720, 480);
        booksStage.setScene(scene);

        initializeSceneTitle(gridPane);

        Label labelBooks = new Label("Books");
        labelBooks.setFont(new Font("Arial", 20));

        Label labelEBooks = new Label("EBooks");
        labelEBooks.setFont(new Font("Arial", 20));

        Label labelAudioBooks = new Label("AudioBooks");
        labelAudioBooks.setFont(new Font("Arial", 20));

        setBookTable();
        setEBookTable();
        setAudioBookTable();

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(labelBooks, booksTable, labelEBooks, eBooksTable, labelAudioBooks, audioBooksTable);
        gridPane.add(vbox, 0, 1);

        booksStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Welcome to our Book Store");
        sceneTitle.setFont(Font.font("Tahome", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    public void setBookTable() {
        BookRepository bookRepository = new BookRepositoryCacheDecorator(new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                new Cache<>()
        );
        BookService bookService = new BookServiceImpl(bookRepository);

        ObservableList<Book> bookList = FXCollections.observableArrayList(bookService.bookFindAll());

        booksTable.setEditable(true);

        TableColumn<Book, Long> idCol = new TableColumn<>("ID");
        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        TableColumn<Book, LocalDate> publishedDateCol = new TableColumn<>("Published Date");

        idCol.setCellValueFactory(new PropertyValueFactory<Book, Long>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("Author"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("Title"));
        publishedDateCol.setCellValueFactory(new PropertyValueFactory<Book, LocalDate>("publishedDate"));

        booksTable.setPrefWidth(600);
        idCol.setPrefWidth(50);
        titleCol.setPrefWidth(200);
        authorCol.setPrefWidth(150);
        publishedDateCol.setPrefWidth(200);

        booksTable.setItems(bookList);
        booksTable.getColumns().addAll(idCol, authorCol, titleCol, publishedDateCol);
    }

    public void setEBookTable() {
        BookRepository bookRepository = new BookRepositoryCacheDecorator(new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                new Cache<>()
        );
        BookService bookService = new BookServiceImpl(bookRepository);

        ObservableList<Book> bookList = FXCollections.observableArrayList(bookService.ebookFindAll());

        eBooksTable.setEditable(true);

        TableColumn<Book, Long> idCol = new TableColumn<>("ID");
        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        TableColumn<Book, String> formatCol = new TableColumn<>("Format");
        TableColumn<Book, LocalDate> publishedDateCol = new TableColumn<>("Published Date");

        idCol.setCellValueFactory(new PropertyValueFactory<Book, Long>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("Author"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("Title"));
        formatCol.setCellValueFactory(new PropertyValueFactory<>("format"));
        publishedDateCol.setCellValueFactory(new PropertyValueFactory<Book, LocalDate>("publishedDate"));

        eBooksTable.setPrefWidth(600);

        idCol.setPrefWidth(50);
        titleCol.setPrefWidth(200);
        authorCol.setPrefWidth(200);
        publishedDateCol.setPrefWidth(100);
        formatCol.setPrefWidth(50);

        eBooksTable.setItems(bookList);
        eBooksTable.getColumns().addAll(idCol, authorCol, titleCol, publishedDateCol, formatCol);
    }

    public void setAudioBookTable() {
        BookRepository bookRepository = new BookRepositoryCacheDecorator(new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                new Cache<>()
        );
        BookService bookService = new BookServiceImpl(bookRepository);

        ObservableList<Book> bookList = FXCollections.observableArrayList(bookService.audiobookFindAll());

        audioBooksTable.setEditable(true);

        TableColumn<Book, Long> idCol = new TableColumn<>("ID");
        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        TableColumn<Book, Integer> runTimeCol = new TableColumn<>("RunTime");
        TableColumn<Book, LocalDate> publishedDateCol = new TableColumn<>("Published Date");

        idCol.setCellValueFactory(new PropertyValueFactory<Book, Long>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("Author"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("Title"));
        runTimeCol.setCellValueFactory(new PropertyValueFactory<>("runTime"));
        publishedDateCol.setCellValueFactory(new PropertyValueFactory<Book, LocalDate>("publishedDate"));

        audioBooksTable.setPrefWidth(600);

        idCol.setPrefWidth(50);
        titleCol.setPrefWidth(200);
        authorCol.setPrefWidth(150);
        publishedDateCol.setPrefWidth(100);
        runTimeCol.setPrefWidth(100);

        System.out.println(bookList);
        audioBooksTable.setItems(bookList);
        audioBooksTable.getColumns().addAll(idCol, authorCol, titleCol, publishedDateCol,  runTimeCol);
    }
}
