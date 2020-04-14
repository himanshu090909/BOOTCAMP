package com.ttn.ecommerceApplication.ecommerceApplication.daoImpl;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.RegistrationDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.CustomerDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.SellerDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Customer;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Role;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Seller;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.PasswordAndConfirmPasswordMismatchException;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.UserRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationDaoImpl implements RegistrationDao
{
    @Autowired
    UserRepository userRepository;

    private JavaMailSender javaMailSender;

    @Autowired
    public RegistrationDaoImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Lazy
    @Autowired
    NotificationService notificationService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper modelMapper;


    public String createCustomer(CustomerDTO customer) {

        if(customer.getPassword().equals(customer.getConfirmPassword()))
        {
            Customer customer1= modelMapper.map(customer,Customer.class);
            String password = customer.getPassword();
            customer1.setPassword(new BCryptPasswordEncoder().encode(password));
            customer1.addRoles(new Role("ROLE_CUSTOMER"));
            customer1.setCreatedBy(customer1.getUsername());
            userRepository.save(customer1);
            if ( userRepository.existsById(customer1.getId()))
            {
                notificationService.sendNotificaitoin(customer1);
            }
            return "success";
        }
        else
        {
            throw new PasswordAndConfirmPasswordMismatchException("password and confirm password does not match");
        }

    }

    @Async
    public String createSeller(SellerDTO seller)
    {
        if (seller.getPassword().equals(seller.getConfirmPassword())) {
            Seller seller1 = modelMapper.map(seller, Seller.class);
            String password = seller.getPassword();
            seller1.setPassword(passwordEncoder.encode(password));
            seller1.addRoles(new Role("ROLE_SELLER"));
            seller1.setCreatedBy(seller.getUsername());
            userRepository.save(seller1);
            if (userRepository.existsById(seller1.getId())) {
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(seller.getUsername());
                mail.setFrom("bhatipinki056@gmail.com");
                mail.setSubject("Regarding account activation");
                mail.setText("you account has been created you can access it once admin verifies it");
                javaMailSender.send(mail);
            }
            return "success";

        }
        else {
            throw  new PasswordAndConfirmPasswordMismatchException("Both passwords are not same");
        }

    }


}
