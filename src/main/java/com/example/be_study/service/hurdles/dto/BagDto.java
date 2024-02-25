package com.example.be_study.service.hurdles.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BagDto {

    private Long id;

    private List<ItemDto> itemList;
}
