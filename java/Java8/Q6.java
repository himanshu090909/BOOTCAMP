package Java8;
interface A
{
    static void display()
    {
        System.out.println("i am in static method");
    }
    default void display1()
    {
        System.out.println("i am in default method");
    }
}
public class Q6 implements A
{
    public static void main(String[] args)
    {
      Q6 q = new Q6();
      A.display();
      q.display1();
    }
}
