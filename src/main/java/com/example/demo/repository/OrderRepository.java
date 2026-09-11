package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    // ===== Поиск по клиенту =====
    List<Order> findByClientContainingIgnoreCase(String client);

    // ===== Поиск по имени =====
    List<Order> findByNameContainingIgnoreCase(String name);

    // ===== Поиск по имени или клиенту =====
    @Query("SELECT o FROM Order o WHERE " +
            "LOWER(o.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(o.client) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Order> search(@Param("query") String query);

    // ===== Проверка существования =====
    boolean existsByName(String name);

    // ===== Получить все с сортировкой по дате =====
    List<Order> findAllByOrderByDeadlineAsc();

}
