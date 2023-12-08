package com.example.demo.repository;

import com.example.demo.entity.ProductTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductTemplateRepository extends MongoRepository<ProductTemplate, UUID> {

}