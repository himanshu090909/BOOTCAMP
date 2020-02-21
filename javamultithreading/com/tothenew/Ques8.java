
////Write a program to demonstrate the use of semaphore

package com.tothenew;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class Connection {

    private static Connection instance = new Connection();

    private Semaphore sem = new Semaphore(10, true);

    private int connections = 0;

    private Connection() {

    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() {
        try {
            sem.acquire();
        } catch (InterruptedException e1) {

            e1.printStackTrace();
        }

        try {
            doConnect();
        } finally {
            sem.release();
        }
    }

    public void doConnect() {

        synchronized (this) {
            connections++;
            System.out.println("Current connections: " + connections);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        synchronized (this) {
            connections--;
        }

    }
}

// in this Ques8 class will create 20 threads and all thread will try to run the connect() method of the connection
// class at the same time and semaphore will limit the connection to 10
public class Ques8 {

    public static void main(String[] args) throws Exception {


        ExecutorService executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 20; i++) {
            executor.submit(new Runnable() {
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }

        executor.shutdown();

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

}