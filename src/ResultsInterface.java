/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dancetask;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Henri
 */
public class ResultsInterface extends Application {
    
    private static final ObservableList<Result> resultList = FXCollections.observableArrayList();
    
    @Override
    public void start(Stage primaryStage) {
        AnchorPane anchorPane = new AnchorPane();
        TableView resultsTable = new TableView();
        TableColumn resultIDClmn = new TableColumn();
        TableColumn resultDateClmn = new TableColumn();
        TableColumn resultNameClmn = new TableColumn();
        TableColumn resultConvsHeader = new TableColumn();
        TableColumn resultHexClmn = new TableColumn();
        TableColumn resultOctClmn = new TableColumn();
        TableColumn resultDenClmn = new TableColumn();
        TableColumn resultBinClmn = new TableColumn();
        TableColumn resultCalcsHeader = new TableColumn();
        TableColumn resultSpeedClmn = new TableColumn();
        TableColumn resultLEDHeader = new TableColumn();
        TableColumn resultRedClmn = new TableColumn();
        TableColumn resultGreenClmn = new TableColumn();
        TableColumn resultBlueClmn = new TableColumn();
        VBox vboxWindow = new VBox();

        vboxWindow.setPrefHeight(400.0);
        vboxWindow.setPrefWidth(895.0);

        resultsTable.setPrefHeight(400.0);
        resultsTable.setPrefWidth(900.0);

        resultIDClmn.setPrefWidth(75.0);
        resultIDClmn.setText("ResultID");

        resultDateClmn.setPrefWidth(104.0);
        resultDateClmn.setText("Date");

        resultNameClmn.setPrefWidth(96.0);
        resultNameClmn.setText("ResultName");

        resultConvsHeader.setPrefWidth(275.0);
        resultConvsHeader.setText("Number Conversion");

        resultHexClmn.setPrefWidth(82.0);
        resultHexClmn.setText("Hexadecimal");

        resultOctClmn.setPrefWidth(77.0);
        resultOctClmn.setText("Octal");

        resultDenClmn.setPrefWidth(63.0);
        resultDenClmn.setText("Decimal");

        resultBinClmn.setPrefWidth(103.0);
        resultBinClmn.setText("Binary");

        resultCalcsHeader.setPrefWidth(317.0);
        resultCalcsHeader.setText("Calculated Values");

        resultSpeedClmn.setPrefWidth(75.0);
        resultSpeedClmn.setText("Speed");

        resultLEDHeader.setPrefWidth(242.0);
        resultLEDHeader.setText("Beak Colour");

        resultRedClmn.setPrefWidth(75.0);
        resultRedClmn.setText("Red");

        resultGreenClmn.setPrefWidth(75.0);
        resultGreenClmn.setText("Green");

        resultBlueClmn.setPrefWidth(75.0);
        resultBlueClmn.setText("Blue");

        resultsTable.getColumns().addAll(resultIDClmn,resultDateClmn,resultNameClmn);
        
        resultIDClmn.setCellValueFactory(new PropertyValueFactory<>("ResultID"));
        resultDateClmn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        resultNameClmn.setCellValueFactory(new PropertyValueFactory<>("ResultName"));
        
        resultConvsHeader.getColumns().addAll(resultHexClmn,resultOctClmn,resultDenClmn,resultBinClmn);
        
        resultHexClmn.setCellValueFactory(new PropertyValueFactory<>("HexNumber"));
        resultOctClmn.setCellValueFactory(new PropertyValueFactory<>("OctalNumber"));
        resultDenClmn.setCellValueFactory(new PropertyValueFactory<>("DenaryNumber"));
        resultBinClmn.setCellValueFactory(new PropertyValueFactory<>("BinaryNumber"));

        resultsTable.getColumns().add(resultConvsHeader);
        
        resultCalcsHeader.getColumns().add(resultSpeedClmn);
        resultHexClmn.setCellValueFactory(new PropertyValueFactory<>("wheelSpeed"));
        
        resultLEDHeader.getColumns().addAll(resultRedClmn,resultGreenClmn,resultBlueClmn);
        
        resultHexClmn.setCellValueFactory(new PropertyValueFactory<>("redColour"));
        resultHexClmn.setCellValueFactory(new PropertyValueFactory<>("greenColour"));
        resultHexClmn.setCellValueFactory(new PropertyValueFactory<>("blueColour"));
        
        resultCalcsHeader.getColumns().add(resultLEDHeader);
        
        resultsTable.getColumns().add(resultCalcsHeader);
        
        resultsTable.setItems(resultList);
        
        anchorPane.getChildren().add(resultsTable);
        vboxWindow.getChildren().add(anchorPane);
        
        Scene scene = new Scene(vboxWindow);
        
        primaryStage.setTitle("Finch Log");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void addRow(String HexNumber, int OctalNumber, int DenaryNumber, String BinaryNumber, int wheelSpeed, int redColour, int greenColour, int blueColour){
        resultList.add(new Result(HexNumber,OctalNumber,DenaryNumber,BinaryNumber,wheelSpeed,redColour,greenColour,blueColour));
    } 
    
}
