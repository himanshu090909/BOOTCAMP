import java.io.IOException;
import java.util.Scanner;

public class Ques3
{
    public static Scanner sc = new Scanner(System.in);
    public static  void main(String[]  args)
    {
        System.out.println("enter string");
        String s = sc.nextLine();
        System.out.println("entered string is : "+s);
        System.out.println("choose the character whose frequency you want to calculate");
        char ch = sc.next().charAt(0);
        int count = s.length()-s.replace(String.valueOf(ch),"").length();

        System.out.println("frequency of "+ch+" is"+ count);

    }
}
