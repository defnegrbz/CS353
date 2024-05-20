import React from 'react';
import Navbar from '../navbar';
import { useParams } from 'react-router-dom';

const HomePage = () => {
  const id = useParams().id;
  return (
    <div>
      <Navbar id={id} />
      <h1 style={{ textAlign: 'center', marginTop: '20px' }}>Welcome to Fitness App</h1>
    </div>
  );
};

export default HomePage;
