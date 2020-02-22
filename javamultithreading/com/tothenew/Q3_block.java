
////Write a program using synchronization block and synchronization method

package com.tothenew;


class Counters
{
    int number;

    public void increment()
    {
        synchronized (this) {
            number++;
        }
    }

}
public class Q3_block
{
    public static void main(String[] args) throws Exception{
        Counters c = new Counters();
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
