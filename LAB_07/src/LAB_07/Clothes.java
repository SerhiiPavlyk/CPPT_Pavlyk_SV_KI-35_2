/**
 * LAB_07 package
 */ 
package LAB_07;
/**
 * class Clothes realize abstract model of clothes
 * @author Pavlyk Serhii
 * @version 1.0
 */
public abstract class Clothes implements actionsWithClothes
{
    /**
     * Constructor of class Cloth
     * @param size size of Cloth
     * @param color color of Cloth
     * @param price price of Cloth
     */
    public Clothes(int size, String color, double price)
    {
        this.size = size;
        this.color = color;
        this.price = price;
    }
    private int    size ;   
    private String color;
    private double price;
    /**
     * Method returns size of clothes
     */
    @Override
    public int getSize() {
        return size;
    }
    /**
     * Method returns color of clothes
     */
    @Override
    public String getColor() {
        return color;
    }
    /**
     * Method returns price of clothes
     */
    @Override
    public double getPrice() {
        return price;
    }
}