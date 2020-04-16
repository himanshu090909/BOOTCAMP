package com.ttn.ecommerceApplication.ecommerceApplication.dao;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.RegisteredCustomersDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.RegisteredSellersDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.User;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.Success;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.UserNotFoundException;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.ProductRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminDao
{

    public void activateCustomerAndSeller(Long id);
    public String  deActivateCustomerAndSeller(Long id);
    public String lockUser(Long id);
    public String unlockUser(Long id);
    public List<Object[]> getAllProducts();
    public List<RegisteredCustomersDTO> getAllRegisteredCustomers(Integer pageNo, Integer pageSize, String sortBy);

    public List<RegisteredSellersDTO> getAllRegisteredSellers(Integer pageNo, Integer pageSize, String sortBy);

}
