package com.personal.asung_coffee_store.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageDto<T> {

    @NonNull
    List<T> items;

    @NonNull
    private int totalPages;

    @NonNull
    private int totalItems;

    @NonNull
    private int currentPageNumber;

    @NonNull
    private int pageSize;

    public PageDto(Page<T> page) {
        this.items = page.getContent();
        this.totalItems = (int) page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.currentPageNumber = page.getNumber() + 1;
        this.pageSize = page.getSize();
    }
}
