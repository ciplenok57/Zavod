package com.example.demo.repository;

import com.example.demo.entity.TemplateCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TemplateCategoryRepository extends JpaRepository<TemplateCategory, UUID> {

    Optional<TemplateCategory> findByName(String name);

    @Query("SELECT c FROM TemplateCategory c WHERE c.name LIKE CONCAT('%', :query, '%')")
    List<TemplateCategory> searchCategories(@Param("query") String query);

    boolean existsByName(String name);
}