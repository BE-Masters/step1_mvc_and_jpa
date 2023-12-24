package com.example.be_study.yugyeong.jpa.application;

import com.example.be_study.yugyeong.jpa.dao.StudentRepository;
import com.example.be_study.yugyeong.jpa.domain.Student;
import com.example.be_study.yugyeong.jpa.dto.StudentsInfoDto;
import com.example.be_study.yugyeong.jpa.dto.request.StudentSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    /**
     * 학생 회원가입
     */
    @Transactional
    public void studentSignUp(@RequestBody StudentSignUpRequest request){
        Student student = Student.builder()
                .studentNumber(request.getStudentNumber())
                .studentName(request.getStudentName())
                .studentGender(request.getStudentGender())
                .studentPhoneNumber(request.getStudentPhoneNumber())
                .build();

        studentRepository.save(student);
    }

    /**
     *  전체 학생 조회
     */
    @Transactional(readOnly = true)
    public List<StudentsInfoDto> studentsInfo(){
        return studentRepository.findAll().stream()
                .map(student -> StudentsInfoDto.builder()
                        .studentNumber(student.getStudentNumber())
                        .studentName(student.getStudentName())
                        .build())
                .toList();
    }

}
