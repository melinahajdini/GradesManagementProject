package com.example.GradesManagmentProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


    @Controller
    public class TestController {
        @GetMapping("/home")
        public String getHome(){
            return "home";
        }
    }
