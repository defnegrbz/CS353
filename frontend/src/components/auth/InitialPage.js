import React from 'react';
import { useNavigate } from 'react-router-dom';

const InitialPage = () => {
    const navigate = useNavigate();

    return (
        <div style={{
            fontFamily: 'Arial, sans-serif',
            maxWidth: '600px',
            margin: '50px auto',
            padding: '20px',
            borderRadius: '10px',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
            backgroundColor: '#fff',
            textAlign: 'center'
        }}>
            <h2 style={{ color: '#333', marginBottom: '20px' }}>Welcome to Fitness App</h2>
            <h3 style={{ color: '#555', marginBottom: '30px' }}>Please login or register to continue</h3>

            <div style={{ display: 'flex', justifyContent: 'center', gap: '10px' }}>
                <button
                    onClick={() => navigate('/auth/login')}
                    style={{
                        backgroundColor: '#007bff',
                        color: '#fff',
                        padding: '10px 20px',
                        borderRadius: '5px',
                        border: 'none',
                        cursor: 'pointer',
                        transition: 'background-color 0.3s ease',
                    }}
                >
                    Login
                </button>
                <button
                    onClick={() => navigate('/register/member')}
                    style={{
                        backgroundColor: '#007bff',
                        color: '#fff',
                        padding: '10px 20px',
                        borderRadius: '5px',
                        border: 'none',
                        cursor: 'pointer',
                        transition: 'background-color 0.3s ease',
                    }}
                >
                    Register As Member
                </button>
                <button
                    onClick={() => navigate('/users/register/trainer')}
                    style={{
                        backgroundColor: '#007bff',
                        color: '#fff',
                        padding: '10px 20px',
                        borderRadius: '5px',
                        border: 'none',
                        cursor: 'pointer',
                        transition: 'background-color 0.3s ease',
                    }}
                >
                    Register As Trainer
                </button>
            </div>
        </div>
    );
};

export default InitialPage;
