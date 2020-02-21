
////Write a program using synchronization block and synchronization method

package com.tothenew;

public class Q3_block {
    public static int i=0;
    public static void main(String[] args) {
        Block obj=new Block();
        TBlock t1=new TBlock(obj);
        TBlock t2=new TBlock(obj);
        t1.start();
        t2.start();

    }
}
class Block {
    void Synching(){
        synchronized (this) {
            for(Q3_block.i=0; Q3_block.i<10; Q3_block.i++){
                System.out.println("i= "+ Q3_block.i);
            }
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class TBlock extends Thread{
    Block sync;
    TBlock(Block sync){
        this.sync=sync;
    }
    public void run(){
        sync.Synching();
    }
}