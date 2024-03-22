package view.customer;

import controller.customer.BuyBooksController;
import database.DatabaseConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Book;
import model.User;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import service.book.BookService;
import service.book.BookServiceImpl;

import java.time.LocalDate;

public class BuyBooksView {

    private TableView<Book> booksTable = new TableView<>();
    private TextField quantityField = new TextField();
    private TextField bookIdField = new TextField();
    private TableView<Book> cartTable = new TableView<>();
    private Button addToCartButton;
    private Button placeOrderButton;

    private BuyBooksController controller;

    private User userLogged;

    private Text actiontarget;

    public BuyBooksView(Stage booksStage, User userLogged) {

        booksStage.setTitle("Buy Books");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 720, 480);
        booksStage.setScene(scene);

        initializeSceneTitle(gridPane);

        Label labelBooks = new Label("Available Books");
        labelBooks.setFont(new Font("Arial", 20));

        addToCartButton = new Button("Add to Cart");
        placeOrderButton = new Button("Place Order");

        this.userLogged=userLogged;
        controller = new BuyBooksController(this, userLogged);
        controller.populateBookIdTextField();


        BookRepository bookRepository = new BookRepositoryCacheDecorator(new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                new Cache<>()
        );
        BookService bookService = new BookServiceImpl(bookRepository);

        ObservableList<Book> bookList = FXCollections.observableArrayList(bookService.bookFindAll());

        setBookTable(bookList);

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(labelBooks, booksTable, createOrderBox());
        gridPane.add(vbox, 0, 1);

        booksStage.show();
    }

    public void setActionTargetText(String text){ this.actiontarget.setText(text);}
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

    private VBox createOrderBox() {
        VBox orderBox = new VBox();
        orderBox.setSpacing(5);
        orderBox.setPadding(new Insets(10, 0, 0, 10));

        Label labelOrder = new Label("Select a book to order");
        labelOrder.setFont(new Font("Arial", 20));

        Label labelQuantity = new Label("Quantity:");

        Label labelBookId = new Label("Book ID:");

        actiontarget = new Text();
        actiontarget.setFill(Color.FIREBRICK);

        orderBox.getChildren().addAll(labelBookId, bookIdField, labelQuantity, quantityField, addToCartButton, cartTable, placeOrderButton, actiontarget);
        return orderBox;
    }

    public void setBookTable(ObservableList<Book> bookList) {

        booksTable.setEditable(true);

        TableColumn<Book, Long> idCol = new TableColumn<>("ID");
        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        TableColumn<Book, LocalDate> publishedDateCol = new TableColumn<>("Published Date");
        TableColumn<Book, Long> stockCol = new TableColumn<>("Stock");
        TableColumn<Book, Float> priceCol = new TableColumn<>("Price");

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Author"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        publishedDateCol.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
        stockCol.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));

        booksTable.setPrefWidth(800);
        idCol.setPrefWidth(50);
        titleCol.setPrefWidth(200);
        authorCol.setPrefWidth(150);
        publishedDateCol.setPrefWidth(150);
        stockCol.setPrefWidth(100);
        priceCol.setPrefWidth(100);

        booksTable.setItems(bookList);
        booksTable.getColumns().addAll(idCol, authorCol, titleCol, publishedDateCol, stockCol, priceCol);
    }


    public void setCartTable() {
        cartTable.setEditable(true);

        TableColumn<Book, Long> idCol = new TableColumn<>("ID");
        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        TableColumn<Book, LocalDate> publishedDateCol = new TableColumn<>("Published Date");
        TableColumn<Book, Long> stockCol = new TableColumn<>("Stock");
        TableColumn<Book, Float> priceCol = new TableColumn<>("Price");

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Author"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        publishedDateCol.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
        stockCol.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));

        cartTable.setPrefWidth(800);
        idCol.setPrefWidth(50);
        titleCol.setPrefWidth(200);
        authorCol.setPrefWidth(150);
        publishedDateCol.setPrefWidth(150);
        stockCol.setPrefWidth(100);
        priceCol.setPrefWidth(100);

        cartTable.setItems(FXCollections.observableArrayList());

        cartTable.getColumns().addAll(idCol, authorCol, titleCol, publishedDateCol, stockCol, priceCol);
    }

    public TextField getQuantityField() {
        return quantityField;
    }

    public TableView<Book> getBooksTable() {
        return booksTable;
    }

    public TableView<Book> getCartTable() {
        return cartTable;
    }

    public TextField getBookIdField() {
        return bookIdField;
    }

    public void addToCartButtonListener(EventHandler<ActionEvent> addToCartButtonListener) {
        addToCartButton.setOnAction(addToCartButtonListener);
    }

    public void placeOrderButtonListener(EventHandler<ActionEvent> placeOrderButtonListener) {
        placeOrderButton.setOnAction(placeOrderButtonListener);
    }
}
