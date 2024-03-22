package view.administrator;

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

public class AdministratorView {

    private Button createEmployeeButton;
    private Button retrieveEmployeeButton;
    private Button updateEmployeeButton;
    private Button deleteEmployeeButton;
    private Button generatePdfButton;
    private Text actiontarget;

    public AdministratorView(Stage administratorStage) {
        administratorStage.setTitle("Administrator View");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 720, 480);
        administratorStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeButtons(gridPane);

        administratorStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Welcome Administrator!");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeButtons(GridPane gridPane) {

        createEmployeeButton = new Button("Create Employee");
        HBox createEmployeeButtonHBox = new HBox(10);
        createEmployeeButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        createEmployeeButtonHBox.getChildren().add(createEmployeeButton);
        gridPane.add(createEmployeeButtonHBox, 0, 2);

        retrieveEmployeeButton = new Button("Retrieve Employee");
        HBox retrieveEmployeeButtonHBox = new HBox(10);
        retrieveEmployeeButtonHBox.setAlignment(Pos.BOTTOM_LEFT);
        retrieveEmployeeButtonHBox.getChildren().add(retrieveEmployeeButton);
        gridPane.add(retrieveEmployeeButtonHBox, 1, 2);

        updateEmployeeButton = new Button("Update Employee");
        HBox updateEmployeeButtonHBox = new HBox(10);
        updateEmployeeButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        updateEmployeeButtonHBox.getChildren().add(updateEmployeeButton);
        gridPane.add(updateEmployeeButtonHBox, 0, 3);

        deleteEmployeeButton = new Button("Delete Employee");
        HBox deleteEmployeeButtonHBox = new HBox(10);
        deleteEmployeeButtonHBox.setAlignment(Pos.BOTTOM_LEFT);
        deleteEmployeeButtonHBox.getChildren().add(deleteEmployeeButton);
        gridPane.add(deleteEmployeeButtonHBox, 1, 3);

        generatePdfButton = new Button("Generate PDF Report");
        HBox generatePdfButtonHBox = new HBox(10);
        generatePdfButtonHBox.setAlignment(Pos.BOTTOM_CENTER);
        generatePdfButtonHBox.getChildren().add(generatePdfButton);
        gridPane.add(generatePdfButtonHBox, 0, 4);

        actiontarget = new Text();
        actiontarget.setFill(Color.GREEN);
        gridPane.add(actiontarget, 1, 5);
    }

    public void addActionTargetText(String text) {
        this.actiontarget.setText(text);
    }

    public void addCreateEmployeeButtonListener(EventHandler<ActionEvent> createEmployeeButtonListener) {
        createEmployeeButton.setOnAction(createEmployeeButtonListener);
    }

    public void addRetrieveEmployeeButtonListener(EventHandler<ActionEvent> retrieveEmployeeButtonListener) {
        retrieveEmployeeButton.setOnAction(retrieveEmployeeButtonListener);
    }

    public void addUpdateEmployeeButtonListener(EventHandler<ActionEvent> updateEmployeeButtonListener) {
        updateEmployeeButton.setOnAction(updateEmployeeButtonListener);
    }

    public void addDeleteEmployeeButtonListener(EventHandler<ActionEvent> deleteEmployeeButtonListener) {
        deleteEmployeeButton.setOnAction(deleteEmployeeButtonListener);
    }

    public void addGeneratePdfButtonListener(EventHandler<ActionEvent> generatePdfButtonListener) {
        generatePdfButton.setOnAction(generatePdfButtonListener);
    }
}
