/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkerboard;

import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Alyssa Nielsen
 *
 */
public class CheckerBoard {

    private int numRows;
    private int numCols;
    private double boardWidth;
    private double boardHeight;

    private Color lightColor;
    private Color darkColor;
    private AnchorPane anchorPane;
    private Rectangle rectangle;
    private double squareSideLength;
    private double squareWidth;
    private double squareHeight;

    public CheckerBoard(int numRows, int numCols, double boardWidth, double boardHeight) {
        this(numRows, numCols, boardWidth, boardHeight, Color.RED, Color.BLACK);
    }

    public CheckerBoard(int numRows, int numCols, double boardWidth, double boardHeight, Color lightColor, Color darkColor) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.lightColor = lightColor;
        this.darkColor = darkColor;
        if ((boardWidth / numCols) > (boardHeight / numRows)) {
            squareSideLength = (boardHeight / numRows);
        } else {
            squareSideLength = (boardWidth / numCols);
        }
    }

    public AnchorPane build() {
        anchorPane = new AnchorPane();

        double squareWidth = (boardWidth / numCols);
        double squareHeight = (boardHeight / numRows);

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                rectangle = new Rectangle();
                rectangle.setWidth(squareWidth);
                rectangle.setHeight(squareHeight);
                rectangle.setX(squareWidth * col);
                rectangle.setY(squareHeight * row);
                if ((row % 2) == (col % 2)) {
                    rectangle.setFill(lightColor);
                } else {
                    rectangle.setFill(darkColor);
                }
                anchorPane.getChildren().add(rectangle);
            }
        }
        return anchorPane;
    }

    public AnchorPane getBoard() {
        return anchorPane;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public double getWidth() {
        return boardWidth;
    }

    public double getHeight() {
        return boardHeight;
    }

    public Color getLightColor() {
        return lightColor;
    }

    public Color getDarkColor() {
        return darkColor;
    }

    public double getRectangleWidth() {
        return squareWidth;
    }

    public double getRectangleHeight() {
        return squareSideLength;
    }
}
