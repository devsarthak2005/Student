package com.myproject.project1.service;

import com.myproject.project1.exception.StudentAlreadyExistsException;
import com.myproject.project1.exception.StudentNotFoundException;
import com.myproject.project1.model.Student;
import com.myproject.project1.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService
{

    private final StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student)
    {
        if(studentAlreadyExists(student.getEmail()))
        {
            throw new StudentAlreadyExistsException("already exists");
        }
        else
        return studentRepository.save(student);
    }

    private boolean studentAlreadyExists(String email)
    {
        return studentRepository.findByEmail(email).isPresent();
    }



    @Override
    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(student.getId()).map(st -> {
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());
            return studentRepository.save(st);
        }).orElseThrow(()->new StudentNotFoundException("Sorry, this user cannot be found"));
    }

    @Override
    public Student getStudentById(long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Sorry, this user cannot be found"));
    }

    @Override
    public void deleteStudent(long id)
    {
        if(!studentRepository.existsById(id))
        {
            throw new StudentNotFoundException("Sorry, this user cannot be found");
        }
        else
            studentRepository.deleteById(id);

    }
}
