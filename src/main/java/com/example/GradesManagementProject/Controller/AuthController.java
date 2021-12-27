package com.example.GradesManagementProject.Controller;

import com.example.GradesManagementProject.Model.ProfessorLoginModel;
import com.example.GradesManagementProject.Model.ProfessorModel;
import com.example.GradesManagementProject.Model.ProfessorSession;
import com.example.GradesManagementProject.Repository.ProfessorRepository;
import com.example.GradesManagementProject.Repository.ProfessorSessionRepository;
import com.example.GradesManagementProject.Service.UtilityService;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/grades")
public class AuthController {
    private final UtilityService utilityService;
    private final ProfessorSessionRepository professorSessionRepository;
    private final ProfessorRepository professorRepository;

    public AuthController(ProfessorRepository professorRepository, UtilityService utilityService,
                          ProfessorSessionRepository professorSessionRepository) {
        this.professorRepository = professorRepository;
        this.utilityService = utilityService;
        this.professorSessionRepository = professorSessionRepository;
    }

    @GetMapping("/register-professor")
    public String getRegister(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
        if (utilityService.isProfessorLoggedIn(request.getCookies())){
            response.sendRedirect("/grades/dashboard");
            return null;

        }
        model.addAttribute("professor", new ProfessorModel());
        return "register";
    }

    @PostMapping("/register-professor")
    public String postRegister(Model model, @ModelAttribute ProfessorModel professorModel){
        ProfessorModel existingProfessor = this.professorRepository.findByEmail(professorModel.getEmail());
        if (existingProfessor != null){
            model.addAttribute("professor", professorModel);
            model.addAttribute("error", "You already registered");
        }
        this.professorRepository.save(professorModel);
        return "dashboard";
    }
    @GetMapping("/login-professor")
    //per mi marre cookies edhe a me kshyr a oshte logged in user-i
    public String getLogin(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
        //me kshyr se nese oshte user-i logged in me e qu te dashboard
        if (utilityService.isProfessorLoggedIn(request.getCookies())){
            response.sendRedirect("/grades/dashboard");
        }
        model.addAttribute("professorLoginModel", new ProfessorLoginModel());
        model.addAttribute("loginAction", "/grades/login-professor");
        return "login";
    }

    @PostMapping("/login-professor")
    //HttpServletResponse response, HttpServletRequest request- e kemi bo per me i rujte cookies edhe sessions
    public String postLogin(Model model, @ModelAttribute ProfessorLoginModel professorLoginModel,
                            HttpServletResponse response, HttpServletRequest request) throws IOException {
        ProfessorModel loggedProfessor = this.professorRepository.findByEmailAndPassword
                (professorLoginModel.getUsername(), professorLoginModel.getPassword());
        if (loggedProfessor == null){
            model.addAttribute("error", "Username or Password is incorrect.");
            model.addAttribute("professorLoginModel", new ProfessorLoginModel());
            return "login";
        }
        Cookie cookie = new Cookie("logged_in", "true");

        //qe me add cookie-n ne browser, qe me dite ku me e qu cookie-n :d
        cookie.setPath("/");

        //response e merr cookie-n edhe e shton dmth e bon add
        response.addCookie(cookie);

        //qetu jena tu e bo add ni session
        ProfessorSession professorSession = new ProfessorSession();
        professorSession.setProfessorModel(loggedProfessor);

        //id e session, e kena bo qe mos me mujt me u hack-u
        professorSession.setSessionHashCode(utilityService.generateRandomString(15));

        //ip address e user-it
        professorSession.setIpAddress(request.getRemoteAddr());

        //e kena rujt ni professor Session
        professorSessionRepository.save(professorSession);
        Cookie sessionCookie = new Cookie("session_id", professorSession.getSessionHashCode());
        sessionCookie.setPath("/");
        response.addCookie(sessionCookie);
        response.sendRedirect("/grades/dashboard");
        return null;
    }

   // @GetMapping("/error")
   // public String getError(){
      //  return "error";
    //}

    //ktu e kemi bo log out te user-it
    @GetMapping("/logout")
    public void logout(HttpServletResponse response) throws IOException {
        Cookie loggedInCookie = new Cookie("logged_in", "false");
        loggedInCookie.setPath("/");
        loggedInCookie.setMaxAge(0);
        Cookie sessionIdCookie = new Cookie("session_id", "");
        sessionIdCookie.setPath("/");
        sessionIdCookie.setMaxAge(0);
        response.addCookie(loggedInCookie);
        response.addCookie(sessionIdCookie);
        response.sendRedirect("/grades/login-professor");
    }
}

