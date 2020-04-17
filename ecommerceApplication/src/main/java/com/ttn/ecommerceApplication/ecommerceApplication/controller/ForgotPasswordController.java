package com.ttn.ecommerceApplication.ecommerceApplication.controller;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.ForgotPasswordDao;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ForgotPasswordController
{
    @Autowired
    ForgotPasswordDao forgotPassword;

    @ApiOperation("uri for forget password in which token is sent to the user")
    @PostMapping("/forgotPassword/{email_id}")
    public ResponseEntity setForgotPasswordHandler(@PathVariable(name = "email_id") String email_id)
    {
        return forgotPassword.forgotPassword(email_id);
    }

    @ApiOperation("uri for setting new password on entering token")
    @PutMapping("/setPassword/{token}/{password}")
    public ResponseEntity setForgotPassword(@Valid @PathVariable(name = "token") String token,@PathVariable(name = "password") String password)
    {
        return  forgotPassword.setPassword(token,password);
    }
}
