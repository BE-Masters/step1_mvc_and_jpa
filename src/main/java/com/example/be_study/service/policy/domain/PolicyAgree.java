package com.example.be_study.service.policy.domain;

import com.example.be_study.service.base.AbstractBaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(indexes = {})
@AttributeOverride(name = "id", column = @Column(name = "policy_agree_id"))
@Entity(name = "policy_agree")
public class PolicyAgree extends AbstractBaseEntity {




}
