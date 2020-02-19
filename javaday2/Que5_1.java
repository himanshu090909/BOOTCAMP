package java2;
//from copy constructor
public class Que5_1
{
    int a;
    Que5_1(int b)
    {
        a=b;
    }
    Que5_1(Que5_1 q)
    {
        a = q.a;
    }
    public static void main(String[] args) {


            Que5_1 q = new Que5_1(5);
            Que5_1 q1 = new Que5_1(q);
            System.out.println("value of a from first object "+q.a);
            System.out.println("value of a from first object "+q1.a);


    }
}
