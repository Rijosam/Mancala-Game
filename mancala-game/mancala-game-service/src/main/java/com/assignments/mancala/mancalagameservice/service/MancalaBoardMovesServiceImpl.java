package com.assignments.mancala.mancalagameservice.service;

import com.assignments.mancala.mancalagameservice.exceptions.MancalaBoardNotFoundException;
import com.assignments.mancala.mancalagameservice.exceptions.MancalaException;
import com.assignments.mancala.mancalagameservice.model.MancalaBoard;
import com.assignments.mancala.mancalagameservice.model.MancalaPit;
import com.assignments.mancala.mancalagameservice.utils.MancalaConstants;
import com.assignments.mancala.mancalagameservice.utils.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
@Slf4j
public class MancalaBoardMovesServiceImpl implements MancalaBoardMovesService {

    @Autowired MancalaBoardService mancalaBoardService;


    @Override public MancalaBoard getUpdatedMancalaBoard(String boardId, int selectedPitId)
            throws MancalaBoardNotFoundException
    {

        MancalaBoard mancalaBoard = mancalaBoardService.findBoard(boardId);
        MancalaPit selectedPit = mancalaBoard.getPit(selectedPitId);
        if(null == mancalaBoard.getCurrentPlayer()) {
            setFirstPlayer(mancalaBoard, selectedPit);
        }
        //Checking whether the selected pit belongs to the current player and the pit is not a mancala pit
        if(!MancalaConstants.playerPitsIdMap.get(mancalaBoard.getCurrentPlayer()).contains(selectedPitId)
            || selectedPitId == MancalaConstants.MANCALA_PLAYER_A || selectedPitId == MancalaConstants.MANCALA_PLAYER_B){
            log.error("Selected pit {} is not allowed for the player {} ",selectedPitId,mancalaBoard.getCurrentPlayer());
            throw new MancalaException("Selected pit is not allowed for " +
                    mancalaBoard.getCurrentPlayer());
        }

        if(selectedPit.isEmpty()){
            log.info("Selected pit {} is empty",selectedPitId);
            mancalaBoard.setCurrentPlayer(exchangeTurn(mancalaBoard.getCurrentPlayer()));
            return mancalaBoard;
        }
        mancalaBoard.setCurrentPitId(selectedPit.getPitId());
        int stones = selectedPit.getStones();
        selectedPit.emptyStones();
        //for n-1 stones putting them in next pits
        IntStream.range(0, stones-1)
                .forEach(stone-> {
                    MancalaPit targetPit = getTargetPit(mancalaBoard);
                    targetPit.addStone();
                    mancalaBoard.setCurrentPitId(targetPit.getPitId());
                });

        MancalaPit lastPit =getTargetPit(mancalaBoard);
        mancalaBoard.setCurrentPitId(lastPit.getPitId());
        
        //Checking whether the last pit is current players mancala pit
        if(MancalaConstants.playerMancalaMap.get(mancalaBoard.getCurrentPlayer()).equals(lastPit.getPitId())){
            lastPit.addStone();
            setNextPlayer(mancalaBoard);
            mancalaBoardService.saveBoard(mancalaBoard);
            return mancalaBoard;
        }
        
        //Checking whether the last pit is current players non empty pit and not  mancala pit
        if(lastPit.isEmpty() && !MancalaConstants.playerMancalaMap.get(mancalaBoard.getCurrentPlayer()).equals(lastPit.getPitId()) &&
                (MancalaConstants.playerPitsIdMap.get(mancalaBoard.getCurrentPlayer()).contains(lastPit.getPitId()))) {

            captureStones(mancalaBoard, lastPit);
            setNextPlayer(mancalaBoard);
            mancalaBoardService.saveBoard(mancalaBoard);
            return mancalaBoard;
        }
        //Incrementing the last pit when it is neither empty nor mancala pit
        lastPit.addStone();
        setNextPlayer(mancalaBoard);
        mancalaBoardService.saveBoard(mancalaBoard);
        return mancalaBoard;

    }

    private void captureStones(MancalaBoard mancalaBoard, MancalaPit lastPit) {
        MancalaPit oppositePit = mancalaBoard.getPit(MancalaConstants.TOTAL_PITS - lastPit.getPitId());
        if(!oppositePit.isEmpty()) {
            //Adding the captured stones to the current players mancala pit
            mancalaBoard.getPit(MancalaConstants.playerMancalaMap.get(mancalaBoard.getCurrentPlayer()))
                    .addStones(oppositePit.getStones() + 1);
            oppositePit.emptyStones();
        }
        else{
            lastPit.addStone();
        }
    }

    private MancalaPit getTargetPit(MancalaBoard mancalaBoard) {

        int nextPitId = mancalaBoard.getCurrentPitId() % MancalaConstants.TOTAL_PITS +1;
        //Checking whether the next pit is opponent  players mancala pit
        if(MancalaConstants.playerMancalaMap.get(exchangeTurn(mancalaBoard.getCurrentPlayer())).equals(nextPitId)){
            nextPitId = nextPitId % MancalaConstants.TOTAL_PITS +1;
        }
        return mancalaBoard.getPit(nextPitId);

    }

    private void setFirstPlayer(MancalaBoard mancalaBoard, MancalaPit selectedPit) {
        //Checking whether the selected pit belongs to current player
        if (selectedPit.getPitId() < MancalaConstants.MANCALA_PLAYER_A) {
            mancalaBoard.setCurrentPlayer(Player.PLAYER_A);
        } else {
            mancalaBoard.setCurrentPlayer(Player.PLAYER_B);
        }
    }

    private void setNextPlayer(MancalaBoard mancalaBoard) {
        //Checking the current pit is current players mancala pit
        if(! MancalaConstants.playerMancalaMap.get(mancalaBoard.getCurrentPlayer()).equals(mancalaBoard.getCurrentPitId())){
            mancalaBoard.setCurrentPlayer(exchangeTurn(mancalaBoard.getCurrentPlayer()));
        }
    }

    private Player exchangeTurn(Player currentPlayer) {
        if(currentPlayer.equals(Player.PLAYER_A)){
            return Player.PLAYER_B;
        }
        else{
            return Player.PLAYER_A;
        }
    }

}
