package com.assignments.mancala.mancalagameservice.utils;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;


public class MancalaConstants {

    private MancalaConstants(){}

    public static final int PIT_ONE_PLAYER_A = 1;
    public static final int PIT_TWO_PLAYER_A = 2;
    public static final int PIT_THREE_PLAYER_A = 3;
    public static final int PIT_FOUR_PLAYER_A = 4;
    public static final int PIT_FIVE_PLAYER_A = 5;
    public static final int PIT_SIX_PLAYER_A = 6;

    public static final int PIT_ONE_PLAYER_B = 8;
    public static final int PIT_TWO_PLAYER_B = 9;
    public static final int PIT_THREE_PLAYER_B = 10;
    public static final int PIT_FOUR_PLAYER_B = 11;
    public static final int PIT_FIVE_PLAYER_B = 12;
    public static final int PIT_SIX_PLAYER_B = 13;

    public static final int MANCALA_PLAYER_A = 7;
    public static final int MANCALA_PLAYER_B = 14;
    public static final int TOTAL_PITS = 14;
    public static final int EMPTY_STONES = 0;
    public static final int DEFAULT_STONES = 6;

    //Maping between player and mancala pit
    public static final Map<Player,Integer> playerMancalaMap = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(Player.PLAYER_A,MANCALA_PLAYER_A),
            new AbstractMap.SimpleEntry<>(Player.PLAYER_B,MANCALA_PLAYER_B)
    );
    //Maping between player and players pits
    public static final Map<Player, Set<Integer>> playerPitsIdMap = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(Player.PLAYER_A,
                    Set.of(PIT_ONE_PLAYER_A, PIT_TWO_PLAYER_A, PIT_THREE_PLAYER_A, PIT_FOUR_PLAYER_A, PIT_FIVE_PLAYER_A,
                            PIT_SIX_PLAYER_A)),
            new AbstractMap.SimpleEntry<>(Player.PLAYER_B,
                    Set.of(PIT_ONE_PLAYER_B, PIT_TWO_PLAYER_B, PIT_THREE_PLAYER_B, PIT_FOUR_PLAYER_B, PIT_FIVE_PLAYER_B,
                            PIT_SIX_PLAYER_B))
    );

}
