package LAB_06;
import java.util.*; 
/**
 * Class CalcExeption realize driver for given expression calculator
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
        String FileNameBin = "RESULT.bin";
        String FileNameTxt = "RESULT.txt";
        Scanner input = new Scanner(System.in);
        try 
        {
        Equations eq = new Equations();
        System.out.print("Enter X in degrees: ");
        eq.calculate(input.nextDouble());
        System.out.println("tg(x) / sin (2x) = " + eq.getResult());

        eq.writeTXT(FileNameTxt);
        eq.writeBIN(FileNameBin);

        eq.ReadResTXT(FileNameTxt);
        System.out.println("Result read from .TXT file = " + eq.getResult());
        eq.ReadResBIN(FileNameBin);
        System.out.println("Result read from .BIN file = " + eq.getResult());

        input.close();
        }
        catch (CalcException ex)
        {
            System.out.print(ex.getMessage());
        }
    } 
}
/**
 * Class CalcException realize a subclass 
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
    System.out.print("I will not work!I need cause");
 }
 /**
 * Constructor
 * @param cause explanatory message, explane why the exception occurred
 */ 
 public CalcException(String cause)
 {
    super(cause);
 }
}