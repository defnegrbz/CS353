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
    const [height, setHeight] = useState('');
    const [weight, setWeight] = useState('');
    const [allergies, setAllergies] = useState('');
    const [diseases, setDiseases] = useState('');
    const [medications, setMedications] = useState('');
    const [fitnessGoals, setFitnessGoals] = useState('');
    const [error, setError] = useState('');
    const [message, setMessage] = useState('');

    const navigate = useNavigate();

    const handleRegister = async (event) => {
        event.preventDefault();
        // Convert birthdate to LocalDate format
        const formattedBirthdate = new Date(birthdate).toISOString().split('T')[0];

        // Parse height and weight to ensure they are numeric
        const numericHeight = parseInt(height, 10);
        const numericWeight = parseFloat(weight);

        try {
            const response = await memberRegister(
                fullName,
                username,
                password,
                gender,
                mail,
                birthdate,
                numericHeight,
                numericWeight,
                allergies,
                diseases,
                medications,
                fitnessGoals
            );
            console.log("Response:", response);

            if (response) {
                setMessage("Registration successful");
                navigate(`/auth/login`);
            }
        } catch (err) {
            setError(err.response?.data?.message || 'An error occurred. Please try again.');
        }
    };

    return (
        <div style={{
            fontFamily: 'Arial, sans-serif',
            maxWidth: '600px',
            margin: '50px auto',
            padding: '20px',
            borderRadius: '10px',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
            backgroundColor: '#fff'
        }}>
            <h2 style={{ textAlign: 'center', color: '#333', marginBottom: '20px' }}>Register</h2>
            <form onSubmit={handleRegister} style={{ display: 'flex', flexDirection: 'column' }}>
                <label style={{ marginBottom: '15px', fontWeight: 'bold', color: '#333' }}>
                    Full Name:
                    <input
                        type="text"
                        value={fullName}
                        onChange={(e) => setFullName(e.target.value)}
                        style={inputStyle}
                    />
                </label>
                <label style={{ marginBottom: '15px', fontWeight: 'bold', color: '#333' }}>
                    Username:
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        style={inputStyle}
                    />
                </label>
                <label style={{ marginBottom: '15px', fontWeight: 'bold', color: '#333' }}>
                    Password:
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        style={inputStyle}
                    />
                </label>
                <label style={{ marginBottom: '15px', fontWeight: 'bold', color: '#333' }}>
                    Gender:
                    <input
                        type="text"
                        value={gender}
                        onChange={(e) => setGender(e.target.value)}
                        style={inputStyle}
                    />
                </label>
                <label style={{ marginBottom: '15px', fontWeight: 'bold', color: '#333' }}>
                    Email:
                    <input
                        type="email"
                        value={mail}
                        onChange={(e) => setMail(e.target.value)}
                        style={inputStyle}
                    />
                </label>
                <label style={{ marginBottom: '15px', fontWeight: 'bold', color: '#333' }}>
                    Birthdate:
                    <input
                        type="date"
                        value={birthdate}
                        onChange={(e) => setBirthdate(e.target.value)}
                        style={inputStyle}
                    />
                </label>
                <label style={{ marginBottom: '15px', fontWeight: 'bold', color: '#333' }}>
                    Fitness Goals:
                    <select value={fitnessGoals} onChange={(e) => setFitnessGoals(e.target.value)} style={inputStyle}>
                        <option value="">Select Fitness Goal</option>
                        <option value="muscle gain">Muscle Gain</option>
                        <option value="lose weight">Lose Weight</option>
                        <option value="endurance training">Endurance Training</option>
                    </select>
                </label>
                <label style={{ marginBottom: '15px', fontWeight: 'bold', color: '#333' }}>
                    Height (cm):
                    <input
                        type="number"
                        value={height}
                        onChange={(e) => setHeight(e.target.value)}
                        placeholder="Enter height in cm"
                        style={inputStyle}
                    />
                </label>
                <label style={{ marginBottom: '15px', fontWeight: 'bold', color: '#333' }}>
                    Weight (kg):
                    <input
                        type="number"
                        value={weight}
                        onChange={(e) => setWeight(e.target.value)}
                        placeholder="Enter weight in kg"
                        style={inputStyle}
                    />
                </label>
                <label style={{ marginBottom: '15px', fontWeight: 'bold', color: '#333' }}>
                    Allergies:
                    <input
                        type="text"
                        value={allergies}
                        onChange={(e) => setAllergies(e.target.value)}
                        placeholder="Enter none if no allergies"
                        style={inputStyle}
                    />
                </label>
                <label style={{ marginBottom: '15px', fontWeight: 'bold', color: '#333' }}>
                    Diseases:
                    <input
                        type="text"
                        value={diseases}
                        onChange={(e) => setDiseases(e.target.value)}
                        placeholder="Enter none if no diseases"
                        style={inputStyle}
                    />
                </label>
                <label style={{ marginBottom: '15px', fontWeight: 'bold', color: '#333' }}>
                    Medications:
                    <input
                        type="text"
                        value={medications}
                        onChange={(e) => setMedications(e.target.value)}
                        placeholder="Enter none if no medications"
                        style={inputStyle}
                    />
                </label>
                {error && <div style={{ color: 'red', marginBottom: '10px' }}>{error}</div>}
                {message && <div style={{ color: 'green', marginBottom: '10px' }}>{message}</div>}
                <button type="submit" style={{
                    backgroundColor: '#007bff',
                    color: '#fff',
                    padding: '10px 20px',
                    borderRadius: '5px',
                    border: 'none',
                    cursor: 'pointer',
                    marginTop: '10px',
                    transition: 'background-color 0.3s ease'
                }}>
                    Register
                </button>
            </form>
        </div>
    );
};

const inputStyle = {
    width: '100%',
    padding: '8px',
    marginTop: '5px',
    borderRadius: '5px',
    border: '1px solid #ccc',
    boxSizing: 'border-box'
};

export default RegisterMember;
