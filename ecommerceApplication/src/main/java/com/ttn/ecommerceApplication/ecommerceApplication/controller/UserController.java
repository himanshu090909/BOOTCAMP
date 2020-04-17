package com.ttn.ecommerceApplication.ecommerceApplication.controller;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.UserDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.AddressDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.UserDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.CustomerRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.UserRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.GetCurrentUser;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("uri for user to update his password")
    @PutMapping("/editPassword")
    public String editPassword(@RequestBody UserDTO user) {
        return userDao.editPassword(user);
    }

    @ApiOperation("uri for user to update an address")
    @PutMapping("/updateAddress/{addressId}")
    public String  update(@Valid @RequestBody AddressDTO address, @PathVariable Long addressId) {
        return userDao.update(address, addressId);
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

    @DeleteMapping("/deleteUser")
    public String deleteUser() {

        return userDao.deleteUser();
    }


}