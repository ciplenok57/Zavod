package com.example.demo.mapper;

import com.example.demo.dto.operation.request.OperationRequestDto;
import com.example.demo.dto.operation.request.OperationUpdateRequestDto;
import com.example.demo.dto.operation.response.OperationResponseDto;
import com.example.demo.dto.operation.response.OperationShortResponseDto;
import com.example.demo.dto.operation_category.request.OperationCategoryRequestDto;
import com.example.demo.dto.operation_category.response.OperationCategoryResponseDto;
import com.example.demo.dto.user.response.UserResponseDto;
import com.example.demo.entity.Operation;
import com.example.demo.entity.OperationCategory;
import com.example.demo.entity.User;
import org.mapstruct.*;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {OperationCategoryMapper.class, UserMapper.class}
)
public interface OperationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "categoryId", qualifiedByName = "mapCategoryFromId")
    @Mapping(target = "responsibleSet", ignore = true)
    Operation toEntity(OperationRequestDto requestDto);

    @Mapping(target = "category", source = "category", qualifiedByName = "mapCategoryToResponse")
    @Mapping(target = "responsibleUsers", source = "responsibleSet", qualifiedByName = "mapUsersToResponse")
    OperationResponseDto toResponseDto(Operation entity);

    OperationShortResponseDto toOperationShortResponseDto(Operation operation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "categoryId", qualifiedByName = "mapCategoryFromId")
    @Mapping(target = "responsibleSet", ignore = true)
    void updateEntity(@MappingTarget Operation entity, OperationUpdateRequestDto requestDto);

    @Named("mapCategoryFromId")
    default OperationCategory mapCategoryFromId(UUID categoryId) {
        if (categoryId == null) {
            return null;
        }
        // Возвращаем прокси с ID для избежания лишнего запроса в БД
        OperationCategory category = new OperationCategory();
        category.setId(categoryId);
        return category;
    }

    @Named("mapCategoryToResponse")
    default OperationCategoryResponseDto mapCategoryToResponse(OperationCategory category) {
        if (category == null) {
            return null;
        }
        return new OperationCategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getOperations() != null ? category.getOperations().size() : 0
        );
    }

    @Named("mapUsersToResponse")
    default Set<UserResponseDto> mapUsersToResponse(Set<User> users) {
        if (users == null || users.isEmpty()) {
            return null;
        }
        return users.stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getMiddleName(),
                        user.getPhone(),
                        user.getRole()
                ))
                .collect(Collectors.toSet());
    }

}
