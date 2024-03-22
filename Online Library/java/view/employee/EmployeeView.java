package view.employee;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EmployeeView {

    private Button createBookButton;
    private Button retrieveBookButton;
    private Button updateBookButton;
    private Button deleteBookButton;
    private Button createReportButton; // New button for creating a report
    private Text actiontarget;

    public EmployeeView(Stage employeeStage) {
        employeeStage.setTitle("Employee View");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 720, 480);
        employeeStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeButtons(gridPane);

        employeeStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Welcome employee!");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeButtons(GridPane gridPane) {

        createBookButton = new Button("Create Book");
        HBox createBookButtonHBox = new HBox(10);
        createBookButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        createBookButtonHBox.getChildren().add(createBookButton);
        gridPane.add(createBookButtonHBox, 0, 2);

        retrieveBookButton = new Button("Retrieve Book");
        HBox retrieveBookButtonHBox = new HBox(10);
        retrieveBookButtonHBox.setAlignment(Pos.BOTTOM_LEFT);
        retrieveBookButtonHBox.getChildren().add(retrieveBookButton);
        gridPane.add(retrieveBookButtonHBox, 1, 2);

        updateBookButton = new Button("Update Book");
        HBox updateBookButtonHBox = new HBox(10);
        updateBookButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        updateBookButtonHBox.getChildren().add(updateBookButton);
        gridPane.add(updateBookButtonHBox, 0, 3);

        deleteBookButton = new Button("Delete Book");
        HBox deleteBookButtonHBox = new HBox(10);
        deleteBookButtonHBox.setAlignment(Pos.BOTTOM_LEFT);
        deleteBookButtonHBox.getChildren().add(deleteBookButton);
        gridPane.add(deleteBookButtonHBox, 1, 3);

        createReportButton = new Button("Create Report of Sold Books");
        HBox createReportButtonHBox = new HBox(10);
        createReportButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        createReportButtonHBox.getChildren().add(createReportButton);
        gridPane.add(createReportButtonHBox, 0, 4);

        actiontarget = new Text();
        actiontarget.setFill(Color.GREEN);
        gridPane.add(actiontarget, 1, 5);
    }

    public void addActionTargetText(String text) {
        this.actiontarget.setText(text);
    }

    public void addCreateBookButtonListener(EventHandler<ActionEvent> createBookButtonListener) {
        createBookButton.setOnAction(createBookButtonListener);
    }

    public void addRetrieveBookButtonListener(EventHandler<ActionEvent> retrieveBookButtonListener) {
        retrieveBookButton.setOnAction(retrieveBookButtonListener);
    }

    public void addUpdateBookButtonListener(EventHandler<ActionEvent> updateBookButtonListener) {
        updateBookButton.setOnAction(updateBookButtonListener);
    }

    public void addDeleteBookButtonListener(EventHandler<ActionEvent> deleteBookButtonListener) {
        deleteBookButton.setOnAction(deleteBookButtonListener);
    }

    public void addCreateReportButtonListener(EventHandler<ActionEvent> createReportButtonListener) {
        createReportButton.setOnAction(createReportButtonListener);
    }
}
