package com.assignments.mancala.mancalagameservice.service;

import com.assignments.mancala.mancalagameservice.exceptions.MancalaBoardNotFoundException;
import com.assignments.mancala.mancalagameservice.model.MancalaBoard;
import com.assignments.mancala.mancalagameservice.repository.MancalaRepo;
import com.assignments.mancala.mancalagameservice.utils.MancalaConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class MancalaBoardServiceImpl implements MancalaBoardService {

    @Autowired
    MancalaRepo mancalaRepo;

    //Creating a new board with six stones and saving to database
    @Override
    public MancalaBoard newBoard(){
        MancalaBoard mancalaBoard = new MancalaBoard(MancalaConstants.DEFAULT_STONES);
        saveBoard(mancalaBoard);
        return mancalaBoard;
    }

    //Save board to cache and database
    @Override
    @CachePut(value = "mancalaBoard", key = "#mancalaBoard.id")
    public MancalaBoard saveBoard(MancalaBoard mancalaBoard){
        return mancalaRepo.save(mancalaBoard);
    }

    //Fetch board data from cache or database
    @Override
    @Cacheable(value = "mancalaBoard", key = "#id")
    public MancalaBoard findBoard(String id) throws MancalaBoardNotFoundException {
       log.info("Reading the board data from database with id" +id);
        Optional<MancalaBoard> mancalaBoardOptional = mancalaRepo.findById(id);
       if(!mancalaBoardOptional.isPresent())
           throw new MancalaBoardNotFoundException("Game not found with Id - " + id);
       return mancalaBoardOptional.get();
    }
}
