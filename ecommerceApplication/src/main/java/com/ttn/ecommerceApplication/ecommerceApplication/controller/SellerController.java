package com.ttn.ecommerceApplication.ecommerceApplication.controller;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.SellerDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.SellerDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.SellerProfileDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Customer;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SellerController
{
    @Autowired
    SellerDao sellerDao;

    @GetMapping("/detailsOfSeller")
    public List<Object[]> getDetails()
    {
        List<Object[]> objects = sellerDao.getSellerDetails();
        return objects;
    }

    @PutMapping("/getCustomerAccount")
    public String getAnCustomerAccount(@RequestBody Customer customer)
    {
        return sellerDao.getAnCustomerAccount(customer);
    }

    @Lazy
    @PutMapping("/editSellerDetails")
    public String updateSellerDetails(@RequestBody Seller seller) {
        return sellerDao.editSellerDetails(seller);
    }

    @GetMapping("/viewProfileForSeller")
    public SellerProfileDTO viewProfile()
    {
        return sellerDao.viewProfile();
    }

    @PutMapping("/updateMyProfile")
    public ResponseEntity updateMyProfile(@RequestBody SellerProfileDTO sellerProfileDTO)
    {
        return sellerDao.editMyProfile(sellerProfileDTO);
    }


}
