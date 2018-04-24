/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

/**
 *
 * @author chrisman
 */
public class FXMLDocumentController implements Initializable {
    
    public static int [][] landMap;
    public int sliceMax;
    public static int x, y;
    public int sliceNum;
    public File file;
    
    @FXML
    private Label label;

    @FXML
    private GridPane gridPane;
    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private Button btnFirst;

    @FXML
    private Button btnPrev;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnLast;
    
    @FXML
    private Label lblPlanSlice;
    
    @FXML
    private Label lblSliceNum;

    @FXML
    private Label lblWaterVol;
    
    @FXML
    private TextField pathInput;
    
    private String path;
    
    @FXML
    void onBrowse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Opening Land Map File");
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String strFile = file.toString();
            pathInput.setText(strFile);
        }
        //pathInput.setText(file.getAbsolutePath());
    }
    
    @FXML
    void onRead(ActionEvent event) {
        String initialSlice = Integer.toString(sliceNum+1);
        lblSliceNum.setText(initialSlice);
        try {
            Scanner sc = new Scanner(file);
            x = 30;
            y = 20;
            landMap = new int[30][20];
            sliceMax = 0;
            int row = 0;
            while (sc.hasNext()) {

                String line = sc.nextLine();
                String[] lineItems = line.split(",");
                int column = 0;
                for (String item : lineItems) {
                    landMap[row][column] = 10 - Integer.parseInt(item.trim());
                    column++;
                }
                row++;
                sliceMax++;
            }
            sc.close();
            sliceNum = 0;
            UpdateGrid();
        } catch (Exception e) {
            System.out.println("Error: please try again.");
        }
        //path = pathInput.getText();
//        for (int row = 0; row < 20; row++) {
//            for (int col = 0; col < 10; col++) {
//                Node cell = getCellFromGridPane(gridPane, row, col);
//                StringBuilder color = new StringBuilder();
//                // here you set the property "background color" to a given color
//                cell.setStyle("-fx-background-color: #" + 774111);
//            }
//        }
    }
    
    private void UpdateGrid() {
        ResetGrid();
        for (int column = 0; column < y; column++) {
            for (int row = landMap[sliceNum][column]; row < 10; row++) {
                Node cell = getCellFromGridPane(gridPane, column, row);
                cell.setStyle("-fx-background-color: sienna");
            }
        }
    }
    
    private void ResetGrid() {
        for (int column = 0; column < y; column++) {
            for (int row = 0; row < 10; row++) {
                Node cell = getCellFromGridPane(gridPane, column, row);
                cell.setStyle("-fx-background-color: seashell");
            }
        }
    }
    
    @FXML
    void onNext(ActionEvent event) {
        String sliceDisplay;
        sliceNum++;
        if (sliceMax > sliceNum) {
            UpdateGrid();
            sliceDisplay = Integer.toString(sliceNum + 1);
            lblSliceNum.setText(sliceDisplay);
        } else {
            sliceNum--;
            sliceDisplay = Integer.toString(sliceNum + 1);
            lblSliceNum.setText(sliceDisplay);
        }
    }
    
    @FXML
    void onPrev(ActionEvent event) {
        String sliceDisplay;
        sliceNum--;
        if (sliceNum >= 0) {
            UpdateGrid();
            sliceDisplay = Integer.toString(sliceNum + 1);
            lblSliceNum.setText(sliceDisplay);
        } else {
            sliceNum++;
            sliceDisplay = Integer.toString(sliceNum + 1);
            lblSliceNum.setText(sliceDisplay);
        }
    }
    
        @FXML
    void onFirst(ActionEvent event) {
        String sliceDisplay;
        sliceNum = 0;
        sliceDisplay = Integer.toString(sliceNum + 1);
        lblSliceNum.setText(sliceDisplay);
        UpdateGrid();

    }

    @FXML
    void onLast(ActionEvent event) {
        String sliceDisplay;
        sliceNum = 14;
        sliceDisplay = Integer.toString(sliceNum + 1);
        lblSliceNum.setText(sliceDisplay);
        UpdateGrid();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gridPane = new GridPane();
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 10; y++) {
                // creating the labels
                Label label = new Label("  ");
                
                // binding the label size with the gridpane size, so that
                // when we resize the window the cells will be changed and the
                // labels also. Try to resize your application to see this happening
                label.prefWidthProperty().bind(gridPane.widthProperty());
                label.prefHeightProperty().bind(gridPane.heightProperty());
                
                // add label to the gridPane, but getting the gridPAne "children"
                // (cells) and adding one more child (cell) to the gridPane
                gridPane.getChildren().add(label);
                
                // this is needed to use the getCellFromGridPane() method below
                GridPane.setColumnIndex(label, x);
                GridPane.setRowIndex(label, y);
            }
        }
        gridPane.setGridLinesVisible(true);
        borderPane.setCenter(gridPane);
    }
    
//    private makeArray(String st) {
//        int[][] gridArray;
//        int rows = getTotalRows();
//        myArray = new int[rows][];
//        
//    }
    
    private Node getCellFromGridPane(GridPane gridPane, int col, int row) {
        // this method returns a cell ID for a given row and column in the gridpane
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
    
}
