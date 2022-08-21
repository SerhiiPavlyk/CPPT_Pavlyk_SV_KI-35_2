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
            System.out.println("==================================");
            System.out.println("Start matrix:");
            Print_Maxtrix(arr, size);//виводимо наш масив
            System.out.println("==================================");
            System.out.println("Matrix with shaded area:");
            func(arr, size);//викликаємо функцію для заштрихування області
            Print_Maxtrix(arr, size);//виводимо наш масив
        }
    
    public static void Fill_Matrix (char[][] arr, int size,String filler)
    {
     for (int i = 0; i < size; i++)
    {
		for (int j = 0; j < size; j++)
			arr[i][j] = (char)filler.codePointAt(0) ;//заповнюємо всі стовпці та колонки
            // двовимірного масиву символом заповнювачем
    }
    }
    public static void Print_Maxtrix(char[][] arr, int size)
{
	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
        {
            System.out.print( arr[i][j]);//виводимо нашу матрицю встановивши відступ 4
            System.out.print("\t");
        }
		System.out.println();
	}
}
public static void func(char[][] arr, int n)
{
	for (int i = 1; i < n-1; i++)//відступаємо рамку товщиною 1
	{
		for (int j = 1; j < n-1; j++)
		{
			arr[1][j] = ' ';//заповнюємо верхню горизонтальну частину заштрихованої області
			arr[i][1] = ' ';//заповнюємо ліву вертикальну частину заштрихованої області
			arr[n-2][j] = ' ';//заповнюємо нижню горизонтальну частину заштрихованої області
			arr[i][n-2] = ' ';//заповнюємо праву вертикальну частину заштрихованої області
		}
	}
}


}
