package com.example.be_study.choi.services.User.domain;

import com.example.be_study.choi.services.User.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "user")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @JsonIgnore
    @Column(name = "password", length = 30, nullable = true)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", length = 20, nullable = false)
    private String provider;

    @Column(name = "provider_id", length = 30, nullable = false)
    private String providerId;

    @Column(name = "image_url", length = 2000, nullable = true)
    private String imageUrl;

    @Column(name = "email_verified", nullable = true)
    private Boolean emailVerified = false;

    @Column(name = "age", nullable = true)
    private Integer age;

    @Column(name = "phone_number", length = 20, nullable = true)
    private String phoneNumber;

    @Column(name = "device_token", length = 256, nullable = true)
    private String deviceToken;

    @Column(name = "user_type" , nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

}
