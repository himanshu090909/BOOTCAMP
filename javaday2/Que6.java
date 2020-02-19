package java2;

import java.util.*;
public class Que6
{
    public static void main(String[] args)
    {

        try
        {
            int a =0;
            int b =5;
            int c = b/a;
            int d[] = {1,2,3,4};
            int e = d[5];
        }
        catch(ArithmeticException e)
        {
            System.out.println("Arithmetic exception in called on dividing the number by 0");

        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("ArrayIndexOutOfBounds Exception is called");
        }
        catch(Exception e)
        {
            System.out.println("it will catch all the remaining exceptions");
        }
        finally
        {
            System.out.println("finally block always gets executed");

        }

    }
}