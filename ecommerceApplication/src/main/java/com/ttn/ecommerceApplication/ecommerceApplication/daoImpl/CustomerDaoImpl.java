
package com.ttn.ecommerceApplication.ecommerceApplication.daoImpl;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.CustomerDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.ProductDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.CustomerDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.ProfileDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.*;
import com.ttn.ecommerceApplication.ecommerceApplication.enums.FromStatus;
import com.ttn.ecommerceApplication.ecommerceApplication.enums.ToStatus;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.*;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.*;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.GetCurrentUser;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.modelmapper.ModelMapper;
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
    CategoryRepository categoryRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductVariationRepository  productVariationRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

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

    public String editContact(Customer customer)
    {
        if (customer.getContactNo().matches("(\\+91|0)[0-9]{10}"))
        {
            String user = getCurrentUser.getUser();
            Customer user1 = customerRepository.findByUsername(user);
            user1.setContactNo(customer.getContactNo());
            user1.setModifiedBy(user);
            customerRepository.save(user1);
            return "success";
        }
        else
            {
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
    public List<Object[]> viewProfile()
    {
        String username = getCurrentUser.getUser();
        Customer customer = customerRepository.findByUsername(username);
        return customerRepository.viewProfile(customer.getId());
    }

    @Override
    public String updateProfile(ProfileDTO customer)
    {
         String username = getCurrentUser.getUser();
         Customer customer1 = customerRepository.findByUsername(username);
         if (customer.getFirstName()!=null)
             customer1.setFirstName(customer.getFirstName());
         if (customer.getMiddleName()!=null)
             customer1.setMiddleName(customer.getMiddleName());
         if (customer.getLastName()!=null)
             customer1.setLastName(customer.getLastName());
         if (customer.getContactNo()!=null)
         {
             if (customer.getContactNo().matches("(\\+91|0)[0-9]{10}"))
             {
                 customer1.setContactNo(customer.getContactNo());
             }
             else
             {
                 throw new PatternMismatchException("Contact number should start with +91 or 0 and length should be 10");
             }
         }
         if (customer.isActive()==false)
         {
             customer1.setActive(false);
             customer1.setEnabled(false);
         }
         customerRepository.save(customer1);
         return "success";
    }

    @Override
    public List<Object[]> viewProduct(Long id) {
        Optional<Product> product1 = productRepository.findById(id);
        if (product1.isPresent())
        {
            if (product1.get().isActive()==true)
            {
                List<Object[]> list = productVariationRepository.getProductVariation(id);
                if (list.isEmpty())
                {
                    throw new NotFoundException("product variations not available for this product");
                }
                else {
                    return productRepository.getSingleProduct(id);
                }
            }
            else
            {
                throw new NotFoundException("product is not present or is currently not active");
            }
        }
        else
        {
            throw new NotFoundException("prooduct with this id is not present");
        }

    }

    @Override
    public List<Object[]> viewProducts(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent())
        {
            int result = categoryRepository.checkIfLeaf(categoryId);
            if (result==1)
            {

            }
            else
            {
                throw new NotFoundException("Category is not a leaf category");
            }
        }
        else
        {
            throw new NotFoundException("category with this id is not present");

        }
            return null;

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
