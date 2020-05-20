package com.assignments.mancala.mancalagameservice.model;

import com.assignments.mancala.mancalagameservice.exceptions.MancalaException;
import com.assignments.mancala.mancalagameservice.utils.MancalaConstants;
import com.assignments.mancala.mancalagameservice.utils.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class MancalaBoard {
    @Id
    String Id;
    List<MancalaPit> mancalaPits;
    Player currentPlayer;
    @JsonIgnore
    int currentPitId;

    public MancalaBoard(int pitStones){
        this.mancalaPits = Arrays.asList(
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_A,pitStones),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_A,pitStones),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_A,pitStones),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_A,pitStones),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_A,pitStones),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_A,pitStones),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_A,MancalaConstants.EMPTY_STONES),
                new MancalaPit(MancalaConstants.PIT_ONE_PLAYER_B,pitStones),
                new MancalaPit(MancalaConstants.PIT_TWO_PLAYER_B,pitStones),
                new MancalaPit(MancalaConstants.PIT_THREE_PLAYER_B,pitStones),
                new MancalaPit(MancalaConstants.PIT_FOUR_PLAYER_B,pitStones),
                new MancalaPit(MancalaConstants.PIT_FIVE_PLAYER_B,pitStones),
                new MancalaPit(MancalaConstants.PIT_SIX_PLAYER_B,pitStones),
                new MancalaPit(MancalaConstants.MANCALA_PLAYER_B,MancalaConstants.EMPTY_STONES)
        );
    }

    public MancalaPit getPit(Integer pitId)  {
        try {
            return this.mancalaPits.get(pitId - 1);
        }
        catch (Exception e){
            throw new MancalaException("Invalid pit index, given index is " + pitId);
        }
    }
}
