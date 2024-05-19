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
        console.log(response.data)
        setUserData(response.data);
      })
      .catch(error => {
        setError(error.response?.data?.message || 'An error occurred. Please try again.');
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
              <p>Height: {userData.height}</p>
              <p>Weight: {userData.weight}</p>
              <p>Allergies: {userData.allergies}</p>
              <p>Diseases: {userData.diseases}</p>
              <p>Medications: {userData.medications}</p>
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
              <li style={{ margin: '10px' }}>
                <Link to="/nutritionLog" style={{ textDecoration: 'none' }}>
                  <Button variant="contained" color="primary">
                    Nutrition Log
                  </Button>
                </Link>
              </li>
              <li style={{ margin: '10px' }}>
                <Link to="/member/setGoal" style={{ textDecoration: 'none' }}>
                  <Button variant="contained" color="primary">
                    Set Goal
                  </Button>
                </Link>
              </li>
            </ul>
          </nav>
        </Grid>
      </Grid>
    </>
  );
};

export default UserProfile;
