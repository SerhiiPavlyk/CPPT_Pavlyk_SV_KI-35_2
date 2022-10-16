/**
 * LAB_07 package
 */ 
package LAB_07;
/**
 *
 * interface actionsWith clotheses realize what we can do
 * with our classes which interpret the clothes
 * @author Pavlyk Serhii
 * @version 1.0
 */ 

public interface actionsWithClothes extends Comparable<actionsWithClothes>
{
    /**
     *  Method getColor() return the color of the clothes
     * @return color of the clothes
     */
    String getColor();
    /**
     * Method getSize() returns the size of the clothes
     * @return color of the clothes
     */

    int getSize();
    /**
     * Method getPrice() returns the price of the clothes
     * @return price of the clothes
     */
    double getPrice();
    /**
     * Method printInformation() print the information of the clothes
     */
    void printInformation();
    /**
     * Method getName() retuns the combination of the specific and color of the clothes
     * @return generated name of the clothes
     */
    String getName();
}