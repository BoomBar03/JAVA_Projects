package view.administrator;

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

public class DeleteEmployeeView {

    private TextField idField;
    private Button deleteEmployeeButton;
    private Text actiontarget;

    public DeleteEmployeeView(Stage deleteEmployeeStage) {
        deleteEmployeeStage.setTitle("Delete Employee");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 400, 300);
        deleteEmployeeStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeFields(gridPane);

        initializeDeleteEmployeeButton(gridPane);

        deleteEmployeeStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Delete Employee");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeFields(GridPane gridPane) {
        Label idLabel = new Label("Employee ID:");
        gridPane.add(idLabel, 0, 1);

        idField = new TextField();
        gridPane.add(idField, 1, 1);
    }

    private void initializeDeleteEmployeeButton(GridPane gridPane) {
        deleteEmployeeButton = new Button("Delete Employee");
        HBox deleteEmployeeButtonHBox = new HBox(10);
        deleteEmployeeButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        deleteEmployeeButtonHBox.getChildren().add(deleteEmployeeButton);
        gridPane.add(deleteEmployeeButtonHBox, 1, 3);

        actiontarget = new Text();
        gridPane.add(actiontarget, 1, 5);
    }

    public String getIdField() {
        return idField.getText();
    }

    public void setActionTargetText(String text) {
        this.actiontarget.setText(text);
    }

    public void addDeleteEmployeeButtonListener(EventHandler<ActionEvent> deleteEmployeeButtonListener) {
        deleteEmployeeButton.setOnAction(deleteEmployeeButtonListener);
    }
}
