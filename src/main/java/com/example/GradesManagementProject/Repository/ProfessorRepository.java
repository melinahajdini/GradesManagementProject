package com.example.GradesManagementProject.Repository;


import com.example.GradesManagementProject.Model.ProfessorModel;
import org.springframework.data.repository.CrudRepository;

public interface  ProfessorRepository extends CrudRepository<ProfessorModel, Long> {
    ProfessorModel findByEmail(String email);
    ProfessorModel findByEmailAndPassword(String email, String password);

}
