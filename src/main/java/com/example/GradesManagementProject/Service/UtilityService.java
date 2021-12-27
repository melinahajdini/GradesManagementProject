package com.example.GradesManagementProject.Service;

import com.example.GradesManagementProject.Model.ProfessorModel;
import com.example.GradesManagementProject.Model.ProfessorSession;
import com.example.GradesManagementProject.Repository.ProfessorRepository;
import com.example.GradesManagementProject.Repository.ProfessorSessionRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

@Service
public class UtilityService {
    public UtilityService(ProfessorRepository professorRepository, ProfessorSessionRepository professorSessionRepository) {
        this.professorRepository = professorRepository;
        this.professorSessionRepository = professorSessionRepository;
    }

    private ProfessorRepository professorRepository;
    private ProfessorSessionRepository professorSessionRepository;
    public boolean isProfessorLoggedIn(Cookie[]cookies){
        return this.getLoggedInProfessor(cookies) != null;
    }

    private ProfessorModel getLoggedInProfessor(Cookie[] cookies) {
        if(cookies == null){
            return null;
        }

        Optional<Cookie> cookieOptional = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("logged_in")
                        && c.getValue().equals("true"))
                .findFirst();

        if(!cookieOptional.isPresent()){
            return null;
        }

        Optional<Cookie> sessionCookieOptional = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("session_id"))
                .findFirst();

        if(!sessionCookieOptional.isPresent()){
            return null;
        }
        //e kshyr session te profesorit qe nese oshte logged in
        ProfessorSession professorSession =
                this.professorSessionRepository.findBySessionHashCode(sessionCookieOptional.get().getValue());
        if (professorSession == null){
            return null;
        }
        return professorSession.getProfessorModel();
    }

    public String generateRandomString(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

}
