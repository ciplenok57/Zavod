package com.example.demo.dto;

import com.example.demo.entity.Operation;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EdgeDto {

    private UUID source;
    private UUID target;

}
