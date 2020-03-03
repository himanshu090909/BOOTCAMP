Ques1 :- Write a program to demonstrate Tightly Coupled code.
Solution:- com/tothenew/bootcamp/springProject/TightCouplingExample.java:3

Ques2 :- Write a program to demonstrate Loosely Coupled code.
Solution:- com.tothenew.bootcamp.springProject.LooseCouplingExample

Ques3 :-  Use @Compenent and @Autowired annotations to in Loosely Coupled code for dependency management
Solution:- classes with @Component- 
           1) com/tothenew/bootcamp/springProject/HR.java:8
           2) com/tothenew/bootcamp/springProject/Tech.java:7
           
           Employee class have a constructor with @Autowired Annotation
           3) com/tothenew/bootcamp/springProject/Employee.java:24

Ques4 :-  Get a Spring Bean from application context and display its properties.
Solution:- from line 10th to 14th i am using application context to get the properties of bean
           com/tothenew/bootcamp/springProject/SpringProjectApplication.java:12                    

Ques5 :- Demonstrate how you will resolve ambiguity while autowiring bean (Hint : @Primary)
Solution:- HR and Tech both have @component so ambiguity occurs there so to solve this we use 
           @primary so whenever there is a request one with @primary will be returned. I have made HR
           primary
           1) com/tothenew/bootcamp/springProject/HR.java:8
           2) com/tothenew/bootcamp/springProject/Tech.java:7
           
Ques6 :- Perform Constructor Injection in a Spring Bean
Solution:- I have used constructor dependency injection here
           com/tothenew/bootcamp/springProject/Employee.java:25