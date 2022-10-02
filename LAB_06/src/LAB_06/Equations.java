package LAB_06;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Class realize method for tg(x) / sin (2x) expression calculation
 * @author Pavlyk Serhii
 * @version 1.0
 */
public class Equations
{
   private double result;
 /** 
 * Method calculates the tg(x) / sin (2x) expression
 * @param x Angle in degrees
 * @throws CalcException
 */
public void calculate(double x) throws CalcException
 {
 try
 {
    result = Math.tan(Math.toRadians(x))/Math.sin(Math.toRadians(2*x));
    if (Double.isNaN(result) || Double.isInfinite(result) || Double.isInfinite(-result) || x == 90 || x == -90)
    throw new ArithmeticException();
 }
 catch (ArithmeticException ex)
 {
 if (x==90 || x==-90)
 throw new CalcException("Exception reason: Illegal value of X for tg calculation");
 else if (2*x==0)
 throw new CalcException("Exception reason: sin 2x = 0");
 else
 throw new CalcException("Unknown reason of the exception during exception calculation");
 }
 }
 /** 
 * Method writes result of expression to .txt file
 * @param file_name Name of .txt file
 */
public void writeTXT(String file_name)
{
   try{
   PrintWriter printWriter = new PrintWriter(file_name);
   printWriter.print(result);
   printWriter.close();
   }
 catch(FileNotFoundException exception)
  {
   System.out.print("Exception reason: Perhaps wrong file path");
  }
}
 /** 
 * Method writes result of expression to .bin file
 * @param file_name Name of .bin file
 */
public void writeBIN(String file_name)
{
   try{
   DataOutputStream writer = new DataOutputStream(new FileOutputStream(file_name));
   writer.writeDouble(result);
   writer.close();
   }
   catch(IOException exception)
  {
   System.out.print("Exception reason: Perhaps wrong file path");
  }
}
 /** 
 * Method reads result of expression from .txt file
 * @param file_name Name of .txt file
 */
public void ReadResTXT(String file_name)
{
  try{
   Scanner scanner = new Scanner(new File(file_name));
   result = Double.parseDouble(scanner.nextLine()) ;
   scanner.close();
   }
   catch(IOException exception)
  {
   System.out.println(exception.getMessage());
  }
}
 /** 
 * Method reads result of expression from .binfile
 * @param file_name Name of .bin file
 */
public void ReadResBIN(String file_name)
{
   try{
   DataInputStream reader = new DataInputStream(new FileInputStream(file_name));
   result = reader.readDouble();
   reader.close();
   }
   catch(IOException exception)
  {
   System.out.print("Exception reason: Perhaps wrong file path");
  }
}
 /** 
 * Method return the result of expression
 * @return private field result of expression
 */
public double getResult()
{
   return result;
}
}
