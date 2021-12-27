package com.example.GradesManagmentProject.Controller;

import com.example.GradesManagmentProject.Model.ProfessorModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/grades")
public class AuthController {
    @GetMapping("/register-professor")
    public String getRegister(Model model){
        model.addAttribute("professorModel", new ProfessorModel());
        return "register";
    }
}

