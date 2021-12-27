package com.example.GradesManagementProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/grades")
public class DashboardController {
    @GetMapping("/dashboard")
    public String getDashboard(){
        return "dashboard";
    }
}
