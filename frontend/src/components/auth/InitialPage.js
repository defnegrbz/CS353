import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

const InitialPage = () => {

    
    const navigate = useNavigate();
  
    
    return (
       <>
         <div style={{ fontFamily: 'Arial, sans-serif', maxWidth: '600px', margin: '0 auto' }}>
         <h2 style={{ textAlign: 'center', color: '#333' }}>Welcome to Fitness App</h2>
            <h3 style={{ textAlign: 'center', color: '#333' }}>Please login or register to continue</h3>

            <button onClick={() => navigate('/users/login/member-login')} style={{ backgroundColor: '#007bff', color: '#fff', padding: '8px 16px', borderRadius: '5px', border: 'none', cursor: 'pointer', marginTop: '10px' }}>Login As Member</button>
            <button onClick={() => navigate('/users/login/trainer-login')} style={{ backgroundColor: '#007bff', color: '#fff', padding: '8px 16px', borderRadius: '5px', border: 'none', cursor: 'pointer', marginTop: '10px', marginLeft: '10px' }}>Login As Trainer</button>
            
            <button onClick={() => navigate('/users/register/member')} style={{ backgroundColor: '#007bff', color: '#fff', padding: '8px 16px', borderRadius: '5px', border: 'none', cursor: 'pointer', marginTop: '10px', marginLeft: '10px' }}>Register As Member</button>
            <button onClick={() => navigate('/users/register/trainer')} style={{ backgroundColor: '#007bff', color: '#fff', padding: '8px 16px', borderRadius: '5px', border: 'none', cursor: 'pointer', marginTop: '10px', marginLeft: '10px' }}>Register As Trainer</button>
        </div>
       </>
    );
};
export default InitialPage;
      