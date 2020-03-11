
package com.example.Restdemo2.DaoService;

import com.example.Restdemo2.ModelClasses.User;
import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Component
@ApiModel(description = "user service controller")
public class UserService
{
    private static List<User> users = new ArrayList<>();
    static
    {
        users.add(new User(1,"himanshu"));
        users.add(new User(2,"ankit"));
        users.add(new User(3,"qwer"));
        users.add(new User(4,"yuio"));
    }
    public List<User> getAllUsers()
    {
        return users;
    }
    public User getOneUser(int id)
    {
        for (User user : users)
        {
            if (user.getId()==id)
            {
                return user;
            }
        }
        return null;
    }
    public User addUser(User user)
    {
       users.add(user);
       return user;

    }
    public User deleteUser(int id)
    {
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext())
        {
            User user = userIterator.next();
            if (user.getId()==id)
            {
                userIterator.remove();
                return user;
            }
        }
        return null;

    }
}
