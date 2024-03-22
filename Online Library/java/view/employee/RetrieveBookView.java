package view.employee;

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
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Book;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import service.book.BookService;
import service.book.BookServiceImpl;

public class RetrieveBookView {

    private TextField idField;
    private Button retrieveBookButton;
    private TableView<Book> bookTable;
    private Text actiontarget;

    public RetrieveBookView(Stage retrieveBookStage) {
        retrieveBookStage.setTitle("Retrieve Book");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 600, 400);
        retrieveBookStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeFields(gridPane);

        initializeRetrieveBookButton(gridPane);

        initializeBookTable(gridPane);

        retrieveBookStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Retrieve Book");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeFields(GridPane gridPane) {
        Label idLabel = new Label("Book ID:");
        gridPane.add(idLabel, 0, 1);

        idField = new TextField();
        gridPane.add(idField, 1, 1);
    }

    private void initializeRetrieveBookButton(GridPane gridPane) {
        retrieveBookButton = new Button("Retrieve Book");
        HBox retrieveBookButtonHBox = new HBox(10);
        retrieveBookButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        retrieveBookButtonHBox.getChildren().add(retrieveBookButton);
        gridPane.add(retrieveBookButtonHBox, 1, 2);

        actiontarget = new Text();
        gridPane.add(actiontarget, 1, 4);

    }

    private void initializeBookTable(GridPane gridPane) {
        bookTable = new TableView<>();

        bookTable.setEditable(true);

        gridPane.add(bookTable, 0, 5, 2, 1);

        TableColumn<Book, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, Integer> publishedDateColumn = new TableColumn<>("Published Date");
        publishedDateColumn.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));

        TableColumn<Book, Integer> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn<Book, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        bookTable.getColumns().addAll(idColumn, titleColumn, authorColumn,publishedDateColumn, stockColumn, priceColumn);
    }

    public String getIdField() {
        return idField.getText();
    }

    public void setActionTargetText(String text) {
        this.actiontarget.setText(text);
    }

    public void addRetrieveBookButtonListener(EventHandler<ActionEvent> retrieveBookButtonListener) {
        retrieveBookButton.setOnAction(retrieveBookButtonListener);
    }

    public TableView<Book> getBookTable() {
        return bookTable;
    }

    public void populateBookTable(ObservableList<Book> data) {
        bookTable.setItems(data);
    }

}
