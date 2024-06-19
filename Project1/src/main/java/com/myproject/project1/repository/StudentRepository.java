package com.myproject.project1.repository;

import com.myproject.project1.model.Student;
import io.micrometer.observation.ObservationFilter;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long>
{
    Optional<Student> findByEmail(String email);
}
