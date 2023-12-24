package com.example.be_study.yugyeong.jpa.domain;

import com.example.be_study.yugyeong.jpa.type.GenderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @Builder
@AllArgsConstructor @NoArgsConstructor
@Table(name = "student")
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(nullable = false)
    private int studentNumber;

    @Column(nullable = false)
    private String studentName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderType studentGender;

    @Column(nullable = false)
    private String studentPhoneNumber;

}
