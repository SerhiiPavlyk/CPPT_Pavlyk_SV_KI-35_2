/**
 * LAB_07 package
 */ 
package LAB_07;
/**
 * class T_Short realize model of t_short
 * @author Pavlyk Serhii
 * @version 1.0
 */
public class T_Short extends Clothes
{
    private String weave;
    /**
     * Method getName() generates the name of T_Short based on its weave and color
     * @return name of Jacket
     */
    public String getName() {
        return weave + getColor();
    }
    /**
     * Constructor of class T_Short
     * @param size size of T_Short
     * @param color color of T_Short
     * @param weave weave of T_Short
     * @param price price of T_Short
     */
    public T_Short(int size, String color, String weave, double price)
    {
        super(size,color,price);
        this.weave = weave;
    }
     /**
     * Method printInformation() print all information about t_short
     */
    @Override
    public void printInformation() {
        System.out.println("T-Short:\nColor: " + getColor() +"\nSize: " + getSize()
        +"\nWeave: " + weave +"\nPrice: " + getPrice() );
    }
    /**
     * Method compareTo() compare price of t_short with price of another clothes
     * @param another element of clothes which extends class Clothes
     * @return result of comparing
     */
    @Override
    public int compareTo(actionsWithClothes another) {
       Double a = this.getPrice();
        return a.compareTo(another.getPrice());
    }
}