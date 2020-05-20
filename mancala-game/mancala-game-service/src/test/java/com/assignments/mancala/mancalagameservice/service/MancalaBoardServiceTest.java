package com.assignments.mancala.mancalagameservice.service;

import com.assignments.mancala.mancalagameservice.exceptions.MancalaBoardNotFoundException;
import com.assignments.mancala.mancalagameservice.model.MancalaBoard;
import com.assignments.mancala.mancalagameservice.repository.MancalaRepo;
import com.assignments.mancala.mancalagameservice.utils.MancalaConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MancalaBoardServiceTest {

    public static final String ID = "3423425";
    @Mock
    MancalaRepo mancalaRepo;
    @InjectMocks
    MancalaBoardServiceImpl mancalaBoardService;
    
    @Test
    @DisplayName("Creating a new mancala board")
    public void createGameTest(){
        MancalaBoard mancalaBoard = mancalaBoardService.newBoard();
        assertEquals(MancalaConstants.DEFAULT_STONES, mancalaBoard.getPit(1).getStones());
    }

    @Test
    @DisplayName("Updating the mancala board")
    public void updateGameTest(){
        MancalaBoard board = new MancalaBoard(4);
        when(mancalaRepo.save(board)).thenReturn(board);
        MancalaBoard mancalaBoard = mancalaBoardService.saveBoard(board);
        assertEquals(4, mancalaBoard.getPit(1).getStones());
    }

    @Test
    @DisplayName("Finding an existing mancala board")
    public void findGameTest() throws MancalaBoardNotFoundException {
        MancalaBoard board = new MancalaBoard(4);
        when(mancalaRepo.findById(ID)).thenReturn(Optional.of(board));
        MancalaBoard mancalaBoard = mancalaBoardService.findBoard(ID);
        assertEquals(4, mancalaBoard.getPit(1).getStones());
    }

    @Test
    @DisplayName("Exception is thrown when mancala board id not found")
    public void findGameExceptionTest() {
        MancalaBoardNotFoundException mancalaBoardNotFoundException = assertThrows(
                MancalaBoardNotFoundException.class,
                () -> {
                    mancalaBoardService.findBoard(ID);
                });
        assertTrue(mancalaBoardNotFoundException.getMessage().equals("Game not found with Id - 3423425"));
    }
}
