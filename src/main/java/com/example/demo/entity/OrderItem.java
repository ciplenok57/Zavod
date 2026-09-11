package com.example.demo.entity;

import com.example.demo.entity.jsonb.OrderItemFieldValue;
import com.example.demo.entity.jsonb.OrderItemOperation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "order_items")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItem {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private Template template;

    @Column(name = "field_values", columnDefinition = "JSONB")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<OrderItemFieldValue> fieldValues = new ArrayList<>();

    @Column(name = "operations", columnDefinition = "JSONB")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<OrderItemOperation> operations = new ArrayList<>();

}
