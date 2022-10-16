package LAB_07;
import java.util.ArrayList;
interface actionsWithClothes extends Comparable<actionsWithClothes>
{
    String getColor();
    int getSize();
    double getPrice();
    void printInformation();
    String getName();
}
abstract class Cloth implements actionsWithClothes
{
    Cloth(int size, String color, double price)
    {
        this.size = size;
        this.color = color;
        this.price = price;
    }
    private int    size ;   
    private String color;
    private double price;
   
    @Override
    public int getSize() {
        return size;
    }
    @Override
    public String getColor() {
        return color;
    }
    @Override
    public double getPrice() {
        return price;
    }
    @Override
    public String getName() {
        return null;
    }
}
class T_Short extends Cloth
{
    private String weave;
    public String getName() {
        return weave + getColor();
    }
    T_Short(int size, String color, String weave, double price)
    {
        super(size,color,price);
        this.weave = weave;
    }
    @Override
    public void printInformation() {
        System.out.println("T-Short:\nColor: " + getColor() +"\nSize: " + getSize()
        +"\nWeave: " + weave +"\nPrice: " + getPrice() );
        
    }
    @Override
    public int compareTo(actionsWithClothes o) {
       Double a = this.getPrice();
        return a.compareTo(o.getPrice());
    }
}
class Pants extends Cloth
{
    private String material;
    public String getName() {
        return material + getColor();
    }
    Pants(int size, String color, String material, double price)
    {
        super(size,color,price);
        this.material = material;
    }
    @Override
    public void printInformation() {
        System.out.println("Pants:\nColor: " + getColor() +"\nSize: " + getSize()
        +"\nMaterial: " + material +"\nPrice: " + getPrice() );
        
    }
    @Override
    public int compareTo(actionsWithClothes o) {
       Double a = this.getPrice();
        return a.compareTo(o.getPrice());
    }
}
class Jacket extends Cloth
{
    private String weave;
    public String getName() {
        return weave + getColor();
    }
    Jacket(int size, String color, String weave, double price)
    {
        super(size,color,price);
        this.weave = weave;
    }
    @Override
    public void printInformation() {
        System.out.println("Jacket:\nColor: " + getColor() +"\nSize: " + getSize()
        +"\nWeave: " + weave +"\nPrice: " + getPrice() );
        
    }
    @Override
    public int compareTo(actionsWithClothes o) {
       Double a = this.getPrice();
        return a.compareTo(o.getPrice());
    }
}
class Wardrobe <T extends actionsWithClothes>
{
private ArrayList<T> arrayList;

public Wardrobe()
{
    arrayList = new ArrayList<T>();
}
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
public void AddData(T data)
{
arrayList.add(data);
System.out.print("Element added: ");
data.printInformation();
}
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
public class Main {
    public static void main(String[] args)
    {
       Wardrobe<? super Cloth> wardrobe = new Wardrobe<Cloth>();
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