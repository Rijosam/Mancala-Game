package com.assignments.mancala.mancalagameservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MancalaPit {

    private int pitId;
    private int stones;

    public void addStone(){
        this.stones ++;
    }

    public void addStones(int stones){
        this.stones += stones;
    }

    @JsonIgnore
    public boolean isEmpty(){
        return stones ==0;
    }

    public void emptyStones(){
        this.stones = 0;
    }

}
