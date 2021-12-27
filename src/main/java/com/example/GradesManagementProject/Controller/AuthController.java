package com.example.GradesManagementProject.Controller;

import com.example.GradesManagementProject.Model.ProfessorModel;
import com.example.GradesManagementProject.Repository.ProfessorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/grades")
public class AuthController {
    private final ProfessorRepository professorRepository;

    public AuthController(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @GetMapping("/register-professor")
    public String getRegister(Model model){
        model.addAttribute("professor", new ProfessorModel());
        return "register";
    }

    @PostMapping("/register-professor")
    public String postRegister(Model model, @ModelAttribute ProfessorModel professorModel){
        model.addAttribute("professor", professorModel);

        this.professorRepository.save(professorModel);
        return "dashboard";
    }

}

