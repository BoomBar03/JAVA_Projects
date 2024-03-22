package view.employee;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UpdateBookView {

    private TextField idField;
    private TextField authorField;
    private TextField titleField;
    private TextField publishedDateField;
    private TextField stockField;
    private TextField priceField;
    private Button updateBookButton;
    private Text actiontarget;

    public UpdateBookView(Stage updateBookStage) {
        updateBookStage.setTitle("Update Book");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 400, 300);
        updateBookStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeFields(gridPane);

        initializeUpdateBookButton(gridPane);

        updateBookStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Update Book");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeFields(GridPane gridPane) {
        Label idLabel = new Label("Book ID:");
        gridPane.add(idLabel, 0, 1);

        idField = new TextField();
        gridPane.add(idField, 1, 1);

        Label authorLabel = new Label("Author:");
        gridPane.add(authorLabel, 0, 2);

        authorField = new TextField();
        gridPane.add(authorField, 1, 2);

        Label titleLabel = new Label("Title:");
        gridPane.add(titleLabel, 0, 3);

        titleField = new TextField();
        gridPane.add(titleField, 1, 3);

        Label publishedDateLabel = new Label("Published Date:");
        gridPane.add(publishedDateLabel, 0, 4);

        publishedDateField = new TextField();
        gridPane.add(publishedDateField, 1, 4);

        Label stockLabel = new Label("Stock:");
        gridPane.add(stockLabel, 0, 5);

        stockField = new TextField();
        gridPane.add(stockField, 1, 5);

        Label priceLabel = new Label("Price:");
        gridPane.add(priceLabel, 0, 6);

        priceField = new TextField();
        gridPane.add(priceField, 1, 6);
    }

    private void initializeUpdateBookButton(GridPane gridPane) {
        updateBookButton = new Button("Update Book");
        HBox updateBookButtonHBox = new HBox(10);
        updateBookButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        updateBookButtonHBox.getChildren().add(updateBookButton);
        gridPane.add(updateBookButtonHBox, 1, 7);

        actiontarget = new Text();
        gridPane.add(actiontarget, 1, 8);

    }

    public String getIdField() {
        return idField.getText();
    }

    public String getAuthorField() {
        return authorField.getText();
    }

    public String getTitleField() {
        return titleField.getText();
    }

    public String getPublishedDateField() {
        return publishedDateField.getText();
    }

    public String getStockField() {
        return stockField.getText();
    }

    public String getPriceField() {
        return priceField.getText();
    }

    public void setActionTargetText(String text) {
        this.actiontarget.setText(text);
    }

    public void addUpdateBookButtonListener(EventHandler<ActionEvent> updateBookButtonListener) {
        updateBookButton.setOnAction(updateBookButtonListener);
    }
}
