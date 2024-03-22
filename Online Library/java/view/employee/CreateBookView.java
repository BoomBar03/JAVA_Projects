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

public class CreateBookView {

    private TextField authorField;
    private TextField titleField;
    private TextField publishedDateField;
    private TextField stockField;
    private TextField priceField;
    private Button createBookButton;
    private Text actiontarget;

    public CreateBookView(Stage createBookStage) {
        createBookStage.setTitle("Create Book");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 400, 300);
        createBookStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeFields(gridPane);

        initializeCreateBookButton(gridPane);

        createBookStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Create Book");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeFields(GridPane gridPane) {
        Label authorLabel = new Label("Author:");
        gridPane.add(authorLabel, 0, 1);

        authorField = new TextField();
        gridPane.add(authorField, 1, 1);

        Label titleLabel = new Label("Title:");
        gridPane.add(titleLabel, 0, 2);

        titleField = new TextField();
        gridPane.add(titleField, 1, 2);

        Label publishedDateLabel = new Label("Published Date:");
        gridPane.add(publishedDateLabel, 0, 3);

        publishedDateField = new TextField();
        gridPane.add(publishedDateField, 1, 3);

        Label stockLabel = new Label("Stock:");
        gridPane.add(stockLabel, 0, 4);

        stockField = new TextField();
        gridPane.add(stockField, 1, 4);

        Label priceLabel = new Label("Price:");
        gridPane.add(priceLabel, 0, 5);

        priceField = new TextField();
        gridPane.add(priceField, 1, 5);
    }

    private void initializeCreateBookButton(GridPane gridPane) {
        createBookButton = new Button("Create Book");
        HBox createBookButtonHBox = new HBox(10);
        createBookButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        createBookButtonHBox.getChildren().add(createBookButton);
        gridPane.add(createBookButtonHBox, 1, 6);

        actiontarget = new Text();
        gridPane.add(actiontarget, 1, 8);

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

    public void setActionTargetText(String text){ this.actiontarget.setText(text);}

    public void addCreateBookButtonListener(EventHandler<ActionEvent> createBookButtonListener) {
        createBookButton.setOnAction(createBookButtonListener);
    }
}