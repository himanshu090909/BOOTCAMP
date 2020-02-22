package com.tothenew;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Q7
{
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future> all = new ArrayList<>();
        for (int i=0;i<2;i++)
        {
            Future<Integer> future = executorService.submit(new Task());
            all.add(future);
        }



        for (int i=0;i<4;i++)
        {   Thread.sleep(1000);
            System.out.println("chilling and executing remaining task");
        }


        for (int i=0;i<2;i++) {
            Future<Integer> future = all.get(i);
            try {
                System.out.println("now fetching values from both the tasks");
                Integer result = future.get(2, TimeUnit.HOURS);
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
class Task implements Callable<Integer>
{
    public Integer call() throws Exception
    {
        int sum=0;
        for (int i=0;i<10;i++)
        {
            Thread.sleep(1000);
            sum=sum+i;
        }
        return sum;
    }
}
