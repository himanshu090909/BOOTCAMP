package Java8;

import java.util.Scanner;

@FunctionalInterface
interface Sum
{
    int summation(int a,int b);
}
public class Q2
{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Sum s = (i,j)->i+j;
        System.out.println("enter first value");
        int x = sc.nextInt();
        System.out.println("enter second value");
        int y = sc.nextInt();
        System.out.println("sum is : "+s.summation(x,y));

    }



}
