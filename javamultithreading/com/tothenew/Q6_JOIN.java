
//// Write a program to demonstrate sleep and join methods.


package com.tothenew;

public class Q6_JOIN {
    public static void main(String[] args) throws InterruptedException {
        joinClass jc = new joinClass();
        jc.start();
        jc.join(1000);//control will be given to main thread after jc has completed its execution
        System.out.println("joins after 1000ms");
        System.out.println("this threads name :" + jc.getName());

    }
}

class joinClass extends Thread {
    @Override
    public void run() {
        joinClass jc1 = new joinClass();
        System.out.println("Current thread: " + jc1.getName());
    }
}