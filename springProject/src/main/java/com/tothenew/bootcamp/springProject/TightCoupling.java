package com.tothenew.bootcamp.springProject;
public class TightCoupling
{
    public static void main(String[] args) {
        new Manager(new LazyWorker()).manageWork();
    }
}
class Manager
{
    private Worker worker;
    public Manager(Worker worker) {
    this.worker=worker;
    }
    public void manageWork()
    {
        worker.work();
    }
}
interface Worker
{
    public void work();
}
class LazyWorker implements Worker
{
    public void work()
    {
        System.out.println("lazy worker is working");
    }

}
class ExceleentWorker implements Worker
{
    public void work()
    {
        System.out.println("exceleent worker is working");
    }
}