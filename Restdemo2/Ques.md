Internationalization
    1) Add support for Internationalization in your application allowing messages to be shown in English, German and Swedish, keeping English as default.
       Create a GET request which takes "username" as param and shows a localized message "Hello Username". (Use parameters in message properties)
Solution) messages.properties
          messages_de.properties -> for german
          messages_fr.properties -> for france
          messages_sv.properties -> for sweden
          hit this url-> localhost:8080/i18ndemo/1
          hit this url-> localhost:8080/i18ndemos/1 
 
*Content Negotiation
      3. Create POST Method to create user details which can accept XML for user creation.
      Solution) hit this url -> localhost:8080/users
      4. Create GET Method to fetch the list of users in XML format.
      
      Solution) hit this url -> localhost:8080/users
      select application/xml on postman
      
*Swagger
  Configure swagger plugin and create document of following methods:
             Get details of User using GET request.
             Save details of the user using POST request.
             Delete a user using DELETE request.
  In swagger documentation, add the description of each class and URI so that in swagger UI the purpose of class                  and URI is clear.

Solution-> hit this url -> localhost:8080/swagger-ui.html
           classes: com.example.Restdemo2.Configuration.SwaggerConfig

*Static and Dynamic filtering
       Create API which saves details of User (along with the password) but on successfully saving returns only non-critical data. (Use static filtering)
       Create another API that does the same by using Dynamic Filtering.
       
       classes: com.example.Restdemo2.ModelClasses.NewUser
       Controller: com.example.Restdemo2.Controller.NewUserController
       DaoService: com.example.Restdemo2.DaoService.NewUserService
       
*Versioning Restful APIs
  Create 2 API for showing user details. The first api should return only basic details of the user and the other API should return more/enhanced details of the user,
  Now apply versioning using the following methods:
  MimeType Versioning
  Request Parameter versioning
  URI versioning
  Custom Header Versioning
  
  Solution) classes: com.example.Restdemo2.ModelClasses.UserVersion1
                     com.example.Restdemo2.ModelClasses.UserVersion2
            Controller: com.example.Restdemo2.Controller.UserVersionController
            
*HATEOAS
 Configure hateoas with your springboot application. Create an api which returns User Details along with url to show all topics.
 
 Solution) com/example/Restdemo2/Controller/UserController.java:37
           hit this url-> http://localhost:8080/users/1