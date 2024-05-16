import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { memberLogin } from '../../api/axiosConfig';

const MemberLogin = () => {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(''); // State to handle any error
    const[message, setMessage] = useState(''); // State to handle any message [e.g. "You are banned."
  
    const navigate = useNavigate();
  
    const handleLogin = async (event) => {
      event.preventDefault(); // Prevent default form submission
      try {
        const response = await memberLogin(email, password);
        console.log(response.data);
        console.log("Login!!!")
        const userId = response.data; 
    
        if (response.data === "Password is incorrect") {
          setError("Password is incorrect");
        }
        else if (response.data === "This email does not exist") {
          setError("This email does not exist");
        }
        else {
          navigate(`/member-profile/${userId}`); // Redirect to user's profile page
        }
      } catch (err) {
        setError(err.response?.data?.message || 'An error occured. Please try again.');
      }
    }
    
    return (
        <div style={{ fontFamily: 'Arial, sans-serif', maxWidth: '500px', margin: '0 auto' }}>
      <h2 style={{ textAlign: 'center', color: '#333' }}>Login As Member</h2>
      <form onSubmit={handleLogin} style={{ backgroundColor: '#f9f9f9', padding: '20px', borderRadius: '8px', boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)' }}>
      <label style={{ display: 'block', marginBottom: '10px' }}>
        <span style={{ fontWeight: 'bold' }}>Email:</span>
        <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} />
      </label>
      <label style={{ display: 'block', marginBottom: '10px' }}>
        <span style={{ fontWeight: 'bold' }}>Password:</span>
        <input type="text" value={password} onChange={(e) => setPassword(e.target.value)} />
        </label>
        <button type="submit" style={{ backgroundColor: '#007bff', color: '#fff', padding: '8px 16px', borderRadius: '5px', border: 'none', cursor: 'pointer', marginTop: '10px' }}>Create Workout</button>
      </form>
    </div>
    );
};
export default MemberLogin;
      