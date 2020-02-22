
////Write a program using synchronization block and synchronization method

package com.tothenew;

class Counter
{
    int number;
    public synchronized void increment()
    {
        number++;
    }

}
public class Q3_method
{
    public static void main(String[] args) throws Exception{
        Counter c = new Counter();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<500;i++)
                {
                    c.increment();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<500;i++)
                {
                    c.increment();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("value of number is "+c.number);
    }
}
