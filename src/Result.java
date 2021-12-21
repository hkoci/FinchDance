/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dancetask;

/**
 *
 * @author Henri
 */
public class Result {
    private String HexNumber;
    private int OctalNumber;
    private int DenaryNumber;
    private String BinaryNumber;
    private int wheelSpeed;
    private int redColour;
    private int greenColour;
    private int blueColour;
    
    public Result(String HexNumber, int OctalNumber, int DenaryNumber, String BinaryNumber, int wheelSpeed, int redColour, int greenColour, int blueColour){

        this.HexNumber = HexNumber;
        this.OctalNumber = OctalNumber;
        this.DenaryNumber = DenaryNumber;
        this.BinaryNumber = BinaryNumber;
        this.wheelSpeed = wheelSpeed;
        this.redColour = redColour;
        this.greenColour = greenColour;
        this.blueColour = blueColour;
    }
    
    public String getHexNumber(){
        return HexNumber;
    }
    
    public void setHexNumber(String HexNumber){
        this.HexNumber = HexNumber;
    }
    
    public int getOctalNumber(){
        return OctalNumber;
    }
    
    public void setOctalNumber(int OctalNumber){
        this.OctalNumber = OctalNumber;
    }
    
    public int getDenaryNumber(){
        return DenaryNumber;
    }
    
    public void setDenaryNumber(int DenaryNumber){
        this.DenaryNumber = DenaryNumber;
    }
    
    public void setBinaryNumber(String BinaryNumber){
        this.BinaryNumber = BinaryNumber;
    }
    
    public String getBinaryNumber(){
        return BinaryNumber;
    }
    
    public int getwheelSpeed(){
        return wheelSpeed;
    }
    
    public void setwheelSpeed(int wheelSpeed){
        this.wheelSpeed = wheelSpeed;
    }
    
    public int getredColour(){
        return redColour;
    }
    
    public void setredColour(int redColour){
        this.redColour = redColour;
    }
    
    public int getgreenColour(){
        return greenColour;
    }
    
    public void setgreenColour(int greenColour){
        this.greenColour = greenColour;
    }
    
    public int getblueColour(){
        return blueColour;
    }
    
    public void setblueColour(int blueColour){
        this.blueColour = blueColour;
    }
    
}
