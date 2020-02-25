package Java8;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class Q9
{
    public static void main(String[] args)
    {
        List<Integer> values = Arrays.asList(3,4,5,6,7,8);
        System.out.println(values.stream().filter(i->i%2==0).collect(Collectors.toList()));
    }
}
