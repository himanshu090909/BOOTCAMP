import java.util.*;
public class Ques6
{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        System.out.println("elements of the array are:");
        int a[]={1,2,3,1,2,3,1,4};
        for(int i=0;i<a.length;i++)
        {
            System.out.print(a[i]+" ");
        }
        System.out.println();
        for(int i=0;i<a.length;i++)
        {
            int count=0;
            for(int j=0;j<a.length;j++)
            {
                if(a[i]==a[j])
                {
                    count++;
                }
            }
            if(count==1)
            {
                System.out.println("unique element is "+a[i]);
                break;
            }
        }
    }
}
