package com.example.demo.dto.productTemplate;

import com.example.demo.dto.OperationPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationMockDto {

    private UUID operationId;
    private UUID graphOperationId;
    private OperationPosition position;

}
