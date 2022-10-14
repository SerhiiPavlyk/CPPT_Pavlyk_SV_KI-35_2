import java.util.*;
import java.io.*;
/**
 * Клас Lab_02 реалізує приклад програми до лабораторної роботи 2
 *
 * @author Pavlyk Serhii
 * @version 1.1
 * @since version 1.0
 *
 */
public class Lab_02 {
/**
 * Статичний метод main є точкою входу в програму
 *
 * @param args
 *
 */
    public static void main (String [] args)throws IOException
    {
        int size = 0;
        System.out.print("Enter size\t");
        Scanner scanner_in = new Scanner(System.in);
        BufferedWriter writer = new BufferedWriter((new FileWriter("MyFile.txt")));
        while  (true)
        {
        try
            {
                size =  scanner_in.nextInt();  //вводимо розмір матриці з клавіатури
                if (size <= 3)//перевіряємо чи наша матриця є більшої розмірності ніж 3
                {
                    System.out.print("The size of the matrix is less than 3!\nRepeat the input\t\t");//якщо ні то виводимо відповідний текст і просимо повторити ввід
                    size = scanner_in.nextInt();//вводимо розмір матриці з клавіатури
                   scanner_in.nextLine();
                }
                break;
            }
            catch (Exception e)
            {
                System.out.println("Incorrect input of int. Try again. ");
                System.out.print("Enter size\t");
                scanner_in.nextLine();
            }
        }
            scanner_in.nextLine();
            String filler;
            System.out.print("Enter fill character\t");
            filler = scanner_in.nextLine();//вводимо символ заповнювач з клавіатури)
            while  (true)
            {
            if  (filler.length() != 1)
            {
                System.out.println("fill character must be one character!");
                System.out.print("Enter fill character\t");
                filler = scanner_in.nextLine();
            }
            else break;
            }
            char [][]arr = new char[size][];
            for (int i = 0; i < size; i++)
            {
                if(i==0 ||i==size-1)
                {
                    System.out.println();
                    writer.write("\n");
                    continue;
                }
                arr[1] = new char[size-1];
                arr[size-2]=new char[size-1];
                arr[i] = new char[size-1];
                for (int j = 1; j < size-1; j++)
                    {
                        System.out.print("\t");
                        writer.write("\t");
                arr[1][j] = (char)filler.codePointAt(0);//заповнюємо верхню горизонтальну частину заштрихованої області
                arr[i][1] = (char)filler.codePointAt(0);//заповнюємо ліву вертикальну частину заштрихованої області
                arr[size-2][j] = (char)filler.codePointAt(0);//заповнюємо нижню горизонтальну частину заштрихованої області
                arr[i][size-2] = (char)filler.codePointAt(0);
                System.out.print(arr[i][j]);
                writer.write( arr[i][j]);
                    }
                    System.out.println();
                    writer.write("\n");
                }
            scanner_in.close();
            writer.flush();
            writer.close();
        }
    }
