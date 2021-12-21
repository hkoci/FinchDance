/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dancetask;

import java.util.ArrayList;

public class NumberConversion {
    
    public static int HexToDec(String HexNum){
        //This is the string containing all the hexadecimal characters which index number in the string will be obtained
        String HexCharacters = "0123456789ABCDEF";

        int DenaryNumber = 0; //This integer stores the denary value which is being converted from hex
        
        /*This variable stores the nth power in the ‘d * 16^n’, the initial conversion power
        starts at 0 and the for loop will increment by this 1 on each iteration.*/
        int hexPower = 0;
        
        ///Loop when the index is still a positive non-zero number
        for (int characterIndex = HexNum.length() - 1; characterIndex >= 0; characterIndex--){
            
            //the current character from HexNum at this characterIndex gets stored in this variable
            char HexNumCurrentCharacter = HexNum.charAt(characterIndex);
            
            /*the index number of the characterIndex is then found in the HexCharacters array, this
              acts as the ‘denary’ equivalent digit for that particular hexadecimal digit*/
            int  IndexHexCharPointer = HexCharacters.indexOf(HexNumCurrentCharacter);
            
            /*Using the hexadecimal notation, the denary equivalent is multiplied by 16 to the power
            of the placeholder value, hexPower. The denary number is then added upon any previous result*/
            DenaryNumber = (int) (DenaryNumber + (IndexHexCharPointer * (Math.pow(16,hexPower))));

            hexPower++; //Increment the placeholder value by 1
                 
        }
        return DenaryNumber; //Return the denary number which has been converted
    }
    
    public static String DenToBin(int DenaryNumber){
        //Large array to store remainders of the division (note only upto 2 digits are being done!)
        int binaryRemainder[] = new int[100];
        
        /*This is the indexCounter which is an increment on each loop iteration and this is used to start
          the opposite reading when storing the remainder to the BinaryNumber string.*/
        int indexCounter=1;
        
        //Loop until DenaryNumber becomes 0
        while(DenaryNumber != 0)
        {
            binaryRemainder[indexCounter++] = DenaryNumber%2; //The remainder of the DenaryNumber divided by 2 is added to the binaryRemainderList
            DenaryNumber = DenaryNumber/2; //The DenaryNumber is then divided by 2 as the divide and conquer approach to work out the next digit placeholder value
        }
	
        /*This variable will store the binary number, the string data type allows us to easily append
          characters to the last character element, and this also allows us to display 0’s in-between 1’s 
          – which makes it useful for binary unlike numerical data types like integer*/
        String BinaryNumber = "";
        
        //The program will use the last index from the list, it is subtracted by 1 as the index starts from 0.
        //We are reading the binary remainder from right to left (last index to the first index) to get the binary result.
        
        for(int binaryReadingCounter=indexCounter-1; binaryReadingCounter>0; binaryReadingCounter--) //Loop until the counter becomes 0
        {
            //append the element of the remainder at the current index to the BinaryNumber string
            BinaryNumber = BinaryNumber + binaryRemainder[binaryReadingCounter]; 
        }
        
        return BinaryNumber; //Return the Binary number from this method
    }
    
    public static int DenToOct(int DenaryNumber){
        //DenaryNumber is a parameter which is the passed input for this method.
        
        int octalRemainer; //This variable will store the remainder after dividing the DenaryNumber by 8
        String octalString = ""; //This variable will store the value of the octal equivalent as a string
        char octalArray[]={'0','1','2','3','4','5','6','7'}; //This array of size 8 stores all the octal characters which the index element number in the array will act as the denary equivalent
        
        //Loop until all remainders of the DenaryNumber has been calculated
        while(DenaryNumber>0)  
        {  
           octalRemainer = DenaryNumber % 8; //The modulus function will work out the remainder of 8 from denaryNumber and store this in the octalRemainder variable
           octalString = octalArray[octalRemainer] + octalString; //The octalString will store the octal character using the remainder as the index number and append this to any previous octalString result (adding strings makes that character go to the last element of the string) 
           DenaryNumber = DenaryNumber / 8; //As the remainder has been worked out, we now need to divide the DenaryNumber by 8 to work out the next octal equivalent as a divide and conquer approach to work out the next digit placeholder value
        }
        
        int octalInteger = Integer.parseInt(octalString); //Convert the octalString from string data-type to integer, this could be parsed or typecasted

        return octalInteger; //Return the octal number result from this method
    
    }
    
}
