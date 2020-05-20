package com.assignments.mancala.mancalagameservice.service;

import com.assignments.mancala.mancalagameservice.exceptions.MancalaBoardNotFoundException;
import com.assignments.mancala.mancalagameservice.exceptions.MancalaException;
import com.assignments.mancala.mancalagameservice.model.MancalaBoard;
import com.assignments.mancala.mancalagameservice.model.MancalaPit;
import com.assignments.mancala.mancalagameservice.utils.MancalaConstants;
import com.assignments.mancala.mancalagameservice.utils.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MancalaBoardMovesServiceTest {

    @Mock
    MancalaBoardService mancalaBoardServiceMock;
    @InjectMocks
    MancalaBoardMovesServiceImpl mancalaBoardMovesService;


    @Test
    @DisplayName("No stones is added to player B's Mancala during player A's turn")
    void boardMovesScenarioTest1() throws MancalaBoardNotFoundException {

        MancalaBoard initialBoard = new MancalaBoard();
        initialBoard.setMancalaPits(Arrays.asList(
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_A,3),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_A,9),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_A,MancalaConstants.EMPTY_STONES),
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_B,6),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_B,6),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_B,6),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_B,6),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_B,6),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_B,6),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_B,MancalaConstants.EMPTY_STONES)
        ));
        when(mancalaBoardServiceMock.findBoard(Mockito.anyString())).thenReturn(initialBoard);
        MancalaBoard resultBoard = mancalaBoardMovesService.getUpdatedMancalaBoard("23543t5346", 6);
        assertEquals(0, resultBoard.getPit(MancalaConstants.PIT_SIX_PLAYER_A).getStones());
        assertEquals(1, resultBoard.getPit(MancalaConstants.MANCALA_PLAYER_A).getStones());
        assertEquals(0, resultBoard.getPit(MancalaConstants.MANCALA_PLAYER_B).getStones());
    }

    @Test
    @DisplayName("No stones is added to player A's Mancala during player B's turn")
    void boardMovesScenarioTest2() throws MancalaBoardNotFoundException {

        MancalaBoard initialBoard = new MancalaBoard();
        initialBoard.setMancalaPits(Arrays.asList(
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_A,3),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_A,9),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_A,MancalaConstants.EMPTY_STONES),
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_B,3),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_B,6),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_B,6),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_B,6),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_B,6),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_B,9),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_B,MancalaConstants.EMPTY_STONES)
        ));
        when(mancalaBoardServiceMock.findBoard(Mockito.anyString())).thenReturn(initialBoard);
        MancalaBoard resultBoard = mancalaBoardMovesService.getUpdatedMancalaBoard("23543t5346", 13);
        assertEquals(0, resultBoard.getPit(MancalaConstants.PIT_SIX_PLAYER_B).getStones());
        assertEquals(1, resultBoard.getPit(MancalaConstants.MANCALA_PLAYER_B).getStones());
        assertEquals(0, resultBoard.getPit(MancalaConstants.MANCALA_PLAYER_A).getStones());
    }

    @Test
    @DisplayName("player turn change Player B to Player A")
    void boardMovesScenarioTest3() throws MancalaBoardNotFoundException {

        MancalaBoard initialBoard = new MancalaBoard();
        initialBoard.setMancalaPits(Arrays.asList(
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_A,3),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_A,9),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_A,MancalaConstants.EMPTY_STONES),
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_B,3),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_B,6),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_B,6),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_B,6),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_B,6),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_B,3),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_B,MancalaConstants.EMPTY_STONES)
        ));
        when(mancalaBoardServiceMock.findBoard(Mockito.anyString())).thenReturn(initialBoard);
        MancalaBoard resultBoard = mancalaBoardMovesService.getUpdatedMancalaBoard("23543t5346", 13);
        assertEquals(Player.PLAYER_A, resultBoard.getCurrentPlayer());
    }

    @Test
    @DisplayName("player turn change Player A to Player B")
    void boardMovesScenarioTest4() throws MancalaBoardNotFoundException {

        MancalaBoard initialBoard = new MancalaBoard();
        initialBoard.setMancalaPits(Arrays.asList(
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_A,3),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_A,9),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_A,MancalaConstants.EMPTY_STONES),
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_B,3),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_B,6),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_B,6),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_B,6),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_B,6),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_B,3),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_B,MancalaConstants.EMPTY_STONES)
        ));
        when(mancalaBoardServiceMock.findBoard(Mockito.anyString())).thenReturn(initialBoard);
        MancalaBoard resultBoard = mancalaBoardMovesService.getUpdatedMancalaBoard("23543t5346", 3);
        assertEquals(Player.PLAYER_B, resultBoard.getCurrentPlayer());
    }

    @Test
    @DisplayName("No player turn change for Player B")
    void boardMovesScenarioTest5() throws MancalaBoardNotFoundException {

        MancalaBoard initialBoard = new MancalaBoard();
        initialBoard.setMancalaPits(Arrays.asList(
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_A,0),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_A,MancalaConstants.EMPTY_STONES),
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_B,2),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_B,0),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_B,MancalaConstants.EMPTY_STONES)
        ));
        when(mancalaBoardServiceMock.findBoard(Mockito.anyString())).thenReturn(initialBoard);
        MancalaBoard resultBoard = mancalaBoardMovesService.getUpdatedMancalaBoard("23543t5346", 12);
        assertEquals(Player.PLAYER_B, resultBoard.getCurrentPlayer());
    }

    @Test
    @DisplayName("No player turn change for Player A")
    void boardMovesScenarioTest6() throws MancalaBoardNotFoundException {

        MancalaBoard initialBoard = new MancalaBoard();
        initialBoard.setMancalaPits(Arrays.asList(
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_A,1),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_A,MancalaConstants.EMPTY_STONES),
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_B,2),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_B,0),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_B,MancalaConstants.EMPTY_STONES)
        ));
        when(mancalaBoardServiceMock.findBoard(Mockito.anyString())).thenReturn(initialBoard);
        MancalaBoard resultBoard = mancalaBoardMovesService.getUpdatedMancalaBoard("23543t5346", 1);
        assertEquals(Player.PLAYER_A, resultBoard.getCurrentPlayer());
    }


    @Test
    @DisplayName("Capture situation for player A")
    void boardMovesScenarioTest7() throws MancalaBoardNotFoundException {

        MancalaBoard initialBoard = new MancalaBoard();
        initialBoard.setMancalaPits(Arrays.asList(
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_A,1),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_A,0),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_A,MancalaConstants.EMPTY_STONES),
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_B,4),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_B,3),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_B,5),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_B,MancalaConstants.EMPTY_STONES)
        ));
        when(mancalaBoardServiceMock.findBoard(Mockito.anyString())).thenReturn(initialBoard);
        MancalaBoard resultBoard = mancalaBoardMovesService.getUpdatedMancalaBoard("23543t5346", 1);
        assertEquals(4, resultBoard.getPit(MancalaConstants.MANCALA_PLAYER_A).getStones());
        assertEquals(Player.PLAYER_B, resultBoard.getCurrentPlayer());
    }

    @Test
    @DisplayName("Capture situation for player A with his mancala is not empty ")
    void boardMovesScenarioTest8() throws MancalaBoardNotFoundException {

        MancalaBoard initialBoard = new MancalaBoard();
        initialBoard.setMancalaPits(Arrays.asList(
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_A,1),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_A,0),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_A,10),
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_B,4),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_B,3),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_B,5),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_B,MancalaConstants.EMPTY_STONES)
        ));
        when(mancalaBoardServiceMock.findBoard(Mockito.anyString())).thenReturn(initialBoard);
        MancalaBoard resultBoard = mancalaBoardMovesService.getUpdatedMancalaBoard("23543t5346", 1);
        assertEquals(14, resultBoard.getPit(MancalaConstants.MANCALA_PLAYER_A).getStones());
        assertEquals(Player.PLAYER_B, resultBoard.getCurrentPlayer());
    }


    @Test
    @DisplayName("Capture situation for player B")
    void boardMovesScenarioTest9() throws MancalaBoardNotFoundException {

        MancalaBoard initialBoard = new MancalaBoard();
        initialBoard.setMancalaPits(Arrays.asList(
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_A,1),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_A,5),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_A,0),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_A,MancalaConstants.EMPTY_STONES),
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_B,4),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_B,4),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_B,5),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_B,MancalaConstants.EMPTY_STONES)
        ));
        when(mancalaBoardServiceMock.findBoard(Mockito.anyString())).thenReturn(initialBoard);
        MancalaBoard resultBoard = mancalaBoardMovesService.getUpdatedMancalaBoard("23543t5346", 8);
        assertEquals(6, resultBoard.getPit(MancalaConstants.MANCALA_PLAYER_B).getStones());
        assertEquals(Player.PLAYER_A, resultBoard.getCurrentPlayer());
    }

    @Test
    @DisplayName("No Capture when in the last stone is in Mancala store of player B in his turn")
    void boardMovesScenarioTest10() throws MancalaBoardNotFoundException {

        MancalaBoard initialBoard = new MancalaBoard();
        initialBoard.setMancalaPits(Arrays.asList(
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_A,1),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_A,1),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_B,4),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_B,3),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_B,1),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_B,0)
        ));
        when(mancalaBoardServiceMock.findBoard(Mockito.anyString())).thenReturn(initialBoard);
        MancalaBoard resultBoard = mancalaBoardMovesService.getUpdatedMancalaBoard("23543t5346", 13);
        assertEquals(1, resultBoard.getPit(MancalaConstants.MANCALA_PLAYER_B).getStones());
        assertEquals(Player.PLAYER_B, resultBoard.getCurrentPlayer());
    }

    @Test
    @DisplayName("No Capture when in the last stone is in Mancala store of player A in his turn")
    void boardMovesScenarioTest11() throws MancalaBoardNotFoundException {

        MancalaBoard initialBoard = new MancalaBoard();
        initialBoard.setMancalaPits(Arrays.asList(
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_A,1),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_A,1),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_B,4),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_B,3),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_B,1),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_B,10)
        ));
        when(mancalaBoardServiceMock.findBoard(Mockito.anyString())).thenReturn(initialBoard);
        MancalaBoard resultBoard = mancalaBoardMovesService.getUpdatedMancalaBoard("23543t5346", 6);
        assertEquals(7, resultBoard.getPit(MancalaConstants.MANCALA_PLAYER_A).getStones());
        assertEquals(Player.PLAYER_A, resultBoard.getCurrentPlayer());
    }

    @Test
    @DisplayName("Player A selects a pit which is empty")
    void boardMovesScenarioTest12() throws MancalaBoardNotFoundException {

        MancalaBoard initialBoard = new MancalaBoard();
        initialBoard.setMancalaPits(Arrays.asList(
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_A,1),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_A,1),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_B,4),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_B,3),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_B,1),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_B,10)
        ));
        when(mancalaBoardServiceMock.findBoard(Mockito.anyString())).thenReturn(initialBoard);
        MancalaBoard resultBoard = mancalaBoardMovesService.getUpdatedMancalaBoard("23543t5346", 2);
        assertEquals(Player.PLAYER_B, resultBoard.getCurrentPlayer());
    }

    @Test
    @DisplayName("No Capture when in the last stone is in empty pit of player A in Player B turn")
    void boardMovesScenarioTest13() throws MancalaBoardNotFoundException {

        MancalaBoard initialBoard = new MancalaBoard();
        initialBoard.setMancalaPits(Arrays.asList(
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_A,0),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_B,3),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_B,4),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_B,0)
        ));
        when(mancalaBoardServiceMock.findBoard(Mockito.anyString())).thenReturn(initialBoard);
        MancalaBoard resultBoard = mancalaBoardMovesService.getUpdatedMancalaBoard("23543t5346", 12);
        assertEquals(1, resultBoard.getPit(MancalaConstants.PIT_ONE_PLAYER_A).getStones());
        assertEquals(1, resultBoard.getPit(MancalaConstants.MANCALA_PLAYER_B).getStones());
        assertEquals(Player.PLAYER_A, resultBoard.getCurrentPlayer());
    }
    @Test
    @DisplayName("No Capture when in the last stone is in a non empty pit ")
    void boardMovesScenarioTest14() throws MancalaBoardNotFoundException {

        MancalaBoard initialBoard = new MancalaBoard();
        initialBoard.setMancalaPits(Arrays.asList(
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_A,1),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_A,0),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_B,3),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_B,4),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_B,0)
        ));
        when(mancalaBoardServiceMock.findBoard(Mockito.anyString())).thenReturn(initialBoard);
        MancalaBoard resultBoard = mancalaBoardMovesService.getUpdatedMancalaBoard("23543t5346", 12);
        assertEquals(2, resultBoard.getPit(MancalaConstants.PIT_ONE_PLAYER_A).getStones());
        assertEquals(1, resultBoard.getPit(MancalaConstants.MANCALA_PLAYER_B).getStones());
        assertEquals(Player.PLAYER_A, resultBoard.getCurrentPlayer());
    }

    @Test
    @DisplayName("Exception thrown when Player A selects Player B's pit ")
    void boardMovesScenarioTest15() throws MancalaBoardNotFoundException, MancalaException {

        MancalaBoard initialBoard = new MancalaBoard();
        initialBoard.setMancalaPits(Arrays.asList(
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_A,7),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_A,7),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_B,3),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_B,5),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_B,3),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_B,4),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_B,0)
        ));
        initialBoard.setCurrentPlayer(Player.PLAYER_A);
        when(mancalaBoardServiceMock.findBoard(Mockito.anyString())).thenReturn(initialBoard);
        assertThrows(MancalaException.class,
                () -> mancalaBoardMovesService.getUpdatedMancalaBoard("23543t5346", 12));
    }

    @Test
    @DisplayName("Exception thrown when Player B selects Player A's pit ")
    void boardMovesScenarioTest16() throws MancalaBoardNotFoundException, MancalaException {

        MancalaBoard initialBoard = new MancalaBoard();
        initialBoard.setMancalaPits(Arrays.asList(
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_A,7),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_A,7),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_B,3),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_B,5),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_B,3),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_B,4),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_B,0)
        ));
        initialBoard.setCurrentPlayer(Player.PLAYER_B);
        when(mancalaBoardServiceMock.findBoard(Mockito.anyString())).thenReturn(initialBoard);
        assertThrows(MancalaException.class,
                () -> mancalaBoardMovesService.getUpdatedMancalaBoard("23543t5346", 2));
    }

    @Test
    @DisplayName("Exception thrown when Player selects Macala pit ")
    void boardMovesScenarioTest17() throws MancalaBoardNotFoundException, MancalaException {

        MancalaBoard initialBoard = new MancalaBoard();
        initialBoard.setMancalaPits(Arrays.asList(
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_A,6),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_A,7),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_A,7),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_A,0),
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_B,3),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_B,5),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_B,0),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_B,3),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_B,4),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_B,0)
        ));
        initialBoard.setCurrentPlayer(Player.PLAYER_A);
        when(mancalaBoardServiceMock.findBoard(Mockito.anyString())).thenReturn(initialBoard);
        assertThrows(MancalaException.class,
                () -> mancalaBoardMovesService.getUpdatedMancalaBoard("23543t5346", 14));
        when(mancalaBoardServiceMock.findBoard(Mockito.anyString())).thenReturn(initialBoard);
        assertThrows(MancalaException.class,
                () -> mancalaBoardMovesService.getUpdatedMancalaBoard("23543t5346", 7));
    }

}
