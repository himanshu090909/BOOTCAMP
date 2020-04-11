package com.ttn.ecommerceApplication.ecommerceApplication.controller;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.ForgotPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ForgotPasswordController
{
    @Autowired
    ForgotPassword forgotPassword;

    @PostMapping("/forgotPassword/{email_id}")
    public void setForgotPasswordHandler(@PathVariable(name = "email_id") String email_id)
    {
        forgotPassword.forgotPassword(email_id);
    }

    @PutMapping("/setPassword/{token}/{password}")
    public void setForgotPassword(@Valid @PathVariable(name = "token") String token,@PathVariable(name = "password") String password)
    {
        forgotPassword.setPassword(token,password);
    }
}
