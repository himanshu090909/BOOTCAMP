package com.example.Restdemo2.Controller;
import com.example.Restdemo2.DaoService.UserService;
import com.example.Restdemo2.ModelClasses.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "details of all the users")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @ApiOperation(value = "details of all the users by id")
    @GetMapping("/users/{id}")
    //hateoas implementation
    public EntityModel<User> getUser(@PathVariable int id) {
        User user = userService.getOneUser(id);
        EntityModel<User> model = new EntityModel<>(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
        model.add(linkTo.withRel("all users"));
        return model;
    }

    @ApiOperation(value = "create a new user")
    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        User user1 = userService.addUser(user);
    }

    @ApiOperation(value = "uri for internationalization")
    @GetMapping("/i18ndemo/{id}")
    public String a(@PathVariable int id) {
        User user = userService.getOneUser(id);
        String[] params = {user.getName()};
        return messageSource.getMessage("good.morning.message", params, LocaleContextHolder.getLocale());
    }
    @ApiOperation(value = "uri for internationalization")
    @GetMapping("/i18ndemos/{id}")
    public String b(@PathVariable int id) {
        User user = userService.getOneUser(id);
        String[] params = {user.getName()};
        return messageSource.getMessage("hello.username", params, LocaleContextHolder.getLocale());
    }
    @ApiOperation(value = "for deleting a user by id")
    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable int id) {
        User user = userService.deleteUser(id);
    }
}
