package Java8;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
public class Q5
{
    public static void main(String[] args)
    {
        /**checking if a number is greater than 2 or not*/
        Predicate<Integer> p = i->i>2;
        System.out.println(p.test(5));
        /**it will give false as 5 is not greater than 2*/

        /**supplier is used to provide value*/
        Supplier<Integer> s = ()->5;
        /** we can get the value using get function*/
        System.out.println(s.get());

        Consumer<Integer> c = i-> System.out.println(i);
        c.accept(6);

        /**function can be used to manipulate values*/
        Function<Integer,Integer> f = (i->i*2);
        System.out.println(f.apply(6));
        /**it will print 6*2=12*/
    }
}
