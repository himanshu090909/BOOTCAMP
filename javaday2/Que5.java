package java2;
//implementing cloneable interface in this
public class Que5 implements Cloneable
{
    int a;
    Que5(int a)
    {
        this.a=a;
    }
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    public static void main(String[] args) {

        try {
            Que5 que5 = new Que5(5);
            Que5 que51 = (Que5) que5.clone();
            System.out.println("value of a from first object "+que5.a);
            System.out.println("value of a from second object "+que51.a);
            }catch (CloneNotSupportedException e)
           {
               System.out.println(e.getMessage());
           }

    }
}
