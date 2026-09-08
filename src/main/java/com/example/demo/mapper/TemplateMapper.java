package com.example.demo.mapper;

import com.example.demo.dto.templates.request.TemplateCreateRequestDto;
import com.example.demo.dto.templates.request.TemplateUpdateRequestDto;
import com.example.demo.dto.templates.response.TemplateResponseDto;
import com.example.demo.entity.Template;
import com.example.demo.entity.TemplateCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = TemplateCategoryMapper.class)
public interface TemplateMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "categoryId")
    @Mapping(target = "additionalFields", source = "additionalFields")
    @Mapping(target = "operations", source = "operations")
    Template toEntity(TemplateCreateRequestDto request);

    TemplateResponseDto toResponseDto(Template template);

    List<TemplateResponseDto> toResponseDtoList(List<Template> templates);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "categoryId")
    @Mapping(target = "additionalFields", source = "additionalFields")
    @Mapping(target = "operations", source = "operations")
    void updateEntity(TemplateUpdateRequestDto request, @MappingTarget Template template);

    default TemplateCategory map(UUID categoryId) {
        if (categoryId == null) {
            return null;
        }
        TemplateCategory category = new TemplateCategory();
        category.setId(categoryId);
        return category;
    }
}