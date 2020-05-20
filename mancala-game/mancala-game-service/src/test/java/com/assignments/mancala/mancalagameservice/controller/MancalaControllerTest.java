package com.assignments.mancala.mancalagameservice.controller;

import com.assignments.mancala.mancalagameservice.exceptions.MancalaBoardNotFoundException;
import com.assignments.mancala.mancalagameservice.model.MancalaBoard;
import com.assignments.mancala.mancalagameservice.service.MancalaBoardMovesService;
import com.assignments.mancala.mancalagameservice.service.MancalaBoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
class MancalaControllerTest {

    private final static String URI = "/games";
    public static final String ID = "546757";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MancalaBoardService mancalaBoardService;

    @MockBean
    private MancalaBoardMovesService mancalaBoardMovesService;

    @Test
    @DisplayName("Test for starting the board")
    public void startGameTest() throws Exception {

        MancalaBoard mancalaBoard = new MancalaBoard(6);
        mancalaBoard.setId(ID);
        when(mancalaBoardService.newBoard()).thenReturn(mancalaBoard);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI + "/boards")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(ID));
    }

    @Test
    @DisplayName("Test for finding an existing the board")
    public void findGameTest() throws Exception {

        MancalaBoard mancalaBoard = new MancalaBoard(6);
        mancalaBoard.setId(ID);
        when(mancalaBoardService.findBoard(ID)).thenReturn(mancalaBoard);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI + "/boards/546757")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(ID));
    }

    @Test
    @DisplayName("Exception thrown when there is no requested board")
    public void findGameTestException() throws Exception {

        MancalaBoard mancalaBoard = new MancalaBoard(6);
        mancalaBoard.setId(ID);
        when(mancalaBoardService.findBoard(ID)).thenThrow(new MancalaBoardNotFoundException("No board found"));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI + "/boards/546757")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("Test for updating the board with moves")
    public void updateGameTest() throws Exception {

        MancalaBoard mancalaBoard = new MancalaBoard(6);
        mancalaBoard.setId(ID);
        when(mancalaBoardMovesService.getUpdatedMancalaBoard(ID,12)).thenReturn(mancalaBoard);
        this.mockMvc
                .perform(MockMvcRequestBuilders.put(URI + "/boards/546757/pits/12")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(ID));
    }

}
