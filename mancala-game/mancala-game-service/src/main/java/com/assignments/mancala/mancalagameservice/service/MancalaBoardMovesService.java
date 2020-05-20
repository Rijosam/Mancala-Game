package com.assignments.mancala.mancalagameservice.service;

import com.assignments.mancala.mancalagameservice.exceptions.MancalaBoardNotFoundException;
import com.assignments.mancala.mancalagameservice.model.MancalaBoard;

public interface MancalaBoardMovesService {
    MancalaBoard getUpdatedMancalaBoard(String boardId, int selectedPitId)
            throws MancalaBoardNotFoundException;
}
