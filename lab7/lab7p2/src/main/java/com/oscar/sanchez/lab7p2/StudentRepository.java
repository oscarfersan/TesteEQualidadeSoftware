package com.oscar.sanchez.lab7p2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    /*List<Student> findByFirsName(String firstName);
    List<Student> findAll();*/
}
