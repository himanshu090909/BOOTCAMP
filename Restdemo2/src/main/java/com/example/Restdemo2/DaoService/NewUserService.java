package com.example.Restdemo2.DaoService;

import com.example.Restdemo2.ModelClasses.NewUser;
import com.example.Restdemo2.ModelClasses.User;
import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Component
@ApiModel(description = "new user service controller")
public class NewUserService
{
     static List<NewUser> users1 = new ArrayList<>();
    static
    {
        users1.add(new NewUser(1,"himanshu",12));
        users1.add(new NewUser(2,"ankit",23));
        users1.add(new NewUser(3,"qwer",34));
        users1.add(new NewUser(4,"yuio",45));
    }

    public List<NewUser> getAllUsers()
    {
        return users1;
    }

    public NewUser addUser(NewUser user)
    {
        users1.add(user);
        return user;
    }
    public NewUser getOneUser(int id)
    {
        for (NewUser user : users1)
        {
            if (user.getId()==id)
            {
                return user;
            }
        }
        return null;
    }
}
