import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { memberRegister } from '../../api/axiosConfig';

const RegisterMember = () => {
    const [fullName, setFullName] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [gender, setGender] = useState('');
    const [mail, setMail] = useState('');
    const [birthdate, setBirthdate] = useState('');
    const [profilePicture, setProfilePicture] = useState('');
    const [fitnessGoals, setFitnessGoals] = useState('');
    const [error, setError] = useState('');
    const [message, setMessage] = useState('');

    const navigate = useNavigate();

    const handleRegister = async (event) => {
        event.preventDefault();
        try {
            const memberData = {
                fullName,
                username,
                password,
                gender,
                mail,
                birthdate,
                profilePicture,
                fitnessGoals: fitnessGoals.split(',').map(goal => goal.trim())
            };
            console.log("gelirsin buraya");
            const response = await memberRegister(memberData);
            console.log("Nah gelirsin buraya");

            if (response.data) {
                setMessage("Registration successful");
                navigate(`/member-profile/${response.data.id}`); // Redirect to member's profile page
            }
        } catch (err) {
            setError(err.response?.data?.message || 'An error occurred. Please try again.');
        }
    };

    return (
        <div style={{ fontFamily: 'Arial, sans-serif', maxWidth: '500px', margin: '0 auto' }}>
            <h2 style={{ textAlign: 'center', color: '#333' }}>Register</h2>
            <form onSubmit={handleRegister} style={{ backgroundColor: '#f9f9f9', padding: '20px', borderRadius: '8px', boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)' }}>
                <label style={{ display: 'block', marginBottom: '10px' }}>
                    <span style={{ fontWeight: 'bold' }}>Full Name:</span>
                    <input
                        type="text"
                        value={fullName}
                        onChange={(e) => setFullName(e.target.value)}
                        style={{ width: '100%', padding: '8px', marginTop: '5px', borderRadius: '4px', border: '1px solid #ccc' }}
                    />
                </label>
                <label style={{ display: 'block', marginBottom: '10px' }}>
                    <span style={{ fontWeight: 'bold' }}>Username:</span>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        style={{ width: '100%', padding: '8px', marginTop: '5px', borderRadius: '4px', border: '1px solid #ccc' }}
                    />
                </label>
                <label style={{ display: 'block', marginBottom: '10px' }}>
                    <span style={{ fontWeight: 'bold' }}>Password:</span>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        style={{ width: '100%', padding: '8px', marginTop: '5px', borderRadius: '4px', border: '1px solid #ccc' }}
                    />
                </label>
                <label style={{ display: 'block', marginBottom: '10px' }}>
                    <span style={{ fontWeight: 'bold' }}>Gender:</span>
                    <input
                        type="text"
                        value={gender}
                        onChange={(e) => setGender(e.target.value)}
                        style={{ width: '100%', padding: '8px', marginTop: '5px', borderRadius: '4px', border: '1px solid #ccc' }}
                    />
                </label>
                <label style={{ display: 'block', marginBottom: '10px' }}>
                    <span style={{ fontWeight: 'bold' }}>Email:</span>
                    <input
                        type="email"
                        value={mail}
                        onChange={(e) => setMail(e.target.value)}
                        style={{ width: '100%', padding: '8px', marginTop: '5px', borderRadius: '4px', border: '1px solid #ccc' }}
                    />
                </label>
                <label style={{ display: 'block', marginBottom: '10px' }}>
                    <span style={{ fontWeight: 'bold' }}>Birthdate:</span>
                    <input
                        type="date"
                        value={birthdate}
                        onChange={(e) => setBirthdate(e.target.value)}
                        style={{ width: '100%', padding: '8px', marginTop: '5px', borderRadius: '4px', border: '1px solid #ccc' }}
                    />
                </label>
                <label style={{ display: 'block', marginBottom: '10px' }}>
                    <span style={{ fontWeight: 'bold' }}>Profile Picture URL:</span>
                    <input
                        type="text"
                        value={profilePicture}
                        onChange={(e) => setProfilePicture(e.target.value)}
                        style={{ width: '100%', padding: '8px', marginTop: '5px', borderRadius: '4px', border: '1px solid #ccc' }}
                    />
                </label>
                <label style={{ display: 'block', marginBottom: '10px' }}>
                    <span style={{ fontWeight: 'bold' }}>Fitness Goals:</span>
                    <input
                        type="text"
                        value={fitnessGoals}
                        onChange={(e) => setFitnessGoals(e.target.value)}
                        placeholder="Enter goals separated by commas"
                        style={{ width: '100%', padding: '8px', marginTop: '5px', borderRadius: '4px', border: '1px solid #ccc' }}
                    />
                </label>
                {error && <div style={{ color: 'red', marginBottom: '10px' }}>{error}</div>}
                {message && <div style={{ color: 'green', marginBottom: '10px' }}>{message}</div>}
                <button type="submit" style={{ backgroundColor: '#007bff', color: '#fff', padding: '10px 20px', borderRadius: '5px', border: 'none', cursor: 'pointer', marginTop: '10px' }}>Register</button>
            </form>
        </div>
    );
};

export default RegisterMember;