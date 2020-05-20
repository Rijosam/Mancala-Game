package com.assignments.mancala.mancalagameservice.model;
import com.assignments.mancala.mancalagameservice.exceptions.MancalaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class MancalaBoardTest {

    MancalaBoard mancalaBoard;

    @Test
    @DisplayName("pit is retrived with pit id is request")
    public void getPitTest(){
        mancalaBoard = new MancalaBoard(4);
        System.out.println(mancalaBoard.getMancalaPits());
        MancalaPit pit = mancalaBoard.getPit(5);
        assertEquals(4,pit.getStones());
    }

    @Test
    @DisplayName("Exception thrown when an invalid pit id request")
    public void getPitTests() throws MancalaException {
        mancalaBoard = new MancalaBoard(4);
        MancalaException mancalaException = Assertions.assertThrows(
                MancalaException.class,
                () -> {
                    mancalaBoard.getPit(15);
                }
        );
        assertTrue(mancalaException.getMessage().equals("Invalid pit index, given index is 15"));
    }
}
