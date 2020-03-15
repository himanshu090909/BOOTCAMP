JPQL: 
classes used: com.jpa2.jpa2project.entities.Employee
repository: com.jpa2.jpa2project.repositories.EmployeeRepository
controller: com.jpa2.jpa2project.controller.EmployeeController

ques1)Display the first name, last name of all employees having salary greater than average salary ordered in ascending by their age and in descending by their salary.
Sol)  com/jpa2/jpa2project/repositories/EmployeeRepository.java:16
          com/jpa2/jpa2project/controller/EmployeeController.java:37
          hit this url: http://localhost:8080/jpqlques1

Ques2)Update salary of all employees by a salary passed as a parameter whose existing salary is less than the average salary.
Sol)  com/jpa2/jpa2project/repositories/EmployeeRepository.java:23
      com/jpa2/jpa2project/controller/EmployeeController.java:49
      hit this url: http://localhost:8080/jpqlques2
      
Ques3)Delete all employees with minimum salary.
Sol)  com/jpa2/jpa2project/repositories/EmployeeRepository.java:31
      com/jpa2/jpa2project/controller/EmployeeController.java:57
      hit this url: http://localhost:8080/jpqlques3
      
Native SQL:
classes used: com.jpa2.jpa2project.entities.Employee
repository: com.jpa2.jpa2project.repositories.EmployeeRepository
controller: com.jpa2.jpa2project.controller.EmployeeController

Ques1)Display the id, first name, age of all employees where last name ends with "singh"
Sol)  com/jpa2/jpa2project/repositories/EmployeeRepository.java:35
      com/jpa2/jpa2project/controller/EmployeeController.java:64
      hit this url: http://localhost:8080/nativeques1
 
Ques2)Delete all employees with age greater than 45(Should be passed as a parameter)
Sol)  com/jpa2/jpa2project/repositories/EmployeeRepository.java:40
      com/jpa2/jpa2project/controller/EmployeeController.java:73
      hit this url: http://localhost:8080/nativeques2
      
Inheritance Mapping:

Ques1)Implement and demonstrate Single Table strategy.
Sol)  classes used:
      com.jpa2.jpa2project.entities.Person
      com.jpa2.jpa2project.entities.Student
      com.jpa2.jpa2project.entities.Doctor
      Repository:
      com.jpa2.jpa2project.repositories.PersonRepository
      Controller:
      com.jpa2.jpa2project.controller.SIngleTableController
      hit this url:http://localhost:8080/singletableinheritance
  
Ques2)Implement and demonstrate Joined strategy.
Sol) classes used:
     com.jpa2.jpa2project.entities.Personjoined
     com.jpa2.jpa2project.entities.Studentjoined
     com.jpa2.jpa2project.entities.Doctorjoined
     Repository:
     com.jpa2.jpa2project.repositories.PersonjoinedRepository
     Controller:
     com.jpa2.jpa2project.controller.JoinedTableController
     hit this url:http://localhost:8080/joinedtableinheritance
     
Ques3)Implement and demonstrate Table Per Class strategy.
Sol) classes used:
     com.jpa2.jpa2project.entities.Personperclass
     com.jpa2.jpa2project.entities.Studentperclass
     com.jpa2.jpa2project.entities.Doctorperclass
     Repository:
     com.jpa2.jpa2project.repositories.PersonperclassRepository
     Controller:
     com.jpa2.jpa2project.controller.TablePerClassController
     hit this url:http://localhost:8080/tableperclassinheritance
     
Component Mapping:
Ques1)Implement and demonstrate Embedded mapping using employee table having following fields: id, firstName, lastName, age, basicSalary, bonusSalary, taxAmount, specialAllowanceSalary.
Sol)  classes used: 
      com.jpa2.jpa2project.entities.Embeddedemployee
      com.jpa2.jpa2project.entities.Salary
      Repository:
      com.jpa2.jpa2project.repositories.EmbeddedEmployeeRepository
      Controller:
      com.jpa2.jpa2project.controller.EmbeddedEMployeeController
      hit this url:http://localhost:8080/embeddeddemo
          
