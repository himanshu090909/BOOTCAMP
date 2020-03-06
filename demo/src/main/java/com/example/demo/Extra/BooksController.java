package com.example.demo.Extra;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BooksController {
    /*@GetMapping("/")
    public String  b()
    {
        return "Welcome to spring boot";

    }
*/
    @GetMapping("/getList{id}")
    public List<Books> b1(@PathVariable int id) {

        List<Books> list = new ArrayList<>();
        list.add(new Books(1,"fghj"));
        list.add(new Books(2,"fghjk"));


            return (List<Books>) list.get(id);


    }
}
