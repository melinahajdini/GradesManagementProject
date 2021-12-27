package com.example.GradesManagementProject.Repository;

import com.example.GradesManagementProject.Model.ProfessorSession;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorSessionRepository extends CrudRepository<ProfessorSession, Long> {
    ProfessorSession findBySessionHashCode(String sessionHashCode);

}
