package com.ttn.ecommerceApplication.ecommerceApplication.configuration;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    AppUserDetailsService userDetailsService;

    public ResourceServerConfiguration() {
        super();
    }

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //only admin accessible uri
                .antMatchers("/activate/{user_id}").hasAnyRole("ADMIN")
                .antMatchers("/deactivate/{user_id}").hasAnyRole("ADMIN")
                .antMatchers("/allCustomers").hasAnyRole("ADMIN")
                .antMatchers("/allSellers").hasAnyRole("ADMIN")
                .antMatchers("/addNewCategory/{parent_category}").hasAnyRole("ADMIN")
                .antMatchers("/lock/{user_id}").hasAnyRole("ADMIN")
                .antMatchers("/unlock/{user_id}").hasAnyRole("ADMIN")
                .antMatchers("/getAllProducts").hasAnyRole("ADMIN")
                .antMatchers("/addMainCategory/{Category_name}").hasAnyRole("ADMIN")

                //only customer accessible uri
                .antMatchers("/placeOrder/{productVariationId}/{quantity}/{paymentMethod}/{AddressId}").hasAnyRole("CUSTOMER")
                .antMatchers("/showOrderHistory").hasAnyRole("CUSTOMER")
                .antMatchers("/returnRequested/{orderStatusId}").hasAnyRole("CUSTOMER")
                .antMatchers("/cancelOrder/{orderStatusId}").hasAnyRole("CUSTOMER")
                .antMatchers("/deletefromcart/{product_variation_id}").hasAnyRole("CUSTOMER")
                .antMatchers("/addToCart/{product_variation_id}/{quantity}").hasAnyRole("CUSTOMER")
                .antMatchers("/viewCart").hasAnyRole("CUSTOMER")
                .antMatchers("/emptyCart").hasAnyRole("CUSTOMER")
                .antMatchers("/getSellerAccount").hasAnyRole("CUSTOMER")
                .antMatchers("/OrderOneProductFromCart/{cartId}/{paymentMethod}/{AddressId}").hasAnyRole("CUSTOMER")
                .antMatchers("/OrderWholeCart/{AddressId}").hasAnyRole("CUSTOMER")
                .antMatchers("/editEmail/{token}").hasAnyRole("CUSTOMER")
                .antMatchers("/viewProfile").hasAnyRole("CUSTOMER")
                .antMatchers("/updateProfile").hasAnyRole("CUSTOMER")
                .antMatchers("/uploadProfilePic").hasAnyRole("CUSTOMER")
                .antMatchers("/viewProfileImage").hasAnyRole("CUSTOMER")
                .antMatchers("/viewProduct/{product_id}").hasAnyRole("CUSTOMER")


                //admin and customer accessible uri
                .antMatchers("/editContact").hasAnyRole("ADMIN,CUSTOMER")
                .antMatchers("/detailsOfCustomer").hasAnyRole("ADMIN,CUSTOMER")
                .antMatchers("/getAddresses").hasAnyRole("ADMIN,CUSTOMER")
                .antMatchers("/getCategory").hasAnyRole("ADMIN,CUSTOMER")
                .antMatchers("/getCategory/{category_name}").hasAnyRole("ADMIN,CUSTOMER")
                .antMatchers("/addReview/{product_id}").hasAnyRole("ADMIN,CUSTOMER")

                //only seller accessible uri
                .antMatchers("/getCustomerAccount").hasAnyRole("SELLER")
                .antMatchers("/addProduct/{category}").hasAnyRole("SELLER")
                .antMatchers("/getProducts").hasAnyRole("SELLER")
                .antMatchers("/deleteProduct/{productName}").hasAnyRole("SELLER")
                .antMatchers("/editProduct/{productName}").hasAnyRole("SELLER")
                .antMatchers("/setActiveStatus/{productName}").hasAnyRole("SELLER")
                .antMatchers("/addProductVariations/{productName}").hasAnyRole("SELLER")
                .antMatchers("/editProductVariations/{productVariationId}").hasAnyRole("SELLER")
                .antMatchers("/deleteProductVariation/{productVariationId}").hasAnyRole("SELLER")
                .antMatchers("/setStatus/{productVariationId}/{orderStatusId}").hasAnyRole("SELLER")

                //seller accessible uri
                .antMatchers("/detailsOfSeller").hasAnyRole("ADMIN,SELLER")
                .antMatchers("/editSellerDetails").hasAnyRole("ADMIN,SELLER")
                .antMatchers("/getSubcategories").hasAnyRole("ADMIN,SELLER")
                .antMatchers("/upload").hasAnyRole("ADMIN,SELLER")
                .antMatchers("/uploadsm").hasAnyRole("ADMIN,SELLER")

                //accessible for all
                .antMatchers("/deleteUser").hasAnyRole("ADMIN,CUSTOMER,SELLER")
                .antMatchers("/addNewAddress").hasAnyRole("ADMIN,CUSTOMER,SELLER")
                .antMatchers("/editUsername").hasAnyRole("ADMIN,CUSTOMER,SELLER")
                .antMatchers("/editEmail").hasAnyRole("ADMIN,CUSTOMER,SELLER")
                .antMatchers("/editPassword").hasAnyRole("ADMIN,CUSTOMER,SELLER")
                .antMatchers("/updateAddress/{addressId}").hasAnyRole("ADMIN,CUSTOMER,SELLER")
                .antMatchers("/doLogout").hasAnyRole("ADMIN","CUSTOMER","SELLER")
                .antMatchers("/getAllProductVariations/{productName}").hasAnyRole("ADMIN,SELLER,CUSTOMER")
                .antMatchers("/getReviews/{product_id}").hasAnyRole("ADMIN,SELLER,CUSTOMER")
                .antMatchers("/download/{fileName:.+}").hasAnyRole("ADMIN,SELLER,CUSTOMER")

                //anonymous
                .antMatchers("/registerAsCustomer").anonymous()
                .antMatchers("/registerAsSeller").anonymous()
                .antMatchers("/verify/{u}").anonymous()
                .antMatchers("/forgotPassword/{email_id}").anonymous()
                .antMatchers("/setPassword/{token}/{password}").anonymous()
                .antMatchers("/filtering").anonymous()
                .antMatchers("/uploadVariationPic/{id}").anonymous()
                .antMatchers("/viewSingleProduct/{id}").anonymous()













                .antMatchers("/").anonymous()
                .antMatchers("/createUser").anonymous()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger.json").permitAll()
                .antMatchers("/listcategories").anonymous()
                .antMatchers("/getProducts/{id}").anonymous()
                .antMatchers("/deleteUser/{user_id}").anonymous()
                .antMatchers("/getProductVariation/{u}").hasAnyRole("CUSTOMER")
                .antMatchers("/getProduct/{i}").anonymous()
                .antMatchers("/uploadFile").anonymous()
                .antMatchers("/listofvendors").hasAnyRole("ADMIN")
                .antMatchers("/listofbuyers").hasAnyRole("ADMIN")
                .antMatchers("/admin/home").hasAnyRole("ADMIN","CUSTOMER")
                .antMatchers("/details").hasAnyRole("CUSTOMER","SELLER")
                .antMatchers("/customer/home").hasAnyRole("ADMIN","CUSTOMER")
                .antMatchers("/seller/home").hasAnyRole("ADMIN","SELLER")
                .antMatchers("/user/home").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable();

    }

}