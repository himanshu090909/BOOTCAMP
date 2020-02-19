package java2;

public class Que3
{
    public static void f()
    {
        try {
            Class.forName("himanshu");
        }catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        f();
    }
}
