package com.example.demo.mapper;

import com.example.demo.dto.operation_category.request.OperationCategoryRequestDto;
import com.example.demo.dto.operation_category.response.OperationCategoryResponseDto;
import com.example.demo.entity.OperationCategory;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface OperationCategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "operations", ignore = true)
    OperationCategory toEntity(OperationCategoryRequestDto requestDto);

    @Mapping(target = "operationsCount", expression = "java(entity.getOperations() != null ? entity.getOperations().size() : 0)")
    OperationCategoryResponseDto toResponseDto(OperationCategory entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "operations", ignore = true)
    void updateEntity(@MappingTarget OperationCategory entity, OperationCategoryRequestDto requestDto);

    // Вспомогательный метод для конвертации в DTO (если нужно)
    @Named("toResponseDto")
    default OperationCategoryResponseDto toResponseDtoWithCheck(OperationCategory entity) {
        if (entity == null) {
            return null;
        }
        return toResponseDto(entity);
    }

}
