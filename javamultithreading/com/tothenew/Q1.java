
////Write a program do to demonstrate the use of volatile keyword.

package com.tothenew;

public class Q1 {
    private static volatile int my = 0;

    public static void main(String[] args) {
        FirstThread ft = new FirstThread();
        Thread t1 = new Thread(ft);
        SecondThread st = new SecondThread();
        Thread t2 = new Thread(st);
        t1.start();
        t2.start();
    }

    static class FirstThread implements Runnable {
        @Override
        public void run() {
            int local_value = my;
            while (local_value < 5) {
                if (local_value != my) {
                    System.out.println("change in my " + my);
                    local_value = my;
                }
            }
        }
    }

    static class SecondThread implements Runnable {
        @Override
        public void run() {

            int local_value = my;
            while (my < 5) {
                System.out.println("Incrementing local: " + (local_value + 1));
                my = ++local_value;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
