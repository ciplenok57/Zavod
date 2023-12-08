package com.example.demo.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RealOperation extends Operation{

    private int price = 0;

    private RealOperationStatus status = RealOperationStatus.NEW;

    public RealOperation(UUID id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return super.getId().toString();
    }
}
