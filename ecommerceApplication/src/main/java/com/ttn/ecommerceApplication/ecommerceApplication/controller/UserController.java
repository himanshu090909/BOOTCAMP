package com.ttn.ecommerceApplication.ecommerceApplication.controller;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.UserDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.UserDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Address;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.User;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.CustomerRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.UserRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.GetCurrentUser;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDTO userDTO;

    @Autowired
    UserDao userDao;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GetCurrentUser getCurrentUser;

    @Autowired
    CustomerRepository customerRepository;

  /*  @PutMapping("/updateAddress")
    public void update(@RequestBody Address address)
    {
        String username = getCurrentDetails.getUser();
        User user=userRepository.findByUsername(username);
        user.addAddress(address);
        userRepository.save(user);
    }*/
/*
    @Transactional
    @PutMapping("/updateProfile/{id}")
    @ResponseBody
    public ResponseEntity<Object> updateProfile(@RequestBody Customer customer, @PathVariable(name = "id") int id)
    {

        Optional<Customer> optionalUser = customerRepository.findById(id);

       Customer customer1=  optionalUser.get();
       customer1.setFirstName(customer.getFirstName());
       customer1.setLastName(customer.getLastName());
       customer1.setMiddleName(customer.getMiddleName());
       customer1.setEmail(customer.getEmail());
       customer1.setPassword(customer.getPassword());
       customer1.setLastName(customer.getLastName());
        customer1.setContact(customer.getContact());
       userRepository.save(customer1);
       URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id")
                .buildAndExpand(customer1.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }*/
    @DeleteMapping("/deleteUser")
    public String deleteUser() {

        return userDao.deleteUser();
    }

    @PutMapping("/addNewAddress")
    public String addNewAddress(@Valid @RequestBody Address address) {

        return userDao.addNewAddress(address);
    }

    @Lazy
    @PutMapping("/editUsername")
    public String editUsername(@RequestBody UserDTO user) {
        return userDao.editUsername(user);
    }

    @Lazy
    @PutMapping("/editEmail")
    public String editEmail(@RequestBody UserDTO user)
    {
        return userDao.editEmail(user);
    }

    @Lazy
    @PutMapping("/editEmail/{token}")
    public String SetNewEmail(@RequestBody UserDTO user,@PathVariable String token)
    {
        return userDao.verifyNewEmail(token,user);
    }
    @PutMapping("/editPassword")
    public String editPassword(@RequestBody UserDTO user) {
        return userDao.editPassword(user);
    }

    @PutMapping("/updateAddress/{addressId}")
    public String  update(@Valid @RequestBody Address address, @PathVariable Long addressId) {
        return userDao.update(address, addressId);
    }
}