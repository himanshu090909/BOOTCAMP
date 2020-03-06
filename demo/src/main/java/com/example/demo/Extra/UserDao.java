package com.example.demo.Extra;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao
{
    private static List<User> users = new ArrayList<>();
    static
    {
        users.add(new User(1,"himanshu"));
        users.add(new User(2,"ankit"));
    }
    public User addUser(User user)
    {

         users.add(user);
         return user;
    }
    public List<User> findAll()
    {
        return users;
    }
    public User findOne(int id)
    {
        for (User user : users )
        {
            if(user.getId()==id)
                return user;

        }
        return null;
    }

}
