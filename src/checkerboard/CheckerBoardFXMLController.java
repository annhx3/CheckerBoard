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
 * @author Alyssa Nielsen
 *
 */
public class CheckerBoardFXMLController implements Initializable, Startable {

    private Stage stage;
    private int numRows;
    private int numCols;
    private String menuItemId;
    private double boardWidth;
    private double boardHeight;
    CheckerBoard checkerBoard;

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
    @FXML
    private VBox vBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        boardWidth = stage.getWidth();
        boardHeight = anchorPane.getHeight();
        checkerBoard = new CheckerBoard(8, 8, boardWidth, boardHeight);

        ChangeListener<Number> lambdaChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, final Number newValue) -> {

            checkerBoard = new CheckerBoard(checkerBoard.getNumRows(), checkerBoard.getNumCols(), stage.getWidth(), anchorPane.getHeight(), checkerBoard.getLightColor(), checkerBoard.getDarkColor());
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

    private void refresh() {
        clearAnchorPane();
        anchorPane.setPrefSize(boardWidth, boardHeight);
        anchorPane.getChildren().addAll(checkerBoard.build());
    }

    @FXML
    private void handleClear(ActionEvent event) {
        System.out.println("clear");
        clearAnchorPane();
    }

    private void clearAnchorPane() {
        anchorPane.getChildren().clear();
    }

    @FXML
    private void handleAbout(Event event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("CheckerBoard");
        alert.setContentText("This application was developed by Alyssa Nielsen for CS4330/7330 at the University of Missouri.  This is based on the code provided at http://code.makery.ch/blog/javafx-dialogs-official/");
        alert.showAndWait();
    }

    @FXML
    private void handleBoardSize(ActionEvent event) {
        MenuItem menuItem = (MenuItem) (event.getSource());
        menuItemId = menuItem.getId();

        switch (menuItemId) {
            case "board16x16":
                checkerBoard = new CheckerBoard(16, 16, checkerBoard.getWidth(), anchorPane.getHeight(), checkerBoard.getLightColor(), checkerBoard.getDarkColor());
                break;
            case "board10x10":
                checkerBoard = new CheckerBoard(10, 10, checkerBoard.getWidth(), anchorPane.getHeight(), checkerBoard.getLightColor(), checkerBoard.getDarkColor());
                break;
            case "board8x8":
                checkerBoard = new CheckerBoard(8, 8, checkerBoard.getWidth(), anchorPane.getHeight(), checkerBoard.getLightColor(), checkerBoard.getDarkColor());
                break;
            case "board3x3":
                checkerBoard = new CheckerBoard(3, 3, checkerBoard.getWidth(), anchorPane.getHeight(), checkerBoard.getLightColor(), checkerBoard.getDarkColor());
                break;
            default:
                break;
        }
        refresh();
    }

    @FXML
    private void handleBoardColor(ActionEvent event) {
        MenuItem menuItem = (MenuItem) (event.getSource());
        menuItemId = menuItem.getId();
        numRows = checkerBoard.getNumRows();
        numCols = checkerBoard.getNumCols();
        boardWidth = checkerBoard.getWidth();
        boardHeight = anchorPane.getHeight();
        switch (menuItemId) {
            case "redBlack":
                checkerBoard = new CheckerBoard(numRows, numCols, boardWidth, boardHeight, Color.RED, Color.BLACK);
                break;
            case "blueDarkBlue":
                checkerBoard = new CheckerBoard(numRows, numCols, boardWidth, boardHeight, Color.SKYBLUE, Color.DARKBLUE);
                break;
        }
        refresh();
    }
}
