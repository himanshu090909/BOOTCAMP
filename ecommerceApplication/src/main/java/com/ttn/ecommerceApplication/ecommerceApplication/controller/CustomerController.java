package com.ttn.ecommerceApplication.ecommerceApplication.controller;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.CustomerDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.ProductDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.UploadDao;;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.ProfileDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Customer;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Seller;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.CustomerRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.GetCurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class CustomerController
{
     @Autowired
     CustomerDao customerDao;

     @Autowired
     GetCurrentUser getCurrentUser;

     @Autowired
     CustomerRepository customerRepository;

     @Autowired
     UploadDao  uploadDao;

     @Autowired
     ProductDao productDao;

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

     @GetMapping("/viewProfile")
     public List<Object[]> viewProfile(HttpServletRequest request) throws IOException
     {
          return customerDao.viewProfile();
     }

     @PostMapping("/uploadProfilePic")
     public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException
     {
          String username = getCurrentUser.getUser();
          Customer customer = customerRepository.findByUsername(username);
          return uploadDao.uploadSingleImage(file,customer);
     }

     @GetMapping("/viewProfileImage")
     public ResponseEntity<Object> viewProfileImage(HttpServletRequest request) throws IOException {
          String username = getCurrentUser.getUser();
          Customer customer = customerRepository.findByUsername(username);
          String filename = customer.getId().toString();
          System.out.println(filename);
          return uploadDao.downloadImage(filename,request);
     }

     @PutMapping("/updateProfile")
     public String updateProfile(@RequestBody ProfileDTO customer)
     {
        return customerDao.updateProfile(customer);
     }

     @GetMapping("/viewProduct/{product_id}")
     public List<Object[]> viewProduct(@PathVariable Long product_id)
     {
          return customerDao.viewProduct(product_id);
     }



}
