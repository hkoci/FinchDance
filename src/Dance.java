/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dancetask;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 *
 * @author Henri
 */
public class Dance {
    
    //Number equivalent variables
    private String HexNum;
    private int DenaryNumber;
    private int OctalNumber;
    private String BinaryNumber;
    
    private static ArrayList<String> HexNumbersArray = new ArrayList<String>();
    
    //Instantiate the results GUI
    private static ResultsInterface ResultsObject = new ResultsInterface();
    
    //Setter for equivalent variables
    public void setNumberEquivalents(String hex, int den, int oct, String bin){
        HexNum = hex;
        DenaryNumber = den;
        OctalNumber = oct;
        BinaryNumber = bin;
    }
    
    //Main method
    public void mainDance(){
        //WheelSpeed value is equal to Octal Number
        int WheelSpeed = OctalNumber;
        
        //Check if Octal number is less than 60, it is too slow to perform dances, so increase it by 50.
        if(WheelSpeed < 60){
            WheelSpeed += 50;
        }
        
        /*Instantiate new Color object (AWT) with arguments RGB (Red,Green,Blue)
        Red: Denary Number
        Green: Denary Number Modulus 80 multipled by 3
        Blue: The greater value of either Red or Green*/
        
        int redColour = DenaryNumber;
        int greenColour = DenaryNumber % 80 * 3;
        
        int blueColour;
        if(redColour > greenColour){
            blueColour = redColour;
        }else{
            blueColour = greenColour;
        }

        Color finchColour = new Color(redColour,greenColour,blueColour);
        
        Finch finchObject = new Finch();
        
        finchObject.setLED(finchColour);
        
        //Update table values
        ResultsObject.addRow(HexNum, OctalNumber, DenaryNumber, BinaryNumber, WheelSpeed, redColour, greenColour, blueColour);
        
        //Output variable information as required for debugging and for user insight
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Movement Variables");
        alert.setHeaderText("Movement Details");
        alert.setContentText("Your hexadecimal number is: " + HexNum + System.lineSeparator() +
            "Your octal equivalent number is: " + OctalNumber + System.lineSeparator() +
            "Your denary equivalent number is: " + DenaryNumber + System.lineSeparator() +
            "Your binary equivalent number is: " + BinaryNumber + System.lineSeparator() +
            "Your wheel speed is: " + WheelSpeed + "Your RGB LED Colour consists of the following values:" +
            System.lineSeparator() + "Red = " + redColour + " Green = " + greenColour + " Blue = " + blueColour
            );

        alert.showAndWait();
        
        //IndexElement counter in the binary equivalent
        int indexElement = 0;
        
        //Whilst the counter has not reached the last element in the binary string
        while(indexElement != BinaryNumber.length()){
            //Initialise the durationToMove variable
            int durationToMove;
            
            //Requirement: if hexadecimal digit was 2 then dance for a second
            if(HexNum.length() == 1){
                durationToMove = 1000;
            }else{ //Unless it is a single digit, then half a second
                durationToMove = 500;
            }
            
            //If the current character is 1 using the indexElement
            if(BinaryNumber.charAt(indexElement) == '1'){
                finchObject.setWheelVelocities(WheelSpeed, WheelSpeed, durationToMove); //Dance forwards
            }else{
                finchObject.setWheelVelocities(-WheelSpeed, -WheelSpeed, durationToMove); //Dance backwards
            }
            
            //Increment index counter for next element in String
            indexElement++;
            
            
        }
        
        //Turn off Finch LED beak colour
        finchObject.setLED(Color.BLACK);
        
        //Add the current hexadecimal number to the arraylist
        HexNumbersArray.add(HexNum);
        
        //Quit finch object so it can be used again freely
        finchObject.quit();
        
    }
    
    private void inputNumberDialog(){
        //Prompt the user to enter another number or to quit
        Alert numberRequest = new Alert(AlertType.CONFIRMATION);
        numberRequest.setTitle("Number Request");
        numberRequest.setHeaderText("Do you wish to input another number?");
        numberRequest.setContentText("It has to be a non-zero positive 2 digit hex number, if you choose to quit - the log will be shown.");

        // Grabs the result from anotherNumberRequest dialog and stores in numberRequest
        Optional<ButtonType> requestResponse = numberRequest.showAndWait();
        
        //If they decide to enter another number
        if (requestResponse.get() == ButtonType.OK){
            TextInputDialog hexInput = new TextInputDialog("Please choose a valid Hex. number");
            hexInput.setTitle("Enter a number");
            hexInput.setHeaderText("Input a Hexadecimal Number");
            hexInput.setContentText("Please enter your 1/2 digit Hex. Number:");

            // Grabs the result from input dialog and stores in inputResult
            Optional<String> hexResult = hexInput.showAndWait();
            
            // If user inputs any number - this is checked by the validality method
            if (hexResult.isPresent()){
                //Check if hex-number is valid
                if(MainInterface.checkHexValidality(hexResult.get().toUpperCase())){
                    //Convert hex to equivalent bases
                    int DenaryNumber = NumberConversion.HexToDec(hexResult.get().toUpperCase());
                    int OctalNumber = NumberConversion.DenToOct(DenaryNumber);
                    String BinaryNumber = NumberConversion.DenToBin(DenaryNumber);
                    
                    //Number valid, set equivalent values and re-run dance method
                    setNumberEquivalents(hexResult.get().toUpperCase(),DenaryNumber,OctalNumber,BinaryNumber);
                    
                    //Call the dance routine again
                    mainDance();
                }else{
                    //Ask number again - invalid
                    inputNumberDialog();
                }
            }
        } //User cancels or presses cancel
        else {
            //Sort hexadecimal numbers entered in ascending order
            Collections.sort(HexNumbersArray);

            //Initialise String containing all values
            String hexAllValues = null;
            
            //Loop through each element in arraylist
            for (int i = 0; i < HexNumbersArray.size(); i++) {
                hexAllValues += HexNumbersArray.get(i) + ", ";
            }
            
            //Write file
            saveAsFile("danceHexValues",hexAllValues);
            
            //Display information log dialog
            Alert logInfoVariables = new Alert(AlertType.INFORMATION);
            logInfoVariables.setTitle("Information Log");
            logInfoVariables.setHeaderText("Information Dance log");
            logInfoVariables.setContentText("Your 10 last recent hexadecimal numbers were: " + hexAllValues + System.lineSeparator()
                    + "Your file has been saved successfully under your profile/code project folder as danceHexValues.txt");
            logInfoVariables.showAndWait();
            

            
            
        }
    }
    
    private static void saveAsFile(String fileName, String lastHex){
        //Try-Catch block
        //The try statements are performed and if there is an IO file exception, this error is outputed in console
        try {
            FileWriter logFile = new FileWriter(fileName + ".txt"); //FileName is a argument for this method
            logFile.write(lastHex); //Hexadecimal values are written through the values argument
            logFile.close(); //Close the file for System to be able to rewrite
        } catch (IOException errorException) {
            System.out.println("Error whilst writing file:" + errorException); //If error then output error
        }
    }
    
    public static void showResultsTable(){
        // New window (Stage)
        Stage newWindow = new Stage();

        //Run the start method which will initialise all graphical elements with the newly called stage
        ResultsObject.start(newWindow);

        newWindow.setResizable(false);

        //Display below current window
        newWindow.setY(550);

        //Display this stage window on screen
        newWindow.show();
    }
    
}
