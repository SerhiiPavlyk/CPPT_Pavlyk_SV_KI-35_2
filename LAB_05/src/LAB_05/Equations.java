package LAB_05;

/**
 * Class realize method for tg(x) / sin (2x) expression calculation
 * @author Pavlyk Serhii
 * @version 1.0
 */
public class Equations
{
 /** 
 * Method calculates the tg(x) / sin (2x) expression
 * @param x Angle in degrees
 * @throws CalcException
 * @return result of calculation
 */
 public double calculate(double x) throws CalcException
 {
    double y;
 try
 {
    y = Math.tan(Math.toRadians(x))/Math.sin(Math.toRadians(2*x));
    if (Double.isNaN(y) || Double.isInfinite(y) || Double.isInfinite(-y) || x == 90 || x == -90)
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
 return y;
 }
}
