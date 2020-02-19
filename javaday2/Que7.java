package java2;

import java.util.Scanner;

public class Que7
{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        System.out.println("enter seconds");
        int  sec = sc.nextInt();
        int day = sec / (24 * 3600);
        sec = sec % (24 * 3600);
        int hour = sec/3600;
        sec= sec%3600;
        int minutes = sec/ 60 ;
        sec= sec%60;
        int seconds=sec;
        System.out.println(day+" days "+hour+" hours "+minutes+" minutes "+seconds+" seconds ");
    }

}
