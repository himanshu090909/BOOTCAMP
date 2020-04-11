package com.ttn.ecommerceApplication.ecommerceApplication.controller;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.CustomerDao;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Address;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Customer;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Seller;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.User;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.AddressRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.CustomerRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.UserRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.GetCurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController
{
     @Autowired
     CustomerDao customerDao;

     @GetMapping("/detailsOfCustomer")
     public List<Object[]> getDetails()
     {
        List<Object[]> objects = customerDao.getCustomerDetails();
        return objects;
     }

     @PutMapping("/getSellerAccount")
     public String  getAnSellerAccount(@RequestBody Seller seller)
     {
        return customerDao.getAnSellerAccount(seller);
     }

     @Lazy
     @PutMapping("/editContact")
     public String  editContact(@RequestBody Customer customer)
     {
        return customerDao.editContact(customer);
     }

     @GetMapping("/getAddresses")
     public List<Object[]> getAddresses()
     {
       return customerDao.getAddresses();
     }

     @PostMapping("/returnRequested/{orderStatusId}")
     public String returnRequested(@PathVariable Long orderStatusId)
     { return customerDao.returnRequested(orderStatusId); }

     @PostMapping("/cancelOrder/{orderStatusId}")
     public String cancelOrder(@PathVariable Long orderStatusId)
     {
        return customerDao.cancelOrder(orderStatusId);
     }

}
