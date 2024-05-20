import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { userLogin } from '../../api/axiosConfig';

const UserLogin = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(''); // State to handle any error
    const [message, setMessage] = useState(''); // State to handle any message [e.g. "You are banned."]
  
    const navigate = useNavigate();
  
    const handleLogin = async (event) => {
        event.preventDefault(); // Prevent default form submission
        try {
            const response = await userLogin(username, password);
            const { userId, userType } = response.data;
            console.log(userType);
            console.log("Login!!!")

            if (userId === 0) {
                console.log("0id!!!")
                setError("Invalid username or password");
            } else {
                if (userType === "member") {
                    console.log("member!!!")
                    navigate(`/members/${userId}`);
                } else if (userType === "trainer") {
                    console.log("trainer!!!")
                    navigate(`/trainers/${userId}`);
                } else {
                    console.log("invalid!!!")
                    setError("Invalid user type");
                }
            }
        } catch (err) {
            setError(err.response?.data?.message || 'An error occurred. Please try again.');
        }
    }
    
    return (
        <div style={{
            fontFamily: 'Arial, sans-serif',
            maxWidth: '400px',
            margin: '50px auto',
            padding: '20px',
            borderRadius: '10px',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
            backgroundColor: '#fff'
        }}>
            <h2 style={{ textAlign: 'center', color: '#333', marginBottom: '20px' }}>Login</h2>
            {error && <p style={{ color: '#ff0000', textAlign: 'center', marginBottom: '10px' }}>{error}</p>}
            <form onSubmit={handleLogin} style={{ display: 'flex', flexDirection: 'column' }}>
                <label style={{ marginBottom: '15px', fontWeight: 'bold', color: '#333' }}>
                    Username:
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        style={{
                            width: '100%',
                            padding: '8px',
                            marginTop: '5px',
                            border: '1px solid #ccc',
                            borderRadius: '5px',
                            boxSizing: 'border-box'
                        }}
                    />
                </label>
                <label style={{ marginBottom: '15px', fontWeight: 'bold', color: '#333' }}>
                    Password:
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        style={{
                            width: '100%',
                            padding: '8px',
                            marginTop: '5px',
                            border: '1px solid #ccc',
                            borderRadius: '5px',
                            boxSizing: 'border-box'
                        }}
                    />
                </label>
                <button type="submit" style={{
                    backgroundColor: '#007bff',
                    color: '#fff',
                    padding: '10px',
                    border: 'none',
                    borderRadius: '5px',
                    cursor: 'pointer',
                    transition: 'background-color 0.3s ease',
                    marginTop: '10px'
                }}>
                    Login
                </button>
            </form>
        </div>
    );
};

export default UserLogin;
