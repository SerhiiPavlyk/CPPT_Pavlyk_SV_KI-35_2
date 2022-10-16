/**
 * LAB_07 package
 */ 
package LAB_07;
/**
 * class Jacket realize model of Jacket
 * @author Pavlyk Serhii
 * @version 1.0
 */
public class Jacket extends Clothes
{
    private String weave;
    /**
     * Method getName() generates the name of Jacket based on its weave and color
     * @return name of Jacket
     */
    public String getName() {
        return weave + getColor();
    }
    /**
     * Constructor of class Jacket
     * @param size size of Jaket 
     * @param color color of Jacket
     * @param weave weave of Jacket
     * @param price price of Jacket
     */
    public Jacket(int size, String color, String weave, double price)
    {
        super(size,color,price);
        this.weave = weave;
    }
    /**
     * Method printInformation() print all information about Jacket
     */
    @Override
    public void printInformation() {
        System.out.println("Jacket:\nColor: " + getColor() +"\nSize: " + getSize()
        +"\nWeave: " + weave +"\nPrice: " + getPrice() );
    }
    /**
     * Method compareTo() compare price of Jacket with price of another cloth
     * @param another element of cloth with extends class Cloth
     * @return result of comparing
     */
    @Override
    public int compareTo(actionsWithClothes another) {
       Double a = this.getPrice();
        return a.compareTo(another.getPrice());
    }
}
