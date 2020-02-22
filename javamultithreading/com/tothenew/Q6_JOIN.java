
////// Write a program to demonstrate sleep and join methods.
package com.tothenew;

public class Q6_JOIN {
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i);
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        try {
            t1.join();     //if we dont use join then control will be give to main thread while t1 has not completed its task
                           //so when we use join then main will wait for t1 to complete and then "in main" will get printed
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("in main");
    }
}