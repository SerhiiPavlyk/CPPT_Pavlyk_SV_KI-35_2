import java.util.Scanner;

public class Main10
{
    public static void main(String[] args)
    {
        String text ;
        System.out.println("Enter text");
        Scanner inputer = new Scanner(System.in);
        text = inputer.nextLine();
        System.out.println(ReplaceNumbersToSpaces(text));
        inputer.close();
    }
    public static String ReplaceNumbersToSpaces(String Text)
    {
        String res = "";
        for (int i = 0; i < Text.length(); i++)
        {
            if (Text.charAt(i) > 47 && Text.charAt(i) < 58)
                res += ' ';
            else
                res += Text.charAt(i);
        }
        return res;
    }
}


    