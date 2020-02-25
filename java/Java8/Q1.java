package Java8;


import java.util.Scanner;

@FunctionalInterface
interface Comp {
    public boolean compare(int i, int j);
}

@FunctionalInterface
interface Incr {
    public int increment(int i);
}

@FunctionalInterface
interface Conc {
    public String concatenate(String a, String b);
}

@FunctionalInterface
interface ToUPPER {
    public String toupper(String a);
}

public class Q1 {
    static Scanner sc = new Scanner(System.in);

    public void f() {
        int choice;
        do {
            System.out.println("Enter 1 to find whether first number is greater than second number:");
            System.out.println("Enter 2 to increment the number by 1 and return incremented value: ");
            System.out.println("Enter 3 for Concatination of 2 string    : ");
            System.out.println("Enter 4 Convert a string to uppercase and return :");
            System.out.println("enter your choice");
            choice = sc.nextInt();
            int x=0,y=0;
            switch (choice) {
                case 1:
                    System.out.print("Enter first value: ");
                    x = sc.nextInt();
                    System.out.println("enter second value");
                    y = sc.nextInt();
                    Comp c = (i, j) -> i > j;
                    System.out.println("result is :"+c.compare(x, y));
                    break;
                case 2:
                    System.out.println("enter value");
                    x= sc.nextInt();
                    Incr c1 = (i) -> ++i;
                    System.out.println("incremented value is "+c1.increment(x));
                    break;
                case 3:
                    System.out.println("enter first string");
                    String s = sc.next();
                    System.out.println("enter second string");
                    String s1 = sc.next();
                    Conc c2 = (i,j) -> i.concat(j);
                    System.out.println("concatenated string is :"+c2.concatenate(s,s1));
                    break;
                case 4:
                    System.out.println("enter the string");
                    String s2 = sc.next();
                    ToUPPER c3 = i -> i.toUpperCase();
                    System.out.println("string in upper case is :"+c3.toupper(s2));
                    break;
                default:
                    System.out.println("wrong choice");
                    break;
            }

        } while (choice<4);
    }
    public static void main(String[] args)
    {
        Q1 q = new Q1();
        q.f();


    }
}