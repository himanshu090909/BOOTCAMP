public class Ques7
{
    static
    {
        System.out.println("using static block");
        System.out.println("FirstName : Himanshu LastName : Bhansali Age : 23");
    }
    public static String firstname = "himanshu";
    public static String lastName = "bhansali";
    public static int age = 23;

    public static void getDetails()
    {
        System.out.println("accessing using static method");

        System.out.println("firstname: "+firstname+ " lastName: "+lastName+ " age: "+age);
    }

    public static void main(String[] args)
    {
        System.out.println("accessing using static variables");
        System.out.println("firstname: "+firstname+ " lastName: "+lastName+ " age: "+age);
        getDetails();

    }
}
