package com.example.demo.mapper;


import com.example.demo.dto.template_category.request.TemplateCategoryCreateRequestDto;
import com.example.demo.dto.template_category.request.TemplateCategoryUpdateRequestDto;
import com.example.demo.dto.template_category.response.TemplateCategoryResponseDto;
import com.example.demo.entity.TemplateCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TemplateCategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "templates", ignore = true)
    TemplateCategory toEntity(TemplateCategoryCreateRequestDto request);

    TemplateCategoryResponseDto toResponseDto(TemplateCategory category);

    List<TemplateCategoryResponseDto> toResponseDtoList(List<TemplateCategory> categories);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "templates", ignore = true)
    void updateEntity(TemplateCategoryUpdateRequestDto request, @MappingTarget TemplateCategory category);
}