import java.util.Scanner;

public class Ques5
{
    public static Scanner sc = new Scanner(System.in);
    public void commArray(int[] a,int[] b)
    {
        System.out.println("matching elements between two arrays are");
        for(int i=0;i<a.length;i++)
        {
            for(int j=0;j<b.length;j++)
            {
                if(a[i]==b[j])
                {
                    System.out.print(a[i]+" ");

                    for(int k=0;k<b.length;k++)
                    {
                        if(b[k]==a[i])
                        {
                            b[k]=-1;
                        }
                    }
                    break;
                }
            }

        }
    }

    public static void main(String[] args) {
        System.out.println("enter length of 1st array");
        int length1 = sc.nextInt();
        int a[] = new int[length1];
        System.out.println("enter elements of first array");
        for(int i=0;i<length1;i++)
        {
            a[i]=sc.nextInt();
        }
        System.out.println("elements of first array are");
        for (int i=0;i<length1;i++)
        {
            System.out.print(a[i]+" ");
        }
        System.out.println();
        System.out.println("enter length of 2nd array");
        int length2 = sc.nextInt();
        int b[] = new int[length2];
        System.out.println("enter elements of second array");
        for(int i=0;i<length2;i++)
        {
            b[i]=sc.nextInt();
        }
        System.out.println("elements of 2nd array are");
        for (int i=0;i<length2;i++)
        {
            System.out.print(b[i]+" ");
        }
        System.out.println();

        Ques5 q = new Ques5();
        q.commArray(a,b);
    }
}
