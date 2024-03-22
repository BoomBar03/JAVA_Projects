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

public class UpdateEmployeeView {

    private TextField idField;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button updateEmployeeButton;
    private Text actiontarget;

    public UpdateEmployeeView(Stage updateEmployeeStage) {
        updateEmployeeStage.setTitle("Update Employee");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 400, 300);
        updateEmployeeStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeFields(gridPane);

        initializeUpdateEmployeeButton(gridPane);

        updateEmployeeStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Update Employee");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeFields(GridPane gridPane) {
        Label idLabel = new Label("ID:");
        gridPane.add(idLabel, 0, 1);

        idField = new TextField();
        gridPane.add(idField, 1, 1);

        Label usernameLabel = new Label("Username:");
        gridPane.add(usernameLabel, 0, 2);

        usernameField = new TextField();
        gridPane.add(usernameField, 1, 2);

        Label passwordLabel = new Label("Password:");
        gridPane.add(passwordLabel, 0, 3);

        passwordField = new PasswordField();
        gridPane.add(passwordField, 1, 3);
    }

    private void initializeUpdateEmployeeButton(GridPane gridPane) {
        updateEmployeeButton = new Button("Update Employee");
        HBox updateEmployeeButtonHBox = new HBox(10);
        updateEmployeeButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        updateEmployeeButtonHBox.getChildren().add(updateEmployeeButton);
        gridPane.add(updateEmployeeButtonHBox, 1, 4);

        actiontarget = new Text();
        gridPane.add(actiontarget, 1, 6);
    }

    public String getIdField() {
        return idField.getText();
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

    public void addUpdateEmployeeButtonListener(EventHandler<ActionEvent> updateEmployeeButtonListener) {
        updateEmployeeButton.setOnAction(updateEmployeeButtonListener);
    }
}
