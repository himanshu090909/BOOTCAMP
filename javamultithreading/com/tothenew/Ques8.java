
////Write a program to demonstrate the use of semaphore

package com.tothenew;

import java.util.concurrent.Semaphore;

class B implements Runnable
{
    Semaphore s = new Semaphore(2);
    public void run()
    {
        try {
            s.acquire();
            System.out.println(Thread.currentThread().getName()+" has acquired the semaphore");
            for (int i=0;i<5;i++)
            {
                System.out.println(Thread.currentThread().getName()+" is running");
                Thread.sleep(1000);
            }
            System.out.println(Thread.currentThread().getName()+" is releasing the semaphore");
            s.release();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
public class Ques8
{
    public static void main(String[] args) {
        B b = new B();
        Thread t1 = new Thread(b);
        Thread t2 = new Thread(b);
        Thread t3 = new Thread(b);
        t1.start();
        t2.start();
        t3.start();

    }
}


