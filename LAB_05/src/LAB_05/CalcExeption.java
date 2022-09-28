package LAB_05;
import java.io.*;
import java.util.*; 
/**
 * Class CalcExeption Implements driver for given expression calculator
 * @author Pavlyk Serhii
 * @version 1.0
*/ 
public class CalcExeption
{
/**
 * Method main start point to the program
 */
    public static void main(String[] args)
    {
        try 
        {
         
        Scanner input = new Scanner(System.in);
        PrintWriter fout = new PrintWriter(new File("result.txt"));
        try 
        {
        try 
        {
        Equations eq = new Equations();
        System.out.print("Enter X in degrees: ");
        double res = eq.calculate(input.nextDouble());
        System.out.print("tg(x) / sin (2x) = "+  res);
        fout.print(res);
        }
        finally
        {
        fout.flush();
        fout.close();
        input.close();
        }
        }
        catch (CalcException ex)
        {
            System.out.print(ex.getMessage());
        }
        }
        catch (FileNotFoundException ex)
        {
            System.out.print("Exception reason: Perhaps wrong file path");
        }
    } 
}
/**
 * Class CalcException realize class implements a subclass 
 * that catches arithmetic exception and provides an explanation
 * why this exception occurred
 * @author Pavlyk Serhii
 * @version 1.0
*/
class CalcException extends ArithmeticException
{
/**
 * Constructor
 */ 
 public CalcException()
 {

 }
 /**
 * Constructor
 * @param <code>cause</code> explanatory message, explane why the exception occurred
 */ 
 public CalcException(String cause)
 {
    super(cause);
 }
}