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
              <p>Name: {userData.full_name}</p>
              <p>Email: {userData.email}</p>
              <p>Age: {userData.age}</p>
              {/* Display other user health data */}
              <p>Goals: {userData.goals}</p>
            </div>
          )}
          

        </Grid>
        <Button
            
            variant="contained"
            color="primary"
            onClick={() => navigate(`/edit-profile/${userId}`)}
          >Edit Profile</Button>
      </Grid>
    </>
  );
};

export default UserProfile;
