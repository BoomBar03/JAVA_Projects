package view;

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

public class CustomerView {

    private Button viewAllBooksButton;
    private Button buyBooksButton;
    private Text actiontarget;

    public CustomerView(Stage customerStage) {
        customerStage.setTitle("Customer View");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 720, 480);
        customerStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeButtons(gridPane);

        customerStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Welcome customer!");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeButtons(GridPane gridPane) {

        viewAllBooksButton = new Button("View all books");
        HBox viewAllBooksButtonHBox = new HBox(10);
        viewAllBooksButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        viewAllBooksButtonHBox.getChildren().add(viewAllBooksButton);
        gridPane.add(viewAllBooksButtonHBox, 0, 2);

        buyBooksButton = new Button("Buy books");
        HBox buyBooksButtonHBox = new HBox(10);
        buyBooksButtonHBox.setAlignment(Pos.BOTTOM_LEFT);
        buyBooksButtonHBox.getChildren().add(buyBooksButton);
        gridPane.add(buyBooksButtonHBox, 1, 2);

        actiontarget = new Text();
        actiontarget.setFill(Color.FIREBRICK);
        gridPane.add(actiontarget, 1, 4);
    }

    public void addActionTargetText(String text) {
        this.actiontarget.setText(text);
    }

    public void addViewAllBooksButtonListener(EventHandler<ActionEvent> viewAllBooksButtonListener) {
        viewAllBooksButton.setOnAction(viewAllBooksButtonListener);
    }

    public void addBuyBooksButtonListener(EventHandler<ActionEvent> buyBooksButtonListener) {
        buyBooksButton.setOnAction(buyBooksButtonListener);
    }
}
