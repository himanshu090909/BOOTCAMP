package Java8;
class EmployeeQ4 {
    String name;
    int age;
    String city;

    EmployeeQ4(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    EmployeeQ4 getObj() {
        return this;
    }

    @Override
    public String toString() {
        return "EmployeeQ4{" + "name='" + name + '\'' + ", age=" + age + ", city='" + city + '\'' + '}';
    }


}
interface EmployeeDetails {
    EmployeeQ4 employeeDetails();
}
public class Q4
{
    public static void main(String[] args)
    {
        EmployeeDetails  employeeDetails = new EmployeeQ4("himanshu",23,"delhi")::getObj;
        System.out.println(employeeDetails.employeeDetails());

    }
}


