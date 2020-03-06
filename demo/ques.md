Q1) Create a simple REST ful service in Spring Boot which returns the Response "Welcome to spring boot".
Sol) localhost:8080/welcome

Q2) Create an Employee Bean(id, name, age) and service to perform different operations related to employee.
Sol) com.example.demo.ModelClasses.Employees
     com.example.demo.Dao.EmployeeDaoService

Q3) Implement GET http request for Employee to get list of employees.
Sol)  com.example.demo.Controller.EmployeeController
      for result hit this url : localhost:8080/employees
      
Q4) Implement GET http request using path variable top get one employee 
Sol) com/example/demo/Controller/EmployeeController.java:27
     insert an id after employees
     for result hit this url : localhost:8080/employees/id
 
Q5) Implement POST http request for Employee to create a new employee.
Sol) com/example/demo/Controller/EmployeeController.java:37
     hit this url : localhost:8080/employees

Q6) Implement Exception Handling for resource not found
Sol) com.example.demo.ExceptionHandler
     In this i have made generic exception handling
     try hitting a url for add or deletion of an employee by entering a wrong id
     
Q7) Implement DELETE http request for Employee to delete employee
Sol) com/example/demo/Controller/EmployeeController.java:42
     hit this url : localhost:8080/employees/id
      
Q8) Implement PUT http request for Employee to update employee
Sol) com/example/demo/Controller/EmployeeController.java:53
     com/example/demo/Dao/EmployeeDaoService.java:65
     hit this url: localhost:8080/employees/update/id

Q9) Apply validation while create a new employee using POST http Request.
Sol) com.example.demo.ModelClasses.Employees

Q10) Configure actuator in your project to check the health of application and get the information about various beans configured in your application
Sol) hit this url : http://localhost:8080/browser/index.html#/actuator
     
            