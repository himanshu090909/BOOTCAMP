package com.ttn.ecommerceApplication.ecommerceApplication.entities;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.UserAttemptsRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class CustomEventListener
{

    @Autowired
    UserAttemptsRepository userAttemptsRepository;

    @Autowired
    UserRepository userRepository;

    @EventListener
    public void AuthenticationFailEvent(AuthenticationFailureBadCredentialsEvent event)
    {
        String username = event.getAuthentication().getPrincipal().toString();
        Iterable<UserAttempts> userAttempts = userAttemptsRepository.findAll();
        int count=0;
        for (UserAttempts userAttempts1 : userAttempts)
        {
            if (userAttempts1.getEmail().equals(username))
            {
                if (userAttempts1.getAttempts()>=2)
                {
                    User user = userRepository.findByUsername(username);
                    user.setAccountNonLocked(false);
                    userRepository.save(user);
                }
                else {
                    userAttempts1.setAttempts(userAttempts1.getAttempts() + 1);
                }
            }
        }
        if (count==0)
        {
            UserAttempts userAttempts1 = new UserAttempts();
            User user = userRepository.findByUsername(username);
            userAttempts1.setEmail(user.getUsername());
            userAttempts1.setAttempts(1);
        }
    }

   /* @EventListener
    public void AuthenticationPass(AuthenticationSuccessEvent event)
    {
        LinkedHashMap<String ,String > hashMap = (LinkedHashMap<String, String>) event.getAuthentication().getDetails();
   *//*     Iterable<UserAttempts> userAttempts = userAttemptsRepository.findAll();
       try {

           for (UserAttempts userAttempts1 : userAttempts)
           {
               if (userAttempts1.getEmail().equals(hashMap.get("username")))
               {
                      userAttempts1.setAttempts(0);
                      userAttemptsRepository.save(userAttempts1);
               }
           }
       }
       catch (Exception e)
       {

       }
    }*/








}
