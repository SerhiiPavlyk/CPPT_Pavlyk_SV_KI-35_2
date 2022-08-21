import java.util.*;
import java.io.*; 
/**
 * Клас Lab_02 реалізує приклад програми до лабораторної роботи 2
 *
 * @author Pavlyk Serhii
 * @version 1.0
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
    public static void main (String [] args)
    {
        int size = 0;
        System.out.print("Enter size\t");
        Scanner scanner_in = new Scanner(System.in);
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
            char [][]arr;//виділяємо динамічну пам'ять під наш масив
            arr = new char[size][];
            for (int i = 0; i < size; i++)
            //допоміжний масив адрес, що зберігає адреси одновимірних масивів, які власне міститимуть дані;
                arr[i] = new char[size];//виділяємо динамічну пам'ять для кожної комірки нашого масиву, роблячи його двовимірним
            Fill_Matrix(arr, size,filler);//викликаємо функцію для заповнення двовимірного масиву
            System.out.println("Start matrix:");
            Print_Maxtrix(arr, size);//виводимо наш масив
            System.out.println("Matrix with shaded area:");
            Creating_picture(arr, size);//викликаємо функцію для заштрихування області
            Print_Maxtrix(arr, size);//виводимо наш масив на екран
            try {Printing_to_document_Maxtrix(arr, size) ;}//виводимо наш масив у файл
            catch(IOException e){e.printStackTrace();}
        }
/**
 * Статичний метод Fill_Matrix заповнює двовимірний масив розміру size символом filler
 *
 * @param arr масив
 * @param size розмір масиву
 * @param filler символ заповнення
 *
 */ 
    public static void Fill_Matrix (char[][] arr, int size,String filler)
    {
     for (int i = 0; i < size; i++)
    {
		for (int j = 0; j < size; j++)
			arr[i][j] = (char)filler.codePointAt(0) ;//заповнюємо всі стовпці та колонки
            // двовимірного масиву символом заповнювачем
    }
    }
/**
 * Статичний метод Print_Maxtrix виводить довимірний масив в консоль у форматі матриці
 *
 * @param arr масив
 * @param size розмір масиву
 *
 */     
    public static void Print_Maxtrix(char[][] arr, int size)
{
	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
            System.out.print( arr[i][j] + "\t");//виводимо нашу матрицю встановивши відступ 4
		System.out.println();
	}
}
/**
 * Статичний метод Printing_to_document_Maxtrix виводить довимірний масив в файл у форматі матриці
 *
 * @param arr масив
 * @param size розмір масиву
 *
 */   
public static void Printing_to_document_Maxtrix(char[][] arr, int size) throws IOException
{
    BufferedWriter writer = new BufferedWriter((new FileWriter("MyFile.txt")));
	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
            writer.write( arr[i][j] + "\t");//виводимо нашу матрицю встановивши відступ 4
            writer.write("\n");
	}
    writer.flush();
    writer.close();
}
/**
 * Статичний метод Creating_picture редагує масив arr відповідно до завдання
 *
 * @param arr масив
 * @param size розмір масиву
 *
 */   
public static void Creating_picture(char[][] arr, int size)
{
	for (int i = 1; i < size-1; i++)//відступаємо рамку товщиною 1
	{
		for (int j = 1; j < size-1; j++)
		{
			arr[1][j] = ' ';//заповнюємо верхню горизонтальну частину заштрихованої області
			arr[i][1] = ' ';//заповнюємо ліву вертикальну частину заштрихованої області
			arr[size-2][j] = ' ';//заповнюємо нижню горизонтальну частину заштрихованої області
			arr[i][size-2] = ' ';//заповнюємо праву вертикальну частину заштрихованої області
		}
	}
}
}
