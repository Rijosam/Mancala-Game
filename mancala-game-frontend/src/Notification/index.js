import React from "react";
import { withStyles } from "@material-ui/core/styles";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import MuiDialogTitle from "@material-ui/core/DialogTitle";
import MuiDialogContent from "@material-ui/core/DialogContent";
import MuiDialogActions from "@material-ui/core/DialogActions";
import IconButton from "@material-ui/core/IconButton";
// import CloseIcon from "@material-ui/icons/Close";
import Typography from "@material-ui/core/Typography";

const styles = (theme) => ({
  root: {
    margin: 0,
    padding: theme.spacing(2),
  },
  closeButton: {
    position: "absolute",
    right: theme.spacing(1),
    top: theme.spacing(1),
    color: theme.palette.grey[500],
  },
});

const DialogTitle = withStyles(styles)((props) => {
  const { children, classes, onClose, ...other } = props;
  return (
    <MuiDialogTitle disableTypography className={classes.root} {...other}>
      <Typography variant="h6">{children}</Typography>
      {onClose ? (
        <IconButton aria-label="close" className={classes.closeButton} onClick={onClose}>
          x
        </IconButton>
      ) : null}
    </MuiDialogTitle>
  );
});

const DialogContent = withStyles((theme) => ({
  root: {
    padding: theme.spacing(2),
  },
}))(MuiDialogContent);

const DialogActions = withStyles((theme) => ({
  root: {
    margin: 0,
    padding: theme.spacing(1),
  },
}))(MuiDialogActions);

export default function CustomizedDialogs(props) {
  return (
    <div>
      <Dialog maxWidth={"sm"} fullWidth={true} open={props.open} onClose={() => props.onClose()} aria-labelledby="customized-dialog-title">
        <DialogTitle id="customized-dialog-title">Are you ready to start a new game?</DialogTitle>
        <DialogContent dividers>
          <ol>
            <li>Any player can start the game by clicking the pits</li>
            <li>Six pits on the bottom are for Player One </li>
            <li>Six pits on the top are for Player Two</li>
            <li>Game starts with six stones in each pit</li>
            <li>Right big pit is Player One's Mancala and Left big pit is Player Two's Mancala</li>
            <li>When all the pits are empty on one players side, Game ends</li>
            <li>Player with maximum stones in his/her Mancala, at the end, is the winner!!</li>
          </ol>
        </DialogContent>
        <DialogActions>
          <Button autoFocus onClick={() => props.onStartGame()} color="primary">
            Start game
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
