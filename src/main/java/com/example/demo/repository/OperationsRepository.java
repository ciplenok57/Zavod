package com.example.demo.repository;

import com.example.demo.entity.Operation;
import com.example.demo.entity.OperationCategory;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OperationsRepository extends JpaRepository<Operation, UUID> {

    Page<Operation> findAll(Pageable pageable);

    List<Operation> findAllByCategoryId(UUID categoryId);

}