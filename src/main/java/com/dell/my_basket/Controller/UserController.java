package com.dell.my_basket.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping({"/user"})
    public String hello(){
        return "Izzath";
    }

}
