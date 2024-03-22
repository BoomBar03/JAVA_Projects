package view.administrator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateEmployeeView {

    private TextField usernameField;
    private PasswordField passwordField;
    private Button createEmployeeButton;
    private Text actiontarget;

    public CreateEmployeeView(Stage createEmployeeStage) {
        createEmployeeStage.setTitle("Create Employee");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 400, 300);
        createEmployeeStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeFields(gridPane);

        initializeCreateEmployeeButton(gridPane);

        createEmployeeStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Create Employee");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeFields(GridPane gridPane) {
        Label usernameLabel = new Label("Username:");
        gridPane.add(usernameLabel, 0, 1);

        usernameField = new TextField();
        gridPane.add(usernameField, 1, 1);

        Label passwordLabel = new Label("Password:");
        gridPane.add(passwordLabel, 0, 2);

        passwordField = new PasswordField();
        gridPane.add(passwordField, 1, 2);
    }

    private void initializeCreateEmployeeButton(GridPane gridPane) {
        createEmployeeButton = new Button("Create Employee");
        HBox createEmployeeButtonHBox = new HBox(10);
        createEmployeeButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        createEmployeeButtonHBox.getChildren().add(createEmployeeButton);
        gridPane.add(createEmployeeButtonHBox, 1, 3);

        actiontarget = new Text();
        gridPane.add(actiontarget, 1, 5);
    }

    public String getUsernameField() {
        return usernameField.getText();
    }

    public String getPasswordField() {
        return passwordField.getText();
    }

    public void setActionTargetText(String text) {
        this.actiontarget.setText(text);
    }

    public void addCreateEmployeeButtonListener(EventHandler<ActionEvent> createEmployeeButtonListener) {
        createEmployeeButton.setOnAction(createEmployeeButtonListener);
    }
}
