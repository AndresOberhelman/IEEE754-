package project2csc4101;
import java.lang.*;
import java.math.*;

/**
 *
 * @author pastu
 */
public class Convertor {

    
    
    public static String IEEE754(double num)
    {
        StringBuilder sign= new StringBuilder();
       
        if(num<0.0)
        {
            sign = sign.append(1);
            StringBuilder numString = new StringBuilder(String.valueOf(num));
            numString.deleteCharAt(0);
            String numString1 = numString.toString();
            num = Double.parseDouble(numString1);
        }    
        else
        {
            sign = sign.append(0);
        }
       
        if(num< 1.0)
        {
            
            int precision=0;
            int counter=0;
            double temp;
            temp = num; 
            double newvalue=0;
            
            while(newvalue<1)
            {
                precision++;
                double num2=Math.pow(2,precision);
                newvalue=num/(1/num2);
            }  
           int expo=127-precision;
           num=newvalue;
           String[] arr=String.valueOf(num).split("\\.");
            int[] intArr=new int[2];
            intArr[0]=Integer.parseInt(arr[0]); 
            BinaryPartOne(arr[0]);
            BinaryPartTwo(arr[1]);
            String combinedString = BinaryPartOne(arr[0]) + "." + BinaryPartTwo(arr[1]);  
            String expoString = String.valueOf(expo);
            String expoBinary = BinaryPartOne(expoString);
            StringBuilder mantissa = new StringBuilder(MoveDecimal(combinedString));
            String output = sign + " | " + expoBinary + " | " + mantissa; 
            System.out.println(output);
        }
        else{
        String[] arr=String.valueOf(num).split("\\.");
        int[] intArr=new int[2];
        intArr[0]=Integer.parseInt(arr[0]); 
        BinaryPartOne(arr[0]);
        BinaryPartTwo(arr[1]);
        String combinedString = BinaryPartOne(arr[0]) + "." + BinaryPartTwo(arr[1]);
        int expo = Exponent(combinedString);   
        String expoString = String.valueOf(expo);
        String expoBinary = BinaryPartOne(expoString);
        StringBuilder mantissa = new StringBuilder(MoveDecimal(combinedString));
        String output = sign + " | " + expoBinary + " | " + mantissa; 
        System.out.println(output);
        }
        String done="";    
        return done;
    }
    
   public static String BinaryPartOne(String num)
    {   

        int temp=Integer.valueOf(num);
        int newnum=0;
        int remain=0;
        String outString;
        StringBuilder Binary=new StringBuilder();
        while (temp != 0)
        {
            newnum = temp / 2;
            remain = temp % 2;
            temp = newnum;
            Binary=Binary.append(remain);
           
        }
        
        Binary.reverse();
        outString = Binary.toString();
        return outString;    
    } 
       
   public static String BinaryPartTwo(String num)
{
        String convert = "0." + num;
        double temp = Double.valueOf(convert);
	int precision=0;
        String binary = "";
    while(temp!=0 && precision!=23)
    {
    	temp = temp*2;
           if(temp < 1)

              binary+="0";

           if(temp >= 1)
           {

               binary+="1";
               temp -= 1;
           }
           precision++;
    }
    return binary;
}
   
    public static int Exponent(String num){
        int indexP = num.indexOf('.');
        int index1 = num.indexOf('1');
        StringBuilder sb = new StringBuilder(num);
        sb.deleteCharAt(indexP);
        sb.insert(index1+1, '.');
        int mov = indexP - index1 - 1;
        int expo = 127 + mov;
        return expo;
    }     
   
    public static StringBuilder MoveDecimal(String num){
        int indexP = num.indexOf('.');
        int index1 = num.indexOf('1');
        StringBuilder sb = new StringBuilder(num);
        sb.deleteCharAt(indexP);
        sb.insert(index1+1, '.');
        sb.deleteCharAt(0);
        sb.deleteCharAt(0);
        while (sb.length() < 24)
            sb.append('0');
        while (sb.length()>23){
            sb.deleteCharAt(sb.length()-1);
        }
        
        return sb;
    }
    
    public static void main(String[] args) {
        
//        //1 | 10000101 | 11101000100010000101010
        System.out.println(Convertor.IEEE754(-122.13313361)); 
        
//        //0 | 10000101 | 00110110010000111001010
        System.out.println(Convertor.IEEE754(77.566)); 
//        
//        //0 | 01111110 | 10001101000110110111000
        System.out.println(Convertor.IEEE754(0.7756)); 
//        
//        //1 | 01110111 | 10111001000011101010100
        System.out.println(Convertor.IEEE754(-0.00673)); 
        
        System.out.println(Convertor.IEEE754(0.899)); 
        
       
     
    }
    
}