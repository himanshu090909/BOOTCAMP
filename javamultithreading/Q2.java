
////Write a program to create a thread using Thread class and Runnable interface each.

package com.javaMultithreading;

class MyThread extends Thread
{
    public void run()
    {
        System.out.println("thread created by extending Thread Class");

    }
}
class Mythread implements Runnable
        {
            public void run()
            {
                System.out.println("thread created by implementing runnable interface");

            }

        }

public class Q2
{
    public static void main(String[] args)
    {
        MyThread thread = new MyThread();
        Mythread thread1 = new Mythread();
        Thread t1 = new Thread(thread1);
        thread.start();
        t1.start();

    }

}
