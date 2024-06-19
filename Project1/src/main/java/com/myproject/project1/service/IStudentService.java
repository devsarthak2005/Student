package com.myproject.project1.service;

import com.myproject.project1.model.Student;

import java.util.List;

public interface IStudentService
{
    Student addStudent(Student student);
    List<Student> getStudents();
    Student updateStudent(Student student, Long id);
    Student getStudentById(long id);
    void deleteStudent(long id);

}
