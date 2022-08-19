import java.util.*;

public class Lab_02 {
    public static void main (String [] args)
    {
        int size;
        System.out.println("Enter size\t");
        Scanner in = new Scanner(System.in);
        size =  in.nextInt();//вводимо розмір матриці з клавіатури
        in.nextLine();
        boolean stop = true;//допоміжна змінна для перевірки розмірность матриці
        String filler;
        System.out.println("Enter fill character\t");
        filler =  in.nextLine();//вводимо розмір матриці з клавіатури
        while (stop)
        {
            if (size < 5)//перевіряємо чи наша матриця є більшої розмірності ніж 5
            {
                System.out.println("Розмiр матрицi є меншим за 5!\nПовторiть ввiд\t\t");//якщо ні то виводимо відповідний текст і просимо повторити ввід
                size =  in.nextInt();//вводимо розмір матриці з клавіатури
                in.nextLine();
            }
            else if(size>=5)//якщо так 
            {
                stop = false; //присвоюємо нашій допоміжній змінній значення false
            }
        }//якщо більше 5 то stop = false і виходимо з циклу
        char [][]arr;//виділяємо динамічну пам'ять під наш масив
        arr = new char[size][];
        for (int i = 0; i < size; i++)
        { //допоміжний масив адрес, що зберігає адреси одновимірних масивів, які власне міститимуть дані;
            arr[i] = new char[size];//виділяємо динамічну пам'ять для кожної комірки нашого масиву, роблячи його двовимірним
        }
        Fill_Matrix(arr, size,filler);//викликаємо функцію для заповнення двовимірного масиву
        System.out.println("==================================");
        System.out.println("Початкова матриця:");
        Print_Maxtrix(arr, size);//виводимо наш масив
        System.out.println("==================================");
        System.out.println("Матриця iз заштрихованою областю:");
        func(arr, size);//викликаємо функцію для заштрихування області
        Print_Maxtrix(arr, size);//виводимо наш масив
    
    }
    public static void Fill_Matrix (char[][] arr, int size,String filler)
    {
     for (int i = 0; i < size; i++)
    {
		for (int j = 0; j < size; j++)
		{
			arr[i][j] = (char)filler.codePointAt(0) ;//заповнюємо всі стовпці та колонки двовимірного масиву 1
		}
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
			arr[1][j] = 0;//заповнюємо верхню горизонтальну частину заштрихованої області
			arr[i][1] = 0;//заповнюємо ліву вертикальну частину заштрихованої області
			arr[n-2][j] = 0;//заповнюємо нижню горизонтальну частину заштрихованої області
			arr[i][n-2] = 0;//заповнюємо праву вертикальну частину заштрихованої області
		}
	}
}


}
