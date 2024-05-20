import React, { useState, useEffect } from 'react';
import Navbar from '../navbar';
import Grid from '@mui/material/Grid';
import Button from '@mui/material/Button';
import { Link } from 'react-router-dom';
import { getTrainer } from '../../api/axiosConfig'; // Import the function to fetch user profile data
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const UserProfile = () => {
  const [userData, setUserData] = useState(null);
  const { userId } = useParams();
  const navigate = useNavigate();
  const [error, setError] = useState(''); 

  useEffect(() => {
    getTrainer(userId)
      .then(response => {
        setUserData(response.data);
        console.log(response.data)
      })
      .catch(error => {
        setError(error.response?.data?.message || 'An error occurred. Please try again.');
      });
  }, [userId]);

  return (
    <>
      <Navbar id={userId}/>
      <Grid container>
        <Grid item xs={12}>
          <h1 style={{ textAlign: 'center' }}>User Profile</h1>
        </Grid>
      </Grid>
      <Grid container justifyContent="center">
        <Grid item xs={6}>
          {userData && (
            <div style={{ textAlign: 'center' }}>
              <p>Full Name: {userData.fullName}</p>
              <p>Rating: {userData.trainerRating}</p>
            </div>
          )}
          <nav style={{ marginTop: '20px' }}>
            <ul style={{ listStyleType: 'none', padding: 0 }}>
              <li style={{ margin: '10px' }}>
                <Link to={`/workouts-trainer/${userId}`} style={{ textDecoration: 'none' }}>
                  <Button variant="contained" color="primary">
                    Workouts
                  </Button>
                </Link>
              </li>
              <li style={{ margin: '10px' }}>
                <Link to={`/trainers/busyDates/${userId}`} style={{ textDecoration: 'none' }}>
                  <Button variant="contained" color="primary">
                    Busy Dates
                  </Button>
                </Link>
              </li>
            </ul>
          </nav>
        </Grid>
        </Grid>
        <Button
            variant="contained"
            color="primary"
            onClick={() => navigate(`/edit-profile/${userId}`)}
          >Edit Profile</Button>
    </>
  );
};

export default UserProfile;
