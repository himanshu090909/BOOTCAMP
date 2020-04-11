package com.ttn.ecommerceApplication.ecommerceApplication.dao;

import com.ttn.ecommerceApplication.ecommerceApplication.dto.CustomerDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.ProfileDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Customer;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Seller;

import java.util.List;

public interface CustomerDao
{
    public List<Object[]> getCustomerDetails();
    public Customer getCustomer();
    public String getAnSellerAccount(Seller seller);
    public String returnRequested(Long orderStatusId);
    public String  cancelOrder(Long orderStatusId);
    String editContact(Customer customer);
    List<Object[]> getAddresses();
    public List<Object[]> viewProfile();
    public String updateProfile(ProfileDTO customer);

}

