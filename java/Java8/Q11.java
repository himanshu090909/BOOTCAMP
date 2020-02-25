package Java8;
import java.util.Arrays;
import java.util.List;
public class Q11
{
    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(2,3);
        System.out.println(values.stream().map(i->i*2).mapToInt(e->e).average().orElse(0));
    }
}
