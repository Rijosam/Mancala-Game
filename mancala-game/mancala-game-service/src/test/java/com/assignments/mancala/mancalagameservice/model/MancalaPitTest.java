package com.assignments.mancala.mancalagameservice.model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class MancalaPitTest {

    @InjectMocks
    MancalaPit mancalaPit;

    @Test
    @DisplayName("Test for adding one stones in pit")
    public void addstoneTest(){
        mancalaPit.addStone();
        assertEquals(1,mancalaPit.getStones());
    }

    @Test
    @DisplayName("Testing the return of isEmpty function when there is no stones in pit")
    public void isEmptyTest(){
        assertEquals(true,mancalaPit.isEmpty());
    }

    @Test
    @DisplayName("Testing the return of isEmpty function when there is stones in pit")
    public void isNotEmptyTest(){
        mancalaPit.setStones(3);
        assertEquals(false,mancalaPit.isEmpty());
    }

    @Test
    @DisplayName("Test for emptying the pit")
    public void emptyStonesTest(){
        mancalaPit.setStones(4);
        mancalaPit.emptyStones();
        assertEquals(0,mancalaPit.getStones());
    }

    @Test
    @DisplayName("Test for adding any number of stones to the pit")
    public void addStonesTest(){
       mancalaPit.addStones(5);
       assertEquals(5,mancalaPit.getStones());
    }

}
