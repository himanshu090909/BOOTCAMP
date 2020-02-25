package Java8;
interface DefaultMethodInterface
{
    default void fun()
    {
        System.out.println("inside default method");
    }
}
public class Q7 implements DefaultMethodInterface
{
    @Override
    public void fun() {
        System.out.println("Overriding default method");
    }

    public static void main(String[] args)
    {
        Q7 q = new Q7();
        q.fun();

    }
}