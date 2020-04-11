
package com.ttn.ecommerceApplication.ecommerceApplication.daoImpl;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.CustomerDao;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.*;
import com.ttn.ecommerceApplication.ecommerceApplication.enums.FromStatus;
import com.ttn.ecommerceApplication.ecommerceApplication.enums.ToStatus;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.NullException;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.PatternMismatchException;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.UserNotFoundException;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.*;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.GetCurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    GetCurrentUser getCurrentUser;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Override
    public String  returnRequested(Long orderStatusId) {
        Optional<OrderStatus> orderStatusOptional = orderStatusRepository.findById(orderStatusId);
        OrderStatus orderStatus = orderStatusOptional.get();
        if (orderStatus.getToStatus() == ToStatus.CLOSED) {
              throw new NullPointerException("You can not request for return ");
        } else if (orderStatus.getToStatus() == ToStatus.DELIVERED) {
            orderStatus.setFromStatus(FromStatus.RETURN_REQUESTED);
            orderStatusRepository.save(orderStatus);
            return "return request approved";
        }
        else
        {
            throw new NullPointerException("You can not request for return ");
        }
    }

    public String editContact(Customer customer) {
        if (customer.getContactNo().matches("(\\+91|0)[0-9]{10}")) {
            String user = getCurrentUser.getUser();
            Customer user1 = customerRepository.findByUsername(user);
            user1.setContactNo(customer.getContactNo());
            user1.setModifiedBy(user);
            customerRepository.save(user1);
            return "success";
        } else {
            throw new PatternMismatchException("Contact number should start with +91 or 0 and length should be 10");
        }
    }

    public List<Object[]> getAddresses() {
        String username = getCurrentUser.getUser();
        Customer customer = customerRepository.findByUsername(username);
        List<Object[]> list = addressRepository.findAllByUser(customer.getId());
        if (list.isEmpty())
        {
            throw new UserNotFoundException("no addresses found for this user");
        }
        return list;
    }

    @Override
    public String cancelOrder(Long orderStatusId) {
        Optional<OrderStatus> orderStatusOptional = orderStatusRepository.findById(orderStatusId);
        OrderStatus orderStatus = orderStatusOptional.get();

        if (orderStatus.getToStatus() == ToStatus.DELIVERED) {
            throw new NullPointerException("You cant cancel the order.");
        } else {
            orderStatus.setFromStatus(FromStatus.CANCELLED);
            orderStatusRepository.save(orderStatus);
        }
        return "request for cancellation is approved";
    }

    public List<Object[]> getCustomerDetails() {
        String username = getCurrentUser.getUser();
        Customer customer = customerRepository.findByUsername(username);
        List<Object[]> objects = customerRepository.getDetails(username);
        return objects;
    }

    @Override
    public Customer getCustomer() {
        String username = getCurrentUser.getUser();
        Customer customer = customerRepository.findByUsername(username);
        return customer;
    }

    @Transactional
    @Override
    public String getAnSellerAccount(Seller seller) {
        if (seller.getCompanyName() != null && seller.getCompanyContactNo() != null && seller.getGstNo() != null) {
            String username = getCurrentUser.getUser();
            User customer = userRepository.findByUsername(username);
            if (seller.getCompanyContactNo().matches("(\\+91|0)[0-9]{10}")) {
                if (seller.getGstNo().matches("\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1}")) {
                    seller.setId(customer.getId());
                    sellerRepository.insertIntoSeller(seller.getCompanyContactNo(), seller.getCompanyName(), seller.getGstNo(), seller.getId());
                    Set<Role> roles = customer.getRoles();
                    Role role = new Role();
                    role.setRole("ROLE_SELLER");
                    roles.add(role);
                    customer.setRoles(roles);
                    Set<User> users = new HashSet<>();
                    role.setUsers(users);
                    userRepository.save(customer);
                    return "success";
                } else {
                    throw new PatternMismatchException("gst number is not correct");
                }
            } else {
                throw new PatternMismatchException("Contact number should start with +91 or 0 and length should be 10");

            }
        } else {
            throw new NullException("all the fields are mandatory");
        }
    }
}
