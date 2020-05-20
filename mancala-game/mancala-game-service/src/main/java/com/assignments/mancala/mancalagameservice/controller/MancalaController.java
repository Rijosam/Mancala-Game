package com.assignments.mancala.mancalagameservice.controller;

import com.assignments.mancala.mancalagameservice.exceptions.MancalaBoardNotFoundException;
import com.assignments.mancala.mancalagameservice.model.MancalaBoard;
import com.assignments.mancala.mancalagameservice.service.MancalaBoardMovesService;
import com.assignments.mancala.mancalagameservice.service.MancalaBoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/games")
@Api(value = "Mancala game service API")
@Slf4j
public class MancalaController {

    @Autowired MancalaBoardService mancalaBoardService;
    @Autowired MancalaBoardMovesService mancalaBoardMovesService;

    @PostMapping(value = "/boards")
    @ApiOperation(value ="Endpoint for creating a new Mancala game board",response = MancalaBoard.class)
    public ResponseEntity<MancalaBoard> startNewGame() {
        log.info("Creating a new game");
        return ResponseEntity.ok(mancalaBoardService.newBoard());

    }

    @GetMapping(value = "/boards/{id}")
    @ApiOperation(value ="Endpoint for finding an existing Mancala game board",response = MancalaBoard.class)
    public ResponseEntity<MancalaBoard> findGame(
            @ApiParam(value ="String id value of the mancala board", required = true)
            @PathVariable String id) throws MancalaBoardNotFoundException
    {
        log.info("Finding the game for id -" + id);
        return ResponseEntity.ok(mancalaBoardService.findBoard(id));
    }

    @PutMapping(value = "/boards/{id}/pits/{pitId}")
    @ApiOperation(value ="Endpoint for updating the Mancala game board with moves",response = MancalaBoard.class)
    public ResponseEntity<MancalaBoard> updateGame(
            @ApiParam(value ="Id value of the mancala board", required = true)
            @PathVariable String id,
            @ApiParam(value ="Id value of the selected pit on the mancala board", required = true)
            @PathVariable int pitId)
            throws MancalaBoardNotFoundException
    {
        log.info("Updating the game with selected pit id - " +pitId );
        return ResponseEntity.ok(mancalaBoardMovesService.getUpdatedMancalaBoard(id, pitId));
    }

}
