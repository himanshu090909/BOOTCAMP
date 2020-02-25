package Java8;

import java.util.Arrays;
import java.util.List;

public class Q10
{
    public static void main(String[] args)
    {
        List<Integer> values = Arrays.asList(2,3,4,5,6,7,8);
        System.out.println(values.stream().filter(i->i>5).mapToInt(e->e).sum());

    }
}
