import React from "react";
import { Snackbar } from "@material-ui/core";
import MuiAlert from "@material-ui/lab/Alert";

function Alert(props) {
  return <MuiAlert elevation={6} variant="filled" {...props} />;
}

const SnackBar = (props) => {
  const { handleClose, open } = props;
  return (
    <Snackbar open={open} autoHideDuration={1000} onClose={handleClose}>
      <Alert onClose={handleClose} severity="success">
        This game has started
      </Alert>
    </Snackbar>
  );
};

export default SnackBar;
