import java.util.Scanner;

public class Ques4
{
    public static Scanner sc = new Scanner(System.in);
    public static int lowercase,upper,spc,number;
    public static String s;
    public static int totalLength;
    public void count(String str)
    {

      for(int i=0;i<str.length();i++)
      {
          if(Character.isUpperCase(str.charAt(i)))
          {
              upper++;
          }
          else if (Character.isLowerCase(str.charAt(i)))
          {
              lowercase++;
          }
          else if (Character.isDigit(str.charAt(i)))
          {
              number++;
          }
          else
          {
              spc++;
          }

      }
        countAndPercentageOfLower(lowercase);
        countAndPercentageOfUpper(upper);
        countAndPercentageOfNumber(number);
        countAndPercentageOfSpecialCharacters(spc);
    }

    public void countAndPercentageOfLower(int lowercase)
    {
        System.out.println("number of lowercase characters "+ lowercase);
        double per =  (lowercase*100.0)/totalLength;
        System.out.println("percentage of lowercase characters "+ per);


    }
    public void countAndPercentageOfUpper(int upper)
    {
        System.out.println("number of uppercae characters "+ upper);
        double per =  (upper*100.0)/totalLength;
        System.out.println("percentage of uppercase characters "+ per);
    }

    public void countAndPercentageOfNumber(int number)
   {
    System.out.println("number of numbers  "+ number);
    double per =  (number*100.0)/totalLength;
    System.out.println("percentage of numbers "+ per);
   }



   public void countAndPercentageOfSpecialCharacters(int spc)
   {
       System.out.println("number of special characters "+ spc);
    double per =  (spc*100.0)/totalLength;
    System.out.println("percentage of special characters "+ per);
    }


    public static void main(String[] args) {
        System.out.println("enter the string");
        s = sc.next();
        totalLength = s.length();
        Ques4 q = new Ques4();
        q.count(s);
    }
}
