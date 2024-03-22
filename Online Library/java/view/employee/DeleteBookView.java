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

public class DeleteBookView {

    private TextField idField;
    private Button deleteBookButton;
    private Text actiontarget;

    public DeleteBookView(Stage deleteBookStage) {
        deleteBookStage.setTitle("Delete Book");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 400, 200);
        deleteBookStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeFields(gridPane);

        initializeDeleteBookButton(gridPane);

        deleteBookStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Delete Book");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeFields(GridPane gridPane) {
        Label idLabel = new Label("Book ID:");
        gridPane.add(idLabel, 0, 1);

        idField = new TextField();
        gridPane.add(idField, 1, 1);
    }

    private void initializeDeleteBookButton(GridPane gridPane) {
        deleteBookButton = new Button("Delete Book");
        HBox deleteBookButtonHBox = new HBox(10);
        deleteBookButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        deleteBookButtonHBox.getChildren().add(deleteBookButton);
        gridPane.add(deleteBookButtonHBox, 1, 2);

        actiontarget = new Text();
        gridPane.add(actiontarget, 1, 4);
    }

    public String getIdField() {
        return idField.getText();
    }

    public void setActionTargetText(String text) {
        this.actiontarget.setText(text);
    }

    public void addDeleteBookButtonListener(EventHandler<ActionEvent> deleteBookButtonListener) {
        deleteBookButton.setOnAction(deleteBookButtonListener);
    }
}
