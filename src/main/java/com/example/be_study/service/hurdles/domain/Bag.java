package com.example.be_study.service.hurdles.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "bag")
@Getter
@Setter
@NoArgsConstructor
public class Bag {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bag_id")
    private Long bagId;

    @OneToMany(mappedBy = "bag", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Item> items;

    @Builder
    public Bag(Long bagId, List<Item> items) {
        this.bagId = bagId;
        this.items = items;
    }
}
