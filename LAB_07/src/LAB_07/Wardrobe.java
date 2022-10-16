package LAB_07;

import java.util.ArrayList;
/**
 * Generic class Wardrobe realize model of Wardrobe.
 * @param <T> class can be created only by types which extends class Clothes
 * @author Pavlyk Serhii
 * @version 1.0
 */

public class Wardrobe <T extends Clothes>
{

private ArrayList<T> arrayList;
/**
 * Constructor of class Wardrobe
 */
public Wardrobe()
{
    arrayList = new ArrayList<T>();
}

/**
 * Method findMin() finds cloth with minimal price
 * @return the cheapest clothes
 */
public T findMin()
{
if (!arrayList.isEmpty())
{
T min = arrayList.get(0);
for (int i=1; i< arrayList.size(); i++)
{
if ( arrayList.get(i).compareTo(min) < 0 )
min = arrayList.get(i);
}
return min;
}
return null;
}

/**
 * Method AddData() adds instances of classes that extends from
 * the Clothes class to the collection arrayList
 * @param data instances of classes that extend from the Clothes class
 */
public void AddData(T data)
{
arrayList.add(data);
System.out.print("Element added: ");
data.printInformation();
}

/**
 * Method DeleteData() deletes instances of classes that extend
 * from the Clothes class from colection arrayList of Wardrobe
 * @param key the key clue which identifies the
 * instances of classes that extend from the Clothes class
 */

public void DeleteData(String key)
{
    int index = 0 ;
    for (int j = 0; j < arrayList.size(); j++)
    {
        if (arrayList.get(j).getName().equals(key + arrayList.get(j).getColor()))
        index = j;
    }
System.out.println("Element deleted:");
arrayList.get(index).printInformation();
arrayList.remove(index);
if (!arrayList.isEmpty())
{
    System.out.println("The following things remained in the Wardrobe:");
   for (int j = 0; j < arrayList.size(); j++)
        arrayList.get(j).printInformation();
}
}

}