package Java8;

interface MethodReferenceInterface
{
    void manipulation(int a,int b);
}
public class Q3
{
    static  void sum(int a,int b){
        System.out.println("sum is "+(a+b));
    }
    static void subtract(int a,int b)
    {
        if (a>b)
        {
            System.out.println("difference is "+(a-b));
        }
        else
        {
            System.out.println("difference is "+(a-b));
        }
    }
    void multiplication(int a,int b)
    {
        System.out.println("result of multiplication is "+(a*b));
    }

    public static void main(String[] args)
    {

        MethodReferenceInterface methodReferenceInterface = Q3::sum;
        MethodReferenceInterface methodReferenceInterface1 = Q3::subtract;
        MethodReferenceInterface methodReferenceInterface2 = new Q3()::multiplication;
        methodReferenceInterface.manipulation(10,20);
        methodReferenceInterface1.manipulation(20,10);
        methodReferenceInterface2.manipulation(2,3);
    }


}
