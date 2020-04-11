package com.ttn.ecommerceApplication.ecommerceApplication.daoImpl;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.AdminDao;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.User;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.UserNotFoundException;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.ProductRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminDaoImpl implements AdminDao
{
    private JavaMailSender javaMailSender;

    @Autowired
    public AdminDaoImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Async
    public void activateCustomerAndSeller(Long id)
    {
        User user1 = null;
        String message;
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent())
        {
            user1 = user.get();
            if (user1.isEnabled()==true)
            {
                message = "user account is already activated";
            }
            else
            {
                user1.setEnabled(true);
                System.out.println("Sending email for account activation");
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(user1.getEmail());
                mail.setFrom("hs631443@gmail.com");
                mail.setSubject("Regarding account activation");
                mail.setText("your account has been activated by admin you can now login");
                System.out.println("now starting");
                javaMailSender.send(mail);
                userRepository.save(user1);
                System.out.println("Email Sent!");
                message = "your account has been activated";
            }
        }
        else
        {
            throw new UserNotFoundException("user with this id is not present");
        }
        System.out.println("message is"+message);
    }

    @Async
    public String  deActivateCustomerAndSeller(Long id)
    {
        User user1 = null;
        String message = null;
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
        {
            user1 = user.get();
            if (user1.isEnabled()==false)
            {
                message = "user account is already deactivated";
            }
            else
            {
                user1.setEnabled(false);
                userRepository.save(user1);
                System.out.println("Sending email...");
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(user1.getEmail());
                mail.setFrom("hs631443@gmail.com");
                mail.setSubject("Regarding account deactivation");
                mail.setText("your account has been deactivated by admin you can not login now");
                javaMailSender.send(mail);
                System.out.println("Email Sent!");
                message = "your account has been activated";
            }
        }
        else
        {
            System.out.println("user with this id is not present");
            throw new RuntimeException();
        }
        return message;
    }

    @Async
    public String lockUser(Long id)
    {
        User user1 = null;
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
        {
            user1 = user.get();
            if (user1.isAccountNonLocked()==false)
            {
                return "user account is already locked";
            }
            else
            {
                user1.setAccountNonLocked(false);
                userRepository.save(user1);
                System.out.println("Sending email...");
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(user1.getEmail());
                mail.setFrom("hs631443@gmail.com");
                mail.setSubject("Regarding account status");
                mail.setText("your account has been locked by admin you can not login now");
                javaMailSender.send(mail);
                System.out.println("Email Sent!");
                return "account has been locked";
            }
        }
        else
        {
            System.out.println("user with this id is not present");
            throw new RuntimeException();
        }

    }

    @Async
    public String unlockUser(Long id)
    {
        User user1 = null;
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
        {
            user1 = user.get();
            if (user1.isAccountNonLocked()==true)
            {
                return "user account is already unlocked";
            }
            else
            {
                user1.setAccountNonLocked(true);
                userRepository.save(user1);
                System.out.println("Sending email...");
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(user1.getEmail());
                mail.setFrom("hs631443@gmail.com");
                mail.setSubject("Regarding account status");
                mail.setText("your account has been unlocked by admin you can login now");
                javaMailSender.send(mail);
                System.out.println("Email Sent!");
                return "account has been locked";
            }
        }
        else
        {
            System.out.println("user with this id is not present");
            throw new RuntimeException();
        }

    }

    public List<Object[]> getAllProducts()
    {
        return productRepository.getAllProducts();
    }

}
