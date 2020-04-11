package com.ttn.ecommerceApplication.ecommerceApplication.daoImpl;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.SellerDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.SellerDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Customer;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Role;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Seller;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.User;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.NullException;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.PatternMismatchException;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.CustomerRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.SellerRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.UserRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.GetCurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SellerDaoImpl implements SellerDao {
    @Autowired
    GetCurrentUser getCurrentUser;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SellerRepository sellerRepository;


    @Override
    public List<Object[]> getSellerDetails() {
        String username = getCurrentUser.getUser();
        List<Object[]> objects = sellerRepository.getDetails(username);
        return objects;
    }

    @Transactional
    @Override
    public String getAnCustomerAccount(Customer customer) {
        if (customer.getContactNo() != null) {
            if (customer.getContactNo().matches("(\\+91|0)[0-9]{10}")) {
                String username = getCurrentUser.getUser();
                User seller = userRepository.findByUsername(username);
                customer.setId(seller.getId());
                customerRepository.insertIntoCustomer(customer.getContactNo(), customer.getId());
                Set<Role> roles = seller.getRoles();
                Role role = new Role();
                role.setRole("ROLE_CUSTOMER");
                roles.add(role);
                seller.setRoles(roles);
                Set<User> users = new HashSet<>();
                role.setUsers(users);
                userRepository.save(seller);
                return "success";
            } else {
                throw new PatternMismatchException("Contact number should start with +91 or 0 and length should be 10");
            }
        } else {
                throw new NullException("contact number is mandatory");
        }
    }

    @Override
    public String editSellerDetails(Seller seller)
    {
        String user = getCurrentUser.getUser();
        Seller user1 = sellerRepository.findByUsername(user);
        if (seller.getCompanyName()!=null) {
            user1.setCompanyName(seller.getCompanyName());
        }
        if (seller.getCompanyContactNo()!=null)
        {
             if (seller.getCompanyContactNo().matches("(\\+91|0)[0-9]{10}")) {
                 user1.setCompanyContactNo(seller.getCompanyContactNo());
             }
             else
             {
                 throw new PatternMismatchException("Contact number should start with +91 or 0 and length should be 10");
             }
        }
        if (seller.getGstNo()!=null) {

            if (seller.getGstNo().matches("\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1}")) {
                user1.setGstNo(seller.getGstNo());
            } else {
                throw new PatternMismatchException("gst number is not correct");
            }
        }
        user1.setModifiedBy(user);
        sellerRepository.save(user1);
        return "success";
    }

}
