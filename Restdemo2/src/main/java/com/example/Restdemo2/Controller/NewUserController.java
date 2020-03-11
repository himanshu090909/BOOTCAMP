package com.example.Restdemo2.Controller;

import com.example.Restdemo2.DaoService.NewUserService;
import com.example.Restdemo2.ModelClasses.NewUser;
import com.example.Restdemo2.ModelClasses.User;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewUserController {
    @Autowired
    private NewUserService newUserService;

    @ApiOperation(value = "details of a specific newuser and password is excluded ")
    @GetMapping("/newusers/{id}")
    public MappingJacksonValue t(@PathVariable int id) {
        NewUser newUser = newUserService.getOneUser(id);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept( "id","name");
        FilterProvider filters = new SimpleFilterProvider().addFilter("filter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(newUser);
        mapping.setFilters(filters);
        return mapping;
    }

    @ApiOperation(value = "details of all newusers and password is excluded")
    @GetMapping("/newusers")
    public MappingJacksonValue q()
    {
        List<NewUser> list = newUserService.getAllUsers();
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name");
        FilterProvider filters = new SimpleFilterProvider().addFilter("filter",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);
        return mapping;
    }

    @ApiOperation(value = "for creating a newuser")
    @PostMapping("/newusers")
    public void createUser(@RequestBody NewUser user)
    {
        NewUser user1 = newUserService.addUser(user);
    }




}
