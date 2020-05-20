package com.assignments.mancala.mancalagameservice.service;

import com.assignments.mancala.mancalagameservice.exceptions.MancalaBoardNotFoundException;
import com.assignments.mancala.mancalagameservice.model.MancalaBoard;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public interface MancalaBoardService {

    MancalaBoard newBoard();

    MancalaBoard saveBoard(MancalaBoard mancalaBoard);

    MancalaBoard findBoard(String id) throws
            MancalaBoardNotFoundException;
}
