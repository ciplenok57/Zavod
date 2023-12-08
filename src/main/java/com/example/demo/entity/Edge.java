package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Edge<T> {

    private T source;
    private T target;

}
