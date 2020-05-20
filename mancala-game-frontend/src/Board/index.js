import React from "react";
import Grid from "@material-ui/core/Grid";
import Box from "../Box";

class Board extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  onCellClick = (id) => {
    alert(id);
  };
  render() {
    const mancalaPits = this.props.mancalaPits;
    const currentPlayerId = this.props.currentPlayerId;

    const playerAMancalaPitStones = (mancalaPits.mancalaPits && mancalaPits.mancalaPits[0] && mancalaPits.mancalaPits[0].stones) || 0;
    const playerBMancalaPitStones = (mancalaPits.mancalaPits && mancalaPits.mancalaPits[1] && mancalaPits.mancalaPits[1].stones) || 0;
    return (
      <Grid container direction="row">
        <Grid item xs={2} style={{ height: "100vh", display: "flex", flexDirection: "column", justifyContent: "center", padding: 20 }}>
          <Grid container direction="row" justify="center" alignItems="center">
            <Box inputStyles={{ height: "50vh", width: "70%" }} stoneCount={playerBMancalaPitStones} />
          </Grid>
        </Grid>
        <Grid item xs={8}>
          <Grid container direction="row" style={{ height: "100vh" }}>
            <Grid item xs={12}>
              <Grid container direction="row" justify="center" alignItems="center" spacing={1} style={{ height: "100%" }}>
                {mancalaPits &&
                  mancalaPits.playerBPits &&
                  mancalaPits.playerBPits.reverse().map((i) => (
                    <Grid item xs={2}>
                      <Box player={"PLAYER_B"} currentPlayerId={currentPlayerId} id={i.pitId} onCellClick={this.props.onPitClick} stoneCount={i.stones} />
                    </Grid>
                  ))}
              </Grid>
            </Grid>
            <Grid item xs={12}>
              <Grid container direction="row" justify="center" alignItems="center" spacing={1} style={{ height: "100%" }}>
                {mancalaPits &&
                  mancalaPits.playerAPits &&
                  mancalaPits.playerAPits.map((i) => (
                    <Grid item xs={2}>
                      <Box player={"PLAYER_A"} currentPlayerId={currentPlayerId} id={i.pitId} onCellClick={this.props.onPitClick} stoneCount={i.stones} />
                    </Grid>
                  ))}
              </Grid>
            </Grid>
          </Grid>
        </Grid>
        <Grid item xs={2} style={{ height: "100vh", display: "flex", flexDirection: "column", justifyContent: "center", padding: 20 }}>
          <Grid container direction="row" justify="center" alignItems="center">
            <Box inputStyles={{ height: "50vh", width: "70%" }} stoneCount={playerAMancalaPitStones} />
          </Grid>
        </Grid>
      </Grid>
    );
  }
}

export default Board;
