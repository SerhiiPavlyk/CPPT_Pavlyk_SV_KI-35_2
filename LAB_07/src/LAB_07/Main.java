/**
 * LAB_07 package
 */ 
package LAB_07;
/**
 * Main class realize driver for the application
 * @author Serhii Pavlyk
 * @version 1.0
 */
public class Main {
    /**
     * Method main start point to the program
     * @param args arguments to pass to the start point
     */
    public static void main(String[] args)
    {
       Wardrobe<? super Clothes> wardrobe = new Wardrobe<Clothes>();
       wardrobe.AddData(new T_Short(44,"red","sun",10.4));
       wardrobe.AddData(new Jacket(52, "black", "skull", 20.9));
       wardrobe.AddData(new Pants(112, "black", "jeans", 16.9));
       wardrobe.AddData(new T_Short(43,"blue","flower",12.4));
       System.out.println("The cheapest cloth in the Wardrobe:");
       wardrobe.findMin().printInformation();
       wardrobe.DeleteData("sun");
       System.out.println("The cheapest cloth in the Wardrobe:");
       wardrobe.findMin().printInformation();
   }
}