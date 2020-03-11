package com.example.Restdemo2.Controller;

import com.example.Restdemo2.ModelClasses.UserVersion1;
import com.example.Restdemo2.ModelClasses.UserVersion2;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserVersionController {
    //URI
    @ApiOperation(value = "version 1 of details of the user by uri versioning")
    @GetMapping("/users/v1")
    public UserVersion1 userVersion1() {
        return new UserVersion1(1, "himanshu", 1234);
    }

    @ApiOperation(value = "version 2 of details of the user by uri versioning")
    @GetMapping("/users/v2")
    public UserVersion2 userVersion2() {
        return new UserVersion2(1, "himanshu", 1234, "h@gmail.com", "vivek vihar");
    }

    //Request Params
    @ApiOperation(value = "version 1 of details of the user by request params versioning")
    @GetMapping(value = "/users/param", params = "version1")
    public UserVersion1 userVersion3() {
        return new UserVersion1(1, "himanshu", 1234);
    }

    @ApiOperation(value = "version 2 of details of the user by request params versioning")
    @GetMapping(value = "/users/param", params = "version2")
    public UserVersion2 userVersion4() {
        return new UserVersion2(1, "himanshu", 1234, "h@gmail.com", "vivek vihar");
    }

    //header versioning
    @ApiOperation(value = "version 1 of details of the user by header versioning")
    @GetMapping(value = "/users/header", headers = "version=1")
    public UserVersion1 userVersion5() {
        return new UserVersion1(1, "himanshu", 1234);

    }

    @ApiOperation(value = "version 2 of details of the user by header versioning")
    @GetMapping(value = "/users/header", headers = "version=2")
    public UserVersion2 userVersion6() {
        return new UserVersion2(1, "himanshu", 1234, "h@gmail.com", "vivek vihar");
    }

    //mime type
    @ApiOperation(value = "version 1 of details of the user by mime type versioning")
    @GetMapping(value = "/users/produces", produces = "application/version1+json")
    public UserVersion1 userVersion7() {
        return new UserVersion1(1, "himanshu", 1234);

    }

    @ApiOperation(value = "version 2 of details of the user by mime type versioning")
    @GetMapping(value = "/users/produces", produces = "application/version2+json")
    public UserVersion2 userVersion8() {
        return new UserVersion2(1, "himanshu", 1234, "h@gmail.com", "vivek vihar");
    }


}
