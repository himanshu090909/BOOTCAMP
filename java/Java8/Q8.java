package Java8;

interface Abc
{
    default void a()
    {
        System.out.println("in Abc");
    }

}
interface  Def
{
    default void a()
    {
        System.out.println("in Def");
    }

}

public class Q8 implements Abc,Def
{
    public static void main(String[] args)
    {
        Q8 q = new Q8();
        q.a();

    }
    @Override
    public void a()
    {
        //we must override a() in class and we can call methods of interface by using super
        Abc.super.a();
        Def.super.a();
        System.out.println("overriding default method because we have two implementations in two interfaces");

    }
}
