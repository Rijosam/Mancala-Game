import React from "react";
import { Backdrop, CircularProgress, Typography, Paper } from "@material-ui/core";

const BackdropLoader = (props) => {
  return (
    <Backdrop style={{ zIndex: 100 }} onClick={() => (props.isGameCompleted ? props.handleClose() : null)} open={props.open}>
      {props.isGameCompleted ? (
        <Paper elevation={2}>
          {props.winner === "Tie" ? (<Typography style={{ fontSize: 30, padding: "3rem" }}>Tie!! Play Again</Typography>) :
              (<Typography style={{ fontSize: 30, padding: "3rem" }}>{props.winner} has won!!</Typography>) }
        </Paper>
      ) : (
        <CircularProgress color="primary" />
      )}
    </Backdrop>
  );
};

export default BackdropLoader;
