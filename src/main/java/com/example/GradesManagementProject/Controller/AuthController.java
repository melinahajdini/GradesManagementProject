package com.example.GradesManagementProject.Controller;

import com.example.GradesManagementProject.Model.ProfessorModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/grades")
public class AuthController {
    @GetMapping("/register-professor")
    public String getRegister(Model model){
        model.addAttribute("professor", new ProfessorModel());
        return "register";
    }
}

