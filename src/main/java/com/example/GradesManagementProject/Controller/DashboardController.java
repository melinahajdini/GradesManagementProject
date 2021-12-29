package com.example.GradesManagementProject.Controller;

import com.example.GradesManagementProject.Model.ProfessorModel;
import com.example.GradesManagementProject.Model.Student;
import com.example.GradesManagementProject.Model.SubjectModel;
import com.example.GradesManagementProject.Repository.StudentRepository;
import com.example.GradesManagementProject.Repository.SubjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/grades")
public class DashboardController {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public DashboardController(StudentRepository studentRepository, SubjectRepository subjectRepository){
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/dashboard")
    public String getDashboard(){
        return "dashboard";
    }
    @GetMapping("/student")
    public String getStudent(Model model){
        model.addAttribute("student", new Student());
        return "student";

    }
    @PostMapping("/student")
    public String postStudent(Model model, @ModelAttribute Student student){
            model.addAttribute("student", student);


        this.studentRepository.save(student);
        return "dashboard";
    }
    @GetMapping("/subject")
    public String getSubjectModel(Model model){
        model.addAttribute("subject", new SubjectModel());
        return "subject";


    }
    @PostMapping("/subject")
    public String postSubject(Model model, @ModelAttribute SubjectModel subjectModel){
        model.addAttribute("subject", subjectModel);
        this.subjectRepository.save(subjectModel);

        return "dashboard";

    }

}
