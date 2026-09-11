package com.example.demo.repository;

import com.example.demo.entity.OrderItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderItemRepository {

    // ===== Поиск по заказу =====
    List<OrderItem> findByOrderId(UUID orderId);

    // ===== Поиск по шаблону =====
    List<OrderItem> findByTemplateId(UUID templateId);

    // ===== Количество изделий в заказе =====
    long countByOrderId(UUID orderId);

    // ===== Поиск по заказу с загрузкой шаблона =====
    @Query("SELECT oi FROM OrderItem oi " +
            "LEFT JOIN FETCH oi.template " +
            "WHERE oi.order.id = :orderId")
    List<OrderItem> findByOrderIdWithTemplate(@Param("orderId") UUID orderId);

    // ===== Удалить все изделия заказа =====
    void deleteByOrderId(UUID orderId);

    // ===== Проверка существования изделия в заказе =====
    boolean existsByOrderIdAndId(UUID orderId, UUID id);

}
