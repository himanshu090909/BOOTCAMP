package com.ttn.ecommerceApplication.ecommerceApplication.daoImpl;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.ForgotPasswordDao;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Token;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.User;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.TokenRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.UserRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.NotificationService;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.TokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordDaoImpl implements ForgotPasswordDao
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Lazy
    @Autowired
    NotificationService notificationService;

    @Autowired
    TokenDao tokenDao;

    private JavaMailSender javaMailSender;

    @Autowired
    public ForgotPasswordDaoImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void forgotPassword(String email_id) {
        User user = userRepository.findByEmail(email_id);
        if (user == null) {
            System.out.println("no user found with this email id");
            throw new RuntimeException();
        } else {
            System.out.println("Sending email...");
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(user.getEmail());
            mail.setFrom("hs631443@gmail.com");
            mail.setSubject("Regarding forgot password");
            String uu = tokenDao.getToken(user);
            mail.setText(uu);
            javaMailSender.send(mail);
            System.out.println("Email Sent!");
        }
    }
    public void setPassword(String token_on_mail, String password) {
        Token token1 = null;
        for (Token token : tokenRepository.findAll()) {
            if (token.getRandomToken().equals(token_on_mail)) {
                token1 = token;
            }
        }
        if (token1 == null) {
            System.out.println("invalid token");
        } else {
            if (token1.isExpired()) {
                notificationService.sendNotificaitoin(userRepository.findByUsername(token1.getName()));
                tokenRepository.delete(token1);
            }
            else {
                User user2 = userRepository.findByUsername(token1.getName());
                user2.setPassword(new BCryptPasswordEncoder().encode(password));
                userRepository.save(user2);
                tokenRepository.delete(token1);
            }
        }
    }
}
