/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkerboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Alyssa
 */
public class CheckerBoardFXMLController implements Initializable, Startable {

    private Stage stage;
    private int numRows;
    private int numCols;
    private String menuItemId;
    public double menuHeight;
    private double anchorWidth;
    private double anchorHeight;
    private double stageHeight;
        private double stageWidth;



    @FXML
    private AnchorPane anchorPane;
    @FXML
    private MenuItem board16x16;
    @FXML
    private MenuItem board10x10;
    @FXML
    private MenuItem board8x8;
    @FXML
    private MenuItem board3x3;
    @FXML
    private MenuBar menu;
    CheckerBoard checkerBoard;

    @FXML
    private VBox vBox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(menu.getHeight());
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        menuHeight = menu.getHeight();
        stageHeight = stage.getHeight();
        stageWidth = stage.getWidth();
        anchorWidth = stage.getWidth();
        anchorHeight = (stageHeight - menuHeight);
        checkerBoard = new CheckerBoard(8, 8, anchorWidth, anchorHeight);

        ChangeListener<Number> lambdaChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, final Number newValue) -> {

            checkerBoard = new CheckerBoard(checkerBoard.getNumRows(), checkerBoard.getNumCols(), stage.getWidth(), (stage.getHeight() - menuHeight), checkerBoard.getLightColor(), checkerBoard.getDarkColor());

            refresh();

        };

        this.stage.widthProperty().addListener(lambdaChangeListener);
        this.stage.heightProperty().addListener(lambdaChangeListener);

        refresh();

    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        refresh();
    }

    @FXML
    private void handleClear(ActionEvent event) {
        System.out.println("clear");
        clearAnchorPane();
    }

    @FXML
    private void handleAbout(Event event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("CheckerBoard");
        alert.setContentText("This application was developed by Alyssa Nielsen for CS4330/7330 at the University of Missouri.  This is based on the code provided at http://code.makery.ch/blog/javafx-dialogs-official/");
        alert.showAndWait();
    }

    private void refresh() {
        clearAnchorPane();
        //anchorPane.setPrefSize(stageWidth, stageHeight);
        anchorPane.getChildren().addAll(checkerBoard.build());
    }

    private void clearAnchorPane() {
        anchorPane.getChildren().clear();
    }

    @FXML
    private void changeSize(ActionEvent event) {
        MenuItem menuItem = (MenuItem) (event.getSource());
        menuItemId = menuItem.getId();

        switch (menuItemId) {
            case "board16x16":
                checkerBoard = new CheckerBoard(16, 16, checkerBoard.getWidth(), checkerBoard.getHeight(), checkerBoard.getLightColor(), checkerBoard.getDarkColor());
                break;
            case "board10x10":
                checkerBoard = new CheckerBoard(10, 10, checkerBoard.getWidth(), checkerBoard.getHeight(), checkerBoard.getLightColor(), checkerBoard.getDarkColor());
                break;
            case "board8x8":
                checkerBoard = new CheckerBoard(8, 8, checkerBoard.getWidth(), checkerBoard.getHeight(), checkerBoard.getLightColor(), checkerBoard.getDarkColor());
                break;
            case "board3x3":
                checkerBoard = new CheckerBoard(3, 3, checkerBoard.getWidth(), checkerBoard.getHeight(), checkerBoard.getLightColor(), checkerBoard.getDarkColor());
                break;
            default:
                break;
        }
        refresh();
    }

    @FXML
    private void changeColor(ActionEvent event) {
        MenuItem menuItem = (MenuItem) (event.getSource());
        menuItemId = menuItem.getId();
        numRows = checkerBoard.getNumRows();
        numCols = checkerBoard.getNumCols();
        anchorWidth = checkerBoard.getWidth();
        anchorHeight = checkerBoard.getHeight();
        //if (checkerBoard.getBoard() != null) {
            switch (menuItemId) {
                case "redBlack":
                    checkerBoard = new CheckerBoard(numRows, numCols, anchorWidth, anchorHeight, Color.RED, Color.BLACK);
                    break;
                case "blueDarkBlue":
                    checkerBoard = new CheckerBoard(numRows, numCols, anchorWidth, anchorHeight, Color.SKYBLUE, Color.DARKBLUE);
                    break;

            }
       // }
        refresh();
    }

}
