package com.example.be_study.service.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractBaseUserDeleteEntity extends AbstractBaseEntity {
    @Column(name = "deleted", nullable = false, columnDefinition = "boolean default false")
    private Boolean deleted;
}
