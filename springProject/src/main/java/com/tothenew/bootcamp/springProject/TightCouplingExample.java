package com.tothenew.bootcamp.springProject;

public class TightCouplingExample {
    public static void main(String[] args) {
        Employeee e = new Employeee();
        e.setDepartment();
    }
}

class Employeee {
    HRDepartment hr = new HRDepartment();
    TechnicalDepartment technicalDepartment = new TechnicalDepartment();

    public void setDepartment() {
        hr.show();
    }
}


/*
interface Worker {
    public void work();
}
*/


class HRDepartment {
    public void show() {
        System.out.println("in HR Department");
    }

}

class TechnicalDepartment {
    public void show() {
        System.out.println("in Technical Department");
    }
}
