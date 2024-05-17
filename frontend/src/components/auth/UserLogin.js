import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { userLogin } from '../../api/axiosConfig';

const UserLogin = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(''); // State to handle any error
    const[message, setMessage] = useState(''); // State to handle any message [e.g. "You are banned."
  
    const navigate = useNavigate();
  
    const handleLogin = async (event) => {
      event.preventDefault(); // Prevent default form submission
      try {
        const response = await userLogin(username, password);
        const { userId, userType } = response.data;
        console.log("Login!!!")
    
        if (userId === 0) {
          setError("Invalid username or password");
        } 
        else {
          if (userType === "member") {
              navigate(`/users/member/${userId}`);
          } else if (userType === "trainer") {
              navigate(`/users/trainer/${userId}`);
          } else {
              setError("Invalid user type");
          }
        }
      } catch (err) {
        setError(err.response?.data?.message || 'An error occured. Please try again.');
      }
    }
    
    return (
        <div style={{ fontFamily: 'Arial, sans-serif', maxWidth: '500px', margin: '0 auto' }}>
      <h2 style={{ textAlign: 'center', color: '#333' }}>Login</h2>
      <form onSubmit={handleLogin} style={{ backgroundColor: '#f9f9f9', padding: '20px', borderRadius: '8px', boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)' }}>
      <label style={{ display: 'block', marginBottom: '10px' }}>
        <span style={{ fontWeight: 'bold' }}>Username:</span>
        <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
      </label>
      <label style={{ display: 'block', marginBottom: '10px' }}>
        <span style={{ fontWeight: 'bold' }}>Password:</span>
        <input type="text" value={password} onChange={(e) => setPassword(e.target.value)} />
        </label>
        <button type="submit" style={{ backgroundColor: '#007bff', color: '#fff', padding: '8px 16px', borderRadius: '5px', border: 'none', cursor: 'pointer', marginTop: '10px' }}>Login</button>
      </form>
    </div>
    );
};
export default UserLogin;
      