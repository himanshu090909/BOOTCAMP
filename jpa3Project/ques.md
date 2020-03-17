1) Create a class Address for Author with instance variables streetNumber, location, State.
Solution) classes used:
          com.jpa3.jpa3Project.entities.Author
          com.jpa3.jpa3Project.entities.Address
          
2) Create instance variable of Address class inside Author class and save it as embedded object.
Solution) com/jpa3/jpa3Project/entities/Author.java:14
          com/jpa3/jpa3Project/controller/AuthorController.java:20
          com/jpa3/jpa3Project/dao/AuthorDao.java:16
          now address will be stored in author table
          
3) Introduce a List of subjects for author.
Solution) classes used: 
          com.jpa3.jpa3Project.entities.Author
          com.jpa3.jpa3Project.entities.Subject
          there is a OneToMany mapping between author and subject
          
4) Persist 3 subjects for each author.
Solution) com/jpa3/jpa3Project/controller/AuthorController.java:20
          com/jpa3/jpa3Project/dao/AuthorDao.java:16
          
5) Create an Entity book with an instance variable bookName.
Solution) com.jpa3.jpa3Project.entities.Book
          
6) Implement One to One mapping between Author and Book.
Solution) com.jpa3.jpa3Project.entities.Author
          com.jpa3.jpa3Project.entities.Book
          com/jpa3/jpa3Project/controller/AuthorController.java:20
          com/jpa3/jpa3Project/dao/AuthorDao.java:16
author and book two tables will be created and author will have an column that will store book_id of book related to that particular author.
         
7) Implement One to Many Mapping between Author and Book(Unidirectional, BiDirectional and without additional table ) and implement cascade save.
Solution) For unidirectional:
          classes used: 
          com.jpa3.jpa3Project.entities.AuthorUnidirectional
          com.jpa3.jpa3Project.entities.BookUnidirectional
          Repository;
          com.jpa3.jpa3Project.repositories.AuthorUnidirectionalRepository
          Controller:
          com.jpa3.jpa3Project.controller.UnidirectionalController
          
          In this 3 tables will be created author_unidirectional,book_unidirectional and one mapping table author_unidirectional_book_unidirectionals
          
          For bidirectional:
          classes used:
          com.jpa3.jpa3Project.entities.AuthorBidirectional
          com.jpa3.jpa3Project.entities.BookBidirectional
          Repository:
          com.jpa3.jpa3Project.repositories.AuthorBidirectionalRepository
          Controller:
          com.jpa3.jpa3Project.controller.BidirectionalController
          
          In this 3 tables will be created author_bidirectional,book_bidirectional and one mapping table author_bidirectional_book_bidirectionals
          
          For bidirectional without extra table:
          classes used:
          com.jpa3.jpa3Project.entities.AuthorWithoutTable
          com.jpa3.jpa3Project.entities.BookWithoutTable
          Repository:
          com.jpa3.jpa3Project.repositories.AuthorWithoutTableRepository
          Controller:
          com.jpa3.jpa3Project.controller.WithoutTableController
          
          In this only two tables will be created author_without_table,book_without_table
          book_without_table will contain an extra column named author_book
            
8)Implement Many to Many Mapping between Author and Book.
Solution) classes used;
          com.jpa3.jpa3Project.entities.AuthorManyToMany
          com.jpa3.jpa3Project.entities.BookManyToMany
          Repository:
          com.jpa3.jpa3Project.repositories.AuthorManyToManyRepository
          Controller:
          com.jpa3.jpa3Project.controller.ManyToManyController
          
          In this 3 tables will be created author_many_to_many,book_many_to_many and one mapping table author_books
         
9) Which method on the session object can be used to remove an object from the cache?
Solution) evict() method is used to remove an object from the cache.
          Steps to remove object from the cache:
          1) add instance of EntityManager which is a JPA object
             @Autowired
             EntitiyManager entityManager;
          2) using entityManager we can get access to the session using
             Session session = entityManager.unwrap(Session.class);
          3) Now, use
             session.evict(objectname) to remove this object from the cache.
               
           classes used:
           com.jpa3.jpa3Project.entities.Caching
           Repository:
           com.jpa3.jpa3Project.repositories.CachingRepository
           Controller:
           com.jpa3.jpa3Project.controller.CachingController
           
10) What does @transactional annotation do?
Solution) 1) By using @transactional spring handles lazy loading problem if not used we will get an exception
          2) @transactional is used in transaction management if not used we have to handle everything but by using transactional spring makes sure that acid property is followed
          3) @transactional is used when we perform dml queries 