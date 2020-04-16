package com.ttn.ecommerceApplication.ecommerceApplication.controller;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.AdminDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.RegisteredCustomerDto;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.RegisteredCustomersDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.RegisteredSellersDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.UserNotFoundException;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminDao adminDao;

    @ApiOperation(value = "uri in which admin can activate a user")
    @PutMapping("/activate/{user_id}")
    public void activateUser(@PathVariable(name = "user_id") Long user_id) {
        adminDao.activateCustomerAndSeller(user_id);
        }

    @ApiOperation(value = "uri in which admin can deactivate a user")
    @PutMapping("/deactivate/{user_id}")
    public String  deactivateUser(@PathVariable(name = "user_id") Long user_id) {
        return adminDao.deActivateCustomerAndSeller(user_id);
    }

    @ApiOperation(value = "uri in which admin can lock an user account")
    @PutMapping("/lock/{user_id}")
    public String lockUser(@PathVariable(name = "user_id") Long user_id)
    {
        return adminDao.lockUser(user_id);
    }

    @ApiOperation(value = "uri in which admin can unlock an user account")
    @PutMapping("/unlock/{user_id}")
    public String unlockUser(@PathVariable(name = "user_id") Long user_id)
    {
        return adminDao.unlockUser(user_id);
    }

    @ApiOperation(value = "uri to get all products")
    @GetMapping("/getAllProducts")
    public List<Object[]> getAllProducts()
    {
        return adminDao.getAllProducts();
    }




    @ApiOperation(value = "uri in which admin can view all the registered customers")
    @GetMapping("/allCustomers")
    public List<RegisteredCustomersDTO> getAllCustomers(@RequestParam(name = "pageNo", required = true, defaultValue = "0") Integer pageNo,
                                                          @RequestParam(name = "pageSize", required = true, defaultValue = "10") Integer pageSize,
                                                          @RequestParam(name = "sortBy", defaultValue = "id") String sortBy)
    {
       return adminDao.getAllRegisteredCustomers(pageNo, pageSize, sortBy);
    }

    @ApiOperation(value = "uri in which admin can view all the registered sellers")
    @GetMapping("/allSellers")
    public List<RegisteredSellersDTO> getAllSellers(@RequestParam(name = "pageNo", required = true, defaultValue = "0") Integer pageNo,
                                                    @RequestParam(name = "pageSize", required = true, defaultValue = "10") Integer pageSize,
                                                    @RequestParam(name = "sortBy", defaultValue = "id") String sortBy)
    {
        return adminDao.getAllRegisteredSellers(pageNo, pageSize, sortBy);

           }

}
