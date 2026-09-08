package com.example.demo.repository;

import com.example.demo.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TemplateRepository extends JpaRepository<Template, UUID> {

    Optional<Template> findByName(String name);

    @Query("SELECT t FROM Template t WHERE t.name LIKE CONCAT('%', :query, '%') OR t.description LIKE CONCAT('%', :query, '%')")
    List<Template> searchTemplates(@Param("query") String query);

    @Query("SELECT t FROM Template t WHERE t.category = :category")
    List<Template> findByCategory(@Param("category") String category);
}