import React from "react";
import "./App.css";
import Board from "./Board";
import { Grid, AppBar, Typography, Button, Paper } from "@material-ui/core";
import CustomizedDialogs from "./Notification";
import BackdropLoader from "./BackdropLoader";
import SnackBar from "./SnackBar";
import { baseUrl } from "./config";

const playerADefaultValue = [
  { pitId: 1, stones: 0 },
  { pitId: 2, stones: 0 },
  { pitId: 3, stones: 0 },
  { pitId: 4, stones: 0 },
  { pitId: 5, stones: 0 },
  { pitId: 6, stones: 0 },
];

const playerBDefaultValue = [
  { pitId: 8, stones: 0 },
  { pitId: 9, stones: 0 },
  { pitId: 10, stones: 0 },
  { pitId: 11, stones: 0 },
  { pitId: 12, stones: 0 },
  { pitId: 13, stones: 0 },
];

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      openDialog: false,
      isLoading: false,
      onSnackBar: false,
      mancalaPits: {
        playerAPits: playerADefaultValue,
        playerBPits: playerBDefaultValue,
        mancalaPits: [],
      },
      gameId: null,
      currentPlayerId: "",
      gameCompleted: false,
      gameWinner: "",
    };
  }

  onDataFormat = async (jsonResponse) => {
    let mancalaPits = {};
    mancalaPits.playerAPits = jsonResponse.mancalaPits.filter((pit) => pit.pitId <= 6);
    mancalaPits.playerBPits = jsonResponse.mancalaPits.filter((pit) => pit.pitId > 7 && pit.pitId <= 13);
    mancalaPits.mancalaPits = jsonResponse.mancalaPits.filter((pit) => pit.pitId === 7 || pit.pitId === 14);
    return mancalaPits;
  };

  onStartGame = async () => {
    try {
      this.setState((prev) => ({ isLoading: !prev.isLoading, openDialog: !prev.openDialog }));
      const headers = new Headers();
      headers.append("Content-Type", "application/json");
      headers.append("Access-Control-Allow-Origin","*");
      let URL = baseUrl + `games/boards`;
      const options = { method: "POST", headers, mode: "cors" };
      let response = await fetch(URL, options);
      let jsonResponse = await response.json();
      let mancalaPits = await this.onDataFormat(jsonResponse);
      this.setState((prev) => ({ isLoading: false, onSnackBar: !prev.onSnackBar, mancalaPits, gameId: jsonResponse.id, currentPlayerId: "all",gameCompleted: false }));
    } catch (error) {}
  };

  calculateWinner = () => {
    const mancalaPits = this.state.mancalaPits;
    const playerAStoneCount = mancalaPits.playerAPits.reduce((a, b) => a + b.stones, 0);
    const playerBStoneCount = mancalaPits.playerBPits.reduce((a, b) => a + b.stones, 0);
    if (playerAStoneCount === 0 || playerBStoneCount === 0) {
      const playedAMancalaPitCount = mancalaPits.mancalaPits[0].stones;
      const playedBMancalaPitCount = mancalaPits.mancalaPits[1].stones;
      if (playedAMancalaPitCount > playedBMancalaPitCount) this.setState({ gameCompleted: true, gameWinner: "Player One", isLoading: true, currentPlayerId: "" });
      else if(playedAMancalaPitCount < playedBMancalaPitCount) this.setState({ gameCompleted: true, gameWinner: "Player Two", isLoading: true, currentPlayerId: "" });
      else this.setState({ gameCompleted: true, gameWinner: "Tie", isLoading: true, currentPlayerId: "" });
    }
  };

  onPitClick = async (id) => {
    try {
      this.setState((prev) => ({ isLoading: !prev.isLoading }));
      const headers = new Headers();
      headers.append("Content-Type", "application/json");
      let URL = baseUrl + `games/boards/${this.state.gameId}/pits/${id}`;

      const options = { method: "PUT" };
      let response = await fetch(URL, options);
      let jsonResponse = await response.json();

      let mancalaPits = await this.onDataFormat(jsonResponse);

      this.setState(
        (prev) => ({ isLoading: !prev.isLoading, mancalaPits, gameId: jsonResponse.id, currentPlayerId: jsonResponse.currentPlayer }),
        () => this.calculateWinner()
      );
    } catch (error) {}
  };

  handleOnGameCompleteClose = () => {
    this.setState({
      openDialog: false,
      isLoading: false,
      onSnackBar: false,
      mancalaPits: {
        playerAPits: playerADefaultValue,
        playerBPits: playerBDefaultValue,
        mancalaPits: [],
      },
      gameId: null,
      currentPlayerId: "",
      gameCompleted: false,
      gameWinner: "",
    });
  };

  render() {
    return (
      <div className="App" style={{ height: "100vh", width: "100wh" }}>
        <AppBar position="static" style={{ padding: 10 }}>
          <Grid container direction="row" justify="space-between" alignItems="center">
            <Typography variant="h6">Mancala</Typography>

            <Button onClick={() => this.setState((prev) => ({ openDialog: !prev.openDialog }))} color="inherit">
              Start
            </Button>
          </Grid>
        </AppBar>
        <div style={{ position: "relative" }}>
          <Board mancalaPits={this.state.mancalaPits} onPitClick={this.onPitClick} currentPlayerId={this.state.currentPlayerId} />

          <BackdropLoader open={this.state.isLoading} winner={this.state.gameWinner} isGameCompleted={this.state.gameCompleted} handleClose={() => this.handleOnGameCompleteClose()} />

          <CustomizedDialogs onStartGame={this.onStartGame} onClose={() => this.setState((prev) => ({ openDialog: !prev.openDialog }))} open={this.state.openDialog} />
          <SnackBar open={this.state.onSnackBar} handleClose={() => this.setState((prev) => ({ onSnackBar: !prev.onSnackBar }))} />

          <Paper className={this.state.currentPlayerId === "PLAYER_B" ? "pulse" : ""} elevation={3} style={{ width: 100, position: "absolute", top: 25, right: "51%", transform: "translateX(51%)" }}>
            <Typography style={{ fontSize: 15 }}>Player Two </Typography>
          </Paper>
          <Paper className={this.state.currentPlayerId === "PLAYER_A" ? "pulse" : ""} elevation={3} style={{ width: 100, position: "absolute", bottom: 60, right: "51%", transform: "translateX(51%)" }}>
            <Typography style={{ fontSize: 15 }}>Player One</Typography>
          </Paper>
        </div>
      </div>
    );
  }
}

export default App;
