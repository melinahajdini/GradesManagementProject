package com.example.GradesManagementProject.Repository;

import com.example.GradesManagementProject.Model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
 Student getStudentsByNameAndSurname(String name, String surname);
}
