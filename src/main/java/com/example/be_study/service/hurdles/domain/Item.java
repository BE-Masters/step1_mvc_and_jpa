package com.example.be_study.service.hurdles.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name")
    private String itemName;

    @ManyToOne
    @JoinColumn(name="bag_id")
    private Bag bag;

    @Builder
    public Item(Long itemId, String itemName) {
        this.itemId = itemId;
        this.itemName = itemName;
    }
}
