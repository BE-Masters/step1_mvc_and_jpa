package com.example.be_study.service.admin.dto;

import org.springframework.data.domain.PageRequest;

public class PagingRequest {

    private int page;

    private int size;

    public PagingRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public PageRequest of() {
        return PageRequest.of(page - 1, size);
    }
}
