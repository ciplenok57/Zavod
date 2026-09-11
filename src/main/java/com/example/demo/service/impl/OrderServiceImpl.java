package com.example.demo.service.impl;

import com.example.demo.dto.order.request.OrderCreateRequest;
import com.example.demo.dto.order.request.OrderUpdateRequest;
import com.example.demo.dto.order.response.OrderResponseDto;
import com.example.demo.dto.order.response.OrderShortResponseDto;
import com.example.demo.dto.order_item.request.OrderItemRequest;
import com.example.demo.dto.order_item.response.OrderItemResponseDto;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Template;
import com.example.demo.entity.jsonb.OrderItemOperation;
import com.example.demo.entity.jsonb.TemplateOperation;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.TemplateRepository;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final TemplateRepository templateRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponseDto create(OrderCreateRequest request) {
        Order order = orderMapper.toEntity(request);

        if (request.items() != null) {
            for (OrderItemRequest itemRequest : request.items()) {
                OrderItem item = buildOrderItem(itemRequest);
                order.addItem(item);
            }
        }

        Order saved = orderRepository.save(order);
        return orderMapper.toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderShortResponseDto> getAll() {
        return orderMapper.toShortDtoList(orderRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponseDto getById(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        return orderMapper.toResponseDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderShortResponseDto> search(String query) {
        return orderMapper.toShortDtoList(orderRepository.search(query));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderShortResponseDto> getByClient(String client) {
        return orderMapper.toShortDtoList(
                orderRepository.findByClientContainingIgnoreCase(client)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderShortResponseDto> getByName(String name) {
        return orderMapper.toShortDtoList(
                orderRepository.findByNameContainingIgnoreCase(name)
        );
    }

    @Override
    public OrderResponseDto update(UUID id, OrderUpdateRequest request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());

        orderMapper.updateEntity(request, order);

        if (request.items() != null) {
            order.getItems().clear();
            for (OrderItemRequest itemRequest : request.items()) {
                OrderItem item = buildOrderItem(itemRequest);
                order.addItem(item);
            }
        }

        Order saved = orderRepository.save(order);
        return orderMapper.toResponseDto(saved);
    }

    @Override
    public void delete(UUID id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException();
        }
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(UUID id) {
        return orderRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return orderRepository.count();
    }

    @Override
    public OrderResponseDto addItem(UUID orderId, OrderItemRequest itemRequest) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException());

        OrderItem item = buildOrderItem(itemRequest);
        order.addItem(item);

        Order saved = orderRepository.save(order);
        return orderMapper.toResponseDto(saved);
    }

    @Override
    public OrderResponseDto removeItem(UUID orderId, UUID itemId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException());

        boolean removed = order.getItems().removeIf(item -> item.getId().equals(itemId));
        if (!removed) {
            throw new RuntimeException();
        }

        Order saved = orderRepository.save(order);
        return orderMapper.toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItemResponseDto> getItems(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException());

        return orderMapper.toItemDtoList(order.getItems());
    }

    @Override
    @Transactional(readOnly = true)
    public OrderItemResponseDto getItem(UUID orderId, UUID itemId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException());

        return order.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .map(orderMapper::toItemDto)
                .orElseThrow(() -> new RuntimeException());
    }

    @Override
    public OrderResponseDto updateItem(UUID orderId, UUID itemId, OrderItemRequest itemRequest) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException());

        OrderItem existingItem = order.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException());

        // Обновляем template (если передан)
        if (itemRequest.templateId() != null) {
            Template template = templateRepository.findById(itemRequest.templateId())
                    .orElse(null);
            existingItem.setTemplate(template);

            // Пересобираем операции из нового шаблона
            if (template != null) {
                existingItem.setOperations(buildOperationsFromTemplate(template));
            }
        }

        // Обновляем fieldValues (если переданы)
        if (itemRequest.fieldValues() != null) {
            existingItem.setFieldValues(new ArrayList<>(itemRequest.fieldValues()));
        }

        Order saved = orderRepository.save(order);
        return orderMapper.toResponseDto(saved);
    }

    // ============================================
    // ===== PRIVATE HELPERS =====
    // ============================================

    private OrderItem buildOrderItem(OrderItemRequest request) {
        OrderItem item = orderMapper.toItemEntity(request);

        // Загружаем шаблон
        if (request.templateId() != null) {
            Template template = templateRepository.findById(request.templateId())
                    .orElse(null);
            item.setTemplate(template);

            // Копируем операции из шаблона
            if (template != null) {
                item.setOperations(buildOperationsFromTemplate(template));
            } else {
                item.setOperations(new ArrayList<>());
            }
        } else {
            item.setOperations(new ArrayList<>());
        }

        // FieldValues — из запроса или пустой список
        if (item.getFieldValues() == null) {
            item.setFieldValues(new ArrayList<>());
        }

        return item;
    }

    /**
     * Создает список операций изделия на основе операций шаблона.
     * Каждая операция получает начальный статус "PENDING".
     */
    private List<OrderItemOperation> buildOperationsFromTemplate(Template template) {
        List<OrderItemOperation> result = new ArrayList<>();

        if (template.getOperations() == null || template.getOperations().isEmpty()) {
            return result;
        }

        for (TemplateOperation templateOp : template.getOperations()) {
            OrderItemOperation op = new OrderItemOperation(
                    templateOp.id(),
                    templateOp.name(),
                    templateOp.duration(),
                    "PENDING",
                    null,
                    null,
                    null,
                    templateOp.prevOperations() != null
                            ? templateOp.prevOperations()
                            : List.of()
            );
            result.add(op);
        }

        return result;
    }

}
