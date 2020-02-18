import java.util.Scanner;

public class Ques8
{
    public static Scanner sc = new Scanner(System.in);
    public static String s;
    public void calc(String s)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(s);
        System.out.println("original string is "+sb);
        sb.reverse();
        System.out.println("reversed string is "+sb);
        sb.delete(4,9);
        System.out.println("modified string is "+sb);
    }

    public static void main(String[] args)
    {
        System.out.println("enter the string ");
        s = sc.next();
        Ques8 q = new Ques8();
        q.calc(s);
    }
}
