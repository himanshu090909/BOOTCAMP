package Java8;
import java.util.Arrays;
import java.util.List;
public class Q12
{
    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(1,2,3,4,5,6,7,8);
        System.out.println(" first even number in the integer list which is greater than 3: "
                          +values.stream().filter(e->e%2==0).filter(i->i>3).findFirst().orElse(0));
    }
}
