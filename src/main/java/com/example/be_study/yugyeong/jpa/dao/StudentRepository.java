package com.example.be_study.yugyeong.jpa.dao;

import com.example.be_study.yugyeong.jpa.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
