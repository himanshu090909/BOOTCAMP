////Write a program do to demonstrate the use of volatile keyword.

package com.tothenew;
public class Q1
{
    private static volatile int number=0;

    public static void main(String[] args) {
        Thread t1 = new Thread()
        {

            public void run()
            {
                int x = number;
                while (true)
                {
                    if(x!=number)
                    {
                        System.out.println("thread1 "+number);
                        x=number;

                    }
                }
            }
        };
        Thread t2 = new Thread()
        {
            public void run()
            {
                int x = number;
                while (true)
                {
                    number++;
                    System.out.println("thread2 "+number);
                    try
                    {
                        Thread.sleep(800);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        t2.start();

    }
}