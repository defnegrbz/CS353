import React, { useState, useEffect } from 'react';
import Navbar from '../navbar';
import Grid from '@mui/material/Grid';
import Button from '@mui/material/Button';
import { Link } from 'react-router-dom';
import { getMember } from '../../api/axiosConfig'; // Import the function to fetch user profile data
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const UserProfile = () => {
  const [userData, setUserData] = useState(null);
  const { userId } = useParams();
  const navigate = useNavigate();
  const [error, setError] = useState(''); 

  useEffect(() => {
    getMember(userId)
      .then(response => {
        setUserData(response.data);
      })
      .catch(error => {
        setError(error.response?.data?.message || 'Wrong password or email. Please try again.');
      });
  }, [userId]);


  return (
    <>
      <Navbar />
      <Grid container>
        <Grid item xs={12}>
          <h1 style={{ textAlign: 'center' }}>User Profile</h1>
        </Grid>
      </Grid>
      <Grid container justifyContent="center">
        <Grid item xs={6}>
          {userData && (
            <div style={{ textAlign: 'center' }}>
              <p>Name: {userData.full_name}</p>
              <p>Email: {userData.email}</p>
              <p>Age: {userData.age}</p>
              {/* Display other user health data */}
              <p>Goals: {userData.goals}</p>
            </div>
          )}
          <nav style={{ marginTop: '20px' }}>
            <ul style={{ listStyleType: 'none', padding: 0 }}>
              <li style={{ margin: '10px' }}>
                <Link to="/workouts" style={{ textDecoration: 'none' }}>
                  <Button variant="contained" color="primary">
                    Workouts
                  </Button>
                </Link>
              </li>
              <li style={{ margin: '10px' }}>
                <Link to="/trainers" style={{ textDecoration: 'none' }}>
                  <Button variant="contained" color="primary">
                    Trainers
                  </Button>
                </Link>
              </li>
              <li style={{ margin: '10px' }}>
                <Link to="/workoutLog" style={{ textDecoration: 'none' }}>
                  <Button variant="contained" color="primary">
                    Workout Log
                  </Button>
                </Link>
              </li>
              {/* Add more links as needed */}
            </ul>
          </nav>
        </Grid>
      </Grid>
    </>
  );
};

export default UserProfile;
