/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dancetask;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Henri
 */
public class MainInterface extends Application {
    
    private boolean accessibilityTheme = false;
    
    @Override
    public void start(Stage primaryStage) {
        VBox vboxWindow = new VBox();
        
        Scene scene = new Scene(vboxWindow,700,370);

        vboxWindow.setPrefHeight(370);
        vboxWindow.setPrefWidth(700);

        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu();
        fileMenu.setText("File");

        MenuItem quitItm = new MenuItem();
        quitItm.setText("Quit");
        quitItm.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        Menu helpMenu = new Menu();
        helpMenu.setText("Help");

        MenuItem aboutItm = new MenuItem();
        aboutItm.setText("About Dance Program");
        aboutItm.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                // Display About
                Alert aboutAlert = new Alert(AlertType.INFORMATION);
                aboutAlert.setTitle("About");
                aboutAlert.setHeaderText("About Dance Task");
                aboutAlert.setContentText("This is the Dance Task, Robot Task 6. Produced by Henri Koci." + System.lineSeparator()
                        + "To start a dance move, enter a hexadecimal number consisting of one to two digits.");
                aboutAlert.showAndWait();
            }
        });

        AnchorPane anchorPane = new AnchorPane();

        Label titleLbl = new Label();
        titleLbl.setLayoutX(42);
        titleLbl.setLayoutY(10);
        titleLbl.setText("Assignment 2 - Finch Dance Task");
        titleLbl.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        titleLbl.setWrapText(false);
        titleLbl.setFont(new Font(43));

        Label subTitleLbl = new Label();
        subTitleLbl.setLayoutX(540);
        subTitleLbl.setLayoutY(60);
        subTitleLbl.setText("Green Group 46");
        subTitleLbl.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        subTitleLbl.setTextFill(javafx.scene.paint.Color.GREEN);
        subTitleLbl.setWrapText(false);
        subTitleLbl.setFont(new Font("Source Sans Pro Light", 18));

        ImageView finchBannerImg = new ImageView();
        finchBannerImg.setFitHeight(200);
        finchBannerImg.setFitWidth(630);
        finchBannerImg.setLayoutX(86);
        finchBannerImg.setLayoutY(60);
        //finchBannerImg.setPickOnBounds(true);
        finchBannerImg.setPreserveRatio(true);
        finchBannerImg.setImage(new Image(getClass().getResource("finchLogo.png").toExternalForm()));

        Label danceHeader = new Label();
        danceHeader.setLayoutX(30);
        danceHeader.setLayoutY(240);
        danceHeader.setText("Finch Dance");
        danceHeader.setFont(new Font("System Bold", 18));

        Label danceHeaderInfoLbl = new Label();
        danceHeaderInfoLbl.setLayoutX(30);
        danceHeaderInfoLbl.setLayoutY(265);
        danceHeaderInfoLbl.setText("Enter your hexadecimal number to start the Finch dance.");

        TextField HexField = new TextField();
        HexField.setLayoutX(30);
        HexField.setLayoutY(280);
        HexField.setPrefHeight(25);
        HexField.setPrefWidth(200);
        HexField.setPromptText("Hexadecimal Number");

        Button StartBtn = new Button();
        StartBtn.setLayoutX(250);
        StartBtn.setLayoutY(280);
        StartBtn.setMnemonicParsing(false);
        StartBtn.setText("Start Dance");
        StartBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                //Input sanitsation
                HexField.setText(HexField.getText().toUpperCase());
                
                if(checkHexValidality(HexField.getText())){
                    //Number equivalent variables
                    int DenaryNumber = NumberConversion.HexToDec(HexField.getText());
                    int OctalNumber = NumberConversion.DenToOct(DenaryNumber);
                    String BinaryNumber = NumberConversion.DenToBin(DenaryNumber);
                    
                    //Instantiate new Dance object as danceObject
                    Dance danceObject = new Dance();

                    //Set equiv. results
                    danceObject.setNumberEquivalents(HexField.getText(), DenaryNumber, OctalNumber, BinaryNumber);
                    
                    //Show results GUI (not working fully)
                    danceObject.showResultsTable();
                    
                    //run main routine
                    danceObject.mainDance();
                    
                }
                

            }
        });

        ToolBar toolBar = new ToolBar();
        toolBar.setNodeOrientation(javafx.geometry.NodeOrientation.RIGHT_TO_LEFT);
        toolBar.setPrefHeight(50);
        toolBar.setPrefWidth(200);

        
        Button accessibilityBtn = new Button();
        accessibilityBtn.setMnemonicParsing(false);
        accessibilityBtn.setText("High Accessibility");
        accessibilityBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                if(!accessibilityTheme){
                    vboxWindow.setStyle("-fx-background-color: black;");
                    titleLbl.setTextFill(Color.WHITE);
                    subTitleLbl.setTextFill(Color.CYAN);
                    danceHeader.setTextFill(Color.WHITE);
                    danceHeaderInfoLbl.setTextFill(Color.WHITE);
                    HexField.setStyle("-fx-control-inner-background: black; -fx-text-fill: white;");
                    StartBtn.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-text-fill: white;");
                    accessibilityBtn.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-text-fill: white;");
                    toolBar.setStyle("-fx-background-color: linear-gradient(black, gray);");
                    accessibilityTheme = true;
                }else{
                    vboxWindow.setStyle(null);
                    titleLbl.setTextFill(Color.BLACK);
                    subTitleLbl.setTextFill(Color.GREEN);
                    danceHeader.setTextFill(Color.BLACK);
                    danceHeaderInfoLbl.setTextFill(Color.BLACK);
                    titleLbl.setTextFill(Color.BLACK);
                    HexField.setStyle(null);
                    StartBtn.setStyle(null);
                    accessibilityBtn.setStyle(null);
                    toolBar.setStyle(null);
                    accessibilityTheme = false;
                }
            }
        });

        fileMenu.getItems().add(quitItm);
        menuBar.getMenus().add(fileMenu);
        helpMenu.getItems().add(aboutItm);
        menuBar.getMenus().add(helpMenu);
        vboxWindow.getChildren().add(menuBar);
        anchorPane.getChildren().add(titleLbl);
        anchorPane.getChildren().add(subTitleLbl);
        anchorPane.getChildren().add(finchBannerImg);
        anchorPane.getChildren().add(danceHeader);
        anchorPane.getChildren().add(danceHeaderInfoLbl);
        anchorPane.getChildren().add(HexField);
        anchorPane.getChildren().add(StartBtn);
        vboxWindow.getChildren().add(anchorPane);
        toolBar.getItems().add(accessibilityBtn);
        vboxWindow.getChildren().add(toolBar);
        
        primaryStage.setTitle("Dance Main Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setY(100);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static boolean checkHexValidality(String HexNumber){
        HexNumber = HexNumber.toUpperCase();
        
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Invalid Hexadecimal Input");
        
        if(HexNumber.length() == 1){
            if(HexNumber.matches("[1-9]|[A-F]") == true){
                return true;
            }
            else{
                alert.setHeaderText("Invalid Input");
                alert.setContentText("User error 002, the first digit is not a valid hexadecimal number");
                alert.showAndWait();
                return false;
            }
        }
        else if(HexNumber.length() == 2){
            if(String.valueOf(HexNumber.charAt(0)).matches("[0-9]|[A-F]") == true){
                if(String.valueOf(HexNumber.charAt(1)).matches("[0-9]|[A-F]") == true){
                    if(!HexNumber.contains("00")){
                        return true;
                    }else{
                        alert.setHeaderText("Invalid Input");
                        alert.setContentText("User error 004, no dance moves can be performed with a zero number");
                        alert.showAndWait();
                        return false;
                    }
                }else{
                    alert.setHeaderText("Invalid Input");
                    alert.setContentText("User error 003, the second digit is not a valid hexadecimal number");
                    alert.showAndWait();
                    return false;
                }
            }else{
                alert.setHeaderText("Invalid Input");
                alert.setContentText("User error 002, the first digit is not a valid hexadecimal number");
                alert.showAndWait();
                return false;
            }
        }else{
            alert.setHeaderText("Invalid Input");
            alert.setContentText("User error 001, the hexadecimal number is longer than the 2 digit constraint or there is no input at all");
            alert.showAndWait();   
            return false;
        }
    }
        
}
