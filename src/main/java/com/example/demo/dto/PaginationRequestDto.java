package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationRequestDto {

    private int page = 0;
    private int count = 10;

}
