
////Write a program to demonstrate wait and notify methods.

package com.tothenew;

class A {
    int number;
    boolean val = false;

    public synchronized void set(int number) {
        while (val) {
            try { wait(); } catch (Exception e) { e.printStackTrace(); }
        }
        System.out.println("thread 1 printing " + number);
        this.number = number;
        val = true;
        notify();
    }
    public synchronized void get() {
        while (!val) {
            try { wait(); } catch (Exception e) { e.printStackTrace(); }
        }
        System.out.println("thread 2 printing " + number);
        val = false;
        notify();
    }
}

public class Q5 {
    public static void main(String[] args) {

        A a = new A();
        Thread t1 = new Thread() {
            public void run() {

                for (int i = 0; i < 10; i++) {
                    a.set(i);

                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    a.get();

                }
            }
        };
        t1.start();
        t2.start();

    }
}