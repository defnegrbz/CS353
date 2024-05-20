import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { trainerRegister } from '../../api/axiosConfig';

const RegisterTrainer = () => {
    const [fullName, setFullName] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [gender, setGender] = useState('');
    const [mail, setMail] = useState('');
    const [birthdate, setBirthdate] = useState('');
    const [trainerDescription, setTrainerDescription] = useState('');
    const [specialization, setSpecialization] = useState('');
    const [trainerExperience, setTrainerExperience] = useState('');
    const [certificate, setCertificate] = useState('');
    const [error, setError] = useState('');
    const [message, setMessage] = useState('');

    const navigate = useNavigate();

    const handleRegister = async (event) => {
        event.preventDefault();
        // Convert birthdate to LocalDate format
        const formattedBirthdate = new Date(birthdate).toISOString().split('T')[0];
        
        try {
            const response = await trainerRegister(fullName,
                username,
                password,
                gender,
                mail,
                birthdate,
                trainerDescription,
                specialization,
                trainerExperience,
                certificate);
            console.log("Response:", response); 

            if (response) {
                setMessage("Registration successful");
                navigate(`/auth/login`); // Redirect to trainer's profile page
            }
        } catch (err) {
            setError(err.response?.data?.message || 'An error occurred. Please try again.');
        }
    };

    return (
        <div style={{ fontFamily: 'Arial, sans-serif', maxWidth: '500px', margin: '0 auto' }}>
            <h2 style={{ textAlign: 'center', color: '#333' }}>Trainer Registration</h2>
            <form onSubmit={handleRegister} style={{ backgroundColor: '#f9f9f9', padding: '20px', borderRadius: '8px', boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)' }}>
            {/* Add form fields for trainer registration */}
            {/* Similar to member registration, add input fields for fullName, username, password, gender, mail, birthdate */}
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
            {/* Add input fields for trainer-specific details */}
            <label style={{ display: 'block', marginBottom: '10px' }}>
                <span style={{ fontWeight: 'bold' }}>Trainer Description:</span>
                <textarea
                    value={trainerDescription}
                    onChange={(e) => setTrainerDescription(e.target.value)}
                    style={{ width: '100%', padding: '8px', marginTop: '5px', borderRadius: '4px', border: '1px solid #ccc' }}
                />
            </label>
            <label style={{ display: 'block', marginBottom: '10px' }}>
                <span style={{ fontWeight: 'bold' }}>Specialization:</span>
                <input
                    type="text"
                    value={specialization}
                    onChange={(e) => setSpecialization(e.target.value)}
                    style={{ width: '100%', padding: '8px', marginTop: '5px', borderRadius: '4px', border: '1px solid #ccc' }}
                />
            </label>
            <label style={{ display: 'block', marginBottom: '10px' }}>
                <span style={{ fontWeight: 'bold' }}>Trainer Experience:</span>
                <input
                    type="number"
                    value={trainerExperience}
                    onChange={(e) => setTrainerExperience(e.target.value)}
                    style={{ width: '100%', padding: '8px', marginTop: '5px', borderRadius: '4px', border: '1px solid #ccc' }}
                />
            </label>
            <label style={{ display: 'block', marginBottom: '10px' }}>
                <span style={{ fontWeight: 'bold' }}>Certificate:</span>
                <input
                    type="text"
                    value={certificate}
                    onChange={(e) => setCertificate(e.target.value)}
                    style={{ width: '100%', padding: '8px', marginTop: '5px', borderRadius: '4px', border: '1px solid #ccc' }}
                />
            </label>
            {error && <div style={{ color: 'red', marginBottom: '10px' }}>{error}</div>}
            {message && <div style={{ color: 'green', marginBottom: '10px' }}>{message}</div>}
            <button type="submit" style={{ backgroundColor: '#007bff', color: '#fff', padding: '10px 20px', borderRadius: '5px', border: 'none', cursor: 'pointer', marginTop: '10px' }}>Register</button>
            {/* Handle state changes accordingly */}
            {/* Handle submission and API call similarly to member registration */}

            </form>
        </div>
    );
};

export default RegisterTrainer;
