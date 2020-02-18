import java.util.Scanner;

public class Ques11
{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int a;
        System.out.println("enter choice");
        System.out.println("1 for gettting details of SBI");
        System.out.println("2 for gettting details of BOI");
        System.out.println("3 for gettting details of ICICI");
        a = sc.nextInt();
        switch (a)
        {
            case 1: SBI s = new SBI();
            s.getDetails();
                break;
            case 2: BOI b = new BOI();
            b.getDetails();
                break;
            case 3: ICICI i = new ICICI();
            i.getDetails();
                break;
            default:
                System.out.println("wrong choice");

        }
    }
}

