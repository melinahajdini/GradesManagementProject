package com.example.GradesManagementProject.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;
    private String age;
    private String email;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private List<SubjectModel> subjectModelList;
    public void setSubjectModelList(List<SubjectModel>subjectModelList){
        this.subjectModelList = subjectModelList;
    }

    public List<SubjectModel> getSubjectModelList() {
        return subjectModelList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public SubjectModel getSubject() {
//        return subject;
//    }
//
//    public void setSubject(SubjectModel subject) {
//        this.subject = subject;
//    }


}
