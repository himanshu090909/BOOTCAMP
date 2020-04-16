package com.ttn.ecommerceApplication.ecommerceApplication.daoImpl;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.UserDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.AddressDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.UserDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.*;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.*;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.AddressRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.TokenRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.UserRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.GetCurrentUser;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserDaoImpl implements UserDao
{
    public int count;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GetCurrentUser getCurrentUser;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    ModelMapper modelMapper;

    private JavaMailSender javaMailSender;

    @Autowired
    public UserDaoImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Lazy
    @Autowired
    NotificationService notificationService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UploadDaoImpl uploadDao;

    public String update(AddressDTO address, Long addressId) {
        String username = getCurrentUser.getUser();
        User user = userRepository.findByUsername(username);
        Set<Address> addresses = user.getAddresses();
        int count = 0;
        for (Address address1 : addresses) {
            if (address1.getId() == addressId) {
                address1 = modelMapper.map(address, Address.class);
                address1.setUser(user);
                address1.setId(addressId);
                address1.setModifiedBy(username);
                addressRepository.save(address1);
                count++;
            }
        }
        if (count == 0) {
            throw new NotFoundException("address id is not valid");
        }
        return "success";
    }

    public String deleteUser() {
        String username = getCurrentUser.getUser();
        User user = userRepository.findByUsername(username);
        userRepository.deleteUser(user.getId());
        return "success";
    }

    public String addNewAddress(Address address) {
        String username = getCurrentUser.getUser();
        User user = userRepository.findByUsername(username);
        address.setUser(user);
        user.addAddress(address);
        user.setModifiedBy(username);
        userRepository.save(user);
        return "success";
    }


    public String editUsername(UserDTO user) {
        User user1 = userRepository.findByUsername(user.getUsername());
        if (user1 == null) {
            String username = getCurrentUser.getUser();
            User user2 = userRepository.findByUsername(username);
            user2.setUsername(user.getUsername());
            user2.setModifiedBy(username);
            userRepository.save(user2);
            return "Success";
        } else {
            throw new AlreadyExists("username is occupied,try with a different username");
        }
    }

    @Lazy
    public String editEmail(UserDTO user) {
        User user1 = modelMapper.map(user, User.class);
        String username = getCurrentUser.getUser();
        user1.setUsername(username);
        notificationService.sendNotificaitoin(user1);
        return "success";
    }

    @Lazy
    public String verifyNewEmail(String token, UserDTO user) {
        Token token1 = null;
        for (Token token2 : tokenRepository.findAll()) {
            if (token2.getRandomToken().equals(token)) {
                token1 = token2;
            }
        }
        if (token1 == null) {
            throw new TokenNotFoundException("token is invalid");
        } else {
            if (token1.isExpired()) {
                User user1 = modelMapper.map(user, User.class);
                String username = getCurrentUser.getUser();
                user1.setUsername(username);
                notificationService.sendNotificaitoin(user1);
                tokenRepository.delete(token1);
                throw new TokenNotFoundException("token is expired check mail for new token");
            } else {
                System.out.println("saving");
                User user2 = userRepository.findByUsername(token1.getName());
                user2.setUsername(user.getUsername());
                user2.setModifiedBy(user2.getUsername());
                userRepository.save(user2);
                tokenRepository.delete(token1);
                return "success";
            }
        }
    }

    public String editPassword(UserDTO user) {
        String username = getCurrentUser.getUser();
        User user1 = userRepository.findByUsername(username);
        if (user.getPassword() != null && user.getConfirmPassword() != null) {
            if (user.getPassword().equals(user.getConfirmPassword())) {
                user1.setPassword(passwordEncoder.encode(user.getPassword()));
                user1.setModifiedBy(username);
                userRepository.save(user1);
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(user1.getUsername());
                mail.setFrom("hs631443@gmail.com");
                mail.setSubject("password changed status");
                mail.setText("your password has been successfully changed");
                javaMailSender.send(mail);
            } else {
                throw new PasswordAndConfirmPasswordMismatchException("password and confirm password does not match");
            }
        } else {
            throw new NullPointerException("password and confirm password both are mandatory");
        }
        return "success";
    }

    @Modifying
    @Transactional
    public String deleteAddress(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            userRepository.deleteAddress(id);
        } else {
            throw new NotFoundException("address not present");
        }
          return "successfully deleted";
    }

















    public AppUser loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        Set<Role> roles = user.getRoles();
        Iterator<Role> roleIterator = roles.iterator();
        String ro = null;
        List<GrantAuthorityImpl> list = new ArrayList<>();
        while (roleIterator.hasNext())
        {
            Role role = roleIterator.next();
            list.add(new GrantAuthorityImpl(role.getRole()));
        }
        System.out.println(user);

        if (username != null) {
            return new AppUser(user.getUsername(), user.getPassword(), list/*Arrays.asList(new GrantAuthorityImpl(ro))*/,user.isEnabled(),user.isAccountNonLocked(),user.isAccountNonExpired());
        } else {
            throw new RuntimeException();
        }
    }
}
