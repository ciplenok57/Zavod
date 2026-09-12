package com.example.demo.mapper;

import com.example.demo.dto.order.request.OrderCreateRequest;
import com.example.demo.dto.order.request.OrderUpdateRequest;
import com.example.demo.dto.order.response.OrderResponseDto;
import com.example.demo.dto.order.response.OrderShortResponseDto;
import com.example.demo.dto.order_item.request.OrderItemRequest;
import com.example.demo.dto.order_item.response.OrderItemResponseDto;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface OrderMapper {


    /**
     * Order → OrderResponseDto
     */
    @Mapping(target = "items", source = "items")
    OrderResponseDto toResponseDto(Order order);

    /**
     * Order → OrderShortResponseDto
     */
    @Mapping(target = "itemsCount", expression = "java(order.getItems() != null ? order.getItems().size() : 0)")
    OrderShortResponseDto toShortDto(Order order);

    /**
     * OrderCreateRequest → Order
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", ignore = true)
    Order toEntity(OrderCreateRequest request);

    /**
     * OrderUpdateRequest → Order (обновление существующего)
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", ignore = true)
    void updateEntity(OrderUpdateRequest request, @MappingTarget Order order);

    /**
     * Список заказов → список DTO
     */
    List<OrderResponseDto> toResponseDtoList(List<Order> orders);

    List<OrderShortResponseDto> toShortDtoList(List<Order> orders);

    // ============================================
    // ===== ORDER ITEM =====
    // ============================================

    /**
     * OrderItem → OrderItemResponseDto
     */
    @Mapping(target = "templateId", source = "template.id")
    @Mapping(target = "templateName", source = "template.name")
    @Mapping(target = "categoryName", source = "template.category.name")
    OrderItemResponseDto toItemDto(OrderItem item);

    /**
     * OrderItemRequest → OrderItem
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "template", ignore = true)
    OrderItem toItemEntity(OrderItemRequest request);

    /**
     * OrderItemRequest → OrderItem (обновление)
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "template", ignore = true)
    void updateItemEntity(OrderItemRequest request, @MappingTarget OrderItem item);

    /**
     * Список items → список DTO
     */
    List<OrderItemResponseDto> toItemDtoList(List<OrderItem> items);

}
