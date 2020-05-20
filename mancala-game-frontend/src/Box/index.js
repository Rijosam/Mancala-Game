import React from "react";
import { Paper, Grid, Typography, ButtonBase } from "@material-ui/core";

class Box extends React.Component {
  render() {
    const { stoneCount, id, onCellClick, currentPlayerId, player } = this.props;
    return (
      <Paper
        disabled={currentPlayerId === "all" ? false : currentPlayerId !== player ? true : false}
        centerRipple={true}
        component={onCellClick ? ButtonBase : Paper}
        onClick={() => (onCellClick ? onCellClick(id) : null)}
        elevation={5}
        style={{ width: 120, height: 120, borderRadius: "100%", justifyContent: "center", alignItems: "center", display: "flex", ...this.props.inputStyles }}
      >
        <Typography style={{ fontSize: 26 }}>{stoneCount}</Typography>
      </Paper>
    );
  }
}

export default Box;

const Stone = () => {
  return <div style={{ backgroundColor: "brown", width: 20, height: 20 }}></div>;
};
