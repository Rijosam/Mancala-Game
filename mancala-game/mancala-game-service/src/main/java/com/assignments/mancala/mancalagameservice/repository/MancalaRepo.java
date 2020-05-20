package com.assignments.mancala.mancalagameservice.repository;

import com.assignments.mancala.mancalagameservice.model.MancalaBoard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface MancalaRepo extends MongoRepository<MancalaBoard, String> {
    @Query("{ '_id' : ?0 }")
    Optional<MancalaBoard> findById(String id);
}
