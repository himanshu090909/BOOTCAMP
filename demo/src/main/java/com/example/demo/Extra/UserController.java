package com.example.demo.Extra;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
//import org.springframework.hateoas.EntityModel;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;
@RestController
public class UserController
{
    @Autowired
    private UserDao userDao;
    User user;
    @GetMapping("/users")
    public List<User> users()
    {
        return userDao.findAll();
    }
    @PostMapping("/users")
    public void createUser(@RequestBody User user)
    {
        User user1 = userDao.addUser(user);
    }
/*
    @GetMapping("/users/{id}")
*/
/*
    public EntityModel<User> getUser(@PathVariable int id)
    {
          User user   =     userDao.findOne(id);
          EntityModel<User> model = new EntityModel<>(user);
          WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).users());

          model.add(linkTo.withRel("all users"));
          return model;
    }
*/

}
