package java2;

import java.io.File;
import java.io.PrintStream;

public class Que13 {
    static void a() throws MyCustomException {
        throw new MyCustomException("exception occured");
    }

    public static void main(String[] args) throws Exception{

        File file = new File("logs.txt");
        PrintStream ps = new PrintStream(file);
        try {
            a();
        }catch (Exception e)
        {
            System.out.println("stack trace can be checked into logs.txt");
            e.printStackTrace(ps);
        }
    }
}
