package com.example.demo.repository;

import com.example.demo.entity.Construction;
import com.example.demo.entity.Material;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConstructionRepository extends MongoRepository<Construction, UUID> {

}