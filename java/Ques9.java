import java.io.FileOutputStream;
import java.util.Scanner;

public class Ques9
{
    public static Scanner sc = new Scanner(System.in);

    enum House {

        BHK(500000),
        BHK1(600000),
        BHK2(700000);

        private int val1;

        House(int val) {
            this.val1 = val;
        }
        public int getPrice()
        {
          return this.val1;
        }

    }

    public static void main(String[] args) {
        int a;

        System.out.println("enter choice");
        System.out.println("1 for BHK");
        System.out.println("2 for BHK");
        System.out.println("3 for BHK");
        a = sc.nextInt();

        switch(a)
        {
            case 1:
                System.out.println("price of BHK is "+House.BHK.getPrice());
                break;
            case 2:
                System.out.println("price of BHK1 is "+House.BHK1.getPrice());
                break;
            case 3:
                System.out.println("price of BHK is "+House.BHK2.getPrice());
                break;
            default:
                System.out.println("wrong choice");



        }

    }
}
