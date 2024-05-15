import React from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import { Link } from 'react-router-dom'; // Import Link from react-router-dom if you're using it for routing

const Navbar = () => {
  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          Fitness App
        </Typography>
        <Button component={Link} to="/" color="inherit">Home</Button>
        <Button component={Link} to="/workouts" color="inherit">Workouts</Button>
        <Button component={Link} to="/about" color="inherit">About</Button>
        {/* Add more buttons for other routes as needed */}
      </Toolbar>
    </AppBar>
  );
};

export default Navbar;
