package java2;

import java.util.Scanner;

// first part - implementing while loop
public class Que8
{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String s=null;
        System.out.println("enter words");
        String s1 = sc.next();
        if(s1.equals("done"))
        {
            System.out.println("you have entered done");
            System.exit(0);
        }
        else
        {
            Character ch = s1.charAt(0);
            Character ch1 = s1.charAt(s1.length()-1);
            if(ch.equals(ch1))
            {
                System.out.println("first and last character is equal");
            }
            else
            {
                System.out.println("first and last character is not equal");
            }
        }
        while(!(s=sc.next()).equals("done"))
        {

            Character ch = s.charAt(0);
            Character ch1 = s.charAt(s.length()-1);
            if(ch.equals(ch1))
            {
                System.out.println("first and last character is equal");
            }
            else
            {
                System.out.println("first and last character is not equal");
            }
        }

    }
}
