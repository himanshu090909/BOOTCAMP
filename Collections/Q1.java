

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Q1 {
    static float sum;

    public static void main(String[] args) {
        System.out.println("enter 5 float numbers");
        Scanner sc = new Scanner(System.in);
        List<Float> list = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.add(sc.nextFloat());

        }
        Iterator<Float> iterator = list.iterator();
        while (iterator.hasNext()) {
            sum = sum + iterator.next();
        }
        System.out.println("Sum of the numbers is the list is " + sum);
    }
}
