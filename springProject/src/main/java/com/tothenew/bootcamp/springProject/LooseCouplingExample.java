package com.tothenew.bootcamp.springProject;
public class LooseCouplingExample
{
    public static void main(String[] args)
    {
        new Emp(new HRDepartments()).manageDepartment();
    }
}
class Emp
{
  Departments departments;
  public Emp(Departments departments)
  {
      this.departments = departments;
  }
  public void manageDepartment()
  {
      departments.show();
  }

}
interface Departments
{
    public void show();
}
class HRDepartments implements Departments{
    public void show() {
        System.out.println("in HR Department");
    }

}
class TechnicalDepartments implements  Departments{
    public void show() {
        System.out.println("in Technical Department");
    }
}