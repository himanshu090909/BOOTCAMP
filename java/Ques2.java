
import java.util.*;

  public class Ques2
  {

    public static Scanner sc = new Scanner(System.in);
    public static String s;


    public void countWords(String s)
    {
        String stringarray[] = s.split("\\s");
        HashMap<String,Integer> dupwords = new HashMap<String,Integer>();
        for(int i=0;i<stringarray.length;i++)
           {
            if(dupwords.containsKey(stringarray[i]))
            {
                dupwords.put(stringarray[i],dupwords.get(stringarray[i])+1);
            }
            else
            {
                dupwords.put(stringarray[i],1);
            }
           }
        for(Map.Entry m:dupwords.entrySet())
        {
            if((int)m.getValue() > 1)
            {
                System.out.println(m.getKey()+" : "+m.getValue());
            }
        }
        System.out.println(dupwords);

    }

    public static void main(String[] args)
    {
        Ques2 q = new Ques2();
        System.out.println("enter the string");
        s = sc.nextLine();
        q.countWords(s);
    }
}
