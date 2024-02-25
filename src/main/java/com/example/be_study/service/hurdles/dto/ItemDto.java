package com.example.be_study.service.hurdles.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {

    private String itemName;

    @JsonIgnore
    private BagDto bag;
}
