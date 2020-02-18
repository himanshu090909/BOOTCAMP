import java.util.*;

public class Ques1 {
    public static Scanner sc = new Scanner(System.in);
    public static String s;
    public static int startIndex,endIndex;

    public static void main(String[] args) {
        System.out.println("enter the string");
        s = sc.next();
        System.out.println("entered string is " + s);
        System.out.println("give the starting and end index of substring");
        startIndex = sc.nextInt();
        endIndex = sc.nextInt();
        if(startIndex<0||startIndex>s.length())
        {
            System.out.println("starting index should be greater than 0 and less than string length");
        }
        if(endIndex<startIndex||endIndex<0||endIndex>s.length())
        {

            System.out.println("end index should be greater than startIndex and less than string length");
        }
        String substr = s.substring(startIndex,endIndex);
        System.out.println("substring you selected is " + substr);
        System.out.println("enter your new substring");

        String newsub = sc.next();
        s = s.replace(substr, newsub);
        System.out.println("new string is " + s);



    }
}
