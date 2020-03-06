package com.example.demo.Extra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;
    @GetMapping(path = "/hello")
    public String helloWorld()
    {
        return "Hello World";
    }
    @GetMapping(path = "/hello-bean")
    public HelloWorldBean helloWorldBean()
    {
        return new HelloWorldBean("Hello World");
    }
    @GetMapping(path = "/hello-bean-pathVariable/{name1}")
    public HelloWorldBean helloWorldBeans(@PathVariable("name1") String name)
    {
        return new HelloWorldBean(String.format("Hello World,%s",name));
    }
    @GetMapping("/i")
    public String he()/*@RequestHeader(name = "Accept-Language",required = false) Locale locale*/
    {
     /*   return messageSource.getMessage("good.morning.message",null,locale);
   */
     return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }

}
