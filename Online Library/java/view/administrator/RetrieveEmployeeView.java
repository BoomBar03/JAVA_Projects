package view.administrator;

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
import model.User;

public class RetrieveEmployeeView {

    private TextField idField;
    private Button retrieveEmployeeButton;
    private Button retrieveAllEmployeesButton;
    private TableView<User> employeeTable;
    private Text actiontarget;

    public RetrieveEmployeeView(Stage retrieveEmployeeStage) {
        retrieveEmployeeStage.setTitle("Retrieve Employee");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 600, 400);
        retrieveEmployeeStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeFields(gridPane);

        initializeRetrieveEmployeeButtons(gridPane);

        initializeEmployeeTable(gridPane);

        retrieveEmployeeStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Retrieve Employee");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeFields(GridPane gridPane) {
        Label idLabel = new Label("Employee ID:");
        gridPane.add(idLabel, 0, 1);

        idField = new TextField();
        gridPane.add(idField, 1, 1);
    }

    private void initializeRetrieveEmployeeButtons(GridPane gridPane) {
        retrieveEmployeeButton = new Button("Retrieve Employee");
        HBox retrieveEmployeeButtonHBox = new HBox(10);
        retrieveEmployeeButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        retrieveEmployeeButtonHBox.getChildren().add(retrieveEmployeeButton);
        gridPane.add(retrieveEmployeeButtonHBox, 1, 2);

        retrieveAllEmployeesButton = new Button("Retrieve All Employees");
        HBox retrieveAllEmployeesButtonHBox = new HBox(10);
        retrieveAllEmployeesButtonHBox.setAlignment(Pos.BOTTOM_LEFT);
        retrieveAllEmployeesButtonHBox.getChildren().add(retrieveAllEmployeesButton);
        gridPane.add(retrieveAllEmployeesButtonHBox, 0, 2);

        actiontarget = new Text();
        gridPane.add(actiontarget, 1, 4);
    }

    private void initializeEmployeeTable(GridPane gridPane) {
        employeeTable = new TableView<>();

        employeeTable.setEditable(true);

        gridPane.add(employeeTable, 0, 5, 2, 1);

        TableColumn<User, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        employeeTable.getColumns().addAll(idColumn, usernameColumn);
    }

    public String getIdField() {
        return idField.getText();
    }

    public void setActionTargetText(String text) {
        this.actiontarget.setText(text);
    }

    public void addRetrieveEmployeeButtonListener(EventHandler<ActionEvent> retrieveEmployeeButtonListener) {
        retrieveEmployeeButton.setOnAction(retrieveEmployeeButtonListener);
    }

    public void addRetrieveAllEmployeesButtonListener(EventHandler<ActionEvent> retrieveAllEmployeesButtonListener) {
        retrieveAllEmployeesButton.setOnAction(retrieveAllEmployeesButtonListener);
    }

    public TableView<User> getEmployeeTable() {
        return employeeTable;
    }

    public void populateEmployeeTable(ObservableList<User> data) {
        employeeTable.setItems(data);
    }
}
