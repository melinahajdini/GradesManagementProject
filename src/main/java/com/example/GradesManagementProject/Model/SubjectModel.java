package com.example.GradesManagementProject.Model;

import javax.persistence.*;

@Entity
public class SubjectModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subjectName;
    private int grade;



    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    public void setGrade(int grade){
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }
}
