/**
 * LAB_07 package
 */ 
package LAB_07;
/**
 * class Pants realize model of pants
 * @author Pavlyk Serhii
 * @version 1.0
 */
public class Pants extends Clothes
{
    private String material;
    /**
     * Method getName() generates the name of Jacket based on its material and color
     * @return name of Jacket
     */
    public String getName() {
        return material + getColor();
    }
    /**
     * Constructor of class Pants
     * @param size size of Pants
     * @param color color of Pants
     * @param material material of Pants
     * @param price price of Pants
     */
    public Pants(int size, String color, String material, double price)
    {
        super(size,color,price);
        this.material = material;
    }
     /**
     * Method printInformation() print all information about Pants
     */
    @Override
    public void printInformation() {
        System.out.println("Pants:\nColor: " + getColor() +"\nSize: " + getSize()
        +"\nMaterial: " + material +"\nPrice: " + getPrice() );
        
    }
    /**
     * Method compareTo() compare price of Pants with price of another clothes
     * @param another element of clothes which extends class Clothes
     * @return result of comparing
     */
    @Override
    public int compareTo(actionsWithClothes another) {
       Double a = this.getPrice();
        return a.compareTo(another.getPrice());
    }
}