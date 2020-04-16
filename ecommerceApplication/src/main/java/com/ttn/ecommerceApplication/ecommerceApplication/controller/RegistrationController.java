package com.ttn.ecommerceApplication.ecommerceApplication.controller;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.CustomerDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.SellerDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.NotificationService;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.RegistrationDao;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.TokenDao;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.User;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.RoleRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class RegistrationController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    private TokenStore tokenStore;


    @Autowired
    RegistrationDao registrationDao;

    @Autowired
    TokenDao tokenDao;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/findall")
    public Iterable<User> y() {
        return userRepository.findAll();
    }

    @Lazy
    @PostMapping("/registerAsCustomer")
    public String createCustomer(@Valid @RequestBody CustomerDTO user) throws InterruptedException
    {
        return registrationDao.createCustomer(user);
    }

    @PostMapping("/verify/{u}")
    public void verifyUser(@PathVariable(name = "u") String u)
    {
        tokenDao.verifyToken(u);
    }

    @PostMapping("/registerAsSeller")
    public String createSeller(@Valid @RequestBody SellerDTO user) throws InterruptedException {
        return registrationDao.createSeller(user);
    }

    @PostMapping("/resendActivationLink/{mailId}")
    public ResponseEntity resendActivationLink(@PathVariable(name = "mailId") String mailId)
    {
        return registrationDao.resendActivationLink(mailId);
    }


    @GetMapping("/doLogout")
    public String logout(HttpServletRequest request)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            System.out.println("username is "+username);
        } else {
             username = principal.toString();
            System.out.println("username is "+ username);
        }
        User user = userRepository.findByUsername(username);
        user.setCount(0);
        userRepository.save(user);

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
        }
        return "Logged out successfully";
    }



















































/*
    @GetMapping("/listofvendors")
    public List<Object[]> find()
    {
        List<Object[]> objects = userRepository.findAllVendors();
        return objects;
    }
    @GetMapping("/listofbuyers")
    public List<Object[]> findBuyers()
    {
        List<Object[]> objects = userRepository.findAllBuyers();
        return objects;
    }*/

  /*  @Transactional
    @DeleteMapping("/deleteUser/{user_id}")
    public void deleteUserById(@PathVariable(name = "user_id")  int user_id)
    {
        List<Object[]> list = userRepository.findRoleIdFromMappingTable(user_id);
        userRepository.deleteFromUserRole(user_id);
        userRepository.deleteById(user_id);
        for (Object[] objects : list)
        {
            int a = (int) objects[0];
            roleRepository.deleteById(a);
        }
    }

  */  @GetMapping("/customer/home")
    public String vendorHome() {
        return "Vendor Home";
    }

    @GetMapping("/buyer/home")
    public String BuyerHome() {
        return "Buyer Home";
    }

    //admin home page
    @GetMapping("/admin/home")
    public String adminHome() {
        return "Admin home";
    }

    @GetMapping("/seller/home")
    public String userHome() {
        return "User home";
    }

}
