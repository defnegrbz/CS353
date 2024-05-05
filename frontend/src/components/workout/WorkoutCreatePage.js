import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { addWorkout } from '../../api/axiosConfig'; 

const WorkoutCreationPage = () => {
  const { trainerID } = useParams(); // Extract trainerID from URL params
  const navigate = useNavigate();
  const [workout_title, setWorkoutTitle] = useState('');
  const [workout_type, setWorkoutType] = useState('');
  const [target_audience, setTargetAudience] = useState('');
  const [workout_estimated_time, setWorkoutEstimatedTime] = useState('');
  const [workout_description, setWorkoutDesription] = useState('');
  const [equipments, setEquipments] = useState('');
  const [calories_burn_per_unit_time, setCaloorieBurnPerUnitTime] = useState('');
  const [intensity_level, setIntensityLevel] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
        const config = {
          headers: {
            'Content-Type': 'application/json'
          }
        };
    
      await addWorkout(
        trainerID,
        workout_title,
        workout_type,
        target_audience,
        workout_estimated_time,
        workout_description,
        equipments,
        calories_burn_per_unit_time,
        intensity_level,
        config
      ); // Use addWorkout function from axiosConfig.js
      console.log('Workout created successfully!');
    } catch (error) {
      console.error('Error creating workout:', error);
      alert('Failed to create workout. Please try again.');
    }
  };

  return (
    <div style={{ fontFamily: 'Arial, sans-serif', maxWidth: '500px', margin: '0 auto' }}>
      <h2 style={{ textAlign: 'center', color: '#333' }}>Create a New Workout</h2>
      <form onSubmit={handleSubmit} style={{ backgroundColor: '#f9f9f9', padding: '20px', borderRadius: '8px', boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)' }}>
      <label style={{ display: 'block', marginBottom: '10px' }}>
        <span style={{ fontWeight: 'bold' }}>Workout Title:</span>
        <input type="text" value={workout_title} onChange={(e) => setWorkoutTitle(e.target.value)} />
      </label>
      <label style={{ display: 'block', marginBottom: '10px' }}>
        <span style={{ fontWeight: 'bold' }}>Workout Type:</span>
        <input type="text" value={workout_type} onChange={(e) => setWorkoutType(e.target.value)} />
        </label>
        <label style={{ display: 'block', marginBottom: '10px' }}>
        <span style={{ fontWeight: 'bold' }}>Target Audience:</span>
        <input type="text" value={target_audience} onChange={(e) => setTargetAudience(e.target.value)} />
        </label>
        <label style={{ display: 'block', marginBottom: '10px' }}>
        <span style={{ fontWeight: 'bold' }}>Estimated Time (minutes):</span>
        <input type="number" value={workout_estimated_time} onChange={(e) => setWorkoutEstimatedTime(e.target.value)} />
        </label>
        <label style={{ display: 'block', marginBottom: '10px' }}>
        <span style={{ fontWeight: 'bold' }}>Workout Description:</span>
        <textarea value={workout_description} onChange={(e) => setWorkoutDesription(e.target.value)} />
        </label>
        <label style={{ display: 'block', marginBottom: '10px' }}>
        <span style={{ fontWeight: 'bold' }}>Equipments:</span>
        <input type="text" value={equipments} onChange={(e) => setEquipments(e.target.value)} />
        </label>
        <label style={{ display: 'block', marginBottom: '10px' }}>
        <span style={{ fontWeight: 'bold' }}>Calories Per Unit Time:</span>
        <input type="number" value={calories_burn_per_unit_time} onChange={(e) => setCaloorieBurnPerUnitTime(e.target.value)} />
        </label>
        <label style={{ display: 'block', marginBottom: '10px' }}>
        <span style={{ fontWeight: 'bold' }}>Intensity Level:</span>
        <input type="number" value={intensity_level} onChange={(e) => setIntensityLevel(e.target.value)} />
        </label>
        <button type="submit" style={{ backgroundColor: '#007bff', color: '#fff', padding: '8px 16px', borderRadius: '5px', border: 'none', cursor: 'pointer', marginTop: '10px' }}>Create Workout</button>
      </form>
    </div>
  );
};

export default WorkoutCreationPage;
