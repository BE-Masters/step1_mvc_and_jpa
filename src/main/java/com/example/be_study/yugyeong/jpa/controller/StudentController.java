package com.example.be_study.yugyeong.jpa.controller;

import com.example.be_study.yugyeong.jpa.application.StudentService;
import com.example.be_study.yugyeong.jpa.dto.StudentsInfoDto;
import com.example.be_study.yugyeong.jpa.dto.request.StudentSignUpRequest;
import com.example.be_study.yugyeong.jpa.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    /**
     *  학생 회원가입
     */
    @PostMapping(name = "학생 회원가입")
    public SuccessResponse studentSignUp(@RequestBody StudentSignUpRequest request){
        studentService.studentSignUp(request);
        return new SuccessResponse();
    }

    /**
     *  전체 학생 조회
     */
    @GetMapping(name = "전체 학생 조회")
    public List<StudentsInfoDto> studentsInfo(){
        return studentService.studentsInfo();
    }

}
